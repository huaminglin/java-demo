mvn exec:java


MyBatis Cache Setup:
a. Model Class must be serializable
org.apache.ibatis.cache.CacheException: Error serializing object.  Cause: java.io.NotSerializableException: huaminglin.demo.spring.mybatis.h2.Student

b. Enable cache in mapper xml: <cache/>

c. Check log to verify that the MyBatis cache is hit
[DEBUG] 2018-06-14 09:13:19.842 [huaminglin.demo.spring.mybatis.h2.SpringMyBatisH2Demo.main()] StudentMapper - Cache Hit Ratio [huaminglin.demo.spring.mybatis.h2.mapper.StudentMapper]: 0.5

d. The object got from the cache is not the same Java object

e. Full log
[DEBUG] 2018-06-14 09:20:17.354 [huaminglin.demo.spring.mybatis.h2.SpringMyBatisH2Demo.main()] StudentMapper - Cache Hit Ratio [huaminglin.demo.spring.mybatis.h2.mapper.StudentMapper]: 0.0
[DEBUG] 2018-06-14 09:20:17.655 [huaminglin.demo.spring.mybatis.h2.SpringMyBatisH2Demo.main()] getStudents - ==>  Preparing: select name, age from student where name = ?
[DEBUG] 2018-06-14 09:20:17.691 [huaminglin.demo.spring.mybatis.h2.SpringMyBatisH2Demo.main()] getStudents - ==> Parameters: name1(String)
[DEBUG] 2018-06-14 09:20:17.725 [huaminglin.demo.spring.mybatis.h2.SpringMyBatisH2Demo.main()] getStudents - <==      Total: 1
Get name1: [huaminglin.demo.spring.mybatis.h2.Student@4aecf10]
[DEBUG] 2018-06-14 09:20:17.832 [huaminglin.demo.spring.mybatis.h2.SpringMyBatisH2Demo.main()] StudentMapper - Cache Hit Ratio [huaminglin.demo.spring.mybatis.h2.mapper.StudentMapper]: 0.5
Get name1: [huaminglin.demo.spring.mybatis.h2.Student@6f962a6]


logging mechanism:
Which logging API does Spring use?
Which logging API does MyBatis use?
How does the logging API redirect to log4j implementation?
Apache common logging API can't detect log4j2 automatically. log4j-jcl is used to resolve this issue.
