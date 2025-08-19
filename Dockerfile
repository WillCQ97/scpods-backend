# Este dockerfile apenas copia o target para dentro do container.
# Logo o build do projeto deve ser executado antes da geração da imagem.

FROM docker.io/openjdk:21

EXPOSE 8080
WORKDIR /app-scpods-backend/

COPY target/scpods-api-1.1.0-SNAPSHOT.jar scpods-api-1.1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/app-scpods-backend/scpods-api-1.1.0-SNAPSHOT.jar"]
