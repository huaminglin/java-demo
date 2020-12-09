package huaminglin.demo.spring.typeconversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MyConverter implements Converter<String, MyName> {
    @Override
    public MyName convert(String source) {
        return new MyName(source);
    }
}
