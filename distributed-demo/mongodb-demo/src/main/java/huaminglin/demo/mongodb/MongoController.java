package huaminglin.demo.mongodb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MongoController {

  @Autowired
  private MongoTemplate mongoTemplate;

  @GetMapping(value = "/")
  public String index() {
    return "redirect:/find";
  }

  @GetMapping(value = "/insert")
  public String insert() {
    Address address1 = new Address("Beijing");
    mongoTemplate.insert(address1);

    Address address2 = new Address("Shang");
    mongoTemplate.insert(address2);

    ArrayList<Address> addresses = new ArrayList<Address>();
    addresses.add(address1);
    addresses.add(address2);

    Student student = new Student("Mary", 18L, "female", new Date(), addresses);

    mongoTemplate.insert(student);
    return "redirect:/find";
  }

  @GetMapping(value = "/save")
  public String save() {
    Sort sort = Sort.by("sex");
    Student student = mongoTemplate
        .findOne(Query.query(Criteria.where("")).with(sort), Student.class);
    student.setAge(28L);
    mongoTemplate.save(student);
    return "redirect:/find";
  }

  @GetMapping(value = "/delete")
  public String delete() {
    Sort sort = Sort.by("sex");
    Student student = mongoTemplate
        .findOne(Query.query(Criteria.where("")).with(sort), Student.class);
    List<Address> addresses = student.getAddresses();
    for (Address address : addresses) {
      mongoTemplate.remove(address);
    }
    mongoTemplate.remove(student);

    return "redirect:/find";
  }

  @GetMapping(value = "/update")
  public String update() {
    Query query = new Query();
    Update update = Update.update("sex_", "male");
    mongoTemplate.updateMulti(query, update, Student.class);
    return "redirect:/find";
  }

  @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Student> find() {
    Sort sort = Sort.by("sex");
    return mongoTemplate
        .find(Query.query(Criteria.where("birthday").lt(new Date())).with(sort), Student.class);
  }
}
