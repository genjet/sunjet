package com.sunjet.front.common.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjRole;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.SjUserRoleRel;
import com.sunjet.front.common.security.vo.SecurityUserDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Title: SecurityUserDetailsService
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Andy-Wang
 * @Date 2020年6月5日
 */
@Slf4j
@Service
public class SecurityUserDetailsService implements UserDetailsService {
	@Autowired
	private SjUserRepository sjUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SjUser appUser = sjUserRepository.findByAccount(username);

		if (appUser == null) {
			throw new UsernameNotFoundException(username + " not found !! ");
		}
		List<SjUserRoleRel> userRoleRels = appUser.getSjUserRoleRels();

		List<SjRole> sjRoles = new ArrayList<SjRole>();
		for (SjUserRoleRel userRoleRel : userRoleRels) {
			SjRole sjRole = userRoleRel.getSjRole();
			sjRoles.add(sjRole);
		}
		SecurityUserDetails userInfo = new SecurityUserDetails(appUser, sjRoles);

		return userInfo;
	}

}
