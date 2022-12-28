# Setup Kafka

## Start a console to input messages

sudo docker exec -it docker_kafka_1 /bin/bash -c 'kafka-console-producer --topic=my-topic --broker-list docker_kafka_1:9092'

## Start a console to listen a topic

sudo docker exec -it docker_kafka_1 /bin/bash -c 'kafka-console-consumer --bootstrap-server docker_kafka_1:9092 --from-beginning --topic  my-topic'

## List topics

sudo docker exec -it docker_kafka_1 /bin/bash -c 'kafka-topics --zookeeper zookeeper:2181 --list'

## Create a topic with one partition

sudo docker exec -it docker_kafka_1 /bin/bash -c 'kafka-topics --create --zookeeper zookeeper:2181 --topic my-topic --replication-factor 1 --partitions 1'

## Delete a topic

sudo docker exec -it docker_kafka_1 /bin/bash -c 'kafka-topics --delete --zookeeper zookeeper:2181 --topic my-topic'

## Topic status

sudo docker exec -it docker_kafka_1 /bin/bash -c 'kafka-run-class kafka.tools.GetOffsetShell --broker-list docker_kafka_1:9092 --topic my-topic'

## kafka-consumer-groups

sudo docker exec -it docker_kafka_1 /bin/bash -c 'kafka-consumer-groups --bootstrap-server docker_kafka_1:9092 --describe --group kafka-intro'
