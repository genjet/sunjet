package com.sunjet.front.common.services.security.jwt;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sunjet.front.common.services.security.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

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
		Date expirationDate = new Date((new Date()).getTime() + jwtExpirationMs);
		log.info(expirationDate.toString());
		return Jwts.builder()
				  // Subject 设置用户名
				.setSubject((userPrincipal.getAccount()))
				 // 設置可公開傳送加密數據
                .addClaims(claims)
				.setIssuedAt(new Date())
				 // 过期时间
				.setExpiration(expirationDate)
				// 签名算法
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getAccountFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
