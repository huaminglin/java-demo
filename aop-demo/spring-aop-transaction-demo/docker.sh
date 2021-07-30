#!/usr/bin/env bash

cd $(dirname $0)

sudo docker stop mysql-demo

sudo docker rm mysql-demo

sudo docker run -p 3306:3306 --name mysql-demo --rm -e MYSQL_DATABASE=demo -e MYSQL_USER=demo -e MYSQL_PASSWORD=demo -e MYSQL_ROOT_HOST=% mysql/mysql-server:8.0
