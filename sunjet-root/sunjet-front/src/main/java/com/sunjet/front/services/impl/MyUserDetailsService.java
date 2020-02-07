package com.sunjet.front.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class MyUserDetailsService{ 
//implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		logger.info("使用者的使用者名稱: {}", username);
//		// TODO 根據使用者名稱，查詢到對應的密碼，與許可權
//		// 封裝使用者資訊，並返回。引數分別是：使用者名稱，密碼，使用者許可權
//		User user = new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//		return user;
//	}

}
