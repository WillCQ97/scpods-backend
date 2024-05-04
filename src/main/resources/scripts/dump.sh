#!/bin/bash

# Dump em SQL do banco de dados
podman exec postgis_ods pg_dump -U postgres -h localhost acoes_db > src/main/resources/db/dumps/acoes_db$(date +%Y-%m-%d).sql

# Passos intermediarios
podman cp src/main/resources/db/dumps/acoes_db-dump_atual.sql postgis_ods:/
podman exec -it postgis_ods bash

# Restore a partir do arquivo sql
# Segundo a documentação o arquivo de texto gerado pelo pg_dump deve ser restaurado usando psql
# https://www.postgresql.org/docs/8.1/backup.html
psql -Upostgres acoes_db < acoes_db-dump_atual.sql
