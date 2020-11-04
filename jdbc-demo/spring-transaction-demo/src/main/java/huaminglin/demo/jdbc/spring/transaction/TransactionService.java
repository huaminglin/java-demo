package huaminglin.demo.jdbc.spring.transaction;

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
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      logger.info("sql1(): " + result);
    }
  }

  @Transactional
  public void sql2() {
    logger.info("sql2()");
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      logger.info("sql2(): " + result);
    }
  }

  @Transactional
  public void sql3() {
    logger.info("sql3()");
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      logger.info("sql3(): " + result);
    }
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      logger.info("sql3(): " + result);
    }
  }

  public void sql4() {
    logger.info("sql4()");
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      logger.info("sql4(): " + result);
    }
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      logger.info("sql4(): " + result);
    }
  }

  @Transactional
  public void sql5() {
    logger.info("sql5()");
    sql1();
    sql2();
  }
}
