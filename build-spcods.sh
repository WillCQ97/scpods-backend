# Script bash contendo os comandos com podman para o build de cada projeto e,
# em seguida, criar as imagens dos contêineres considerando o diretório atual scpods-backend
# Exemplo com docker-compose em src/main/resource/docker-compose.yml

cd ..
cd scpods-frontend
yarn build
podman build -t willcq97/scpods-frontend:2.0.0 .

cd ..
cd scpods-backend
./mvnw clean package -DskipTests
podman build -t willcq97/scpods-api:1.0.0 .

cd ..

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

