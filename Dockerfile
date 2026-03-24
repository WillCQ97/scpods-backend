# Etapa 1: build
FROM docker.io/maven:3.9.14-eclipse-temurin-25 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM docker.io/eclipse-temurin:25
EXPOSE 8080
WORKDIR /app
COPY --from=builder /app/target/scpods-api.jar scpods-api.jar
ENTRYPOINT ["java","-jar","scpods-api.jar"]
