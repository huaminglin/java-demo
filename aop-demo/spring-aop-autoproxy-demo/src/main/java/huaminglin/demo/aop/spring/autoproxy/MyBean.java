package huaminglin.demo.aop.spring.autoproxy;

import org.springframework.stereotype.Component;

@Component
public class MyBean implements MyInterface {

    public MyBean() {

    }

    @Override
    public void hello(String name) {
        System.out.println("Hello " + name);
    }
}
