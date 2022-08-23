package com.jaas.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author sagarwalke.dev
 *
 */
@Configuration
public class DatasourceConfig {

	@Value("${database.username}")
	private String username;
	@Value("${database.password}")
	private String password;
	@Value("${database.url}")
	private String url;
	@Value("${database.driver}")
	private String driver;

	/**
	 * @return
	 */
	@Bean
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);
		dataSource.setUrl(this.url);
		dataSource.setDriverClassName(this.driver);
		return dataSource;
	}

	/**
	 * @return
	 */
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
