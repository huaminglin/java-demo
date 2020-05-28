package huaminglin.demo.aop.spring.autoproxy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAopAutoProxyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(SpringAopAutoProxyDemo.class.getPackage().getName());
        context.refresh();
        MyInterface bean = (MyInterface) context.getBean("myBean");
        System.out.println(bean.getClass().getName());
        bean.hello("origin");
    }

}
