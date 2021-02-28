# SocketServerBindDemo

## somaxconn

sysctl -a | grep somaxconn

## serverSocket.supportedOptions()

```
class java.lang.Integer/TCP_KEEPIDLE
class java.lang.Boolean/SO_REUSEPORT
class java.lang.Integer/IP_TOS
class java.lang.Integer/SO_RCVBUF
class java.lang.Boolean/SO_REUSEADDR
class java.lang.Integer/TCP_KEEPCOUNT
class java.lang.Boolean/TCP_QUICKACK
class java.lang.Integer/TCP_KEEPINTERVAL
```

## mvn exec:java -Dexec.args="1000"

## Monitor Sockets related to 9191

Start 6 telnet
```

    telnet 127.0.0.1 9191 &
    telnet 127.0.0.1 9191 &
    telnet 127.0.0.1 9191 &
    telnet 127.0.0.1 9191 &
    telnet 127.0.0.1 9191 &
    telnet 127.0.0.1 9191 &


```

ps aux|grep telnet
```
myname     32499  0.0  0.0   6196  3188 pts/0    T    22:46   0:00 telnet 127.0.0.1 9191
myname     32500  0.0  0.0   6196  3248 pts/0    T    22:46   0:00 telnet 127.0.0.1 9191
myname     32501  0.0  0.0   6196  3296 pts/0    T    22:46   0:00 telnet 127.0.0.1 9191
myname     32502  0.0  0.0   6196  3200 pts/0    S    22:46   0:00 telnet 127.0.0.1 9191
myname     32503  0.0  0.0   6196  3136 pts/0    S    22:46   0:00 telnet 127.0.0.1 9191
myname     32504  0.0  0.0   6196  3284 pts/0    S    22:46   0:00 telnet 127.0.0.1 9191
```

ss|grep 9191
```
tcp               ESTAB                  0                   0                                                                              127.0.0.1:42198                                 127.0.0.1:9191                                      
tcp               SYN-SENT               0                   1                                                                              127.0.0.1:42202                                 127.0.0.1:9191                                      
tcp               ESTAB                  0                   0                                                                              127.0.0.1:42194                                 127.0.0.1:9191                                      
tcp               SYN-SENT               0                   1                                                                              127.0.0.1:42200                                 127.0.0.1:9191                                      
tcp               ESTAB                  0                   0                                                                              127.0.0.1:42196                                 127.0.0.1:9191                                      
tcp               SYN-SENT               0                   1                                                                              127.0.0.1:42204                                 127.0.0.1:9191                                      
tcp               ESTAB                  0                   0                                                                     [::ffff:127.0.0.1]:9191                         [::ffff:127.0.0.1]:42194                                     
tcp               ESTAB                  0                   0                                                                     [::ffff:127.0.0.1]:9191                         [::ffff:127.0.0.1]:42198                                     
tcp               ESTAB                  0                   0                                                                     [::ffff:127.0.0.1]:9191                         [::ffff:127.0.0.1]:42196 
```

Note: After the accept queue is full, there are 3 "SYN-SENT" sockets with "1" in Send-Q.
Note: 3 "SYN-SENT" sockets are from the telnet client view; on the server side, there is no such sockets.

ss -l | grep 9191
```
tcp    LISTEN  3       2                                                      *:9191                                            *:*
```

Note: the limit control on Accept queue is not strict, it can reach "MAX + 1".

## sudo docker run -it --rm --net=container:javademo -v /tmp/javademo:/capture nicolaka/netshoot tcpdump  -v -i eth0 -w /capture/javademo.pcap

## sudo docker run -it --rm --net=container:javademo nicolaka/netshoot ss state all

```
sudo docker run -it --rm --net=container:javademo nicolaka/netshoot ss state all
Netid State  Recv-Q Send-Q Local Address:Port   Peer Address:Port Process
nl    UNCONN 0      0               rtnl:kernel             *            
nl    UNCONN 0      0               rtnl:4102               *            
nl    UNCONN 4352   0            tcpdiag:ss/1               *            
nl    UNCONN 768    0            tcpdiag:kernel             *            
nl    UNCONN 0      0               xfrm:kernel             *            
nl    UNCONN 0      0              audit:kernel             *            
nl    UNCONN 0      0          fiblookup:kernel             *            
nl    UNCONN 0      0                nft:kernel             *            
nl    UNCONN 0      0             uevent:kernel             *            
nl    UNCONN 0      0               genl:kernel             *            
u_str ESTAB  0      0                  * 343619            * 0           
tcp   LISTEN 3      2            0.0.0.0:9191        0.0.0.0:*           
tcp   ESTAB  0      0         172.17.0.2:9191     172.17.0.1:52232       
tcp   ESTAB  0      0         172.17.0.2:9191     172.17.0.1:52224       
tcp   ESTAB  0      0         172.17.0.2:9191     172.17.0.1:52228 
```
Note: The sockets in the sync queue are not shown.

SYN-RECEIVED: The device has both received a SYN (connection request) from its partner and sent its own SYN. It is now waiting for an ACK to its SYN to finish connection setup.

In the normal case, if the server receives a SYN package, it should send ACK-SYN immediately and the state is SYN-RECEIVED.
But when the accept queue is full, "ACK-SYN" is never sent (we can know this from the .pacap file) and it can't go to SYN-RECEIVED state.

## close.pcap

serverSocket.close() -> "[RST, ACK] "

Since we don't call serverSocket.accept(), we can't shutdown the socket with our Java code.
If we close serverSocket, the sockets in the accept queue send "[RST, ACK] " package.

```
10	13.272478	172.17.0.2	172.17.0.1	TCP	66	9191 → 51984 [RST, ACK] Seq=1 Ack=1 Win=65536 Len=0 TSval=1873620877 TSecr=2489431789
```
