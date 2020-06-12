package com.sunjet.front.common.security.jwt;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sunjet.front.common.security.vo.SecurityUserDetails;

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

	public String generateJwtToken(SecurityUserDetails userPrincipal, Map<String, Object> claims) {

		final Date now = new Date();
		final Date expirationDate = new Date(now.getTime() + jwtExpirationMs);
		log.info("token expiration Date is {}", expirationDate.toString());
		return Jwts.builder()
				  // Subject 设置用户名
				.setSubject((userPrincipal.getAccount()))
				 // 設置可公開傳送加密數據
                .addClaims(claims)
				.setIssuedAt(now)
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
		} catch (SignatureException e) {// 签名错误
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) { // 非正确的jwt结构
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {// token过期
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {//当接收到的JWT格式/配置与应用程序期望的格式不匹配时抛出异常。例如，当应用程序需要一个加密签名的JWS声明时，如果解析一个无签名的明文JWT，则会引发此异常。
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {// 传递非法参数
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
