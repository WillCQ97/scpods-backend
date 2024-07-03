# Sistema para Cadastro de Projetos relacionados com os ODS

- ODS são os Objetivos de Desenvolvimento Sustentável definidos pela [Agenda 2030](http://www.agenda2030.com.br/).
- Este repositório contém a implementação do _backend_ em Java com Spring Boot de um sistema para a realização de cadastros de projetos relacionados com os ODS na UFES.
- Tudo isso faz parte do meu TCC para obtenção do título de Bacharel em Ciência da Computação, na UFES de Alegre.

## Banco de dados

- Está sendo utilizado um container do postgres com postgis com a configuração abaixo:
  - OS debian:bullseye
  - Postgres 16
  - PostGIS 3.4.2
- Abaixo estão definidos alguns comandos para referência.

### Execução do container com podman no linux

```bash
podman pull docker.io/postgis/postgis:16-3.4

# Execução do contêiner com as configurações padrão
podman run -d \
    --name postgis_ods \
    -e POSTGRES_PASSWORD=admin.123 \
    -p 5432:5432 \
    --restart always \
    -v postgis_data:/var/lib/postgresql/data \
    docker.io/postgis/postgis:16-3.4
```

### Execução do container com docker no windows

```powershell
docker run -d `
    --name postgis_ods `
    -e POSTGRES_PASSWORD=admin.123 `
    -p 5432:5432 `
    --restart always `
    -v postgis_data:/var/lib/postgresql/data `
    docker.io/postgis/postgis:16-3.4
```

### Dump do banco diretamente pelo container

```bash
# Dump em SQL do banco de dados
podman exec postgis_ods pg_dump -U postgres -h localhost acoes_db > src/main/resources/db/docker/acoes_db-dump.sql

# Passos intermediarios (cópia do arquivo para dentro do container e execução do shell no container)
podman cp ./src/main/resources/db/docker/acoes_db-dump.sql postgis_ods:/
podman exec -it postgis_ods bash

# Restore a partir do arquivo sql
# Segundo a documentação o arquivo de texto gerado pelo pg_dump deve ser restaurado usando psql
# https://www.postgresql.org/docs/8.1/backup.html
psql -Upostgres acoes_db < acoes_db-dump.sql
```

### Criação de um container com banco pré-populado

```bash
cd ./src/main/resources/db/docker
docker build -t willcq97/postgis-ods-db:latest .
```

## Criação de um container com a aplicação

```bash
docker build -t willcq97/scpods-backend:latest -f ./Dockerfile .
```

## Alguns materiais de base

- [InMemoryAuthWebSecurityConfigurer.java Example](https://github.com/eugenp/tutorials/blob/master/spring-security-modules/spring-security-web-rest-basic-auth/src/main/java/com/baeldung/inmemory/InMemoryAuthWebSecurityConfigurer.java)
- [In-Memory Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html)
- [Spring Security Basic Authentication](https://www.baeldung.com/spring-security-basic-authentication)
- [Usage of PreAuthorize](https://www.baeldung.com/spring-security-method-security)
- [Documenting a Spring REST API Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation)
