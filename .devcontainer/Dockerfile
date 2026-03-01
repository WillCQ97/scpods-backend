FROM mcr.microsoft.com/vscode/devcontainers/java:21-eclipse-temurin-focal

# Instala Maven
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends maven \
    && apt-get clean -y && rm -rf /var/lib/apt/lists/*

# Define versão do Maven (já incluída no pacote)
ENV MAVEN_VERSION=3.9.0

# Define variáveis de ambiente para o PostgreSQL
ENV POSTGRES_SERVER_NAME=postgis-db \
    POSTGRES_SERVER_PORT=5432 \
    POSTGRES_DATABASE=acoes_db \
    POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=admin.123

# Cria o diretório da aplicação
WORKDIR /workspace

# Cópia do arquivo de configuração Maven
RUN mkdir -p /home/vscode/.m2 && \
    chown -R vscode:vscode /home/vscode/.m2

# Permite que o docker CLI funcione dentro do container
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends docker.io \
    && apt-get clean -y && rm -rf /var/lib/apt/lists/*

EXPOSE 8080 5432
