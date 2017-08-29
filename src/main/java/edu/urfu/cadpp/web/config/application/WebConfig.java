package edu.urfu.cadpp.web.config.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan({ "edu.urfu.cadpp.web.config.*", "edu.urfu.cadpp.web.controller", "edu.urfu.cadpp.web.controller.json",
		"edu.urfu.cadpp.web", "edu.urfu.cadpp.web.dao", "edu.urfu.cadpp.web.dao.impl" })
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setViewClass(JstlView.class);
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setContentType("text/html; charset=utf-8");
		return internalResourceViewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/static/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/static/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/static/js/");
		registry.addResourceHandler("/font/**").addResourceLocations("/WEB-INF/static/font/");
		registry.addResourceHandler("/html/**").addResourceLocations("/WEB-INF/static/html/");
	}
}
