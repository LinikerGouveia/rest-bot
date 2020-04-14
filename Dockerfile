FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} rest_robot.jar
ENTRYPOINT ["java","-jar","/rest_robot.jar"]