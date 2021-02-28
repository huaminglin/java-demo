# SocketServerAcceptDemo

## run.sh

## sudo docker run -it --rm --net=container:javademo -v /tmp/javademo:/capture nicolaka/netshoot tcpdump  -v -i eth0 -w /capture/javademo.pcap

## telnet 127.0.0.1 9191

## javademo.pcap

With clientSocket.close(), the socket is cosed with packaets:

```
13	73.797914	172.17.0.2	172.17.0.1	TCP	66	9191 → 52566 [FIN, ACK] Seq=1 Ack=1 Win=65280 Len=0 TSval=1874902839 TSecr=2490660644
14	73.798294	172.17.0.1	172.17.0.2	TCP	66	52566 → 9191 [FIN, ACK] Seq=1 Ack=2 Win=64256 Len=0 TSval=2490720692 TSecr=1874902839
15	73.798317	172.17.0.2	172.17.0.1	TCP	66	9191 → 52566 [ACK] Seq=2 Ack=2 Win=65280 Len=0 TSval=1874902839 TSecr=2490720692
```

The "14" "[FIN, ACK]" in normal case should be two separate packages. "ACK" to confirm receiving the "FIN" request; after close preparation, send "FIN" to contine the close process.
 