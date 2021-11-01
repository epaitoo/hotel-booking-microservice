FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8761
ARG JAR_FILE=target/hotel-booking-service-registry-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]