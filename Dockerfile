FROM openjdk:8-jdk-alpine
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime && echo Asia/Seoul > /etc/timezone
RUN mkdir -p /logs
ARG JAR_FILE
COPY ${JAR_FILE:-build/libs/attendance.jar} app.jar
ENTRYPOINT exec java ${JAVA_OPTS} -jar /app.jar
EXPOSE 8080