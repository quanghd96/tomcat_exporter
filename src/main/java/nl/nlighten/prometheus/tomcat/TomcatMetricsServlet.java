package nl.nlighten.prometheus.tomcat;


import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.exporter.common.TextFormat;
import io.prometheus.client.hotspot.DefaultExports;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;


@WebServlet("/")
public class TomcatMetricsServlet extends MetricsServlet {

    @Override
    public void init(ServletConfig config) {
        if (!initialized()) {
            DefaultExports.initialize();
            new TomcatGenericExports(false).register();
            if (TomcatJdbcPoolExports.isTomcatJdbcUsed()) {
                new TomcatJdbcPoolExports().register();
            } else {
                new TomcatDbcp2PoolExports().register();
            }
        }
    }

    private boolean initialized() {
        Enumeration<Collector.MetricFamilySamples> samples = CollectorRegistry.defaultRegistry.filteredMetricFamilySamples(new HashSet<String>(Collections.singletonList("tomcat_info")));
        return samples.hasMoreElements();
    }
}


