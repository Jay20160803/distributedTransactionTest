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
@MapperScan(basePackages = "com.andy.mapper.ds0",sqlSessionTemplateRef = "ds0SqlSessionTemplate")
public class Ds0Config {

    @Primary
    @Bean(name="ds0DataSource")
    public DataSource ds0DataSource(Ds0Properties ds0Properties) throws SQLException {
        // 创建MYsql实现XA规范的分布式数据源
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        // 设置连接信息
        mysqlXaDataSource.setUrl(ds0Properties.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(ds0Properties.getPassword());
        mysqlXaDataSource.setUser(ds0Properties.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 数据源改为Atomikos，将事务交给Atomikos统一管理
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("ds0DataSource");

        return xaDataSource;

    }

    @Bean(name="ds0SqlSessionFactory")
    public SqlSessionFactory ds0SqlSessionFactory(@Qualifier("ds0DataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper0/**/*.xml"));
        return bean.getObject();
    }

    @Bean(name="ds0SqlSessionTemplate")
    public SqlSessionTemplate ds0SqlSessionTemplate(@Qualifier("ds0SqlSessionFactory") SqlSessionFactory sqlSessionFactory){

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
