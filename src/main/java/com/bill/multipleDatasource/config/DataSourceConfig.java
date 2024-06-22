package com.bill.multipleDatasource.config;

import jakarta.persistence.EntityManagerFactory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.bill.multipleDatasource.config.DataSourceType.MASTER;
import static com.bill.multipleDatasource.config.DataSourceType.READ_REPLICA;

/**
 * @author Bill.Lin 2024/6/21
 */

enum DataSourceType {
    READ_REPLICA, MASTER;
}

@Configuration
@EnableJpaRepositories(basePackages = {"com.bill.multipleDatasource.dao"})
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean(name = "writeDataSource")
    public DataSource writeDataSource(@Value("${spring.datasource.write-db.url}") String url,
                                      @Value("${spring.datasource.write-db.username}") String username,
                                      @Value("${spring.datasource.write-db.password}") String password,
                                      @Value("${spring.datasource.write-db.driverClassName}") String driverClassName) {
        BasicDataSource writeDataSource = new BasicDataSource();
        writeDataSource.setUrl(url);
        writeDataSource.setUsername(username);
        writeDataSource.setPassword(password);
        writeDataSource.setDriverClassName(driverClassName);
        return writeDataSource;
    }

    @Bean(name = "readDataSource")
    public DataSource readDataSource(@Value("${spring.datasource.read-db.url}") String url,
                                     @Value("${spring.datasource.read-db.username}") String username,
                                     @Value("${spring.datasource.read-db.password}") String password,
                                     @Value("${spring.datasource.read-db.driverClassName}") String driverClassName) {
        BasicDataSource readDataSource = new BasicDataSource();
        readDataSource.setUrl(url);
        readDataSource.setUsername(username);
        readDataSource.setPassword(password);
        readDataSource.setDriverClassName(driverClassName);
        return readDataSource;
    }

    @Bean(name = "routingDataSource")
    @DependsOn({"writeDataSource", "readDataSource"})
    public DataSource routingDataSource(@Qualifier("writeDataSource") DataSource writeDataSource,
                                        @Qualifier("readDataSource") DataSource readDataSource) {
        final Map<Object, Object> dataSourceMap = Map.of(MASTER, writeDataSource, READ_REPLICA, readDataSource);
        final AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? READ_REPLICA : MASTER;
            }
        };

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        return routingDataSource;
    }

    @Bean(name = "dataSource")
    @DependsOn("routingDataSource")
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.bill.multipleDatasource.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(additionalProperties());
        em.afterPropertiesSet();
        return em.getObject();
    }

    Map<String, Object> additionalProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}
