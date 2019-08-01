package huaminglin.demo.spring.mybatis.h2.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import huaminglin.demo.spring.mybatis.h2.Student;
import huaminglin.demo.spring.mybatis.h2.mapper.StudentMapper;



@Component
public class StudentDao {
    @Autowired
    private StudentMapper mapper;

    @Transactional
    public void tryAndRollbackAddOneAge(String name) {
        addOneAge(name);
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    @Transactional
    public void tryAndRollbackWithRuntimeExceptionAddOneAge(String name) throws Exception {
        addOneAge(name);
        throw new Exception("Force roll back by RuntimeException");
    }

    @Transactional
    public void tryAndRollbackWithExceptionAddOneAge(String name) {
        addOneAge(name);
        throw new RuntimeException("Force roll back by Exception");
    }

    @Transactional
    public void addOneAge(String name) {
        List<Student> students = getStudents(name);
        mapper.updateAge(name, students.get(0).getAge() + 1);
    }

    @Transactional
    public List<Student> getStudents(String name) {
        return mapper.getStudents(name);
    }

}

