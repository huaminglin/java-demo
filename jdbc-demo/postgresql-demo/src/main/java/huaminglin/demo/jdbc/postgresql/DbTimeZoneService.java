package huaminglin.demo.jdbc.postgresql;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DbTimeZoneService {
  private static Logger logger = LoggerFactory.getLogger(DbTimeZoneService.class);
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private void timezone() {
    {
      String result = jdbcTemplate.queryForObject(
          "show time zone", String.class);
      logger.info("show time zone: " + result);
    }
    {
      Date result = jdbcTemplate.queryForObject(
          "SELECT current_timestamp", Date.class);
      logger.info("current_timestamp -> Date: " + result.toGMTString());
    }
    {
      Instant result = jdbcTemplate.queryForObject(
          "SELECT current_timestamp", Instant.class);
      logger.info("current_timestamp -> Instant: " + result);
    }
    {
      LocalDateTime result = jdbcTemplate.queryForObject(
          "SELECT current_timestamp", LocalDateTime.class);
      logger.info("current_timestamp -> LocalDateTime: " + result);
    }

    {
      Date result = jdbcTemplate.queryForObject(
          "SELECT localtimestamp", Date.class);
      logger.info("localtimestamp -> Date: " + result.toGMTString());
    }
    {
      Instant result = jdbcTemplate.queryForObject(
          "SELECT localtimestamp", Instant.class);
      logger.info("localtimestamp -> Instant: " + result);
    }
    {
      LocalDateTime result = jdbcTemplate.queryForObject(
          "SELECT localtimestamp", LocalDateTime.class);
      logger.info("localtimestamp -> LocalDateTime: " + result);
    }
  }

  @Transactional
  public void jvm_timezone() {
    timezone();
  }

  @Transactional
  public void session_timezone() {
    {
      jdbcTemplate.execute("set timezone to 'UTC'");
      logger.info("set timezone to 'UTC'");
      timezone();
    }
  }

  @Transactional
  public void insertRows(String timezone) {
    if (timezone != null) {
      jdbcTemplate.execute("set timezone to '" + timezone + "'");
      logger.info("set timezone to " + timezone);
    }
    {
      String result = jdbcTemplate.queryForObject(
          "show time zone", String.class);
      logger.info("show time zone: " + result);
    }
    Date instant = new Date();
    jdbcTemplate.update("insert into tstz values(?, ?)", instant, instant);
  }

}
