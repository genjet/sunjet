package com.sunjet.front;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootConfiguration
@SpringBootApplication
//@EnableAutoConfiguration(exclude = {
//		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
public class SunjetFrontApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SunjetFrontApp.class, args);
	}
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
