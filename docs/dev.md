# Notas de desenvolvimento

## Banco de dados

- Está sendo utilizado um container do `postgres` com `postgis` com a configuração abaixo:
  - OS debian 11 bullseye
  - Postgres 16
  - PostGIS 3.4.2

## Imagem docker com banco de dados inicializado

- É possível criar um dockerfile que já inicialize o banco de dados. Como no exemplo abaixo:

```dockerfile
# Cria um container com postGIS pré populado com dados de teste
# https://dev.to/andre347/how-to-easily-create-a-postgres-database-in-docker-4moj

FROM docker.io/postgis/postgis:16-3.4

ENV POSTGRES_DB acoes_db
ENV POSTGRES_PASSWORD admin.123

COPY acoes_db-dump.sql /docker-entrypoint-initdb.d/
```

- Então, para criar a imagem do banco de dados, basta executar o comando abaixo no diretório onde está o `Dockerfile` acima:

```bash
docker build -t willcq97/postgis-ods-db:latest .
```

## Execução do container de banco de dados

- Com `podman` no Linux:

```bash
podman run -d \
    --name postgis_ods \
    -e POSTGRES_DB=acoes_db \
    -e POSTGRES_PASSWORD=admin.123 \
    -p 5432:5432 \
    --restart always \
    -v postgis_data:/var/lib/postgresql/data \
    docker.io/postgis/postgis:16-3.4
```

- Com `docker` no Windows:

```powershell
docker run -d `
    --name postgis_ods `
    -e POSTGRES_DB=acoes_db `
    -e POSTGRES_PASSWORD=admin.123 `
    -p 5432:5432 `
    --restart always `
    -v postgis_data:/var/lib/postgresql/data `
    docker.io/postgis/postgis:16-3.4
```

## Dump do banco diretamente pelo container

```bash
# Dump em SQL do banco de dados
podman exec postgis_ods pg_dump -U postgres -h localhost acoes_db > acoes_db-dump.sql

# Passos intermediarios (cópia do arquivo para dentro do container e execução do shell no container)
podman cp ./acoes_db-dump.sql postgis_ods:/
podman exec -it postgis_ods bash

# Restore a partir do arquivo sql
# Segundo a documentação o arquivo de texto gerado pelo pg_dump deve ser restaurado usando psql
# https://www.postgresql.org/docs/8.1/backup.html
psql -Upostgres acoes_db < acoes_db-dump.sql
```

## Build do backend

Na raiz do projeto:

```bash
docker build -t willcq97/scpods-backend:latest -f ./Dockerfile .
```

## Build do backend e frontend

- **Atenção quanto a atualização das versões conforme desejado**.

### Backend

```bash
cd scpods-backend
podman build -t willcq97/scpods-api:latest .
```

### Frontend

```bash
cd scpods-frontend
yarn build
podman build -t willcq97/scpods-site:latest .
```

## Comunicação entre contêineres

### Pod

- Exemplo de uso do `podman` para criar um ambiente (pod) em que os contêineres do banco de dados, backend e frontend possam se comunicar.

```bash
podman pod create --name scpods-pod -p 8080:8080 -p 3000:3000 -p 5432:5432

podman run -d \
    --pod scpods-pod \
    --name postgis-acoes-db \
    -e POSTGRES_DB=acoes_db \
    -e POSTGRES_PASSWORD=admin.123 \
    --restart always \
    -v acoes-db-data:/var/lib/postgresql/data \
    docker.io/postgis/postgis:16-3.4

podman run -d \
    --pod scpods-pod \
    --name scpods-api \
    willcq97/scpods-api:latest

podman run -d \
    --pod scpods-pod \
    --name scpods-site \
    willcq97/scpods-site:latest
```

### Docker Compose

- Exemplo de um `docker compose` para a aplicação que realiza o build dos projetos e os configura de acordo, considerando que o diretório atual contém os projetos `scpods-backend` e `scpods-frontend`.

```yaml
services:
  scpods-frontend:
    container_name: scpods-site
    build:
      context: scpods-fronted
      dockerfile: Dockerfile
    image: willcq97/scpods-site:latest
    ports:
      - 3000:3000
    networks:
      - scpods-network

  scpods-api:
    container_name: scpods-api
    build:
      context: scpods-backend
      dockerfile: Dockerfile
    image: willcq97/scpods-backend:latest
    ports:
      - 8080:8080
    environment:
      POSTGRES_SERVER_NAME: postgis-acoes-db
      POSTGRES_SERVER_PORT: 5432
      POSTGRES_DATABASE: acoes_db
    networks:
      - scpods-network

  postgis-acoes-db:
    container_name: postgis-acoes-db
    image: docker.io/postgis/postgis:16-3.4
    ports:
      - 5432:5432
    environment:
      POSTGRES_DB: acoes_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: admin.123
    volumes:
      - postgis_data:/var/lib/postgresql/data
    networks:
      - scpods-network

networks:
  scpods-network:
    driver: bridge

volumes:
  postgis_data:

```
