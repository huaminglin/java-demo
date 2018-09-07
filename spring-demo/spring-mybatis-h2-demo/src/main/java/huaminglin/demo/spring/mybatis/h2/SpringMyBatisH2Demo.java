package huaminglin.demo.spring.mybatis.h2;

import huaminglin.demo.spring.mybatis.h2.mapper.StudentMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SpringMyBatisH2Demo {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("huaminglin.demo.spring.mybatis.h2");
        context.refresh();
        StudentMapper bean = context.getBean(StudentMapper.class);
        {
            List<Student> students = bean.getStudents("name1");
            System.out.println("Get name1: " + students);
        }
        {
            List<Student> students = bean.getStudents("name1");
            System.out.println("Get name1: " + students);
        }
        context.close();
    }

}
