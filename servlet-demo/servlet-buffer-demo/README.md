## Run a http web server
mvn -Djetty.http.port=9999 jetty:run
http://127.0.0.1:9999/hello/
Java Servlet: buffer size and Content-Length header automatically
Java Servlet: buffer size and Transfer-Encoding:chunked

1. Default buffer can buffer and set Content-Length automatically.
curl -v http://127.0.0.1:9999/hello/HelloWorld
< HTTP/1.1 200 OK
< Date: Tue, 24 Jul 2018 09:31:38 GMT
< Content-Type: text/html;charset=utf-8
< Content-Length: 10
< Server: Jetty(9.4.11.v20180605)

2. Flush the buffer without setting Content-Length
curl -v http://127.0.0.1:9999/hello/HelloWorld?flush
< HTTP/1.1 200 OK
< Date: Tue, 24 Jul 2018 09:32:13 GMT
< Content-Type: text/html;charset=utf-8
< Transfer-Encoding: chunked
< Server: Jetty(9.4.11.v20180605)

3. Flush the buffer with setting Content-Length
curl -v "http://127.0.0.1:9999/hello/HelloWorld?flush&length"
HTTP/1.1 200 OK
< Date: Tue, 24 Jul 2018 12:24:13 GMT
< Content-Type: text/html;charset=utf-8
< Content-Length: 10
< Server: Jetty(9.4.11.v20180605)


4. Output large string and try to overflow the buffer with 10000, still not large enough
curl -sSL -D - "http://127.0.0.1:9999/hello/HelloWorld?count=10000" -o /dev/null
HTTP/1.1 200 OK
Date: Tue, 24 Jul 2018 12:29:08 GMT
Content-Type: text/html;charset=utf-8
Content-Length: 10000
Server: Jetty(9.4.11.v20180605)

5. Output large string and try to overflow the buffer with 100000, large enough
curl -sSL -D - "http://127.0.0.1:9999/hello/HelloWorld?count=100000" -o /dev/null
HTTP/1.1 200 OK
Date: Tue, 24 Jul 2018 12:29:17 GMT
Content-Type: text/html;charset=utf-8
Transfer-Encoding: chunked
Server: Jetty(9.4.11.v20180605)
