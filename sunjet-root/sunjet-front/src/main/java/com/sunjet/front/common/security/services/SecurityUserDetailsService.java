package com.sunjet.front.common.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjApi;
import com.sunjet.common.entity.SjAuthority;
import com.sunjet.common.entity.SjMenu;
import com.sunjet.common.entity.SjRole;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.SjUserRoleRel;
import com.sunjet.front.common.security.vo.SecurityUserDetails;
import com.sunjet.front.common.vo.MenuInfo;

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
		log.info(
				"=============================================    SecurityUserDetailsService   ======================================================");

		SjUser appUser = sjUserRepository.findByAccount(username);

		if (appUser == null) {
			throw new UsernameNotFoundException(username + " not found");
		}
		List<SjUserRoleRel> userRoleRels = appUser.getSjUserRoleRels();
		Set<MenuInfo> rtnMenuInfo = new TreeSet<MenuInfo>((o1, o2) -> o1.getOrdinary().compareTo(o2.getOrdinary()));
		List<SjMenu> mainMenus = new ArrayList<SjMenu>();
		List<SjMenu> sonMenus = new ArrayList<SjMenu>();

		// Set<MenuInfo> menus = new TreeSet<MenuInfo>((o1, o2) ->
		// o1.getOrdinary().compareTo(o2.getOrdinary()));
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		for (SjUserRoleRel userRoleRel : userRoleRels) {
			SjRole sjRole = userRoleRel.getSjRole();
			List<SjAuthority> sjAuthorities = sjRole.getSjRoleAuthorityRels().stream().map(it -> it.getSjAuthority())
					.collect(Collectors.toList());
			authorities.add(new SimpleGrantedAuthority(sjRole.getRoleCode()));
			for (SjAuthority sjAuthority : sjAuthorities) {
				SjApi sjApi = sjAuthority.getSjApi();
				// for (SjApi sjApi : sjApis) {
				if (null != sjApi) {
					SjMenu sjMenu = sjApi.getSjMenu();

					// MenuInfo menu = new MenuInfo();
					// BeanUtils.copyProperties(sjMenu, menu);
					//
					String parentMenuCode = sjMenu.getParentMenu();
					if (StringUtils.isBlank(parentMenuCode)) {
						mainMenus.add(sjMenu);
						// map.put(sjMenu.getOid(), menu);
					} else {
						sonMenus.add(sjMenu);
						// MenuInfo mainMenu = map.get(parentMenuCode);
						// mainMenu.getSonMenus().add(menu);

					}

					// }
				}
				sonMenus = sonMenus.stream().sorted(Comparator.comparing(SjMenu::getOrdinary))
						.collect(Collectors.toList());
				mainMenus = mainMenus.stream().sorted(Comparator.comparing(SjMenu::getOrdinary))
						.collect(Collectors.toList());
				for (SjMenu mainMenu : mainMenus) {
					MenuInfo menu = new MenuInfo();
					BeanUtils.copyProperties(mainMenu, menu);
					for (SjMenu sonMenu : sonMenus) {
						if (mainMenu.getOid().equals(sonMenu.getParentMenu())) {
							MenuInfo sMenu = new MenuInfo();
							BeanUtils.copyProperties(sonMenu, sMenu);
							menu.getSonMenus().add(sMenu);
						}
					}
					rtnMenuInfo.add(menu);
				}
				// roles.add(sjRole.getRoleName());
				
//				for (SjRoleAuthorityRel sjRoleAuthorityRel : sjRole.getSjRoleAuthorityRels()) {
					authorities.add(new SimpleGrantedAuthority(sjAuthority.getAuthorityCode()));
//				}
			}
			// authorities.add(new SimpleGrantedAuthority("ROLE_"+
			// sjRole.getRoleName()));
		}
		// session.setAttribute("sjMenus", menus);
		// RequestAttributes attribs =
		// RequestContextHolder.getRequestAttributes();
		// if (attribs instanceof NativeWebRequest) {
		// HttpServletRequest request = (HttpServletRequest) ((NativeWebRequest)
		// attribs).getNativeRequest();
		// request.getSession().setAttribute("sjMenus", menus);
		// }
		// if (RequestContextHolder.getRequestAttributes() != null) {
		// HttpServletRequest request = ((ServletRequestAttributes)
		// attribs).getRequest();
		// request.getSession().setAttribute("sjMenus", menus);
		// }

		// UserDetails userInfo =
		// UserInfo.builder().username(appUser.getAccount()).password("{noop}" +
		// appUser.getPwd())
		// .roles(roles.stream().toArray(String[]::new)).authorities(authorities).build();
		log.info(" authorities ============>>> {}", authorities);
		SecurityUserDetails userInfo = new SecurityUserDetails(appUser.getAccount(), appUser.getName(),
				"{noop}" + appUser.getPwd(), authorities, appUser.getAvatar());

		// userInfo.setName(appUser.getName());
		userInfo.setDep(appUser.getSjDep().getName());
		userInfo.setMenus(rtnMenuInfo);
		// UserDetails userInfo =
		// userInfo1.builder().roles(roles.stream().toArray(String[]::new)).build();
		return userInfo;
	}
}
