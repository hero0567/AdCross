package com.crossp.configure;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ConfigurationProperties("classpath:application.properties")
public class JPAConfigure {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.username}")
	private String dbUname;
	@Value("${spring.datasource.password}")
	private String dbPwd;
	@Value("${spring.datasource.driverClassName}")
	private String dbDriverClassName;
	
	@Value("${spring.jpa.hibernate.hbm2ddl.auto}")
	private String hibDDLAuto;	
	@Value("${spring.jpa.hibernate.dialect}")
	private String hibDialect;		
	@Value("${spring.jpa.hibernate.show_sql}")
	private String hibShowSQL;
	

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] {"com.crossp.jpa.domain"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalJpaProperties());

        return em;
    }

	Properties additionalJpaProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hibDDLAuto);
        properties.setProperty("hibernate.dialect", hibDialect);
        properties.setProperty("hibernate.show_sql", hibShowSQL);

        return properties;
    }
	
    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .url(dbUrl)
                .driverClassName(dbDriverClassName)
                .username(dbUname)
                .password(dbPwd)
                .build();
    } 
}
