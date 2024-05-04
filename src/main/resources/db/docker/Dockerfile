# Cria um container com postGIS pré populado com dados de teste
# https://dev.to/andre347/how-to-easily-create-a-postgres-database-in-docker-4moj

FROM docker.io/postgis/postgis:16-3.4

ENV POSTGRES_PASSWORD admin.123
ENV POSTGRES_DB acoes_db

COPY ./data/dump-acoes_db.sql /docker-entrypoint-initdb.d/
