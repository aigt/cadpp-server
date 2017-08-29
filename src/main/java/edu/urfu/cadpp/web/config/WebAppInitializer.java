package edu.urfu.cadpp.web.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import edu.urfu.cadpp.web.config.application.WebConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/**
	 * Filters are defined here
	 */
	@Override
	protected Filter[] getServletFilters() {
		// Filter set UTF-8 encoding for servlets
		// Resolving 99% problems with non-latin character encoding
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("utf-8");
		return new Filter[] { characterEncodingFilter };
	}

}
