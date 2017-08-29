package edu.urfu.cadpp.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
@PropertySource(value = { "classpath:auth.properties" })
@PropertySource(value = { "classpath:util.properties" })
public class AppConfig {

	private Environment environment;

	@Autowired
	public AppConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
		jdbcImpl.setDataSource(dataSource());
		jdbcImpl.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
		jdbcImpl.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
		return jdbcImpl;
	}

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.postgresql.driverClass"));
		dataSource.setUrl(environment.getProperty("jdbc.postgresql.url"));
		dataSource.setUsername(environment.getProperty("jdbc.postgresql.username"));
		dataSource.setPassword(environment.getProperty("jdbc.postgresql.password"));
		return dataSource;
	}

}
