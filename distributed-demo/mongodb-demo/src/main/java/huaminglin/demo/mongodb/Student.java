package huaminglin.demo.mongodb;

import static org.springframework.data.mongodb.core.index.IndexDirection.DESCENDING;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ex_entity_student")
@CompoundIndexes({
    @CompoundIndex(name = "compound_index", def = "{'age': 1, 'name': -1}")
})

public class Student implements Serializable {

  @Id
  private ObjectId id;

  @Indexed(direction = DESCENDING)
  private String name;

  private Long age;

  @Field("sex_")
  private String sex;

  private Date birthday;

  @DBRef
  private List<Address> addresses;

  @Transient
  private boolean active;

  public Student() {

  }

  @PersistenceConstructor
  public Student(String name, Long age, String sex, Date birthday, List<Address> addresses) {
    this.age = age;
    this.sex = sex;
    this.name = name;
    this.birthday = birthday;
    this.addresses = addresses;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

}
