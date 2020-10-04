package huaminglin.demo.spring.boot.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LoggingController {
  private Logger log = LoggerFactory.getLogger(LoggingController.class);

  @GetMapping
  public String hello() {
    log.trace("TRACE level message");
    log.debug("DEBUG level message");
    log.info("INFO level message");
    log.warn("WARN level message");
    log.error("ERROR level message");
    return "Hello";
  }
}
