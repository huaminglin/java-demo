package huaminglin.demo.spring.mybatis.h2.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.h2.jdbcx.JdbcDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

@Configuration
@MapperScan("huaminglin.demo.spring.mybatis.h2.mapper")
public class DataSourceConfiguration {
    @Bean
    public DataSource dataSource(ResourceLoader resourceLoader) throws Exception {
        Resource createSql = resourceLoader.getResource("classpath:create.sql");
        String createSqlFilepath = createSql.getFile().getAbsolutePath();
        Resource initSql = resourceLoader.getResource("classpath:init.sql");
        String initSqlFilepath = initSql.getFile().getAbsolutePath();
        JdbcDataSource ds = new JdbcDataSource();

        // Backslashes within the init script (for example within a runscript statement, to specify the folder names in Windows) need to be escaped as well (using a second backslash).
        // It might be simpler to avoid backslashes in folder names for this reason; use forward slashes instead.
        createSqlFilepath = createSqlFilepath.replace("\\", "/");
        initSqlFilepath = initSqlFilepath.replace("\\", "/");

        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
        url += ";INIT=runscript from '" + createSqlFilepath + "'";
        url += "\\;runscript from '" + initSqlFilepath + "'";
        System.out.println("url: " + url);
        ds.setURL(url);
        ds.setUser("sa");
        ds.setPassword("sa");
        return ds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource(null));
        return sessionFactory.getObject();
    }

}
