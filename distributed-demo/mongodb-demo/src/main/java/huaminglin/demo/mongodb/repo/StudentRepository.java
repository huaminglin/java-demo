package huaminglin.demo.mongodb.repo;

import huaminglin.demo.mongodb.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
