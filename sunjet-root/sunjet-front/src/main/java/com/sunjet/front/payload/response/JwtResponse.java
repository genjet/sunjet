package com.sunjet.front.payload.response;

import java.util.List;

import lombok.Data;
@Data
public class JwtResponse {
	private Integer code = 20000;
	private final String accessToken;
	private String type = "Bearer";
	private final String id;
	private final String username;
	private final String account;
	private final List<String> roles;
	private String avatar;


}
