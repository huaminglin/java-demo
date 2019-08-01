package huaminglin.demo.spring.mybatis.h2.transaction;

import huaminglin.demo.spring.mybatis.h2.Student;
import huaminglin.demo.spring.mybatis.h2.transaction.StudentDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SpringMyBatisH2TransactionDemo {
    private static AnnotationConfigApplicationContext createMyContext() {
        return new AnnotationConfigApplicationContext();
    }

    private static void scanMyBean(AnnotationConfigApplicationContext context) {
        context.scan("huaminglin.demo.spring.mybatis.h2.datasource",
            "huaminglin.demo.spring.mybatis.h2.mapper",
            "huaminglin.demo.spring.mybatis.h2.transaction");
    }

    private static void refreshMyContext(AnnotationConfigApplicationContext context) {
        context.refresh();
    }

    private static StudentDao getStudentDao(AnnotationConfigApplicationContext context) {
        return context.getBean(StudentDao.class);
    }

    private static void printStudents(StudentDao dao) {
        List<Student> students = dao.getStudents("name1");
        System.out.println("Students: " + students);
    }

    private static void addOneAge(StudentDao dao) {
        dao.addOneAge("name1");
    }

    private static void tryAndRollbackAddOneAge(StudentDao dao) {
        dao.tryAndRollbackAddOneAge("name1");
    }

    private static void tryAndRollbackWithRuntimeExceptionAddOneAge(StudentDao dao) {
        try {
            dao.tryAndRollbackWithRuntimeExceptionAddOneAge("name1");
        } catch (Exception e) {
        }
    }

    private static void tryAndRollbackWithExceptionAddOneAge(StudentDao dao) {
        try {
            dao.tryAndRollbackWithExceptionAddOneAge("name1");
        } catch (Exception e) {
        }
    }

    private static void closeMyContext(AnnotationConfigApplicationContext context) {
        context.close();
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = createMyContext();
        scanMyBean(context);
        refreshMyContext(context);

        StudentDao dao = getStudentDao(context);
        System.out.println("getStudentDao: " + dao.getClass() + "/" + dao.toString());

        printStudents(dao);

        if (args.length > 0) {// Use args == 0 to learn the transaction initialization logic only.
            addOneAge(dao);

            printStudents(dao);

            tryAndRollbackAddOneAge(dao);

            printStudents(dao);

            tryAndRollbackWithRuntimeExceptionAddOneAge(dao); // Rollback successfully with RuntimeException

            tryAndRollbackWithExceptionAddOneAge(dao); // Rollback doesn't work with checked Exception

            printStudents(dao);
        }

        closeMyContext(context);
    }
}

