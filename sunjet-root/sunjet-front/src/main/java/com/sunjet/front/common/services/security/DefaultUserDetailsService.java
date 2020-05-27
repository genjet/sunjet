package com.sunjet.front.common.services.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sunjet.common.dao.SjUserRepository;
import com.sunjet.common.entity.SjAuthority;
import com.sunjet.common.entity.SjMenu;
import com.sunjet.common.entity.SjRole;
import com.sunjet.common.entity.SjRoleMenuRel;
import com.sunjet.common.entity.SjUser;
import com.sunjet.common.entity.SjUserRoleRel;
import com.sunjet.front.common.services.security.vo.MenuInfo;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SjUserRepository sjUserRepository;
	// @Autowired
	// private HttpServletRequest request;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// HttpSession session = request.getSession();
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
			List<SjRoleMenuRel> sjRoleMenuRels = sjRole.getSjRoleMenuRels();
			for (SjRoleMenuRel sjRoleMenuRel : sjRoleMenuRels) {
				SjMenu sjMenu = sjRoleMenuRel.getSjMenu();

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
			}
			sonMenus = sonMenus.stream().sorted(Comparator.comparing(SjMenu::getOrdinary)).collect(Collectors.toList());
			mainMenus = mainMenus.stream().sorted(Comparator.comparing(SjMenu::getOrdinary)).collect(Collectors.toList());
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
			System.out.println(sjRole.getRoleCode());
			authorities.add(new SimpleGrantedAuthority(sjRole.getRoleCode()));
			for (SjAuthority sjAuthority : sjRole.getSjAuthoritys()) {
				authorities.add(new SimpleGrantedAuthority(sjAuthority.getAuthorityCode()));
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
		UserDetailsImpl userInfo = new UserDetailsImpl(appUser.getAccount(), appUser.getName(), "{noop}" + appUser.getPwd(), authorities, appUser.getAvatar());
		
//		userInfo.setName(appUser.getName());
		userInfo.setDep(appUser.getSjDep().getName());
		userInfo.setMenus(rtnMenuInfo);
		// UserDetails userInfo =
		// userInfo1.builder().roles(roles.stream().toArray(String[]::new)).build();
		return userInfo;
	}

}
