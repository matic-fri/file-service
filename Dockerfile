FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD target/rso-file-service.jar rso-file-service.jar
ENTRYPOINT ["java", "-jar", "rso-file-service.jar"]