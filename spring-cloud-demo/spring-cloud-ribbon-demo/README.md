mvn spring-boot:run

cd /var; python -m SimpleHTTPServer 9091
cd /tmp; python -m SimpleHTTPServer 9092

Note: @RibbonClient org.springframework.cloud.netflix.ribbon.RibbonClient can be used to customize ribbon client configuration.
