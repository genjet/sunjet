package com.sunjet.front.common.services;

import java.util.List;

import com.sunjet.common.entity.SjLeave;
import com.sunjet.common.entity.SjMenu;

public interface TestService {
	
	public int insertUser();
	
	public List<SjMenu> getAllMenu();
	
	public List<SjLeave> getAllLeave();
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
}
