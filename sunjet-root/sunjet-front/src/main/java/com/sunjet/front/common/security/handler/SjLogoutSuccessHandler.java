package com.sunjet.front.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.sunjet.front.common.payload.response.ApiResponse;
import com.sunjet.front.util.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title: SjLogoutSuccessHandler</p>
 * <p>Description: </p>
 * @author Andy-Wang
 * @Date 2020年6月5日
 */
@Slf4j
@Component
public class SjLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info(request.getParameter("token"));
		ResponseUtils.out(response, ApiResponse.ok("Logout Success!"));
	}

}