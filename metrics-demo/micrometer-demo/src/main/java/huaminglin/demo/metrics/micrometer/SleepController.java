package huaminglin.demo.metrics.micrometer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SleepController {

  @Autowired SleepService sleepService;

  @RequestMapping(
      value="/sleep",
      method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_JSON_VALUE, }
  )
  @ResponseBody
  public String sleep() {
    return Long.toString(sleepService.sleep());
  }
}
