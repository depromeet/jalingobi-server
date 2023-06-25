FROM openjdk:11-jre-slim

ARG JAR_FILE=./build/libs/*.jar
ARG PROFILE
ENV PROFILE=${PROFILE}
ENV TZ=Asia/Seoul

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "/app.jar"]