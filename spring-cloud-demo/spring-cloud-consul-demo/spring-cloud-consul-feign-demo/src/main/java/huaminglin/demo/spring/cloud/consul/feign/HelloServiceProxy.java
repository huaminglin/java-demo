package huaminglin.demo.spring.cloud.consul.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spring-cloud-consul-server-demo")
public interface HelloServiceProxy {

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  String hello();
}
