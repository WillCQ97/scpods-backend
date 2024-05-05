FROM docker.io/openjdk:21-jdk-bullseye
MAINTAINER WillCQ97

#ARG DATABASE_SERVER=localhost
#ARG DATABASE_PORT=5432
#ARG DATABASE_NAME=acoes_db
#ARG DATABASE_USER=postgres
#ARG DATABASE_PASSWORD=admin.123

EXPOSE 8080
WORKDIR /app
COPY target/scpods-backend-1.0.0-SNAPSHOT.jar scpods-backend-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/scpods-backend-1.0.0-SNAPSHOT.jar"]