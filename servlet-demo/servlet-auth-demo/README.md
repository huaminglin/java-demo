Container Managed Security for Web APplication

## Run a http web server
mvn -Djetty.http.port=9999 jetty:run
http://127.0.0.1:9999/hello/

## org.eclipse.jetty.security.authentication.BasicAuthenticator
java.lang.IllegalStateException: No LoginService for org.eclipse.jetty.security.authentication.BasicAuthenticator@6e4f263e in org.eclipse.jetty.security.ConstraintSecurityHandler@48b4a043

The realm names must match: web.xml: <realm-name>MySecurityRealm</realm-name> and pom.xml: <loginService implementation="org.eclipse.jetty.security.HashLoginService"><name>MySecurityRealm</name>

