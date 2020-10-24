#!/usr/bin/env bash

cd $(dirname $0)

echo '' > /tmp/gc.log
sudo docker-compose down
sudo docker-compose up
