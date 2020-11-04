package huaminglin.demo.spring.mybatis.h2.mapper;

import huaminglin.demo.spring.mybatis.h2.Student;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

  List<Student> getStudents(@Param("name") String name);

  int updateAge(@Param("name") String name, @Param("age") Integer age);
}

