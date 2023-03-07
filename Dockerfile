FROM openjdk:17-jdk-alpine
MAINTAINER Bonuccelli Diego
ARG JAR_FILE=target/tenpo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} tenpo-1.0.jar
ENTRYPOINT ["java", "-jar", "/tenpo-1.0.jar"]