FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/hospital-backend.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
