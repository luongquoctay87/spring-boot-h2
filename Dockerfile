FROM openjdk

WORKDIR /app

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} api-service.jar

ENTRYPOINT ["java","-jar","api-service.jar"]

EXPOSE 8080
