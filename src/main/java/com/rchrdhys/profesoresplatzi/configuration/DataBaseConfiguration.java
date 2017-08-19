/**
 * 
 */
package com.rchrdhys.profesoresplatzi.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author rchrdhys
 *
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration {

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactoriBean = new LocalSessionFactoryBean();
		sessionFactoriBean.setDataSource(dataSource());
		
		sessionFactoriBean.setPackagesToScan("com.rchrdhys.profesoresplatzi.model");
		sessionFactoriBean.setHibernateProperties(hibernateProperties());
		
		return sessionFactoriBean;
	}
	
	@Bean
	public DataSource dataSource() {
		   	
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/platziprofesores");
		dataSource.setUsername("platziprofesores");
		dataSource.setPassword("platziprofesores");
		
		return dataSource;
	}
	
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("show_sql", "true");
		
		return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
		
		return hibernateTransactionManager;
	}
}
