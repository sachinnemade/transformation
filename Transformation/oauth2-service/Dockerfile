FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY "target/oauth2-service-0.0.1-SNAPSHOT.jar" ./
ENTRYPOINT ["java","-jar","oauth2-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080