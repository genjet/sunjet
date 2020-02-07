package com.sunjet.front.services.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.sunjet.common.dao.SjRoleRepository;
import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjRole;
import com.sunjet.common.entity.SjUser;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private SjUserRepository jsUserRepository;
	@Autowired
	private SjRoleRepository jsRoleRepository;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		SjUser user = jsUserRepository.findByIdAndPwd(name, password);
		if (user != null) {

			List<SjRole> roleList = jsRoleRepository.findBySjUser(user.getOid());
			if (!roleList.isEmpty()) {
				String[] roleArr = new String[roleList.size()];
//			String[] roleArr = {"ROLE_ADMIN"};
				for (int i = 0; i < roleList.size(); i++) {
					roleArr[i] = roleList.get(0).getRoleName();
				}
				List<GrantedAuthority> grantedAuthority = AuthorityUtils.createAuthorityList(roleArr);
				return new UsernamePasswordAuthenticationToken(name, password, grantedAuthority);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
//		return authentication.equals(UsernamePasswordAuthenticationToken.class);
		return true;
	}

}
