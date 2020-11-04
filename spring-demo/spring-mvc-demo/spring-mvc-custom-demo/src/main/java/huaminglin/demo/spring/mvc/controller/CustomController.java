package huaminglin.demo.spring.mvc.controller;

import huaminglin.demo.spring.mvc.CustomValue;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomController {

  @RequestMapping(
      value = "/",
      method = RequestMethod.GET,
      produces = {MediaType.TEXT_PLAIN_VALUE}
  )
  @ResponseBody
  public CustomValue helloworld() {
    CustomValue value = new CustomValue();
    value.setValue("value1");
    return value;
  }
}
