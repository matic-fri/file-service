FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD file-service-0.0.1-SNAPSHOT.jar file-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "file-service-0.0.1-SNAPSHOT.jar"]