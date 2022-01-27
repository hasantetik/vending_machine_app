FROM openjdk:11
ARG JAR_FİLE=target/*.jar
COPY ${JAR_FİLE} cloud-gateway.jar
ENTRYPOINT ["java","-jar","/cloud-gateway.jar"]
EXPOSE 9191