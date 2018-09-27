## Run a http web server
mvn -Djetty.http.port=9999 jetty:run
http://127.0.0.1:9999/hello%2F/?p=%2F
/hello%2F/
p=%2F
/
Conclusion: jetty doesn't forbid "%2F" like Tomcat do by default.
org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH

## Put the war to Tomcat to verify how Tomcat handle %2F in the request PATH
Deploy hello.war to Tomcat
1. Default tomcat configuration
http://127.0.0.1:8080/hello%2F/?p=%2F -> Tomcat responses with 400 Bad Request.

2. Start tomcat with "JAVA_OPTS=-Dorg.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH=true"
http://127.0.0.1:8080/hello%2F/?p=%2F -> Tomcat responses the following output:
/hello%2F/

<br/>p=%2F
<br/>/

## Apache AllowEncodedSlashes On|Off|NoDecode
Apache denies all URLs with %2F in the path part, for security reasons: scripts can't normally (ie. without rewriting) tell the difference between %2F and / due to the PATH_INFO environment variable being automatically URL-decoded (which is stupid, but a long-standing part of the CGI specification so there's nothing can be done about it).
