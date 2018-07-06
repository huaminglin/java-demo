package huaminglin.demo.spring.mvc;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomValueHttpMessageConverter extends AbstractHttpMessageConverter<CustomValue> {
    public CustomValueHttpMessageConverter() {
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(supportedMediaTypes);
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return CustomValue.class.isAssignableFrom(aClass);
    }

    @Override
    protected CustomValue readInternal(Class<? extends CustomValue> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void writeInternal(CustomValue customValue, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String value = "CustomValueHttpMessageConverter: " + customValue.getValue();
        httpOutputMessage.getBody().write(value.getBytes());
    }
}
