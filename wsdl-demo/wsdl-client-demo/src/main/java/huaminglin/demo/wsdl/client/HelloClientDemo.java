package huaminglin.demo.wsdl.client;

import com.sun.xml.ws.developer.JAXWSProperties;
import huaminglin.demo.wsdl.client.services.HelloProvider;
import huaminglin.demo.wsdl.client.services.HelloProviderService;
import huaminglin.demo.wsdl.server.HelloRequest;
import huaminglin.demo.wsdl.server.HelloResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;

public class HelloClientDemo {

  public static URL getLocalWsdl(Class<? extends Service> serviceClass) {
    WebServiceClient webServiceClient = serviceClass.getAnnotation(WebServiceClient.class);
    String wsdlLocation = webServiceClient.wsdlLocation();
    System.out.println("WSDL:" + wsdlLocation);
    return HelloClientDemo.class.getClassLoader().getResource(wsdlLocation);
  }

  public static QName getQName(Class<? extends Service> serviceClass) {
    WebServiceClient webServiceClient = serviceClass.getAnnotation(WebServiceClient.class);
    String targetNamespace = webServiceClient.targetNamespace();
    String name = webServiceClient.name();
    return new QName(targetNamespace, name);
  }

  public static void initRequestContext(BindingProvider bindingProvider, String url) {
    Map<String, Object> ctx = bindingProvider.getRequestContext();
    if (url != null) {
      ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
    }
    ctx.put(BindingProvider.USERNAME_PROPERTY, "user");
    ctx.put(BindingProvider.PASSWORD_PROPERTY, "password");
    ctx.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
    ctx.put(JAXWSProperties.CONNECT_TIMEOUT, 15 * 1000);
    ctx.put(JAXWSProperties.REQUEST_TIMEOUT, 15 * 1000);
  }

  public static void main(String[] args) throws MalformedURLException {
    HelloProvider port;
    QName qName = getQName(HelloProviderService.class);
    if ("local".equals(System.getProperty("wsdl.location"))) {
      URL url = getLocalWsdl(HelloProviderService.class);
      System.out.println("WSDL:" + url);
      port = new HelloProviderService(url, qName).getHelloProviderPort();
      initRequestContext((BindingProvider) port, "http://127.0.0.1:9999/hello/helloProvider");
    } else {
      URL url = new URL("http://127.0.0.1:9999/hello/helloProvider");
      port = new HelloProviderService(url, qName).getHelloProviderPort();
      initRequestContext((BindingProvider) port, null);
    }
    HelloRequest request = new HelloRequest();
    request.setName("name1");
    HelloResponse response = port.sayHi(request);
    System.out.println(response.getMessage());
  }
}
