#!/usr/bin/env bash

cd $(dirname $0)

rm /tmp/gc.log
touch /tmp/gc.log
sudo docker-compose down
sudo docker-compose up
