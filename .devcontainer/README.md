# Devcontainer - SCPODS Backend

Este devcontainer fornece um ambiente de desenvolvimento completo para o projeto SCPODS Backend com todas as dependências pré-configuradas.

## O que está incluído

- **Java 21** - Runtime e JDK
- **Maven 3.9** - Gerenciador de dependências
- **Docker CLI** - Para interagir com containers
- **GitHub CLI** - Utilitários Git
- **Extensões VS Code**:
  - Extension Pack for Java (Red Hat Java, Debugger, Test Runner)
  - Maven for Java
  - Spring Boot Extension Pack
  - Lombok
  - GitLens
  - GitHub Copilot (opcional, requer configuração)

## Como usar

### 1. Abrir o projeto no devcontainer

No VS Code:

1. Pressione `F1` e procure por "Dev Containers: Reopen in Container"
2. Aguarde a construção da imagem e inicialização do container

### 2. Serviços PostgreSQL/PostGIS

O container automaticamente inicia o serviço PostgreSQL com PostGIS através do `docker-compose.yml`:

```bash
# Verificar status dos serviços (dentro do container)
docker ps

# Conectar ao banco de dados
psql -h postgis-db -U postgres -d acoes_db
# Senha: admin.123
```

### 3. Compilar e executar a aplicação

```bash
# Compilar o projeto
mvn clean install

# Executar os testes
mvn test

# Rodar a aplicação
mvn spring-boot:run

# Build da imagem Docker
mvn clean package && docker build -t willcq97/scpods-backend:latest .
```

## Portas forwarded

- **8080**: Spring Boot API
- **5432**: PostgreSQL/PostGIS

## Variáveis de ambiente

Os seguintes valores estão pré-configurados:

```
POSTGRES_SERVER_NAME=postgis-db
POSTGRES_SERVER_PORT=5432
POSTGRES_DATABASE=acoes_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=admin.123
```

## Cache Maven

O diretório `~/.m2` no seu host está sincronizado com o container para melhor performance. As dependências Maven são reutilizadas entre sessões.

## Customizações

Para alterar a configuração do devcontainer, edite:

- `devcontainer.json` - Configurações principais (extensões, portas, variáveis de ambiente)
- `Dockerfile` - Dependências do sistema operacional

Após qualquer alteração, use `F1` > "Dev Containers: Rebuild Container" para aplicar as mudanças.

## Solução de problemas

### Container não inicia

- Verifique se o Docker Desktop está rodando
- Limpe as imagens antigas: `docker system prune -a`

### Banco de dados não conecta

- Verifique se o serviço postgis-db está rodando: `docker ps`
- Confirme a senha em `docker-compose.yml`

### Maven não encontra dependências

- Limpe o cache: `mvn clean`
- Reconstrua: `mvn install -DskipTests`

## Referências

- [Dev Containers Documentation](https://containers.dev/)
- [Spring Boot Development](https://spring.io/projects/spring-boot)
- [Maven Documentation](https://maven.apache.org/guides/)
