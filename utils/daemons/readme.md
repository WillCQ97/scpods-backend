# Arquivos .service

- Para fazer um deploy de testes, foram criados arquivos .service para o front e backend.
- São arquivos para execução de comandos manejados pelo systemd no linux.
- Para tal, tais arquivos devem estar presentes no diretório `/etc/systemd/system`, podem ser links simbólicos para outros arquivos.
- Nesse caso, foram instalados o javaJRE e o node em `~/Programs/`.
- O scpods-api e scp-site ficaram em `~/my-apps`:

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
