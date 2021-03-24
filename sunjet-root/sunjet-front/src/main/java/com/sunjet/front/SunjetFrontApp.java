package com.sunjet.front;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sunjet.front.common.utils.ApplicationContextUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootConfiguration
@SpringBootApplication
@EnableJpaAuditing
@Slf4j
//@EnableAutoConfiguration(exclude = {
//		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
public class SunjetFrontApp extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		log.info("=================   執行spring boot main  =======================");
		final ApplicationContext app = SpringApplication.run(SunjetFrontApp.class, args);
		ApplicationContextUtil.setApplicationContext(app);
	}

	private static Class<SunjetFrontApp> applicationClass = SunjetFrontApp.class;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

//	public static void main(String[] args) {
//		GitResources.printVersionClose(args);
//		final ApplicationContext context = SpringApplication.run(WebApplication.class, args);
//		ProfileMode.print(context);
//	}
//	 @RestController
//	    class SimpleController {
//
//	        @GetMapping
//	        public Map hello() {
//	            Map map = new HashMap();
//	            map.put("say", "hello");
//	            return map;
//	        }
//	    }
}
