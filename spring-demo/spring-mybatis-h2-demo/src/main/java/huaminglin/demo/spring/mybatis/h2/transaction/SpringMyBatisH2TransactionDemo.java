package huaminglin.demo.spring.mybatis.h2.transaction;

import huaminglin.demo.spring.mybatis.h2.Student;
import huaminglin.demo.spring.mybatis.h2.transaction.StudentDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SpringMyBatisH2TransactionDemo {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("huaminglin.demo.spring.mybatis.h2.datasource", "huaminglin.demo.spring.mybatis.h2.mapper", "huaminglin.demo.spring.mybatis.h2.transaction");
        context.refresh();
        StudentDao dao = context.getBean(StudentDao.class);

        List<Student> students = dao.getStudents("name1");
        System.out.println("Before update: " + students);

        dao.addOneAge("name1");

        students = dao.getStudents("name1");
        System.out.println("After addOneAge: " + students);

        dao.tryAndRollbackAddOneAge("name1");

        students = dao.getStudents("name1");
        System.out.println("After tryAndRollbackAddOneAge: " + students);

        try {
            dao.tryAndRollbackWithExceptionAddOneAge("name1");
        } catch (Exception e) {
        }

        students = dao.getStudents("name1");
        System.out.println("After tryAndRollbackWithExceptionAddOneAge: " + students);

        context.close();
    }
}
