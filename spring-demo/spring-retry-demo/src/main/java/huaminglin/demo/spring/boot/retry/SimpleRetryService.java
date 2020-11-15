package huaminglin.demo.spring.boot.retry;


import org.springframework.stereotype.Service;

@Service
public class SimpleRetryService implements RetryService {

  @Override
  public void retryService() throws MyRetryException {
    System.out.println("huaminglin.demo.spring.boot.SimpleRetryService#retryService");
    throw new MyRetryException();
  }
}
