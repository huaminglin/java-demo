package huaminglin.demo.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
    @RequestMapping(value = "/")
    @ResponseBody
    public Object helloworld() {
        return "Hello World.";
    }
}
