package huaminglin.demo.spring.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
class MessageRestController {

    @Value("${a:Hello default}")
    private String message;

    @RequestMapping("/")
    String getMessage() {
        return this.message;
    }
}
