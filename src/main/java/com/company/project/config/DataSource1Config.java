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
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(value = "com.company.project.annotation.mapper", sqlSessionTemplateRef = "sqlSessionTemplate1")
public class DataSource1Config {

  /**
   * 自己配置一个 DataSource 来覆盖默认的属性
   */
  @ConfigurationProperties(prefix = "spring.datasource.db1")
  @Bean(name = "dataSource1")
  @Primary
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "sqlSessionFactory1")
  @Primary
  public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dataSource1") DataSource datasource)
      throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(datasource);

    SqlSessionFactory object = bean.getObject();
    org.apache.ibatis.session.Configuration configuration = object.getConfiguration();
    //开启驼峰
    configuration.setMapUnderscoreToCamelCase(true);
    return object;
  }

  @Bean("sqlSessionTemplate1")
  @Primary
  public SqlSessionTemplate test1sqlsessiontemplate(
      @Qualifier("sqlSessionFactory1") SqlSessionFactory sessionfactory) {
    return new SqlSessionTemplate(sessionfactory);
  }


}
