FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
ADD ./target/microservice-security-0.0.1-SNAPSHOT.jar microservice-security.jar
ENTRYPOINT ["java", "-jar", "microservice-security.jar"]
