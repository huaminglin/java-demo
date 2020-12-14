package huaminglin.demo.spring.typeconversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MyConverter implements Converter<String, MyAge> {
    @Override
    public MyAge convert(String source) {
        System.out.println("huaminglin.demo.spring.typeconversion.MyConverter#convert");
        MyAge age = new MyAge();
        age.setAge(source);
        return age;
    }
}
