package com.sunjet.front.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.sunjet.front.common.payload.response.ApiResponse;
import com.sunjet.front.common.payload.response.ApiResponse.ResultCode;
import com.sunjet.front.util.ResponseUtils;

/**
 * <p>
 * Title: SjAccessDeniedHandler
 * </p>
 * <p>
 * Description: 没有权限,被拒绝访问时的调用类
 * </p>
 * 
 * @author Andy-Wang
 * @Date 2020年6月5日
 */
@Component
public class SjAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		ResponseUtils.out(response,
				ApiResponse.fail(ResultCode.UNAUTHORIZED.getCode(), accessDeniedException.getMessage()));

	}

}
