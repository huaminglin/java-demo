package huaminglin.demo.aop.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAopDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(SpringAopDemo.class.getPackage().getName());
        context.refresh();
        MyBean bean = (MyBean) context.getBean("myBean");
        bean.hello("origin");

        MyInterface myInterface = (MyInterface) context.getBean("myBeanProxy");
        System.out.println(myInterface.getClass());
        myInterface.hello("proxy");
    }

}
