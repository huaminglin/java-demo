# SocketCloseDemo

## Only call serverSocket.close(), no clientSocket.close() on either server or client side

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
tcp   TIME-WAIT  0      0                                    [::ffff:127.0.0.1]:9191                           [::ffff:127.0.0.1]:34996

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

```
accept() ...
connect(): Socket[addr=/127.0.0.1,port=9191,localport=35272]
connect() read() ...
accept(): Socket[addr=/127.0.0.1,port=35272,localport=9191]
serverSocket.close()
serverThread exit
connect() read() got: -1
clientThread exit
```

ss -a | grep 9191
```
tcp   TIME-WAIT  0      0                                    [::ffff:127.0.0.1]:9191                           [::ffff:127.0.0.1]:35290
```
Note: TIME-WAIT is on the active close side: server side.

tcpdump -n -vvv -r server-close.pcap
```
reading from file server-close.pcap, link-type EN10MB (Ethernet)
09:46:09.499387 IP (tos 0x0, ttl 64, id 34876, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.35290 > 127.0.0.1.9191: Flags [S], cksum 0xfe30 (incorrect -> 0x196a), seq 2611606450, win 65495, options [mss 65495,sackOK,TS val 2266788445 ecr 0,nop,wscale 7], length 0
09:46:09.499404 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.9191 > 127.0.0.1.35290: Flags [S.], cksum 0xfe30 (incorrect -> 0x6631), seq 4151624260, ack 2611606451, win 65483, options [mss 65495,sackOK,TS val 2266788445 ecr 2266788445,nop,wscale 7], length 0
09:46:09.499418 IP (tos 0x0, ttl 64, id 34877, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.35290 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0x8ced), seq 1, ack 1, win 512, options [nop,nop,TS val 2266788445 ecr 2266788445], length 0
09:46:09.526879 IP (tos 0x0, ttl 64, id 56357, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.35290: Flags [F.], cksum 0xfe28 (incorrect -> 0x8cd1), seq 1, ack 1, win 512, options [nop,nop,TS val 2266788472 ecr 2266788445], length 0
09:46:09.527112 IP (tos 0x0, ttl 64, id 34878, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.35290 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0x8cb5), seq 1, ack 2, win 512, options [nop,nop,TS val 2266788473 ecr 2266788472], length 0
09:46:09.551222 IP (tos 0x0, ttl 64, id 34879, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.35290 > 127.0.0.1.9191: Flags [F.], cksum 0xfe28 (incorrect -> 0x8c9c), seq 1, ack 2, win 512, options [nop,nop,TS val 2266788497 ecr 2266788472], length 0
09:46:09.551241 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.35290: Flags [.], cksum 0x8c83 (correct), seq 2, ack 2, win 512, options [nop,nop,TS val 2266788497 ecr 2266788497], length 0
```
When client side receives FIN package, it's reading(). It ACK the FIN first, then read() return -1 and sends its FIN package.

##

```
accept() ...
accept(): Socket[addr=/127.0.0.1,port=35452,localport=9191]
accept() read() ...
connect(): Socket[addr=/127.0.0.1,port=9191,localport=35452]
clientThread exit
accept() read() got: -1
serverSocket.close()
serverThread exit
```

ss -a | grep 9191
```
tcp   TIME-WAIT  0      0                                    [::ffff:127.0.0.1]:35452                          [::ffff:127.0.0.1]:9191
```
Note: TIME-WAIT is on the active close side: client side.

tcpdump -n -vvv -r client-close.pcap
```
reading from file client-close.pcap, link-type EN10MB (Ethernet)
10:03:40.524007 IP (tos 0x0, ttl 64, id 9968, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.35452 > 127.0.0.1.9191: Flags [S], cksum 0xfe30 (incorrect -> 0x44d5), seq 3814291028, win 65495, options [mss 65495,sackOK,TS val 2267839470 ecr 0,nop,wscale 7], length 0
10:03:40.524017 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.9191 > 127.0.0.1.35452: Flags [S.], cksum 0xfe30 (incorrect -> 0xa655), seq 1537753015, ack 3814291029, win 65483, options [mss 65495,sackOK,TS val 2267839470 ecr 2267839470,nop,wscale 7], length 0
10:03:40.524027 IP (tos 0x0, ttl 64, id 9969, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.35452 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0xcd11), seq 1, ack 1, win 512, options [nop,nop,TS val 2267839470 ecr 2267839470], length 0
10:03:40.641341 IP (tos 0x0, ttl 64, id 9970, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.35452 > 127.0.0.1.9191: Flags [F.], cksum 0xfe28 (incorrect -> 0xcc9b), seq 1, ack 1, win 512, options [nop,nop,TS val 2267839587 ecr 2267839470], length 0
10:03:40.643207 IP (tos 0x0, ttl 64, id 4591, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.35452: Flags [.], cksum 0xfe28 (incorrect -> 0xcc24), seq 1, ack 2, win 512, options [nop,nop,TS val 2267839589 ecr 2267839587], length 0
10:03:40.968310 IP (tos 0x0, ttl 64, id 4592, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.35452: Flags [F.], cksum 0xfe28 (incorrect -> 0xcade), seq 1, ack 2, win 512, options [nop,nop,TS val 2267839914 ecr 2267839587], length 0
10:03:40.968321 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.35452 > 127.0.0.1.9191: Flags [.], cksum 0xc997 (correct), seq 2, ack 2, win 512, options [nop,nop,TS val 2267839914 ecr 2267839914], length 0
```

When server side receives FIN package, it's reading(). It ACK the FIN first, then read() return -1 and sends its FIN package.

## Conclusion

Client side close() is enough to close the socket connection, and the connection is closed without close() call by server side.

It seems the there is no API we can use to trigger CLOSE-WAIT with Java programming API.
CLOSE-WAIT can indicator that there are still data in the write buffer; as soon as kernel sends the write buffer, the status is no longer CLOSE-WAIT.
