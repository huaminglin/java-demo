package huaminglin.demo.jpa;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<MyUser,Long> {

}
