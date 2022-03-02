FROM openjdk:11.0.14-slim

VOLUME /vol

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", \
    "-Ddiscord.oAuth2.client_id=${CLIENT_ID}", \
    "-Ddiscord.oAuth2.client_secret=${CLIENT_SECRET}", \
    "-jar", \
    "/app.jar"]