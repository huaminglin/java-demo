# Java Security properties to config DNS response cache

## DNS positive response cache

java -cp target/classes/ huaminglin.demo.network.jdk.dns.DnsDemo 5 6000 server01

```
server01/10.2.2.67, time: 5161589
server01/10.2.2.67, time: 83454
server01/10.2.2.67, time: 144073
server01/10.2.2.67, time: 56324
server01/10.2.2.67, time: 34082
```

Note: networkaddress.cache.ttl default value is: -1 (forever).


## DNS negative response cache

java -cp target/classes/ huaminglin.demo.network.jdk.dns.DnsDemo 5 6000 server0123

```
Unknown host: server0123, time: 5297460
Unknown host: server0123, time: 128889
Unknown host: server0123, time: 1551532
Unknown host: server0123, time: 150074
Unknown host: server0123, time: 2634105
```

Note: networkaddress.cache.negative.ttl default value is 10.

## Disable positive response cache

java -cp target/classes/ huaminglin.demo.network.jdk.dns.DnsDemo 5 6000 server01 0

Update /etc/hosts to check whether the running programming can get the update.

```
server01/10.2.2.70, time: 1495925
server01/10.2.2.70, time: 253817
server01/10.2.2.70, time: 198478
server01/10.2.2.71, time: 251857
server01/10.2.2.71, time: 188935
```

## Disable negative response cache

java -cp target/classes/ huaminglin.demo.network.jdk.dns.DnsDemo 5 6000 server0123 -1 0

```
Unknown host: server0123, time: 2482463
Unknown host: server0123, time: 1752881
Unknown host: server0123, time: 1610260
Unknown host: server0123, time: 1647940
Unknown host: server0123, time: 1357274
```

## Use strace to capture related system calls

strace -o /tmp/java.dns.strace java -cp target/classes/ huaminglin.demo.network.jdk.dns.DnsDemo

It seems we can't capture the network related system calls from Java.

Python is a wrapper of native C; try strace with Python to get host name related system call

strace -o /tmp/python.dns.strace python -c "import socket; print(socket.gethostbyname('usa.gov'))"

```strace
823: stat("/etc/resolv.conf", {st_mode=S_IFREG|0644, st_size=715, ...}) = 0
830: openat(AT_FDCWD, "/etc/resolv.conf", O_RDONLY|O_CLOEXEC) = 3
837: connect(3, {sa_family=AF_UNIX, sun_path="/var/run/nscd/socket"}, 110) = -1 ENOENT (No such file or directory)
840: connect(3, {sa_family=AF_UNIX, sun_path="/var/run/nscd/socket"}, 110) = -1 ENOENT (No such file or directory)
842: openat(AT_FDCWD, "/etc/nsswitch.conf", O_RDONLY|O_CLOEXEC) = 3
852: openat(AT_FDCWD, "/lib/x86_64-linux-gnu/libnss_files.so.2", O_RDONLY|O_CLOEXEC) = 3
862: openat(AT_FDCWD, "/etc/hosts", O_RDONLY|O_CLOEXEC) = 3
872: openat(AT_FDCWD, "/lib/x86_64-linux-gnu/libnss_mdns4_minimal.so.2", O_RDONLY|O_CLOEXEC) = 3
886: openat(AT_FDCWD, "/lib/x86_64-linux-gnu/libnss_dns.so.2", O_RDONLY|O_CLOEXEC) = 3
906: connect(3, {sa_family=AF_INET, sin_port=htons(53), sin_addr=inet_addr("127.0.0.53")}, 16) = 0
```
