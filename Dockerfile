FROM openjdk:20
EXPOSE 8080
ADD target/spring-click.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]