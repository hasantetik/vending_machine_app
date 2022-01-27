FROM openjdk:11
ARG JAR_FİLE=target/*.jar
COPY ${JAR_FİLE} user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]
EXPOSE 9002