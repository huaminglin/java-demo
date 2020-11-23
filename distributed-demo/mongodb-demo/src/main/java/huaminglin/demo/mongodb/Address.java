package huaminglin.demo.mongodb;

import java.io.Serializable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ex_entity_address")
public class Address implements Serializable {

  @Id
  private ObjectId id;

  @Indexed
  private String value;

  public Address() {

  }

  public Address(String value) {
    this.value = value;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
