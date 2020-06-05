package com.sunjet.front.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.sunjet.front.common.vo.ApiResponse;
import com.sunjet.front.util.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title: SjAuthenticationFailureHandler</p>
 * <p>Description: 登录账号密码错误等情况下,会调用的处理类</p>
 * @author Andy-Wang
 * @Date 2020年6月5日
 */
@Slf4j
@Component
public class SjAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		ApiResponse result;
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            result = ApiResponse.fail(exception.getMessage());
        } else if (exception instanceof LockedException) {
            result = ApiResponse.fail("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            result = ApiResponse.fail("证书过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            result = ApiResponse.fail("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            result = ApiResponse.fail("账户被禁用，请联系管理员!");
        } else {
            log.error("登录失败：", exception);
            result = ApiResponse.fail("登录失败!");
        }
        ResponseUtils.out(response, result);
		
	}

}
