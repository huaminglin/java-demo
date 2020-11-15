package huaminglin.demo.spring.boot.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface RetryService {
  @Retryable( value = MyRetryException.class, maxAttempts = 3, backoff = @Backoff(delay = 100))
  void retryService() throws MyRetryException;
}
