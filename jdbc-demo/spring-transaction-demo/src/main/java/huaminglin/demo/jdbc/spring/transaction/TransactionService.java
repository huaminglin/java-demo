package huaminglin.demo.jdbc.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Transactional
  public void sql1() {
    System.out.println("sql1()");
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      System.out.println(result);
    }
  }

  @Transactional
  public void sql2() {
    System.out.println("sql2()");
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      System.out.println(result);
    }
  }

  @Transactional
  public void sql3() {
    System.out.println("sql3()");
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      System.out.println(result);
    }
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      System.out.println(result);
    }
  }

  public void sql4() {
    System.out.println("sql4()");
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      System.out.println(result);
    }
    {
      Long result = jdbcTemplate.queryForObject(
          "SELECT txid_current()", Long.class);
      System.out.println(result);
    }
  }

  @Transactional
  public void sql5() {
    System.out.println("sql5()");
    sql1();
    sql2();
  }
}
