## install growroot

```shell
curl -fL -o glowroot.zip "https://github.com/glowroot/glowroot/releases/download/v0.14.4/glowroot-0.14.4-dist.zip" && unzip -o glowroot.zip && rm glowroot.zip
```

## growroot

http://localhost:4000

## swagger

http://localhost:8080/swagger-ui.html

## install otel agent

### vm options

```
-javaagent:/path/to/opentelemetry-javaagent.jar
-Dotel.instrumentation.micrometer.enabled=true
-Dotel.metric.export.interval=500
-Dotel.bsp.schedule.delay=500
```

## grafana spring boot board

https://grafana.com/grafana/dashboards/19004-spring-boot-statistics/