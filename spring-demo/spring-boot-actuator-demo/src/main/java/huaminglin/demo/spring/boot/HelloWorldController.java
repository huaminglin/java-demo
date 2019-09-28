package huaminglin.demo.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import huaminglin.demo.spring.boot.cache.CacheIdProvider;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    @Autowired
    private CacheIdProvider idProvider;

    @RequestMapping(value = "/world")
    @ResponseBody
    public Object main() {
        return "Hello World: " + idProvider.getId();
    }
}
