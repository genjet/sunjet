package com.sunjet.front.configuration;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// String[] exposedHeaders = new String[] { "Content-Disposition",
	// "Cache-Control", "Content-Type" };
	// registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").exposedHeaders(exposedHeaders);
	// }
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

//	@Bean
//	public ServletRegistrationBean h2servletRegistration() {
//		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
//		registrationBean.addUrlMappings("/h2/*");
//		return registrationBean;
//	}

}
