ARG IMAGE_REPO=eu.gcr.io/eevee-bank
FROM ${IMAGE_REPO}/base-java:11
COPY build/libs/kafka-producer-*-all.jar /mettle/app.jar
COPY build/javaAgents/*-1.6.2-all.jar otel-agent.jar
ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://otel-collector.observability:55680/v1/traces
ENV OTEL_SERVICE_NAME=test-producer
ENV OTEL_PROPAGATORS=tracecontext,baggage,jaeger,ottrace
CMD java $JAVA_OPTS -javaagent:otel-agent.jar -jar /mettle/app.jar