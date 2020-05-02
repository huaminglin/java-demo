package huaminglin.demo.encoding.query.plus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/servlet")
    public String servlet(HttpServletRequest request) {
        String name = request.getParameter("name");
        System.out.println("name: " + name);
        return "hello: " + name;
    }

    @ResponseBody
    @RequestMapping("/path/{name}")
    public String path(@PathVariable String name) {
        System.out.println("name: " + name);
        return "hello: " + name;
    }

    @ResponseBody
    @RequestMapping("/request")
    public String request(@RequestParam String name) {
        System.out.println("name: " + name);
        return "hello: " + name;
    }

}
