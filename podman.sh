podman run -d \
    --name postgis_ods \
    -e POSTGRES_PASSWORD=admin.123 \
    -p 5432:5432 \
    --restart always \
    -v postgis_data:/var/lib/postgresql/data\
    docker.io/postgis/postgis:latest
