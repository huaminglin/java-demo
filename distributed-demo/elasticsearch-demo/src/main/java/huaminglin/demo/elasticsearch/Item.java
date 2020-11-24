package huaminglin.demo.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "item",type = "docs", shards = 1, replicas = 0)
public class Item {
  @Id
  private String id;

  @Field(type = FieldType.Text, analyzer = "ik_max_word")
  private String title;

  @Field(type = FieldType.Keyword)
  private String category;

  @Field(type = FieldType.Keyword)
  private String brand;

  @Field(type = FieldType.Double)
  private Double price;

  @Field(index = false, type = FieldType.Keyword)
  private String images;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getImages() {
    return images;
  }

  public void setImages(String images) {
    this.images = images;
  }
}
