package huaminglin.demo.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

  @RequestMapping(value = "/")
  @ResponseBody
  public Object helloworld() {
    return "Hello world.";
  }

  @RequestMapping(
      value = "/post",
      method = RequestMethod.POST
  )
  @ResponseBody
  public Object demoRequestBody(@RequestBody(required = false) String requestBody) {
    return requestBody;
  }
}
