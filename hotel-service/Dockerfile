FROM openjdk:16-jdk-alpine
EXPOSE 9002
ARG JAR_FILE=target/hotel-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]