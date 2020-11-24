package huaminglin.demo.mongodb.repo;

import huaminglin.demo.mongodb.Address;
import huaminglin.demo.mongodb.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/repo")
public class MongoRepositoryController {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private AddressRepository addressRepository;

  @GetMapping(value = "/")
  public String index() {
    return "redirect:/find";
  }

  @GetMapping(value = "/insert")
  public String insert() {
    Address address1 = new Address("Beijing");
    addressRepository.insert(address1);

    Address address2 = new Address("Shang");
    addressRepository.insert(address2);

    ArrayList<Address> addresses = new ArrayList<Address>();
    addresses.add(address1);
    addresses.add(address2);

    Student student = new Student("Mary", 18L, "female", new Date(), addresses);

    studentRepository.insert(student);
    return "redirect:/find";
  }

  @GetMapping(value = "/save")
  public String save() {
    Student student = studentRepository.findAll().get(0);
    student.setAge(28L);
    studentRepository.save(student);
    return "redirect:/find";
  }

  @GetMapping(value = "/delete")
  public String delete() {
    Student student = studentRepository.findAll().get(0);
    List<Address> addresses = student.getAddresses();
    for (Address address : addresses) {
      addressRepository.delete(address);
    }
    studentRepository.delete(student);

    return "redirect:/find";
  }

  @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Student> find() {
    Sort sort = Sort.by("sex");
    return studentRepository.findAll();
  }
}
