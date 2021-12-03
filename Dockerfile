FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD target/file-service.jar file-service.jar
ENTRYPOINT ["java", "-jar", "file-service.jar"]