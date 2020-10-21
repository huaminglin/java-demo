# Demo does JDBC handle TIMESTAMPTZ AND TIMESTAMP

## PostgreSQL JDBC timezone

```
17:46:28.778 [main] INFO  h.demo.jdbc.postgresql.DbService - show time zone: Asia/Harbin
17:46:28.780 [main] INFO  h.demo.jdbc.postgresql.DbService - current_timestamp -> Date: 12 Jul 2020 09:46:28 GMT
17:46:28.784 [main] INFO  h.demo.jdbc.postgresql.DbService - current_timestamp -> Instant: 2020-07-12T09:46:28.773102Z
17:46:28.786 [main] INFO  h.demo.jdbc.postgresql.DbService - current_timestamp -> LocalDateTime: 2020-07-12T17:46:28.773102
17:46:28.787 [main] INFO  h.demo.jdbc.postgresql.DbService - localtimestamp -> Date: 12 Jul 2020 09:46:28 GMT
17:46:28.788 [main] INFO  h.demo.jdbc.postgresql.DbService - localtimestamp -> Instant: 2020-07-12T09:46:28.773102Z
17:46:28.788 [main] INFO  h.demo.jdbc.postgresql.DbService - localtimestamp -> LocalDateTime: 2020-07-12T17:46:28.773102
17:46:28.794 [main] INFO  h.demo.jdbc.postgresql.DbService - set timezone to 'UTC'
17:46:28.795 [main] INFO  h.demo.jdbc.postgresql.DbService - show time zone: UTC
17:46:28.796 [main] INFO  h.demo.jdbc.postgresql.DbService - current_timestamp -> Date: 12 Jul 2020 09:46:28 GMT
17:46:28.796 [main] INFO  h.demo.jdbc.postgresql.DbService - current_timestamp -> Instant: 2020-07-12T09:46:28.794486Z
17:46:28.797 [main] INFO  h.demo.jdbc.postgresql.DbService - current_timestamp -> LocalDateTime: 2020-07-12T17:46:28.794486
17:46:28.797 [main] INFO  h.demo.jdbc.postgresql.DbService - localtimestamp -> Date: 12 Jul 2020 01:46:28 GMT
17:46:28.798 [main] INFO  h.demo.jdbc.postgresql.DbService - localtimestamp -> Instant: 2020-07-12T01:46:28.794486Z
17:46:28.798 [main] INFO  h.demo.jdbc.postgresql.DbService - localtimestamp -> LocalDateTime: 2020-07-12T09:46:28.794486
```


"17:46:28.797 [main] INFO  h.demo.jdbc.postgresql.DbService - localtimestamp -> Date: 12 Jul 2020 01:46:28 GMT":

The session timezone is UTC, so the value in the result is "2020-07-12 09:46:28.794486".

JDBC uses system timezone to parse "2020-07-12 09:46:28.794486" to Timestamp (in UTC).

Conclusion:

From the PostgreSQL server's perspective, session timezone matters.

1) "SELECT current_timestamp", the TCP package in the response:

'2020-07-12 17:46:28.773102+08' and the oid 1184 in the package

org.postgresql.core.Oid: public static final int TIMESTAMPTZ = 1184;

2) "SELECT localtimestamp", the TCP package in the response:

'2020-07-12 17:46:28.773102' and the oid 1114 in the package

org.postgresql.core.Oid: public static final int TIMESTAMP = 1114;

From the JDBC's perspective, JVM timezone matters.

JVM's timezone is used to generate "2020-07-12 22:43:33.114+08" in INSERT SQL.

JVM's timezone is used to parse string into java.sql.Timestamp.

## JVM timezone: -Duser.timezone="Europe/London"

## How does PostgreSQL driver parse the timestamp field value into java.sql.Timestamp

```
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.postgresql.jdbc.PgResultSet.getTimestamp(PgResultSet.java:560)
	  at org.postgresql.jdbc.PgResultSet.getTimestamp(PgResultSet.java:2414)
	  at org.springframework.jdbc.support.JdbcUtils.getResultSetValue(JdbcUtils.java:234)
	  at org.springframework.jdbc.core.SingleColumnRowMapper.getColumnValue(SingleColumnRowMapper.java:149)
	  at org.springframework.jdbc.core.SingleColumnRowMapper.mapRow(SingleColumnRowMapper.java:114)
	  at org.springframework.jdbc.core.RowMapperResultSetExtractor.extractData(RowMapperResultSetExtractor.java:94)
	  at org.springframework.jdbc.core.RowMapperResultSetExtractor.extractData(RowMapperResultSetExtractor.java:61)
	  at org.springframework.jdbc.core.JdbcTemplate$1QueryStatementCallback.doInStatement(JdbcTemplate.java:439)
	  at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:375)
	  at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:451)
	  at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:461)
	  at org.springframework.jdbc.core.JdbcTemplate.queryForObject(JdbcTemplate.java:472)
	  at org.springframework.jdbc.core.JdbcTemplate.queryForObject(JdbcTemplate.java:479)
	  at huaminglin.demo.jdbc.postgresql.DbTimeZoneService.pg_now(DbService.java:19)
	  at huaminglin.demo.jdbc.postgresql.PostgresqlDemo.main(PostgresqlDemo.java:44)
```

org.postgresql.jdbc.PgResultSet.getTimestamp(int, java.util.Calendar)

```

  @Override
  public Timestamp getTimestamp(int i, java.util.Calendar cal) throws SQLException {
    checkResultSet(i);
    if (wasNullFlag) {
      return null;
    }

    if (cal == null) {
      cal = getDefaultCalendar();
    }
    int col = i - 1;
    int oid = fields[col].getOID();
    if (isBinary(i)) {
      if (oid == Oid.TIMESTAMPTZ || oid == Oid.TIMESTAMP) {
        boolean hasTimeZone = oid == Oid.TIMESTAMPTZ;
        TimeZone tz = cal.getTimeZone();
        return connection.getTimestampUtils().toTimestampBin(tz, thisRow[col], hasTimeZone);
      } else {
        // JDBC spec says getTimestamp of Time and Date must be supported
        long millis;
        if (oid == Oid.TIME || oid == Oid.TIMETZ) {
          millis = getTime(i, cal).getTime();
        } else if (oid == Oid.DATE) {
          millis = getDate(i, cal).getTime();
        } else {
          throw new PSQLException(
              GT.tr("Cannot convert the column of type {0} to requested type {1}.",
                  Oid.toString(oid), "timestamp"),
              PSQLState.DATA_TYPE_MISMATCH);
        }
        return new Timestamp(millis);
      }
    }

    // If this is actually a timestamptz, the server-provided timezone will override
    // the one we pass in, which is the desired behaviour. Otherwise, we'll
    // interpret the timezone-less value in the provided timezone.
    String string = getString(i);
    if (oid == Oid.TIME || oid == Oid.TIMETZ) {
      // If server sends us a TIME, we ensure java counterpart has date of 1970-01-01
      return new Timestamp(connection.getTimestampUtils().toTime(cal, string).getTime());
    }
    return connection.getTimestampUtils().toTimestamp(cal, string);
  }
```

org.postgresql.jdbc.TimestampUtils

```
  public synchronized Timestamp toTimestamp(Calendar cal, String s) throws SQLException {
    if (s == null) {
      return null;
    }

    int slen = s.length();

    // convert postgres's infinity values to internal infinity magic value
    if (slen == 8 && s.equals("infinity")) {
      return new Timestamp(PGStatement.DATE_POSITIVE_INFINITY);
    }

    if (slen == 9 && s.equals("-infinity")) {
      return new Timestamp(PGStatement.DATE_NEGATIVE_INFINITY);
    }

    ParsedTimestamp ts = parseBackendTimestamp(s);
    Calendar useCal = ts.tz != null ? ts.tz : setupCalendar(cal);
    useCal.set(Calendar.ERA, ts.era);
    useCal.set(Calendar.YEAR, ts.year);
    useCal.set(Calendar.MONTH, ts.month - 1);
    useCal.set(Calendar.DAY_OF_MONTH, ts.day);
    useCal.set(Calendar.HOUR_OF_DAY, ts.hour);
    useCal.set(Calendar.MINUTE, ts.minute);
    useCal.set(Calendar.SECOND, ts.second);
    useCal.set(Calendar.MILLISECOND, 0);

    Timestamp result = new Timestamp(useCal.getTimeInMillis());
    result.setNanos(ts.nanos);
    return result;
  }

  private Calendar setupCalendar(Calendar cal) {
    TimeZone timeZone = cal == null ? null : cal.getTimeZone();
    return getSharedCalendar(timeZone);
  }

  public Calendar getSharedCalendar(TimeZone timeZone) {
    if (timeZone == null) {
      timeZone = getDefaultTz();
    }
    Calendar tmp = calendarWithUserTz;
    tmp.setTimeZone(timeZone);
    return tmp;
  }

  private TimeZone getDefaultTz() {
    // Fast path to getting the default timezone.
    if (DEFAULT_TIME_ZONE_FIELD != null) {
      try {
        TimeZone defaultTimeZone = (TimeZone) DEFAULT_TIME_ZONE_FIELD.get(null);
        if (defaultTimeZone == prevDefaultZoneFieldValue) {
          return defaultTimeZoneCache;
        }
        prevDefaultZoneFieldValue = defaultTimeZone;
      } catch (Exception e) {
        // If this were to fail, fallback on slow method.
      }
    }
    TimeZone tz = TimeZone.getDefault();
    defaultTimeZoneCache = tz;
    return tz;
  }
```

Note:

From "Calendar useCal = ts.tz != null ? ts.tz : setupCalendar(cal)" we know that:

calendar from the result > calendar from the parameter > system default calendar

## How does Spring framework convert java.sql.Timestamp into target class

org.springframework.core.convert.support.GenericConversionService.Converters.converters

```
{GenericConverter$ConvertiblePair@5104} "java.lang.Number -> java.lang.Number" -> {GenericConversionService$ConvertersForPair@5105} "java.lang.Number -> java.lang.Number : org.springframework.core.convert.support.NumberToNumberConverterFactory@6ceb7b5e"
{GenericConverter$ConvertiblePair@5106} "java.lang.String -> java.lang.Number" -> {GenericConversionService$ConvertersForPair@5107} "java.lang.String -> java.lang.Number : org.springframework.core.convert.support.StringToNumberConverterFactory@7dd00705"
{GenericConverter$ConvertiblePair@5108} "java.lang.Number -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5109} "java.lang.Number -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@f14e5bf"
{GenericConverter$ConvertiblePair@5110} "java.lang.String -> java.lang.Character" -> {GenericConversionService$ConvertersForPair@5111} "java.lang.String -> java.lang.Character : org.springframework.core.convert.support.StringToCharacterConverter@d176a31"
{GenericConverter$ConvertiblePair@5112} "java.lang.Character -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5113} "java.lang.Character -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@3a91d146"
{GenericConverter$ConvertiblePair@5114} "java.lang.Number -> java.lang.Character" -> {GenericConversionService$ConvertersForPair@5115} "java.lang.Number -> java.lang.Character : org.springframework.core.convert.support.NumberToCharacterConverter@4784013e"
{GenericConverter$ConvertiblePair@5116} "java.lang.Character -> java.lang.Number" -> {GenericConversionService$ConvertersForPair@5117} "java.lang.Character -> java.lang.Number : org.springframework.core.convert.support.CharacterToNumberFactory@6f952d6c"
{GenericConverter$ConvertiblePair@5118} "java.lang.String -> java.lang.Boolean" -> {GenericConversionService$ConvertersForPair@5119} "java.lang.String -> java.lang.Boolean : org.springframework.core.convert.support.StringToBooleanConverter@5965844d"
{GenericConverter$ConvertiblePair@5120} "java.lang.Boolean -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5121} "java.lang.Boolean -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@6d4a65c6"
{GenericConverter$ConvertiblePair@5122} "java.lang.String -> java.lang.Enum" -> {GenericConversionService$ConvertersForPair@5123} "java.lang.String -> java.lang.Enum : org.springframework.core.convert.support.StringToEnumConverterFactory@aa004a0"
{GenericConverter$ConvertiblePair@5124} "java.lang.Enum -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5125} "java.lang.Enum -> java.lang.String : org.springframework.core.convert.support.EnumToStringConverter@4c98a6d5"
{GenericConverter$ConvertiblePair@5126} "java.lang.Integer -> java.lang.Enum" -> {GenericConversionService$ConvertersForPair@5127} "java.lang.Integer -> java.lang.Enum : org.springframework.core.convert.support.IntegerToEnumConverterFactory@392a04e7"
{GenericConverter$ConvertiblePair@5128} "java.lang.Enum -> java.lang.Integer" -> {GenericConversionService$ConvertersForPair@5129} "java.lang.Enum -> java.lang.Integer : org.springframework.core.convert.support.EnumToIntegerConverter@7f02251"
{GenericConverter$ConvertiblePair@5130} "java.lang.String -> java.util.Locale" -> {GenericConversionService$ConvertersForPair@5131} "java.lang.String -> java.util.Locale : org.springframework.core.convert.support.StringToLocaleConverter@dffa30b"
{GenericConverter$ConvertiblePair@5132} "java.util.Locale -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5133} "java.util.Locale -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@4d8126f"
{GenericConverter$ConvertiblePair@5134} "java.lang.String -> java.nio.charset.Charset" -> {GenericConversionService$ConvertersForPair@5135} "java.lang.String -> java.nio.charset.Charset : org.springframework.core.convert.support.StringToCharsetConverter@6d3c232f"
{GenericConverter$ConvertiblePair@5136} "java.nio.charset.Charset -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5137} "java.nio.charset.Charset -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@6b587673"
{GenericConverter$ConvertiblePair@5138} "java.lang.String -> java.util.Currency" -> {GenericConversionService$ConvertersForPair@5139} "java.lang.String -> java.util.Currency : org.springframework.core.convert.support.StringToCurrencyConverter@1bcf67e8"
{GenericConverter$ConvertiblePair@5142} "java.util.Currency -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5143} "java.util.Currency -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@5f404594"
{GenericConverter$ConvertiblePair@5144} "java.lang.String -> java.util.Properties" -> {GenericConversionService$ConvertersForPair@5145} "java.lang.String -> java.util.Properties : org.springframework.core.convert.support.StringToPropertiesConverter@53692008"
{GenericConverter$ConvertiblePair@5146} "java.util.Properties -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5147} "java.util.Properties -> java.lang.String : org.springframework.core.convert.support.PropertiesToStringConverter@7b2a3ff8"
{GenericConverter$ConvertiblePair@5148} "java.lang.String -> java.util.UUID" -> {GenericConversionService$ConvertersForPair@5149} "java.lang.String -> java.util.UUID : org.springframework.core.convert.support.StringToUUIDConverter@1bbae752"
{GenericConverter$ConvertiblePair@5140} "java.util.UUID -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5141} "java.util.UUID -> java.lang.String : org.springframework.core.convert.support.ObjectToStringConverter@460b6d54"
{GenericConverter$ConvertiblePair@5150} "[Ljava.lang.Object; -> java.util.Collection" -> {GenericConversionService$ConvertersForPair@5151} "org.springframework.core.convert.support.ArrayToCollectionConverter@5cf87cfd"
{GenericConverter$ConvertiblePair@5152} "java.util.Collection -> [Ljava.lang.Object;" -> {GenericConversionService$ConvertersForPair@5153} "org.springframework.core.convert.support.CollectionToArrayConverter@76075d65"
{GenericConverter$ConvertiblePair@5154} "[Ljava.lang.Object; -> [Ljava.lang.Object;" -> {GenericConversionService$ConvertersForPair@5155} "org.springframework.core.convert.support.ArrayToArrayConverter@3a4ba480"
{GenericConverter$ConvertiblePair@5156} "java.util.Collection -> java.util.Collection" -> {GenericConversionService$ConvertersForPair@5157} "org.springframework.core.convert.support.CollectionToCollectionConverter@27b71f50"
{GenericConverter$ConvertiblePair@5158} "java.util.Map -> java.util.Map" -> {GenericConversionService$ConvertersForPair@5159} "org.springframework.core.convert.support.MapToMapConverter@383790cf"
{GenericConverter$ConvertiblePair@5160} "[Ljava.lang.Object; -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5161} "org.springframework.core.convert.support.ArrayToStringConverter@74971ed9"
{GenericConverter$ConvertiblePair@5162} "java.lang.String -> [Ljava.lang.Object;" -> {GenericConversionService$ConvertersForPair@5163} "org.springframework.core.convert.support.StringToArrayConverter@131fcb6f"
{GenericConverter$ConvertiblePair@5164} "[Ljava.lang.Object; -> java.lang.Object" -> {GenericConversionService$ConvertersForPair@5165} "org.springframework.core.convert.support.ArrayToObjectConverter@ccd1bc3"
{GenericConverter$ConvertiblePair@5166} "java.lang.Object -> [Ljava.lang.Object;" -> {GenericConversionService$ConvertersForPair@5167} "org.springframework.core.convert.support.ObjectToArrayConverter@878537d"
{GenericConverter$ConvertiblePair@5168} "java.util.Collection -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5169} "org.springframework.core.convert.support.CollectionToStringConverter@4455f57d"
{GenericConverter$ConvertiblePair@5170} "java.lang.String -> java.util.Collection" -> {GenericConversionService$ConvertersForPair@5171} "org.springframework.core.convert.support.StringToCollectionConverter@29fc1a2b"
{GenericConverter$ConvertiblePair@5172} "java.util.Collection -> java.lang.Object" -> {GenericConversionService$ConvertersForPair@5173} "org.springframework.core.convert.support.CollectionToObjectConverter@4d0b0fd4"
{GenericConverter$ConvertiblePair@5174} "java.lang.Object -> java.util.Collection" -> {GenericConversionService$ConvertersForPair@5175} "org.springframework.core.convert.support.ObjectToCollectionConverter@7a24eb3"
{GenericConverter$ConvertiblePair@5176} "java.util.stream.Stream -> [Ljava.lang.Object;" -> {GenericConversionService$ConvertersForPair@5177} "org.springframework.core.convert.support.StreamConverter@6c37bd27"
{GenericConverter$ConvertiblePair@5178} "java.util.stream.Stream -> java.util.Collection" -> {GenericConversionService$ConvertersForPair@5179} "org.springframework.core.convert.support.StreamConverter@6c37bd27"
{GenericConverter$ConvertiblePair@5180} "java.util.Collection -> java.util.stream.Stream" -> {GenericConversionService$ConvertersForPair@5181} "org.springframework.core.convert.support.StreamConverter@6c37bd27"
{GenericConverter$ConvertiblePair@5182} "[Ljava.lang.Object; -> java.util.stream.Stream" -> {GenericConversionService$ConvertersForPair@5183} "org.springframework.core.convert.support.StreamConverter@6c37bd27"
{GenericConverter$ConvertiblePair@5184} "[B -> java.nio.ByteBuffer" -> {GenericConversionService$ConvertersForPair@5185} "org.springframework.core.convert.support.ByteBufferConverter@25d3cfc8"
{GenericConverter$ConvertiblePair@5186} "java.lang.Object -> java.nio.ByteBuffer" -> {GenericConversionService$ConvertersForPair@5187} "org.springframework.core.convert.support.ByteBufferConverter@25d3cfc8"
{GenericConverter$ConvertiblePair@5188} "java.nio.ByteBuffer -> [B" -> {GenericConversionService$ConvertersForPair@5189} "org.springframework.core.convert.support.ByteBufferConverter@25d3cfc8"
{GenericConverter$ConvertiblePair@5190} "java.nio.ByteBuffer -> java.lang.Object" -> {GenericConversionService$ConvertersForPair@5191} "org.springframework.core.convert.support.ByteBufferConverter@25d3cfc8"
{GenericConverter$ConvertiblePair@5192} "java.lang.String -> java.util.TimeZone" -> {GenericConversionService$ConvertersForPair@5193} "java.lang.String -> java.util.TimeZone : org.springframework.core.convert.support.StringToTimeZoneConverter@30331109"
{GenericConverter$ConvertiblePair@5194} "java.time.ZoneId -> java.util.TimeZone" -> {GenericConversionService$ConvertersForPair@5195} "java.time.ZoneId -> java.util.TimeZone : org.springframework.core.convert.support.ZoneIdToTimeZoneConverter@2571066a"
{GenericConverter$ConvertiblePair@5196} "java.time.ZonedDateTime -> java.util.Calendar" -> {GenericConversionService$ConvertersForPair@5197} "java.time.ZonedDateTime -> java.util.Calendar : org.springframework.core.convert.support.ZonedDateTimeToCalendarConverter@74fe5966"
{GenericConverter$ConvertiblePair@5198} "java.lang.Object -> java.lang.Object" -> {GenericConversionService$ConvertersForPair@5199} "org.springframework.core.convert.support.IdToEntityConverter@4fe875be,org.springframework.core.convert.support.ObjectToObjectConverter@677b8e13"
{GenericConverter$ConvertiblePair@5200} "java.lang.Object -> java.lang.String" -> {GenericConversionService$ConvertersForPair@5201} "org.springframework.core.convert.support.FallbackObjectToStringConverter@4a9486c0"
{GenericConverter$ConvertiblePair@5202} "java.util.Collection -> java.util.Optional" -> {GenericConversionService$ConvertersForPair@5203} "org.springframework.core.convert.support.ObjectToOptionalConverter@4c27d39d"
{GenericConverter$ConvertiblePair@5204} "[Ljava.lang.Object; -> java.util.Optional" -> {GenericConversionService$ConvertersForPair@5205} "org.springframework.core.convert.support.ObjectToOptionalConverter@4c27d39d"
{GenericConverter$ConvertiblePair@5206} "java.lang.Object -> java.util.Optional" -> {GenericConversionService$ConvertersForPair@5207} "org.springframework.core.convert.support.ObjectToOptionalConverter@4c27d39d"
```

org.springframework.core.convert.support.GenericConversionService.Converters.add

```
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.core.convert.support.GenericConversionService$Converters.add(GenericConversionService.java:507)
	  at org.springframework.core.convert.support.GenericConversionService.addConverter(GenericConversionService.java:105)
	  at org.springframework.core.convert.support.GenericConversionService.addConverterFactory(GenericConversionService.java:119)
	  at org.springframework.core.convert.support.DefaultConversionService.addScalarConverters(DefaultConversionService.java:135)
	  at org.springframework.core.convert.support.DefaultConversionService.addDefaultConverters(DefaultConversionService.java:88)
	  at org.springframework.boot.convert.ApplicationConversionService.configure(ApplicationConversionService.java:88)
	  at org.springframework.boot.convert.ApplicationConversionService.<init>(ApplicationConversionService.java:52)
	  at org.springframework.boot.convert.ApplicationConversionService.<init>(ApplicationConversionService.java:45)
	  at org.springframework.boot.convert.ApplicationConversionService.getSharedInstance(ApplicationConversionService.java:71)
	  - locked <0x74c> (a java.lang.Class)
	  at org.springframework.boot.SpringApplication.configureEnvironment(SpringApplication.java:472)
	  at org.springframework.boot.SpringApplication.prepareEnvironment(SpringApplication.java:340)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:305)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215)
	  at org.springframework.boot.SpringApplication.run(SpringApplication.java:1204)
	  at huaminglin.demo.jdbc.postgresql.PostgresqlDemo.main(PostgresqlDemo.java:42)
```

org.springframework.core.convert.support.ObjectToObjectConverter.hasConversionMethodOrConstructor

public java.time.Instant java.sql.Timestamp.toInstant()

```
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.springframework.core.convert.support.ObjectToObjectConverter.getValidatedMember(ObjectToObjectConverter.java:140)
	  at org.springframework.core.convert.support.ObjectToObjectConverter.hasConversionMethodOrConstructor(ObjectToObjectConverter.java:129)
	  at org.springframework.core.convert.support.ObjectToObjectConverter.matches(ObjectToObjectConverter.java:81)
	  at org.springframework.core.convert.support.GenericConversionService$ConvertersForPair.getConverter(GenericConversionService.java:669)
	  at org.springframework.core.convert.support.GenericConversionService$Converters.getRegisteredConverter(GenericConversionService.java:566)
	  at org.springframework.core.convert.support.GenericConversionService$Converters.find(GenericConversionService.java:550)
	  at org.springframework.core.convert.support.GenericConversionService.getConverter(GenericConversionService.java:260)
	  at org.springframework.core.convert.support.GenericConversionService.canConvert(GenericConversionService.java:145)
	  at org.springframework.core.convert.support.GenericConversionService.canConvert(GenericConversionService.java:135)
	  at org.springframework.jdbc.core.SingleColumnRowMapper.convertValueToRequiredType(SingleColumnRowMapper.java:207)
	  at org.springframework.jdbc.core.SingleColumnRowMapper.mapRow(SingleColumnRowMapper.java:118)
	  at org.springframework.jdbc.core.RowMapperResultSetExtractor.extractData(RowMapperResultSetExtractor.java:94)
	  at org.springframework.jdbc.core.RowMapperResultSetExtractor.extractData(RowMapperResultSetExtractor.java:61)
	  at org.springframework.jdbc.core.JdbcTemplate$1QueryStatementCallback.doInStatement(JdbcTemplate.java:439)
	  at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:375)
	  at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:451)
	  at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:461)
	  at org.springframework.jdbc.core.JdbcTemplate.queryForObject(JdbcTemplate.java:472)
	  at org.springframework.jdbc.core.JdbcTemplate.queryForObject(JdbcTemplate.java:479)
	  at huaminglin.demo.jdbc.postgresql.DbTimeZoneService.timezone(DbService.java:41)
	  at huaminglin.demo.jdbc.postgresql.DbTimeZoneService.jvm_timezone(DbService.java:80)
	  at huaminglin.demo.jdbc.postgresql.DbTimeZoneService$$FastClassBySpringCGLIB$$41cfaaeb.invoke(<generated>:-1)
	  at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)
	  at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:749)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	  at org.springframework.transaction.interceptor.TransactionInterceptor$$Lambda$240.684230144.proceedWithInvocation(Unknown Source:-1)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:295)
	  at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
	  at huaminglin.demo.jdbc.postgresql.DbTimeZoneService$$EnhancerBySpringCGLIB$$c12a4066.jvm_timezone(<generated>:-1)
	  at huaminglin.demo.jdbc.postgresql.PostgresqlDemo.main(PostgresqlDemo.java:44)
```

java.sql.Timestamp

```
    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.of(getYear() + 1900,
                                getMonth() + 1,
                                getDate(),
                                getHours(),
                                getMinutes(),
                                getSeconds(),
                                getNanos());
    }

    public Instant toInstant() {
        return Instant.ofEpochSecond(super.getTime() / MILLIS_PER_SECOND, nanos);
    }
```

java.util.Date

```
    public int getYear() {
        return normalize().getYear() - 1900;
    }

    private final BaseCalendar.Date normalize() {
        if (cdate == null) {
            BaseCalendar cal = getCalendarSystem(fastTime);
            cdate = (BaseCalendar.Date) cal.getCalendarDate(fastTime,
                                                            TimeZone.getDefaultRef());
            return cdate;
        }

        // Normalize cdate with the TimeZone in cdate first. This is
        // required for the compatible behavior.
        if (!cdate.isNormalized()) {
            cdate = normalize(cdate);
        }

        // If the default TimeZone has changed, then recalculate the
        // fields with the new TimeZone.
        TimeZone tz = TimeZone.getDefaultRef();
        if (tz != cdate.getZone()) {
            cdate.setZone(tz);
            CalendarSystem cal = getCalendarSystem(cdate);
            cal.getCalendarDate(fastTime, cdate);
        }
        return cdate;
    }
```

Conclusion:

normalize() bind the default timezone to the result.

normalize(): getYear(), normalize().getMonth(), getDate(), getDay(), getHours(), getMinutes(), getSeconds()

## PostgreSQL Startup message: TimeZone; TimeZone config on connection initialization

DB connection is DB session.

org.postgresql.core.v3.ConnectionFactoryImpl.getParametersForStartup

org.postgresql.core.v3.ConnectionFactoryImpl#sendStartupPacket

```
java.lang.Thread.State: RUNNABLE
	  at org.postgresql.core.v3.ConnectionFactoryImpl.getParametersForStartup(ConnectionFactoryImpl.java:317)
	  at org.postgresql.core.v3.ConnectionFactoryImpl.tryConnect(ConnectionFactoryImpl.java:137)
	  at org.postgresql.core.v3.ConnectionFactoryImpl.openConnectionImpl(ConnectionFactoryImpl.java:192)
	  at org.postgresql.core.ConnectionFactory.openConnection(ConnectionFactory.java:49)
	  at org.postgresql.jdbc.PgConnection.<init>(PgConnection.java:211)
	  at org.postgresql.Driver.makeConnection(Driver.java:458)
	  at org.postgresql.Driver.connect(Driver.java:260)
	  at java.sql.DriverManager.getConnection(DriverManager.java:677)
	  at java.sql.DriverManager.getConnection(DriverManager.java:189)
	  at org.springframework.jdbc.datasource.DriverManagerDataSource.getConnectionFromDriverManager(DriverManagerDataSource.java:154)
	  at org.springframework.jdbc.datasource.DriverManagerDataSource.getConnectionFromDriver(DriverManagerDataSource.java:145)
	  at org.springframework.jdbc.datasource.AbstractDriverBasedDataSource.getConnectionFromDriver(AbstractDriverBasedDataSource.java:205)
	  at org.springframework.jdbc.datasource.AbstractDriverBasedDataSource.getConnection(AbstractDriverBasedDataSource.java:169)
	  at net.ttddyy.dsproxy.support.ProxyDataSource.getConnection(ProxyDataSource.java:63)
	  at org.springframework.jdbc.datasource.DataSourceTransactionManager.doBegin(DataSourceTransactionManager.java:262)
	  at org.springframework.transaction.support.AbstractPlatformTransactionManager.getTransaction(AbstractPlatformTransactionManager.java:378)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.createTransactionIfNecessary(TransactionAspectSupport.java:475)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:289)
	  at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
	  at huaminglin.demo.jdbc.postgresql.DbTimeZoneService$$EnhancerBySpringCGLIB$$87787590.jvm_timezone(<generated>:-1)
	  at huaminglin.demo.jdbc.postgresql.PostgresqlDemo.main(PostgresqlDemo.java:44)
```

```
  private List<String[]> getParametersForStartup(String user, String database, Properties info) {
    List<String[]> paramList = new ArrayList<String[]>();
    paramList.add(new String[]{"user", user});
    paramList.add(new String[]{"database", database});
    paramList.add(new String[]{"client_encoding", "UTF8"});
    paramList.add(new String[]{"DateStyle", "ISO"});
    paramList.add(new String[]{"TimeZone", createPostgresTimeZone()});

    Version assumeVersion = ServerVersion.from(PGProperty.ASSUME_MIN_SERVER_VERSION.get(info));

    if (assumeVersion.getVersionNum() >= ServerVersion.v9_0.getVersionNum()) {
      // User is explicitly telling us this is a 9.0+ server so set properties here:
      paramList.add(new String[]{"extra_float_digits", "3"});
      String appName = PGProperty.APPLICATION_NAME.get(info);
      if (appName != null) {
        paramList.add(new String[]{"application_name", appName});
      }
    } else {
      // User has not explicitly told us that this is a 9.0+ server so stick to old default:
      paramList.add(new String[]{"extra_float_digits", "2"});
    }

    String replication = PGProperty.REPLICATION.get(info);
    if (replication != null && assumeVersion.getVersionNum() >= ServerVersion.v9_4.getVersionNum()) {
      paramList.add(new String[]{"replication", replication});
    }

    String currentSchema = PGProperty.CURRENT_SCHEMA.get(info);
    if (currentSchema != null) {
      paramList.add(new String[]{"search_path", currentSchema});
    }

    String options = PGProperty.OPTIONS.get(info);
    if (options != null) {
      paramList.add(new String[]{"options", options});
    }

    return paramList;
  }

  private static String createPostgresTimeZone() {
    String tz = TimeZone.getDefault().getID();
    if (tz.length() <= 3 || !tz.startsWith("GMT")) {
      return tz;
    }
    char sign = tz.charAt(3);
    String start;
    switch (sign) {
      case '+':
        start = "GMT-";
        break;
      case '-':
        start = "GMT+";
        break;
      default:
        // unknown type
        return tz;
    }

    return start + tz.substring(4);
  }
```

org.postgresql.core.v3.QueryExecutorImpl.readStartupMessages

```
"main@1" prio=5 tid=0x1 nid=NA runnable
  java.lang.Thread.State: RUNNABLE
	  at org.postgresql.core.v3.QueryExecutorImpl.receiveParameterStatus(QueryExecutorImpl.java:2644)
	  at org.postgresql.core.v3.QueryExecutorImpl.readStartupMessages(QueryExecutorImpl.java:2626)
	  at org.postgresql.core.v3.QueryExecutorImpl.<init>(QueryExecutorImpl.java:135)
	  at org.postgresql.core.v3.ConnectionFactoryImpl.openConnectionImpl(ConnectionFactoryImpl.java:250)
	  at org.postgresql.core.ConnectionFactory.openConnection(ConnectionFactory.java:49)
	  at org.postgresql.jdbc.PgConnection.<init>(PgConnection.java:211)
	  at org.postgresql.Driver.makeConnection(Driver.java:458)
	  at org.postgresql.Driver.connect(Driver.java:260)
	  at java.sql.DriverManager.getConnection(DriverManager.java:677)
	  at java.sql.DriverManager.getConnection(DriverManager.java:189)
	  at org.springframework.jdbc.datasource.DriverManagerDataSource.getConnectionFromDriverManager(DriverManagerDataSource.java:154)
	  at org.springframework.jdbc.datasource.DriverManagerDataSource.getConnectionFromDriver(DriverManagerDataSource.java:145)
	  at org.springframework.jdbc.datasource.AbstractDriverBasedDataSource.getConnectionFromDriver(AbstractDriverBasedDataSource.java:205)
	  at org.springframework.jdbc.datasource.AbstractDriverBasedDataSource.getConnection(AbstractDriverBasedDataSource.java:169)
	  at net.ttddyy.dsproxy.support.ProxyDataSource.getConnection(ProxyDataSource.java:63)
	  at org.springframework.jdbc.datasource.DataSourceTransactionManager.doBegin(DataSourceTransactionManager.java:262)
	  at org.springframework.transaction.support.AbstractPlatformTransactionManager.getTransaction(AbstractPlatformTransactionManager.java:378)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.createTransactionIfNecessary(TransactionAspectSupport.java:475)
	  at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:289)
	  at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)
	  at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	  at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:688)
	  at huaminglin.demo.jdbc.postgresql.DbTimeZoneService$$EnhancerBySpringCGLIB$$ea2c6317.jvm_timezone(<generated>:-1)
	  at huaminglin.demo.jdbc.postgresql.PostgresqlDemo.main(PostgresqlDemo.java:44)
```

```
  public void receiveParameterStatus() throws IOException, SQLException {
    // ParameterStatus
    pgStream.receiveInteger4(); // MESSAGE SIZE
    String name = pgStream.receiveString();
    String value = pgStream.receiveString();

    if (LOGGER.isLoggable(Level.FINEST)) {
      LOGGER.log(Level.FINEST, " <=BE ParameterStatus({0} = {1})", new Object[]{name, value});
    }

    /* Update client-visible parameter status map for getParameterStatuses() */
    if (name != null && !name.equals("")) {
      onParameterStatus(name, value);
    }

    if (name.equals("client_encoding")) {
      if (allowEncodingChanges) {
        if (!value.equalsIgnoreCase("UTF8") && !value.equalsIgnoreCase("UTF-8")) {
          LOGGER.log(Level.FINE,
              "pgjdbc expects client_encoding to be UTF8 for proper operation. Actual encoding is {0}",
              value);
        }
        pgStream.setEncoding(Encoding.getDatabaseEncoding(value));
      } else if (!value.equalsIgnoreCase("UTF8") && !value.equalsIgnoreCase("UTF-8")) {
        close(); // we're screwed now; we can't trust any subsequent string.
        throw new PSQLException(GT.tr(
            "The server''s client_encoding parameter was changed to {0}. The JDBC driver requires client_encoding to be UTF8 for correct operation.",
            value), PSQLState.CONNECTION_FAILURE);

      }
    }

    if (name.equals("DateStyle") && !value.startsWith("ISO")
        && !value.toUpperCase().startsWith("ISO")) {
      close(); // we're screwed now; we can't trust any subsequent date.
      throw new PSQLException(GT.tr(
          "The server''s DateStyle parameter was changed to {0}. The JDBC driver requires DateStyle to begin with ISO for correct operation.",
          value), PSQLState.CONNECTION_FAILURE);
    }

    if (name.equals("standard_conforming_strings")) {
      if (value.equals("on")) {
        setStandardConformingStrings(true);
      } else if (value.equals("off")) {
        setStandardConformingStrings(false);
      } else {
        close();
        // we're screwed now; we don't know how to escape string literals
        throw new PSQLException(GT.tr(
            "The server''s standard_conforming_strings parameter was reported as {0}. The JDBC driver expected on or off.",
            value), PSQLState.CONNECTION_FAILURE);
      }
      return;
    }

    if ("TimeZone".equals(name)) {
      setTimeZone(TimestampUtils.parseBackendTimeZone(value));
    } else if ("application_name".equals(name)) {
      setApplicationName(value);
    } else if ("server_version_num".equals(name)) {
      setServerVersionNum(Integer.parseInt(value));
    } else if ("server_version".equals(name)) {
      setServerVersion(value);
    }  else if ("integer_datetimes".equals(name)) {
      if ("on".equals(value)) {
        setIntegerDateTimes(true);
      } else if ("off".equals(value)) {
        setIntegerDateTimes(false);
      } else {
        throw new PSQLException(GT.tr("Protocol error.  Session setup failed."),
            PSQLState.PROTOCOL_VIOLATION);
      }
    }
  }
```

## PostgreSQL connection TimeZone

org.postgresql.core.v3.QueryExecutorImpl.timeZone

```
  /**
   * TimeZone of the current connection (TimeZone backend parameter).
   */
  private TimeZone timeZone;
```

org.postgresql.jdbc.PgConnection.PgConnection


org.postgresql.jdbc.PgConnection.PgConnection()

```
    // Initialize timestamp stuff
    timestampUtils = new TimestampUtils(!queryExecutor.getIntegerDateTimes(), new Provider<TimeZone>() {
      @Override
      public TimeZone get() {
        return queryExecutor.getTimeZone();
      }
    });
```

org.postgresql.jdbc.TimestampUtils

```
  /**
   * True if the backend uses doubles for time values. False if long is used.
   */
  private final boolean usesDouble;
  private final Provider<TimeZone> timeZoneProvider;

  /**
   * Returns the given time value as String matching what the current postgresql server would send
   * in text mode.
   *
   * @param time time value
   * @param withTimeZone whether timezone should be added
   * @return given time value as String
   */
  public String timeToString(java.util.Date time, boolean withTimeZone) {
    Calendar cal = null;
    if (withTimeZone) {
      cal = calendarWithUserTz;
      cal.setTimeZone(timeZoneProvider.get());
    }
    if (time instanceof Timestamp) {
      return toString(cal, (Timestamp) time, withTimeZone);
    }
    if (time instanceof Time) {
      return toString(cal, (Time) time, withTimeZone);
    }
    return toString(cal, (Date) time, withTimeZone);
  }
```

org.postgresql.jdbc.PgResultSet.getString(int)

```
      // hack to be compatible with text protocol
      if (obj instanceof java.util.Date) {
        int oid = field.getOID();
        return connection.getTimestampUtils().timeToString((java.util.Date) obj,
            oid == Oid.TIMESTAMPTZ || oid == Oid.TIMETZ);
      }
```

Conclusion: org.postgresql.core.v3.QueryExecutorImpl.timeZone is not used very much in PostgreSQL driver.

## org.postgresql.core.Field.BINARY_FORMAT


org.postgresql.jdbc.PgResultSet.isBinary

```
  /**
   * Returns true if the value of the given column is in binary format.
   *
   * @param column The column to check. Range starts from 1.
   * @return True if the column is in binary format.
   */
  protected boolean isBinary(int column) {
    return fields[column - 1].getFormat() == Field.BINARY_FORMAT;
  }
```

## What format is the TIMESTAMPTZ value in the TCP package?

org.postgresql.core.v3.QueryExecutorImpl#processResults

```
        case 'D': // Data Transfer (ongoing Execute response)
            LOGGER.log(Level.FINEST, " <=BE DataRow(len={0})", length);
        case 'T': // Row Description (response to Describe)
          Field[] fields = receiveFields();
          tuples = new ArrayList<byte[][]>();

          SimpleQuery query = pendingDescribePortalQueue.peekFirst();
          if (!pendingExecuteQueue.isEmpty() && !pendingExecuteQueue.peekFirst().asSimple) {
            pendingDescribePortalQueue.removeFirst();
          }
          query.setFields(fields);

          if (doneAfterRowDescNoData) {
            DescribeRequest describeData = pendingDescribeStatementQueue.removeFirst();
            SimpleQuery currentQuery = describeData.query;
            currentQuery.setFields(fields);

            handler.handleResultRows(currentQuery, fields, tuples, null);
            tuples = null;
          }
          break;
```

The TCP package contains the query result:

```
0000   02 42 a1 8e ba 2f 02 42 ac 18 00 03 08 00 45 00   .B¡.º/.B¬.....E.
0010   00 a5 d6 98 40 00 40 06 0b 86 ac 18 00 03 ac 18   .¥Ö.@.@...¬...¬.
0020   00 01 15 38 e2 26 d7 a6 fb 55 dc 83 90 22 80 18   ...8â&×¦ûUÜ.."..
0030   01 fd 58 cc 00 00 01 01 08 0a fe 43 e8 1c 3e 69   .ýXÌ......þCè.>i
0040   9a 2f 31 00 00 00 04 32 00 00 00 04 54 00 00 00   ./1....2....T...
0050   2a 00 01 63 75 72 72 65 6e 74 5f 74 69 6d 65 73   *..current_times
0060   74 61 6d 70 00 00 00 00 00 00 00 00 00 04 a0 00   tamp.......... .
0070   08 ff ff ff ff 00 00 44 00 00 00 27 00 01 00 00   .ÿÿÿÿ..D...'....
0080   00 1d 32 30 32 30 2d 30 37 2d 31 32 20 31 37 3a   ..2020-07-12 17:
0090   34 36 3a 32 38 2e 37 37 33 31 30 32 2b 30 38 43   46:28.773102+08C
00a0   00 00 00 0d 53 45 4c 45 43 54 20 31 00 5a 00 00   ....SELECT 1.Z..
00b0   00 05 54                                          ..T
```

When we use WireShark to analyse the TCP package:

```
Column name: current_timestamp
Table OID: 0
Type OID: 1184
Column length: 8
Type modifier: -1
Format: Text (0)
```

```
org.postgresql.core.Oid: public static final int TIMESTAMPTZ = 1184;
org.postgresql.jdbc.FieldMetadata.FieldMetadata: FieldMetadata(String columnName, String tableName, String schemaName, int nullable, boolean autoIncrement)
org.postgresql.core.Field.Field: Field(String columnLabel, int oid, int length, int mod, int tableOid, int positionInTable)
```

Conclusion: We can find '2020-07-12 17:46:28.773102+08' and the oid 1184 in the package.

## What format is the TIMESTAMPTZ value in the TCP package?

```
0000   02 42 a1 8e ba 2f 02 42 ac 18 00 03 08 00 45 00   .B¡.º/.B¬.....E.
0010   00 9f d6 9b 40 00 40 06 0b 89 ac 18 00 03 ac 18   ..Ö.@.@...¬...¬.
0020   00 01 15 38 e2 26 d7 a6 fc a8 dc 83 90 eb 80 18   ...8â&×¦ü¨Ü..ë..
0030   01 fd 58 c6 00 00 01 01 08 0a fe 43 e8 24 3e 69   .ýXÆ......þCè$>i
0040   9a 37 31 00 00 00 04 32 00 00 00 04 54 00 00 00   .71....2....T...
0050   27 00 01 6c 6f 63 61 6c 74 69 6d 65 73 74 61 6d   '..localtimestam
0060   70 00 00 00 00 00 00 00 00 00 04 5a 00 08 ff ff   p..........Z..ÿÿ
0070   ff ff 00 00 44 00 00 00 24 00 01 00 00 00 1a 32   ÿÿ..D...$......2
0080   30 32 30 2d 30 37 2d 31 32 20 31 37 3a 34 36 3a   020-07-12 17:46:
0090   32 38 2e 37 37 33 31 30 32 43 00 00 00 0d 53 45   28.773102C....SE
00a0   4c 45 43 54 20 31 00 5a 00 00 00 05 54            LECT 1.Z....T
```


  public static final int TIMESTAMP = 1114;

Conclusion: We can find '2020-07-12 17:46:28.773102' and the oid 1114 in the package.

## Insert data as timestamp without time zone and timestamp with time zone

```
0000   02 42 ac 18 00 03 02 42 a1 8e ba 2f 08 00 45 00   .B¬....B¡.º/..E.
0010   00 c7 b9 5e 40 00 40 06 28 9e ac 18 00 01 ac 18   .Ç¹^@.@.(.¬...¬.
0020   00 03 e8 64 15 38 fa d4 cf e2 bd 38 7a be 80 18   ..èd.8úÔÏâ½8z¾..
0030   01 f5 58 ee 00 00 01 01 08 0a 3f 79 94 8e ff 53   .õXî......?y..ÿS
0040   e2 65 50 00 00 00 2f 00 69 6e 73 65 72 74 20 69   âeP.../.insert i
0050   6e 74 6f 20 74 73 74 7a 20 76 61 6c 75 65 73 28   nto tstz values(
0060   24 31 2c 20 24 32 29 00 00 02 00 00 00 00 00 00   $1, $2).........
0070   00 00 42 00 00 00 4c 00 00 00 02 00 00 00 00 00   ..B...L.........
0080   02 00 00 00 1a 32 30 32 30 2d 30 37 2d 31 32 20   .....2020-07-12 
0090   32 32 3a 34 33 3a 33 33 2e 31 31 34 2b 30 38 00   22:43:33.114+08.
00a0   00 00 1a 32 30 32 30 2d 30 37 2d 31 32 20 32 32   ...2020-07-12 22
00b0   3a 34 33 3a 33 33 2e 31 31 34 2b 30 38 00 00 44   :43:33.114+08..D
00c0   00 00 00 06 50 00 45 00 00 00 09 00 00 00 00 01   ....P.E.........
00d0   53 00 00 00 04                                    S....
```

```
select * from tstz;
           ts            |             tz             
-------------------------+----------------------------
 2020-07-12 22:36:04.151 | 2020-07-12 14:36:04.151+00
 2020-07-12 22:36:04.18  | 2020-07-12 14:36:04.18+00
```

Conclusion:

JDBC doesn't know the column type, use "2020-07-12 22:43:33.114+08" for both fields.

Use JVM timezone to format the date; the session timezone doesn't matter.

On the server size, it drops the timezone info in the string then save it as timestamp without time zone.
