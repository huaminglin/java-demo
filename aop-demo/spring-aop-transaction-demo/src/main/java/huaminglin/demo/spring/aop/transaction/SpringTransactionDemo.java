package huaminglin.demo.spring.aop.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SpringTransactionDemo {

  private static final Logger logger = LoggerFactory.getLogger(SpringTransactionDemo.class);

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication
        .run(SpringTransactionDemo.class, args);
    TransactionService transactionService = context.getBean(TransactionService.class);
    transactionService.sql1();
    logger.info("Main Thread exits: {}, {}", Thread.currentThread().getId(), Thread.currentThread()
        .getName());
  }

}
