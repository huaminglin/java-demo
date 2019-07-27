package huaminglin.demo.spring.mybatis.h2.cache;

import huaminglin.demo.spring.mybatis.h2.Student;
import huaminglin.demo.spring.mybatis.h2.mapper.StudentMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SpringMyBatisH2CacheDemo {
    private static AnnotationConfigApplicationContext createMyContext() {
        return new AnnotationConfigApplicationContext();
    }

    private static void scanMyBean(AnnotationConfigApplicationContext context) {
        context.scan("huaminglin.demo.spring.mybatis.h2.datasource", "huaminglin.demo.spring.mybatis.h2.mapper");
    }

    private static void refreshMyContext(AnnotationConfigApplicationContext context) {
        context.refresh();
    }

    private static StudentMapper getStudentMapper(AnnotationConfigApplicationContext context) {
        return context.getBean(StudentMapper.class);
    }

    private static void printStudentsFirstTime(StudentMapper bean) {
        List<Student> students = bean.getStudents("name1");
        System.out.println("Get name1: " + students);
    }

    private static void printStudentsSecondTime(StudentMapper bean) {
        List<Student> students = bean.getStudents("name1");
        System.out.println("Get name1: " + students);
    }

    private static void closeMyContext(AnnotationConfigApplicationContext context) {
        context.close();
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = createMyContext();
        scanMyBean(context);
        refreshMyContext(context);
        StudentMapper bean = getStudentMapper(context);
        System.out.println("getStudentMapper: " + bean.getClass() + "/" + bean.toString());
        printStudentsFirstTime(bean);
        printStudentsSecondTime(bean);
        closeMyContext(context);
    }
}
