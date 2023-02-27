package com.card.note.mvp.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.card.note.mvp")
@EnableJpaAuditing
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class DataSourceConfig {
 
    @Value("${spring.datasource.url}")
    private String url;
 
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
 
    @Value("${spring.datasource.username}")
    private String username;
 
    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.jpa.properties.hibernate.dialect.storage_engin}")
    private String dialect;
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
 
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
 
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("com.card.note.mvp");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // emf.setJpaProperties(jpaProperties());
        return emf;
    }
 
    // private Properties jpaProperties() {
    //     Properties props = new Properties();
    //     props.put("hibernate.dialect", dialect);
    //     return props;
    // }
}
