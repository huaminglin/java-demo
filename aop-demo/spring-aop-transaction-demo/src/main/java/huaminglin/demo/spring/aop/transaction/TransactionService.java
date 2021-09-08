package huaminglin.demo.spring.aop.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

  private static Logger logger = LoggerFactory.getLogger(TransactionService.class);
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Transactional
  public void sql1() {
    logger.info("sql1()");
    {
      String result = jdbcTemplate.queryForObject(
          "SELECT 'abc'", String.class);
      logger.info("sql1(): {}", result);
    }
  }

  @Transactional
  public void sql2() {
    logger.info("sql2()");
    {
      String result = jdbcTemplate.queryForObject(
          "SELECT 'xyz'", String.class);
      logger.info("sql2(): {}", result);
    }
  }

}
