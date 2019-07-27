package huaminglin.demo.spring.mybatis.h2.mapper;

import huaminglin.demo.spring.mybatis.h2.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    List<Student> getStudents(@Param("name") String name);
    int updateAge(@Param("name") String name, @Param("age") Integer age);
}

