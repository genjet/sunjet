package com.sunjet.front.common.services.security.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.sunjet.front.common.vo.ApiResponse;
import com.sunjet.front.util.ResponseUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

//	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//		log.error("Unauthorized error: {}", authException.getMessage());
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
//	}
	
	  @Override
	    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
	        // 未登录 或 token过期
	        if (e.getCause() !=null){
	        	log.info("AAAAAAAAAAAAAAAAAAA111AAAAAAAAAAAAAAAAAAAAA"+ e.getCause().getMessage());
	            ResponseUtils.out(response, ApiResponse.unLogin(e.getMessage()));
	        } else {
	        	log.info("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
	            ResponseUtils.out(response, ApiResponse.expired("jwtToken 過期!"));
	        }
	    }

}
