package huaminglin.demo.aop.spring;

import org.springframework.stereotype.Component;

@Component
public class MyBean implements MyInterface {

    @Override
    public void hello(String name) {
        System.out.println("Hello " + name);
    }
}
