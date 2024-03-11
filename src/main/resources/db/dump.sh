#!/bin/bash
podman exec postgis_ods pg_dump -U postgres -h localhost db_acoes > dumps/db_acoes$(date +%Y-%m-%d).sql
