#!/usr/bin/env bash

cd $(dirname $0)

sudo docker exec -it docker_mysql_1 bash -c "mysqldump -pdemo quartz_demo" > /tmp/spring-quartz-demo-mysql-dump.sql
