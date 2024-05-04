# Obtendo a versão atual da imagem do postgres com postgis
# ---
# OS debian:bullseye
# Postgres 16
# PostGIS 3.4.2
# ---
podman pull docker.io/postgis/postgis:16-3.4

# Execução do contêiner com as configurações padrão
podman run -d \
    --name postgis_ods \
    -e POSTGRES_PASSWORD=admin.123 \
    -p 5432:5432 \
    --restart always \
    -v postgis_data:/var/lib/postgresql/data \
    docker.io/postgis/postgis:16-3.4
