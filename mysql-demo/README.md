mvn clean package exec:java

## Capture the network packages
docker run -it --rm --net=container:mysql-demo_server_1 -v /var/tmp:/capture itsthenetwork/alpine-tcpdump:latest -v -i eth0 -w /capture/jdbc.pcap

## Check the sql received on the server side
docker-compose exec server bash -c "tail -f /var/log/mysql.general.log /var/log/mysqld.log"

2019-01-11T13:16:18.970599Z	  150 Connect	root@172.30.0.1 on mysql using TCP/IP
2019-01-11T13:16:18.974524Z	  150 Query	/* mysql-connector-java-8.0.13 (Revision: 66459e9d39c8fd09767992bc592acd2053279be6) */SELECT  @@session.auto_increment_increment AS auto_increment_increment, @@character_set_client AS character_set_client, @@character_set_connection AS character_set_connection, @@character_set_results AS character_set_results, @@character_set_server AS character_set_server, @@collation_server AS collation_server, @@collation_connection AS collation_connection, @@init_connect AS init_connect, @@interactive_timeout AS interactive_timeout, @@license AS license, @@lower_case_table_names AS lower_case_table_names, @@max_allowed_packet AS max_allowed_packet, @@net_write_timeout AS net_write_timeout, @@sql_mode AS sql_mode, @@system_time_zone AS system_time_zone, @@time_zone AS time_zone, @@transaction_isolation AS transaction_isolation, @@wait_timeout AS wait_timeout
2019-01-11T13:16:18.995876Z	  150 Query	SET character_set_results = NULL
2019-01-11T13:16:18.998966Z	  150 Query	SET autocommit=1
2019-01-11T13:16:19.025104Z	  150 Query	SELECT '是'
2019-01-11T13:16:19.041780Z	  150 Quit	
2019-01-11T13:16:19.051488Z	  151 Connect	root@172.30.0.1 on mysql using TCP/IP
2019-01-11T13:16:19.052985Z	  151 Query	/* mysql-connector-java-8.0.13 (Revision: 66459e9d39c8fd09767992bc592acd2053279be6) */SELECT  @@session.auto_increment_increment AS auto_increment_increment, @@character_set_client AS character_set_client, @@character_set_connection AS character_set_connection, @@character_set_results AS character_set_results, @@character_set_server AS character_set_server, @@collation_server AS collation_server, @@collation_connection AS collation_connection, @@init_connect AS init_connect, @@interactive_timeout AS interactive_timeout, @@license AS license, @@lower_case_table_names AS lower_case_table_names, @@max_allowed_packet AS max_allowed_packet, @@net_write_timeout AS net_write_timeout, @@sql_mode AS sql_mode, @@system_time_zone AS system_time_zone, @@time_zone AS time_zone, @@transaction_isolation AS transaction_isolation, @@wait_timeout AS wait_timeout
2019-01-11T13:16:19.056618Z	  151 Query	SET character_set_results = NULL
2019-01-11T13:16:19.057853Z	  151 Query	SET autocommit=1
2019-01-11T13:16:19.059491Z	  151 Query	SELECT '?'
2019-01-11T13:16:19.061153Z	  151 Quit	
2019-01-11T13:16:19.065832Z	  152 Connect	root@172.30.0.1 on mysql using TCP/IP
2019-01-11T13:16:19.066896Z	  152 Query	/* mysql-connector-java-8.0.13 (Revision: 66459e9d39c8fd09767992bc592acd2053279be6) */SELECT  @@session.auto_increment_increment AS auto_increment_increment, @@character_set_client AS character_set_client, @@character_set_connection AS character_set_connection, @@character_set_results AS character_set_results, @@character_set_server AS character_set_server, @@collation_server AS collation_server, @@collation_connection AS collation_connection, @@init_connect AS init_connect, @@interactive_timeout AS interactive_timeout, @@license AS license, @@lower_case_table_names AS lower_case_table_names, @@max_allowed_packet AS max_allowed_packet, @@net_write_timeout AS net_write_timeout, @@sql_mode AS sql_mode, @@system_time_zone AS system_time_zone, @@time_zone AS time_zone, @@transaction_isolation AS transaction_isolation, @@wait_timeout AS wait_timeout
2019-01-11T13:16:19.068859Z	  152 Query	SET character_set_results = NULL
2019-01-11T13:16:19.069458Z	  152 Query	SET autocommit=1
2019-01-11T13:16:19.070377Z	  152 Query	SELECT '?'
2019-01-11T13:16:19.071534Z	  152 Quit	
2019-01-11T13:16:19.074709Z	  153 Connect	root@172.30.0.1 on mysql using TCP/IP
2019-01-11T13:16:19.075886Z	  153 Query	/* mysql-connector-java-8.0.13 (Revision: 66459e9d39c8fd09767992bc592acd2053279be6) */SELECT  @@session.auto_increment_increment AS auto_increment_increment, @@character_set_client AS character_set_client, @@character_set_connection AS character_set_connection, @@character_set_results AS character_set_results, @@character_set_server AS character_set_server, @@collation_server AS collation_server, @@collation_connection AS collation_connection, @@init_connect AS init_connect, @@interactive_timeout AS interactive_timeout, @@license AS license, @@lower_case_table_names AS lower_case_table_names, @@max_allowed_packet AS max_allowed_packet, @@net_write_timeout AS net_write_timeout, @@sql_mode AS sql_mode, @@system_time_zone AS system_time_zone, @@time_zone AS time_zone, @@transaction_isolation AS transaction_isolation, @@wait_timeout AS wait_timeout
2019-01-11T13:16:19.077850Z	  153 Query	SET NAMES utf8mb4
2019-01-11T13:16:19.078428Z	  153 Query	SET character_set_results = NULL
2019-01-11T13:16:19.079095Z	  153 Query	SET autocommit=1
2019-01-11T13:16:19.079908Z	  153 Query	SELECT '是'
2019-01-11T13:16:19.081009Z	  153 Quit	
2019-01-11T13:16:19.085033Z	  154 Connect	root@172.30.0.1 on mysql using TCP/IP
2019-01-11T13:16:19.086158Z	  154 Query	/* mysql-connector-java-8.0.13 (Revision: 66459e9d39c8fd09767992bc592acd2053279be6) */SELECT  @@session.auto_increment_increment AS auto_increment_increment, @@character_set_client AS character_set_client, @@character_set_connection AS character_set_connection, @@character_set_results AS character_set_results, @@character_set_server AS character_set_server, @@collation_server AS collation_server, @@collation_connection AS collation_connection, @@init_connect AS init_connect, @@interactive_timeout AS interactive_timeout, @@license AS license, @@lower_case_table_names AS lower_case_table_names, @@max_allowed_packet AS max_allowed_packet, @@net_write_timeout AS net_write_timeout, @@sql_mode AS sql_mode, @@system_time_zone AS system_time_zone, @@time_zone AS time_zone, @@transaction_isolation AS transaction_isolation, @@wait_timeout AS wait_timeout
2019-01-11T13:16:19.088081Z	  154 Query	SET NAMES utf8mb4
2019-01-11T13:16:19.088672Z	  154 Query	SET character_set_results = latin1
2019-01-11T13:16:19.089252Z	  154 Query	SET autocommit=1
2019-01-11T13:16:19.090258Z	  154 Query	SELECT '是'
2019-01-11T13:16:19.091676Z	  154 Quit	


## Check the default settings returned from MySQL server
docker-compose exec server bash -c 'mysql -pdemo -e "/* mysql-connector-java-8.0.13 (Revision: 66459e9d39c8fd09767992bc592acd2053279be6) */SELECT  @@session.auto_increment_increment AS auto_increment_increment, @@character_set_client AS character_set_client, @@character_set_connection AS character_set_connection, @@character_set_results AS character_set_results, @@character_set_server AS character_set_server, @@collation_server AS collation_server, @@collation_connection AS collation_connection, @@init_connect AS init_connect, @@interactive_timeout AS interactive_timeout, @@license AS license, @@lower_case_table_names AS lower_case_table_names, @@max_allowed_packet AS max_allowed_packet, @@net_write_timeout AS net_write_timeout, @@sql_mode AS sql_mode, @@system_time_zone AS system_time_zone, @@time_zone AS time_zone, @@transaction_isolation AS transaction_isolation, @@wait_timeout AS wait_timeout"'

+--------------------------+----------------------+--------------------------+-----------------------+----------------------+------------------+----------------------+--------------+---------------------+---------+------------------------+--------------------+-------------------+-----------------------------------------------------------------------------------------------------------------------+------------------+-----------+-----------------------+--------------+
| auto_increment_increment | character_set_client | character_set_connection | character_set_results | character_set_server | collation_server | collation_connection | init_connect | interactive_timeout | license | lower_case_table_names | max_allowed_packet | net_write_timeout | sql_mode                                                                                                              | system_time_zone | time_zone | transaction_isolation | wait_timeout |
+--------------------------+----------------------+--------------------------+-----------------------+----------------------+------------------+----------------------+--------------+---------------------+---------+------------------------+--------------------+-------------------+-----------------------------------------------------------------------------------------------------------------------+------------------+-----------+-----------------------+--------------+
|                        1 | latin1               | latin1                   | latin1                | utf8                 | utf8_general_ci  | latin1_swedish_ci    |              |               28800 | GPL     |                      0 |           67108864 |                60 | ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION | UTC              | SYSTEM    | REPEATABLE-READ       |        28800 |
+--------------------------+----------------------+--------------------------+-----------------------+----------------------+------------------+----------------------+--------------+---------------------+---------+------------------------+--------------------+-------------------+-----------------------------------------------------------------------------------------------------------------------+------------------+-----------+-----------------------+--------------+

## SET character_set_results = NULL
To tell the server to perform no conversion of result sets or error messages, set character_set_results to NULL or binary.
