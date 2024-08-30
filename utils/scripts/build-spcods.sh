# Script bash contendo os comandos com podman para o build de cada projeto e,
# em seguida, criar as imagens dos contêineres considerando o diretório atual scpods-backend

cd ..
cd scpods-backend
./mvnw clean package -DskipTests
podman build --platform linux/amd64 -t willcq97/scpods-api:1.0.0-amd64 .
podman build --platform linux/arm64 -t willcq97/scpods-api:1.0.0-arm64 .

cd ..
cd scpods-frontend
yarn build
podman build --platform linux/amd64 -t willcq97/scpods-site:1.0.0-amd64 .
podman build --platform linux/arm64 -t willcq97/scpods-site:1.0.0-arm64 .
