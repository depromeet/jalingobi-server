FROM openjdk:11-jre-slim

ARG JAR_FILE=./build/libs/*.jar
ARG PROFILE
ENV PROFILE=${PROFILE}

COPY ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "/app.jar"]