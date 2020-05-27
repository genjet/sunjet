package com.sunjet.front.common.services.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.common.services.security.vo.MenuInfo;

import lombok.Data;

@Data
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = -3344020769010483378L;

	private final String account;

	private final String username;

	private String email;
	
	private Set<MenuInfo> menus;
	private String dep;

	@JsonIgnore
	private final String password;

	private final Collection<? extends GrantedAuthority> authorities;
	
	private final String avatar;

	

	public Set<MenuInfo> getMenus() {
		return menus;
	}



	public void setMenus(Set<MenuInfo> menus) {
		this.menus = menus;
	}



	public String getDep() {
		return dep;
	}



	public void setDep(String dep) {
		this.dep = dep;
	}



	public static UserDetailsImpl build(SjUser user) {
		List<GrantedAuthority> authorities = user.getSjUserRoleRels().stream()
				.map(role -> new SimpleGrantedAuthority(role.getSjRole().getRoleCode()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getAccount(), 
				user.getName(),
				user.getPwd(),
				authorities, 
				user.getAvatar());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return account;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
