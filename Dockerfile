FROM adoptopenjdk/openjdk11

RUN mkdir -p /software

ADD target/fileService.jar /software/fileService.jar

CMD java -Dserver.port=$PORT $JAVA_OPTS -jar /software/fileService.jar