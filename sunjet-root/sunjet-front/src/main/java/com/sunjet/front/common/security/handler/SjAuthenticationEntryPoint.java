package com.sunjet.front.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.sunjet.front.common.vo.ApiResponse;
import com.sunjet.front.util.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 匿名未登录的时候访问,需要登录的资源的调用类
 * 
 * @author Andy-Wang
 *
 */
@Slf4j
@Component
public class SjAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		if (authException != null) {
			log.info("AuthenticationEntryPoint error :{}", authException.getMessage());
			ResponseUtils.out(response, ApiResponse.unLogin(authException.getMessage()));
		}

	}

}
