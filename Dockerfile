FROM openjdk:8
EXPOSE 1470
ADD target/spring-jwt.jar spring-jwt.jar
ENTRYPOINT ["java", "-jar", "spring-jwt.jar"]