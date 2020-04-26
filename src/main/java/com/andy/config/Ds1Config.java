package com.andy.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Andy
 * @date 2020/4/24 9:21
 */
@Configuration
@MapperScan(basePackages = "com.andy.mapper.ds1",sqlSessionTemplateRef = "ds1SqlSessionTemplate")
public class Ds1Config {


    @Bean(name="ds1DataSource")
    public DataSource ds1DataSource(Ds1Properties ds1Properties) throws SQLException {
        // 创建MYsql实现XA规范的分布式数据源
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        // 设置连接信息
        mysqlXaDataSource.setUrl(ds1Properties.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(ds1Properties.getPassword());
        mysqlXaDataSource.setUser(ds1Properties.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 数据源改为Atomikos，将事务交给Atomikos统一管理
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("ds1DataSource");

        return xaDataSource;

    }

    @Bean(name="ds1SqlSessionFactory")
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper1/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name="ds1SqlSessionTemplate")
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("ds1SqlSessionFactory") SqlSessionFactory sqlSessionFactory){

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
