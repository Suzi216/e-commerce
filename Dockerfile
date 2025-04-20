FROM maven:3.9.4-amazoncorretto-17 AS build
COPY . .
RUN mvn  clean package

# Package stage
FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar e-commerce-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/e-commerce-service.jar"]


