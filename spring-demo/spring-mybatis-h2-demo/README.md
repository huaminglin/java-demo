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


#################################################################################################
## How does Spring integrate MyBatis?
The @MapperScan used with @Configuration is where Spring starts to load MyBatis.
The key point is inside @MapperScan definition. There is "@Import(MapperScannerRegistrar.class)" defined by @MapperScan. This means that it will create a MapperScannerRegistrar instance and starts to scan the configured classpath to find mapper definitions as Spring bean definitions. ClassPathMapperScanner: void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions), definition.setBeanClass(this.mapperFactoryBean.getClass()).
The mapper definition is assigned as org.mybatis.spring.mapper.MapperFactoryBean. MapperFactoryBean implements BeanFactory. MapperFactoryBean is used to create a JDK proxy class for the user defined mapper class:
MapperFactoryBean: T getObject ()
	getSqlSession().getMapper(this.mapperInterface)
		org.apache.ibatis.binding.MapperRegistry: <T> T getMapper(Class<T> type, SqlSession sqlSession)
			org.apache.ibatis.binding.MapperProxyFactory: T newInstance(SqlSession sqlSession)
				MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, mapperInterface, methodCache)
				Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy)

getStudentMapper: class com.sun.proxy.$Proxy47/org.apache.ibatis.binding.MapperProxy@773036df. The JDK proxy class uses org.apache.ibatis.binding.MapperProxy to execute the sql.


The bean definition changes by ClassPathMapperScanner
Generic bean: class [huaminglin.demo.spring.mybatis.h2.mapper.StudentMapper]; scope=singleton; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in file [/home/user1/workspace/java-demo/spring-demo/spring-mybatis-h2-demo/target/classes/huaminglin/demo/spring/mybatis/h2/mapper/StudentMapper.class]

Generic bean: class [org.mybatis.spring.mapper.MapperFactoryBean]; scope=singleton; abstract=false; lazyInit=false; autowireMode=2; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in file [/home/user1/workspace/java-demo/spring-demo/spring-mybatis-h2-demo/target/classes/huaminglin/demo/spring/mybatis/h2/mapper/StudentMapper.class]]

