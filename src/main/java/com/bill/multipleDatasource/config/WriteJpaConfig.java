package com.bill.multipleDatasource.config;

import com.bill.multipleDatasource.entity.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author Bill.Lin 2024/6/19
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = Person.class,
        entityManagerFactoryRef = "writeEntityManagerFactory",
        transactionManagerRef = "writeTransactionManager",
        basePackages = {"com.bill.multipleDatasource.dao"}
)
public class WriteJpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactory(
            @Qualifier("writeDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(Person.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager writeTransactionManager(
            @Qualifier("writeEntityManagerFactory") LocalContainerEntityManagerFactoryBean writeEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(writeEntityManagerFactory.getObject()));
    }
}
