package com.sunjet.front.common.security.vo;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunjet.common.entity.SjUser;
import com.sunjet.front.common.vo.MenuInfo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * <p>Title: SecurityUserDetails</p>
 * <p>Description: 安全认证用户详情</p>
 * @author Andy-Wang
 * @Date 2020年6月5日
 */
@Data
@Slf4j
public class SecurityUserDetails implements UserDetails {
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



	public static SecurityUserDetails build(SjUser user) {
		List<GrantedAuthority> authorities = user.getSjUserRoleRels().stream()
				.map(role -> new SimpleGrantedAuthority(role.getSjRole().getRoleCode()))
				.collect(Collectors.toList());

		return new SecurityUserDetails(
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
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * 当前登录用户
//	 */
//	private transient SjUser currentUserInfo;
//	/**
//	 * 角色
//	 */
//	private transient List<SjRole> roleList;
//	/**
//	 * 当前用户所拥有角色代码
//	 */
//	private transient String roleCodes;
//
//	public SecurityUserDetails(SjUser user, List<SjRole> roleList) {
//		if (user != null) {
//			this.currentUserInfo = user;
//			this.roleList = roleList;
//			if (!CollectionUtils.isEmpty(roleList)) {
//				StringJoiner roleCodes = new StringJoiner(",", "[", "]");
//				roleList.forEach(e -> roleCodes.add(e.getRoleCode()));
//				this.roleCodes = roleCodes.toString();
//			}
//		}
//	}
//
//	/**
//	 * 获取当前用户所具有的角色
//	 *
//	 * @return
//	 */
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> authorities = new ArrayList<>();
//		if (!CollectionUtils.isEmpty(this.roleList)) {
//			for (SjRole role : this.roleList) {
//				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleCode());
//				authorities.add(authority);
//			}
//		}
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return "{noop}" + currentUserInfo.getPwd();
//	}
//
//	@Override
//	public String getUsername() {
//		return currentUserInfo.getName();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}

}
