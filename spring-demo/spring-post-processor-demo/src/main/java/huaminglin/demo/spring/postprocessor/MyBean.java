package huaminglin.demo.spring.postprocessor;

import org.springframework.stereotype.Component;

@Component
public class MyBean {

    static {
        System.out.println("MyBean.static");
    }

    public MyBean() {
        System.out.println("MyBean()");
    }
}
