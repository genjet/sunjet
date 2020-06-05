package com.sunjet.front.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sunjet.common.entity.SjUser;
import com.sunjet.front.common.security.vo.SecurityUserDetails;
import com.sunjet.front.common.vo.ApiResponse;
import com.sunjet.front.util.ResponseUtils;

import lombok.extern.slf4j.Slf4j;


/**
 * <p>Title: SjAuthenticationSuccessHandler</p>
 * <p>Description:  登录成功处理类,登录成功后会调用里面的方法</p>
 * @author Andy-Wang
 * @Date 2020年6月5日
 */
@Slf4j
@Component
public class SjAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		 SjUser user = new SjUser();
	        SecurityUserDetails securityUser = ((SecurityUserDetails) authentication.getPrincipal());
//	        user.setToken(securityUser.getCurrentUserInfo().getToken());
	        ResponseUtils.out(response, ApiResponse.ok("登录成功!", user));
	}
	
	 
}
