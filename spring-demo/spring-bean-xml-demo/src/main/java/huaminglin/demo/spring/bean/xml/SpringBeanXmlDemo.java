package huaminglin.demo.spring.bean.xml;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class SpringBeanXmlDemo {

  public static void main(String[] args) {
    DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
    XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
    ClassPathResource resource = new ClassPathResource("applicationContext.xml");
    reader.loadBeanDefinitions(resource);
    MyBean bean = (MyBean) factory.getBean("mybean");
    System.out.println(bean.getName());
  }

}
