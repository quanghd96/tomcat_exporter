# Tomcat exporter
Tomcat exporter with some system info: MinHeapFreeRatio, MaxHeapFreeRatio, NewSize, MaxNewSize, NewRatio
## Install
1. Create get tomcat id sh
```
echo "ps -ef | grep tomcat | grep -v grep | awk '{ print \$2; print \$8 }'" > /tmp/getpid.sh && chmod +x /tmp/getpid.sh
```
2. Download metrics.war in target/metrics.war
3. Go to tomcat manager page, deploy metrics.war
4. Test by go to http://TOMCAT/metrics
