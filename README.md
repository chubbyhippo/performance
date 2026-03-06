## swagger

http://localhost:8080/swagger-ui.html

## growroot

http://localhost:4000

## install growroot

```shell
curl -fL -o glowroot.zip "https://github.com/glowroot/glowroot/releases/download/v0.14.4/glowroot-0.14.4-dist.zip" &&
unzip -o glowroot.zip &&
rm glowroot.zip
```

### vm options

```
-javaagent:./glowroot/glowroot.jar
```

## install otel agent with zero-code instrumentation

```shell
mkdir opentelemetry-javaagent &&
curl -fL -o opentelemetry-javaagent/opentelemetry-javaagent.jar "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.25.0/opentelemetry-javaagent.jar"
```

### vm options

```
-javaagent:/path/to/opentelemetry-javaagent.jar
-Dotel.instrumentation.micrometer.enabled=trude
-Dotel.metric.export.interval=500
-Dotel.bsp.schedule.delay=500
```

http://localhost:3000

## grafana spring boot board

https://grafana.com/grafana/dashboards/19004-spring-boot-statistics/

## jfr

```shell
jfr summary app.jfr
```

```shell
jmc
```

https://visualvm.github.io/

## jmeter
