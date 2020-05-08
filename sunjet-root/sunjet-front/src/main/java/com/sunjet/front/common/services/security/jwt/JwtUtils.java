package com.sunjet.front.common.services.security.jwt;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sunjet.front.common.services.security.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(UserDetailsImpl userPrincipal, Map<String, Object> claims) {

//		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//		 // 遍历用户角色
//		StringBuffer stringBuffer = new StringBuffer();
//		userPrincipal.getAuthorities().forEach(authority -> {
//            stringBuffer.append(authority.getAuthority()).append(",");
//        });
		return Jwts.builder()
				  // Subject 设置用户名
				.setSubject((userPrincipal.getUsername()))
				 // 設置可公開傳送加密數據
                .addClaims(claims)
				.setIssuedAt(new Date())
				 // 过期时间
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				// 签名算法
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
