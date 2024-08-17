#
# Este dockerfile apenas copia o target para dentro do container.
# Logo o projeto deve ser buildado antes da geração da imagem.
#

FROM docker.io/openjdk:21
MAINTAINER WillCQ97

EXPOSE 8080
WORKDIR /app-scpods-backend/

COPY target/scpods-api-1.0.0-SNAPSHOT.jar scpods-api-1.0.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/app-scpods-backend/scpods-api-1.0.0-SNAPSHOT.jar"]
