#!/bin/bash

# Dump em SQL do banco de dados
podman exec postgis_ods pg_dump -U postgres -h localhost db_acoes > src/main/resources/db/dumps/db_acoes$(date +%Y-%m-%d).sql

# Passos intermediarios
podman cp src/main/resources/db/dumps/db_acoes_dump_atual.sql postgis_ods:/
podman exec -it postgis_ods bash

# Restore a partir do arquivo sql
# Segundo a documentação o arquivo de texto gerado pelo pg_dump deve ser restaurado usando psql
# https://www.postgresql.org/docs/8.1/backup.html
psql -Upostgres db_acoes < db_acoes_dump_atual.sql
