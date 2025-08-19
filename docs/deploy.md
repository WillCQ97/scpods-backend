# Deploy no Linux com Systemd

- Para fazer testes de deploy foram criados arquivos .service para o front-end e back-end.
- São arquivos para configurar processos gerenciados pelo `systemd` em sistemas linux.
- Para isso, os arquivos .service devem estar presentes no diretório `/etc/systemd/system` e podem ser links simbólicos.
- O deploy de teste foi realizado em um Raspberry Pi:
  - Foram instalados o Java JRE (OpenJDK21U-jre_aarch64_linux_hotspot_21.0.4_7.tar.gz) e o NodeJS (node-v20.17.0-linux-arm64.tar.xz) em `~/Programs/`.
  - O scpods-api e scp-site ficaram em `~/my-apps`:

```bash
sha256sum *.tar.*
7ebbb636c23f89dbf66648e227fbd0998ce33db4c588520256425102d97dd1a4  node-v20.17.0-linux-arm64.tar.xz
58845ce4275f3ec74fba075597c8216bb201773da036c4703be8b7b7b457355d  OpenJDK21U-jre_aarch64_linux_hotspot_21.0.4_7.tar.gz
```

```bash
my-apps
├── scpods-api
│  ├── env
│  └── scpods-api-1.0.0-RC.jar
├── scpods-api.service
├── scpods-site
│  ├── env
│  ├── nitro.json
│  ├── public
│  └── server
└── scpods-site.service
```

```bash
lrwxrwxrwx 39 root 22 ago 22:37 /etc/systemd/system/scpods-api.service -> /home/willcq/my-apps/scpods-api.service
lrwxrwxrwx 40 root 22 ago 22:37 /etc/systemd/system/scpods-site.service -> /home/willcq/my-apps/scpods-site.service
```

- Comandos úteis

```bash
sudo systemctl daemon-reload
systemctl status scpods-api.service
systemctl status scpods-site.service
journalctl -f -u scpods-api.service
journalctl -f -u scpods-site.service
```

## Arquivos de serviço

### scpods-api.service

```ini
[Unit]
Description=SCPODS API - Backend do sistema para cadastro de projetos relacionados aos ODS na UFES.
After=network.target postgresql@16-main.service
Wants=postgresql@16-main.service

[Service]
Type=simple
User=willcq
EnvironmentFile=/home/willcq/my-apps/scpods-api/env
ExecStart=/home/willcq/Programs/jdk-21.0.4+7-jre/bin/java -jar /home/willcq/my-apps/scpods-api/scpods-api-1.0.0-RC.jar
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```

### scpods-site.service

```ini
[Unit]
Description=SCPODS Site - Frontend do sistema para cadastro de projetos relacionados aos ODS na UFES.
After=network.target scpods-api.service
Wants=scpods-api.service

[Service]
Type=simple
User=willcq
EnvironmentFile=/home/willcq/my-apps/scpods-site/env
ExecStart=/home/willcq/Programs/node-v20.17.0-linux-arm64/bin/node /home/willcq/my-apps/scpods-site/server/index.mjs
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```

## Variáveis de ambiente

### scpods-api/env

```text
# Configurações de Conexão com o Banco de Dados
#POSTGRESQL_SERVER_NAME="servername"
POSTGRESQL_USERNAME="username"
POSTGRESQL_PASSWORD="password"
```

### scpods-site/env

```text
# Configuração de consumo da api
NUXT_PUBLIC_API_BASE_URL="http://apibaselink/"
```
