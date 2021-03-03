# SocketCloseDemo

## Only call serverSocket.close(), no clientSocket.close() on either server or client side

sudo tcpdump -vv -i lo port 9191 -w /tmp/javademo.pcap

```
accept() ...
accept(): Socket[addr=/127.0.0.1,port=34996,localport=9191]
connect(): Socket[addr=/127.0.0.1,port=9191,localport=34996]
connect() read() ...
serverSocket.close()
serverThread exit
```

The process is blocked by "clientSocket.getInputStream().read();"

"ss -l | grep 9191" matches no socket.

ss -a | grep 9191
```
tcp   ESTAB      0      0                                    [::ffff:127.0.0.1]:9191                           [::ffff:127.0.0.1]:34996                         
tcp   ESTAB      0      0                                    [::ffff:127.0.0.1]:34996                          [::ffff:127.0.0.1]:9191
```
Note: after serverSocket.close() call, the ESTAB connections are not affected.

Kill the process: "kill -9 13689" and check "ss -a | grep 9191":
```
tcp   TIME-WAIT  0      0                                    [::ffff:127.0.0.1]:9191                           [::ffff:127.0.0.1]:34996
```

tcpdump -n -vvv -r kill-0.pcap
```
reading from file kill-9.pcap, link-type EN10MB (Ethernet)
09:23:37.952377 IP (tos 0x0, ttl 64, id 19959, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.34996 > 127.0.0.1.9191: Flags [S], cksum 0xfe30 (incorrect -> 0x93e9), seq 276604182, win 65495, options [mss 65495,sackOK,TS val 2265436898 ecr 0,nop,wscale 7], length 0
09:23:37.952389 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.9191 > 127.0.0.1.34996: Flags [S.], cksum 0xfe30 (incorrect -> 0x6301), seq 2272939902, ack 276604183, win 65483, options [mss 65495,sackOK,TS val 2265436898 ecr 2265436898,nop,wscale 7], length 0
09:23:37.952398 IP (tos 0x0, ttl 64, id 19960, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.34996 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0x89bd), seq 1, ack 1, win 512, options [nop,nop,TS val 2265436898 ecr 2265436898], length 0
09:26:04.982647 IP (tos 0x0, ttl 64, id 50767, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.34996: Flags [F.], cksum 0xfe28 (incorrect -> 0x4b64), seq 1, ack 1, win 512, options [nop,nop,TS val 2265583928 ecr 2265436898], length 0
09:26:04.982667 IP (tos 0x0, ttl 64, id 19961, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.34996 > 127.0.0.1.9191: Flags [F.], cksum 0xfe28 (incorrect -> 0x0d0b), seq 1, ack 2, win 512, options [nop,nop,TS val 2265583928 ecr 2265583928], length 0
09:26:04.982681 IP (tos 0x0, ttl 64, id 50768, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.34996: Flags [.], cksum 0xfe28 (incorrect -> 0x0d0b), seq 2, ack 2, win 512, options [nop,nop,TS val 2265583928 ecr 2265583928], length 0
```
Note: The connection is closed with "FIN" packages even force kill the processes.

## clientSocket.close() on server side

sudo tcpdump -vv -i lo port 9191 -w /tmp/javademo.pcap

```
accept() ...
accept(): Socket[addr=/127.0.0.1,port=42026,localport=9191]
connect(): Socket[addr=/127.0.0.1,port=9191,localport=42026]
connect() read() ...
serverSocket.close()
serverThread exit
connect() read() got: -1
read < 0 means server side starts to close(); sleep 1 minute
clientThread exit
```
Note: "serverThread exit" exits as soon as the close() call; the close() doesn't wait the other side to send its FIN package.

ss -a | grep 9191 (sleeping)
```
tcp   CLOSE-WAIT 0      0                                    [::ffff:127.0.0.1]:42026                          [::ffff:127.0.0.1]:9191                          
tcp   FIN-WAIT-2 0      0                                    [::ffff:127.0.0.1]:9191                           [::ffff:127.0.0.1]:42026 
```

ss -a | grep 9191 (after process exits)
```
tcp   TIME-WAIT  0      0                                    [::ffff:127.0.0.1]:9191                           [::ffff:127.0.0.1]:42026
```
Note: TIME-WAIT is on the active close side: server side.

tcpdump -n -vvv -r server-close.pcap
```
reading from file server-close.pcap, link-type EN10MB (Ethernet)
22:55:33.928267 IP (tos 0x0, ttl 64, id 17891, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.42026 > 127.0.0.1.9191: Flags [S], cksum 0xfe30 (incorrect -> 0x8171), seq 1266615654, win 65495, options [mss 65495,sackOK,TS val 2314152874 ecr 0,nop,wscale 7], length 0
22:55:33.928285 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.9191 > 127.0.0.1.42026: Flags [S.], cksum 0xfe30 (incorrect -> 0xcc43), seq 561307162, ack 1266615655, win 65483, options [mss 65495,sackOK,TS val 2314152874 ecr 2314152874,nop,wscale 7], length 0
22:55:33.928298 IP (tos 0x0, ttl 64, id 17892, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.42026 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0xf2ff), seq 1, ack 1, win 512, options [nop,nop,TS val 2314152874 ecr 2314152874], length 0
22:55:33.945101 IP (tos 0x0, ttl 64, id 22835, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.42026: Flags [F.], cksum 0xfe28 (incorrect -> 0xf2ed), seq 1, ack 1, win 512, options [nop,nop,TS val 2314152891 ecr 2314152874], length 0
22:55:33.947118 IP (tos 0x0, ttl 64, id 17893, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.42026 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0xf2da), seq 1, ack 2, win 512, options [nop,nop,TS val 2314152893 ecr 2314152891], length 0
22:56:34.273284 IP (tos 0x0, ttl 64, id 17894, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.42026 > 127.0.0.1.9191: Flags [F.], cksum 0xfe28 (incorrect -> 0x0733), seq 1, ack 2, win 512, options [nop,nop,TS val 2314213219 ecr 2314152891], length 0
22:56:34.273321 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.42026: Flags [.], cksum 0x1b8a (correct), seq 2, ack 2, win 512, options [nop,nop,TS val 2314213219 ecr 2314213219], length 0
```
Note: Note: When client side receives FIN package, it's reading(), then read() return -1 since there is noging to read and the other side starts close() and it will not write any more data.
Note: The last two packages are sent when the process exits; it's the Linux kernel cleanup the process resources and the FIN package.

## clientSocket.close() on client side

sudo tcpdump -vv -i lo port 9191 -w /tmp/javademo.pcap

```
accept() ...
connect(): Socket[addr=/127.0.0.1,port=9191,localport=42128]
accept(): Socket[addr=/127.0.0.1,port=42128,localport=9191]
accept() read() ...
clientThread exit
accept() read() got: -1
read < 0 means client side starts to close(); sleep 1 minute
serverSocket.close()
serverThread exit
```
Note: "clientThread exit" exits as soon as the close() call; the close() doesn't wait the other side to send its FIN package.

ss -a | grep 9191 (sleeping)
```
tcp   LISTEN     0      2                                                     *:9191                                            *:*                             
tcp   FIN-WAIT-2 0      0                                    [::ffff:127.0.0.1]:42128                          [::ffff:127.0.0.1]:9191                          
tcp   CLOSE-WAIT 0      0                                    [::ffff:127.0.0.1]:9191                           [::ffff:127.0.0.1]:4212
```

ss -a | grep 9191 (process exits)
```
tcp   TIME-WAIT  0      0                                    [::ffff:127.0.0.1]:42128                          [::ffff:127.0.0.1]:9191
```
Note: TIME-WAIT is on the active close side: client side.

tcpdump -n -vvv -r client-close.pcap
```
reading from file client-close.pcap, link-type EN10MB (Ethernet)
23:07:47.787023 IP (tos 0x0, ttl 64, id 4873, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.42128 > 127.0.0.1.9191: Flags [S], cksum 0xfe30 (incorrect -> 0x5430), seq 1102317918, win 65495, options [mss 65495,sackOK,TS val 2314886733 ecr 0,nop,wscale 7], length 0
23:07:47.787034 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.9191 > 127.0.0.1.42128: Flags [S.], cksum 0xfe30 (incorrect -> 0xf195), seq 2920270909, ack 1102317919, win 65483, options [mss 65495,sackOK,TS val 2314886733 ecr 2314886733,nop,wscale 7], length 0
23:07:47.787041 IP (tos 0x0, ttl 64, id 4874, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.42128 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0x1852), seq 1, ack 1, win 512, options [nop,nop,TS val 2314886733 ecr 2314886733], length 0
23:07:47.902209 IP (tos 0x0, ttl 64, id 4875, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.42128 > 127.0.0.1.9191: Flags [F.], cksum 0xfe28 (incorrect -> 0x17de), seq 1, ack 1, win 512, options [nop,nop,TS val 2314886848 ecr 2314886733], length 0
23:07:47.907127 IP (tos 0x0, ttl 64, id 64421, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.42128: Flags [.], cksum 0xfe28 (incorrect -> 0x1766), seq 1, ack 2, win 512, options [nop,nop,TS val 2314886853 ecr 2314886848], length 0
23:08:48.229402 IP (tos 0x0, ttl 64, id 64422, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.42128: Flags [F.], cksum 0xfe28 (incorrect -> 0x2bc2), seq 1, ack 2, win 512, options [nop,nop,TS val 2314947175 ecr 2314886848], length 0
23:08:48.229417 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.42128 > 127.0.0.1.9191: Flags [.], cksum 0x401a (correct), seq 2, ack 2, win 512, options [nop,nop,TS val 2314947175 ecr 2314947175], length 0
```

Note: When server side receives FIN package, it's reading(), then read() return -1 since there is noging to read and the other side starts close() and it will not write any more data.
Note: The last two packages are sent when the process exits; it's the Linux kernel cleanup the process resources and the FIN package.


## clientSocket.close() on server side and client side

sudo tcpdump -vv -i lo port 9191 -w /tmp/javademo.pcap


```
accept() ...
accept(): Socket[addr=/127.0.0.1,port=42216,localport=9191]
accept() read() ...
connect(): Socket[addr=/127.0.0.1,port=9191,localport=42216]
clientThread exit
accept() read() got: -1
read < 0 means client side starts to close(); sleep 1 minute
serverSocket.close()
serverThread exit
```

Note: the "clientThread exit" as soon as the close(), which means the client close() call doesn't wait until the server sends FIN package.

ss -a | grep 9191 (sleeping)
```
tcp   LISTEN     0      2                                                     *:9191                                            *:*                             
tcp   CLOSE-WAIT 0      0                                    [::ffff:127.0.0.1]:9191                           [::ffff:127.0.0.1]:42216                         
tcp   FIN-WAIT-2 0      0                                    [::ffff:127.0.0.1]:42216                          [::ffff:127.0.0.1]:9191
```

ss -a | grep 9191 (process exits)
```
tcp   TIME-WAIT  0      0                                    [::ffff:127.0.0.1]:42216                          [::ffff:127.0.0.1]:9191
```

tcpdump -n -vvv -r both-close.pcap

```
reading from file both-close.pcap, link-type EN10MB (Ethernet)
23:18:29.615473 IP (tos 0x0, ttl 64, id 9826, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.42216 > 127.0.0.1.9191: Flags [S], cksum 0xfe30 (incorrect -> 0x76d4), seq 869935374, win 65495, options [mss 65495,sackOK,TS val 2315528561 ecr 0,nop,wscale 7], length 0
23:18:29.615501 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.9191 > 127.0.0.1.42216: Flags [S.], cksum 0xfe30 (incorrect -> 0xb4c3), seq 1475393189, ack 869935375, win 65483, options [mss 65495,sackOK,TS val 2315528561 ecr 2315528561,nop,wscale 7], length 0
23:18:29.615509 IP (tos 0x0, ttl 64, id 9827, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.42216 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0xdb7f), seq 1, ack 1, win 512, options [nop,nop,TS val 2315528561 ecr 2315528561], length 0
23:18:29.732062 IP (tos 0x0, ttl 64, id 9828, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.42216 > 127.0.0.1.9191: Flags [F.], cksum 0xfe28 (incorrect -> 0xdb09), seq 1, ack 1, win 512, options [nop,nop,TS val 2315528678 ecr 2315528561], length 0
23:18:29.735223 IP (tos 0x0, ttl 64, id 57175, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.42216: Flags [.], cksum 0xfe28 (incorrect -> 0xda91), seq 1, ack 2, win 512, options [nop,nop,TS val 2315528681 ecr 2315528678], length 0
23:19:29.740982 IP (tos 0x0, ttl 64, id 57176, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.42216: Flags [F.], cksum 0xfe28 (incorrect -> 0xf029), seq 1, ack 2, win 512, options [nop,nop,TS val 2315588687 ecr 2315528678], length 0
23:19:29.741021 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.42216 > 127.0.0.1.9191: Flags [.], cksum 0x05c0 (correct), seq 2, ack 2, win 512, options [nop,nop,TS val 2315588687 ecr 2315588687], length 0
```

## Conclusion

"CLOSE-WAIT" happens when the passive close side receive the active close side's FIN package but the passive close side fails to call close() and the process is still active for quite a long time.

It should be mentioned that the Socket instance in both client and the server end needs to explicitly invoke close().
If only one of the ends invokes close() then too, the socket will remain in CLOSE_WAIT state.
