package com.sunjet.front;


import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootConfiguration
@SpringBootApplication
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
