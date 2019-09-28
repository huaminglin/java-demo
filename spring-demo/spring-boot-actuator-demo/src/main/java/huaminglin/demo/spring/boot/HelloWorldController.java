package huaminglin.demo.spring.boot;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/session")
    @ResponseBody
    public Object main(HttpServletRequest request) {
        Integer count = (Integer) request.getSession().getAttribute("count");
        if (count == null) {
            count = 0;
        }
        count += 1;
        request.getSession().setAttribute("count", count);
        return "Hello World: " + count;
    }
}
