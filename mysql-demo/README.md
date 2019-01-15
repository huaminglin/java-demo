mvn clean package exec:java

## Capture the network packages
docker run -it --rm --net=container:mysql-demo_server_1 -v /var/tmp:/capture itsthenetwork/alpine-tcpdump:latest -v -i eth0 -w /capture/jdbc.pcap

## Check the default settings returned from MySQL server

## Check the sql received on the server side
docker-compose exec server bash -c "tail -f /var/log/mysql.general.log /var/log/mysqld.log"
Please check mysql.log.

## SET character_set_results = NULL
To tell the server to perform no conversion of result sets or error messages, set character_set_results to NULL or binary.
