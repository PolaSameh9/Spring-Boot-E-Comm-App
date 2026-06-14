FROM eclipse-temurin
ADD target/spring-ecom.jar spring-ecom.jar
ENTRYPOINT [ "java", "-jar", "/spring-ecom.jar" ]