# Setup Kafka

## Start a console to input messages

sudo docker exec -it docker_kafka_1 /bin/bash -c 'kafka-console-producer --topic=test --broker-list docker_kafka_1:9092'

## Start a console to listen a topic

sudo docker exec -it docker_kafka_1 /bin/bash -c 'kafka-console-consumer --bootstrap-server docker_kafka_1:9092 --from-beginning --topic test'
