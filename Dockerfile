FROM maven:3.9.6-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTest

# produção
FROM openjdk:17-jdk-slim AS production-stage
WORKDIR /app
COPY --from=build /app/target/woof-app-back-end.jar .
EXPOSE 8080
CMD ["java", "-jar", "woof-app-back-end.jar"]
