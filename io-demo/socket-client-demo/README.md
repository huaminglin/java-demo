# SocketClientDemo

## java.net.ConnectException: Connection refused (Connection refused)

```
Exception in thread "main" java.net.ConnectException: Connection refused (Connection refused)
	at java.base/java.net.PlainSocketImpl.socketConnect(Native Method)
	at java.base/java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:399)
	at java.base/java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:240)
	at java.base/java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:224)
	at java.base/java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
	at java.base/java.net.Socket.connect(Socket.java:609)
	at java.base/java.net.Socket.connect(Socket.java:558)
	at huaminglin.demo.io.socket.client.SocketClientDemo.main(SocketClientDemo.java:17)
```

## Echo server

```
mkfifo fifo
cat fifo  | nc -n -k -l 9191 -v | cat  > fifo
```

## sudo tcpdump -vv -i lo port 9191 -w /tmp/javademo.pcap

## mvn exec:java

## tcpdump -n -vvv -r javademo.pcap

```
reading from file javademo.pcap, link-type EN10MB (Ethernet)
00:24:53.653221 IP (tos 0x0, ttl 64, id 48918, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [S], cksum 0xfe30 (incorrect -> 0x7f46), seq 3195111741, win 65495, options [mss 65495,sackOK,TS val 3658722734 ecr 0,nop,wscale 7], length 0
00:24:53.653236 IP (tos 0x0, ttl 64, id 0, offset 0, flags [DF], proto TCP (6), length 60)
    127.0.0.1.9191 > 127.0.0.1.56674: Flags [S.], cksum 0xfe30 (incorrect -> 0x129f), seq 4120964926, ack 3195111742, win 65483, options [mss 65495,sackOK,TS val 3658722734 ecr 3658722734,nop,wscale 7], length 0
00:24:53.653247 IP (tos 0x0, ttl 64, id 48919, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0x395b), seq 1, ack 1, win 512, options [nop,nop,TS val 3658722734 ecr 3658722734], length 0
00:24:53.672629 IP (tos 0x0, ttl 64, id 48920, offset 0, flags [DF], proto TCP (6), length 69)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [P.], cksum 0xfe39 (incorrect -> 0x84fb), seq 1:18, ack 1, win 512, options [nop,nop,TS val 3658722753 ecr 3658722734], length 17
00:24:53.672653 IP (tos 0x0, ttl 64, id 55202, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.56674: Flags [.], cksum 0xfe28 (incorrect -> 0x3923), seq 1, ack 18, win 512, options [nop,nop,TS val 3658722754 ecr 3658722753], length 0
00:24:53.672739 IP (tos 0x0, ttl 64, id 55203, offset 0, flags [DF], proto TCP (6), length 69)
    127.0.0.1.9191 > 127.0.0.1.56674: Flags [P.], cksum 0xfe39 (incorrect -> 0x84d6), seq 1:18, ack 18, win 512, options [nop,nop,TS val 3658722754 ecr 3658722753], length 17
00:24:53.672747 IP (tos 0x0, ttl 64, id 48921, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0x3911), seq 18, ack 18, win 512, options [nop,nop,TS val 3658722754 ecr 3658722754], length 0
00:24:53.684406 IP (tos 0x0, ttl 64, id 48922, offset 0, flags [DF], proto TCP (6), length 69)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [P.], cksum 0xfe39 (incorrect -> 0x84b8), seq 18:35, ack 18, win 512, options [nop,nop,TS val 3658722765 ecr 3658722754], length 17
00:24:53.684425 IP (tos 0x0, ttl 64, id 55204, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.56674: Flags [.], cksum 0xfe28 (incorrect -> 0x38ea), seq 18, ack 35, win 512, options [nop,nop,TS val 3658722765 ecr 3658722765], length 0
00:24:53.684532 IP (tos 0x0, ttl 64, id 55205, offset 0, flags [DF], proto TCP (6), length 69)
    127.0.0.1.9191 > 127.0.0.1.56674: Flags [P.], cksum 0xfe39 (incorrect -> 0x849c), seq 18:35, ack 35, win 512, options [nop,nop,TS val 3658722765 ecr 3658722765], length 17
00:24:53.684540 IP (tos 0x0, ttl 64, id 48923, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0x38d9), seq 35, ack 35, win 512, options [nop,nop,TS val 3658722765 ecr 3658722765], length 0
00:24:53.684681 IP (tos 0x0, ttl 64, id 48924, offset 0, flags [DF], proto TCP (6), length 69)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [P.], cksum 0xfe39 (incorrect -> 0x8489), seq 35:52, ack 35, win 512, options [nop,nop,TS val 3658722766 ecr 3658722765], length 17
00:24:53.684689 IP (tos 0x0, ttl 64, id 55206, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.56674: Flags [.], cksum 0xfe28 (incorrect -> 0x38c6), seq 35, ack 52, win 512, options [nop,nop,TS val 3658722766 ecr 3658722766], length 0
00:24:53.684730 IP (tos 0x0, ttl 64, id 55207, offset 0, flags [DF], proto TCP (6), length 69)
    127.0.0.1.9191 > 127.0.0.1.56674: Flags [P.], cksum 0xfe39 (incorrect -> 0x8477), seq 35:52, ack 52, win 512, options [nop,nop,TS val 3658722766 ecr 3658722766], length 17
00:24:53.684737 IP (tos 0x0, ttl 64, id 48925, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0x38b5), seq 52, ack 52, win 512, options [nop,nop,TS val 3658722766 ecr 3658722766], length 0
00:24:53.684867 IP (tos 0x0, ttl 64, id 48926, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [F.], cksum 0xfe28 (incorrect -> 0x38b4), seq 52, ack 52, win 512, options [nop,nop,TS val 3658722766 ecr 3658722766], length 0
00:24:53.684884 IP (tos 0x0, ttl 64, id 55208, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.9191 > 127.0.0.1.56674: Flags [F.], cksum 0xfe28 (incorrect -> 0x38b3), seq 52, ack 53, win 512, options [nop,nop,TS val 3658722766 ecr 3658722766], length 0
00:24:53.684899 IP (tos 0x0, ttl 64, id 48927, offset 0, flags [DF], proto TCP (6), length 52)
    127.0.0.1.56674 > 127.0.0.1.9191: Flags [.], cksum 0xfe28 (incorrect -> 0x38b3), seq 53, ack 53, win 512, options [nop,nop,TS val 3658722766 ecr 3658722766], length 0
```

