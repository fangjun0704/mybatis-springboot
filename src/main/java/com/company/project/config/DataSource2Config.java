package com.company.project.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(value = "com.company.project.xml.mapper", sqlSessionTemplateRef = "sqlSessionTemplate2")
public class DataSource2Config {

  /**
   * 自己配置一个 DataSource 来覆盖默认的属性
   */
  @ConfigurationProperties(prefix = "spring.datasource.db2")
  @Bean(name = "dataSource2")
  public DataSource dataSource2() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "sqlSessionFactory2")
  public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource datasource)
      throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(datasource);
    //设置sql的xml文件

    //这里一定要注意，如果这里【没有】添加以下语句
    //到时候在application中添加 mybatis.mapper-locations=classpath*:mapper/*.xml 【无效】
    bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));

    SqlSessionFactory object = bean.getObject();
    org.apache.ibatis.session.Configuration configuration = object.getConfiguration();
    //开启驼峰
    configuration.setMapUnderscoreToCamelCase(true);

    return object;
  }

  @Bean("sqlSessionTemplate2")
  public SqlSessionTemplate sqlSessionTemplate2(
      @Qualifier("sqlSessionFactory2") SqlSessionFactory sessionfactory) {
    return new SqlSessionTemplate(sessionfactory);
  }


}
