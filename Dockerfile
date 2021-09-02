# Extract the layered JAR
FROM adoptopenjdk/openjdk11:latest as appbuilder_stage
WORKDIR application
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk/openjdk11:latest as new_relic_stage
RUN mkdir /newrelic && cd /newrelic \
  && curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/6.5.0/newrelic-agent-6.5.0.jar

FROM adoptopenjdk/openjdk11:latest
LABEL ostk.app.name=GlobalZonosShipConfirmer
LABEL ostk.app.type=webservice

ENV TZ=America/Denver
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Create appuser to run as non-root for security purposes and /app for app-specific files
RUN groupadd -g 999 appuser \
    && useradd -r -u 999 -g appuser appuser \
    && mkdir /app \
    && chown appuser:appuser /app
USER appuser

COPY --from=appbuilder_stage --chown=appuser:appuser application/dependencies/ /app/
COPY --from=appbuilder_stage --chown=appuser:appuser application/snapshot-dependencies/ /app/
COPY --from=appbuilder_stage --chown=appuser:appuser application/spring-boot-loader/ /app/
COPY --from=appbuilder_stage --chown=appuser:appuser application/application/ /app/

WORKDIR /app
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
EXPOSE 8080