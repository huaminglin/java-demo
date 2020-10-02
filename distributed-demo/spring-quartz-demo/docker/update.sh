#!/usr/bin/env bash

cd $(dirname $0)

touch /tmp/spring-quartz-demo-mysql.general.log

sudo docker-compose down
sudo docker-compose up --no-start


sudo docker-compose start

sudo docker exec docker_server_1 /etc/my.cnf | grep general_log_file || {
  sudo docker cp my-log.cnf docker_server_1:/my-log.cnf;
  sudo docker exec docker_server_1  bash -c "cat /my-log.cnf >> /etc/my.cnf; cat /etc/my.cnf";
}

sudo docker exec -it docker_server_1 bash -c "chown mysql:mysql /var/log/mysql.general.log"

sudo docker-compose restart

sudo docker exec -it docker_server_1 bash -c "tail -f /var/log/mysql.general.log /var/log/mysqld.log"
