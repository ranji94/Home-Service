FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 9010
ENTRYPOINT ["java","-jar","/application.jar"]