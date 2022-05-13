#!/usr/bin/env bash

cd $(dirname $0)

sudo docker stop mypostgres
sudo docker rm mypostgres
sudo docker create -e POSTGRES_DB=pgdb -e POSTGRES_USER=pgdemo -e POSTGRES_PASSWORD=123456 -p 5432:5432 --name mypostgres postgres:12.1
sudo docker start mypostgres

sleep 5

sudo docker exec -it mypostgres bash -c "echo 'create table tstz(ts timestamp, tz timestamptz)' | PGPASSWORD=123456 psql -U pgdemo pgdb";
