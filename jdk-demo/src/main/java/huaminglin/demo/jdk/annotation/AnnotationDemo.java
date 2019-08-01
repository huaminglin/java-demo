package huaminglin.demo.jdk.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Transactional("abc")
public class AnnotationDemo {
    public static void printAllAnnotations() {
        for (Annotation annotation : AnnotationDemo.class.getDeclaredAnnotations()) {
            System.out.println(annotation.getClass() + ": " + annotation.toString());
        }
    }

    public static void printTransactionAnnotation() {
        Transactional annotation = (Transactional) AnnotationDemo.class.getAnnotation(Transactional.class);
        System.out.println(annotation.getClass() + ": " + annotation.toString());
    }

    public static void main(String[] args) {
        System.out.println("AnnotationDemo");
        printAllAnnotations();
        printTransactionAnnotation();
    }
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@interface Transactional {
    String value() default "";
}
