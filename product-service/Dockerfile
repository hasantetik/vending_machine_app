FROM openjdk:11
ARG JAR_FİLE=target/*.jar
COPY ${JAR_FİLE} product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]
EXPOSE 9001