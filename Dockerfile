FROM docker.io/openjdk:21-jdk-bullseye
MAINTAINER WillCQ97

EXPOSE 8080
WORKDIR /api-scpods-backend/

COPY target/scpods-api-1.0.0-SNAPSHOT.jar scpods-api-1.0.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/api-scpods-backend/scpods-api-1.0.0-SNAPSHOT.jar"]
