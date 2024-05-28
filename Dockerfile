 Etapa de build 
FROM amazoncorretto:17 AS build 
WORKDIR /app 
COPY pom.xml . 
COPY src ./src 
RUN yum install -y maven && mvn clean package 
# Etapa de produção 
FROM openjdk:17-jdk-slim AS production-stage 
WORKDIR /app 
COPY --from=build /app/target/woof-joy-back-end-0.0.1-SNAPSHOT.jar . 
EXPOSE 8080 
CMD ["java", "-jar", "woof-joy-back-end-0.0.1-SNAPSHOT.jar"] 