package huaminglin.demo.encoding.query.plus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/")
    public String welcome(HttpServletRequest request) {
        String name = request.getParameter("name");
        System.out.println("name: " + name);
        return "hello: " + name;
    }

}
