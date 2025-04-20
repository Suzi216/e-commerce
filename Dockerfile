FROM maven:3.9.4-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM amazoncorretto:17
COPY --from=build /home/app/target/*.jar e-commerce-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/e-commerce-service.jar"]


