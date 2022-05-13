package huaminglin.demo.jpa;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;



  public void addUser(String name) {
    MyUser user = new MyUser();
    user.setName(name);
    userRepository.save(user);
  }

  @Transactional
  public void addUser(boolean rollback, String name) {
    addUser(name + "-01");
    addUser(name + "-02");
    if (rollback) {
      throw new RuntimeException("Rollback trigger");
    }
  }
}
