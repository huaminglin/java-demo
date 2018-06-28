package huaminglin.demo.spring.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;

public class SpringBeanDemo {

    @Configuration
    public static class MyConfiguration {
        @Bean
        public String myString1() {
            return "my1";
        }

        @Bean public String myString2() {
            return "my2";
        }

        @Bean public Date myDate1(String myString1) {
            System.out.println("myDate1: " + myString1);
            return new Date();
        }

        @Bean public Date myDate2(String myString2) {
            System.out.println("myDate2: " + myString2);
            return new Date();
        }

    }

    private static void detectParameterNameByReflect() {
        Method myDate2 = null;
        try {
            myDate2 = MyConfiguration.class.getDeclaredMethod("myDate2", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Parameter parameter = myDate2.getParameters()[0];
        System.out.println("detectParameterNameByReflect: " + parameter.getName());
        // Print "myString2" if <maven.compiler.parameters>true</maven.compiler.parameters>;
        // Otherwise "arg0" is printed.

    }

    private static void detectParameterNameBySpring() {
        Method myDate2 = null;
        try {
            myDate2 = MyConfiguration.class.getDeclaredMethod("myDate2", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(myDate2);
        if (parameterNames != null && parameterNames.length > 0) {
            System.out.println("detectParameterNameBySpring: " + parameterNames[0]);
        } else {
            System.out.println("detectParameterNameBySpring failed");
        }
    }

    public static void main(String[] args) {
        detectParameterNameByReflect();
        detectParameterNameBySpring();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("huaminglin.demo.spring.bean");
        context.refresh();
    }

}
