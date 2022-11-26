FROM mysql:8.0.31
COPY ./scripts/schema.sql /docker-entrypoint-initdb.d/1.sql
COPY ./scripts/data.sql /docker-entrypoint-initdb.d/2.sql