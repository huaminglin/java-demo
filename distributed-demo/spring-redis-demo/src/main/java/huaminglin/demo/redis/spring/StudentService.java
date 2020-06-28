package huaminglin.demo.redis.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
  @Autowired
  private StudentRepository studentRepository;

  public void save(String id, String name) {
    Student student;
    if (studentRepository.existsById(id)) {
      student = studentRepository.findById(id).get();
    } else {
      System.out.println("Create a new student.");
      student = new Student();
      student.setId(id);
      student.setName(name);
    }
    studentRepository.save(student);
  }
}
