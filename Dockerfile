FROM openjdk:17-jdk-slim

COPY build/libs/*.jar phService.jar


EXPOSE 8089

CMD $JAVA_HOME/bin/java \
-Xms1024m -Xmx1024m \
-Dspring.profiles.active="${PROFILE}" \
-jar phService.jar
