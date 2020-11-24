package huaminglin.demo.elasticsearch;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ElasticsearchController {

  @Autowired
  private ItemRepository itemRepository;

  @GetMapping(value = "/")
  public String index() {
    return "redirect:/find";
  }

  @GetMapping(value = "/insert")
  @ResponseBody
  public String insert() {
    Item item = new Item();
    item.setBrand("brand");
    item.setCategory("category");
    item.setImages("images");
    item.setPrice(1.2);
    item.setTitle("title");
    itemRepository.save(item);
    return "success";
  }

  @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Item> find() {
    List<Item> list = new ArrayList<>();
    itemRepository.findAll().iterator().forEachRemaining(list::add);
    return list;
  }
}
