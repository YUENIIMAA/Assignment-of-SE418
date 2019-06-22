FROM openjdk:11
ADD target/service-server.jar service-server.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","service-server.jar"]