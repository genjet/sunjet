package com.sunjet.front.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sunjet.front.interceptor.PlatformInterceptor;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PlatformInterceptor platformInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] patterns = new String[] { "/login", "/logout" };
		registry.addInterceptor(platformInterceptor).addPathPatterns("/**").excludePathPatterns(patterns);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// String[] exposedHeaders = new String[] { "Content-Disposition",
	// "Cache-Control", "Content-Type" };
	// registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").exposedHeaders(exposedHeaders);
	// }
	// // @Override
	// // public void addCorsMappings(CorsRegistry registry) {
	// // String[] exposedHeaders = new String[] { "Content-Disposition",
	// // "Cache-Control", "Content-Type" };
	// //
	// registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").exposedHeaders(exposedHeaders);
	// // }
	// @Override
	// public void configureViewResolvers(ViewResolverRegistry registry) {
	// registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
	// }
	//
	// @Override
	// public void addViewControllers(ViewControllerRegistry registry) {
	// registry.addViewController("/login").setViewName("login");
	// }
	//
	//// @Bean
	//// public ServletRegistrationBean h2servletRegistration() {
	//// ServletRegistrationBean registrationBean = new
	// ServletRegistrationBean(new WebServlet());
	//// registrationBean.addUrlMappings("/h2/*");
	//// return registrationBean;
	//// }

}