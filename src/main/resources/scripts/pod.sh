# Exemplo de uso de pod para criar um ambiente em que os contêineres 
# possam se comunicar.

# Exemplo com docker-compose em src/main/resource/docker-compose.yml

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
        willcq97/scpods-api:1.0.0

podman run -d \
    --pod scpods-pod \
    --name scpods-fronted \
    willcq97/scpods-frontend:2.0.0

