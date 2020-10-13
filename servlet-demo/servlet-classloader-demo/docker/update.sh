#!/usr/bin/env bash

cd $(dirname $0)

touch /tmp/spring-quartz-demo-mysql.general.log

sudo docker-compose down
sudo docker-compose up --no-start
sudo docker-compose start
