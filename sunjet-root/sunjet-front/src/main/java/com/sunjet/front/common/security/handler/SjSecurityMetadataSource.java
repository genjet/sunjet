package com.sunjet.front.common.security.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.sunjet.common.dao.SjMenuRepository;
import com.sunjet.common.entity.SjApi;
import com.sunjet.common.entity.SjAuthority;
import com.sunjet.common.entity.SjMenu;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SjSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	// @Override
	// public Collection<ConfigAttribute> getAttributes(Object object) throws
	// IllegalArgumentException {
	// FilterInvocation fi = (FilterInvocation) object;
	//
	// Map<String, Collection<ConfigAttribute>> metadataSource =
	// CustomSecurityContext.getMetadataSource();
	//
	// for (Map.Entry<String, Collection<ConfigAttribute>> entry :
	// metadataSource.entrySet()) {
	// String uri = entry.getKey();
	// RequestMatcher requestMatcher = new AntPathRequestMatcher(uri);
	// if (requestMatcher.matches(fi.getHttpRequest())) {
	// return entry.getValue();
	// }
	// }
	//
	// return null;
	// }
	@Autowired
	private SjMenuRepository permissionDao;

	// 此map缓存 URL与其权限关系
	private volatile HashMap<String, Collection<ConfigAttribute>> map = null;

	// 在demo启动第一个用户登陆后，加载所有权限进map
	// 当DB中URL对应的权限发生变化时，可以调用此方法更新Security的url权限缓存map
	// 经测试方法执行后 实时生效
	public void loadResourceDefine() {
		map = new HashMap<>();
		Collection<ConfigAttribute> array;

		List<SjMenu> menus = permissionDao.findAll();
		for (SjMenu menu : menus) {
			for (SjApi sjApi : menu.getSjApis()) {
				final String url = sjApi.getUrl().concat(null == sjApi.getMethod() ? "" : sjApi.getMethod().name());
				if (map.containsKey(url)) {
					array = map.get(url);
				} else {
					array = new HashSet<>();
				}
				// 此处只添加了用户的名字，其实还可以添加更多权限的信息，
				// 例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
				SjAuthority role = sjApi.getSjAuthority();
				ConfigAttribute cfg = new SecurityConfig(role.getAuthorityCode());
				array.add(cfg);
				// 用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value
				map.put(url, array);
			}
		}
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (map == null) { // 当DB中URL对应的权限发生变化时，也可以将map设置为null，触发重新加载权限
			// 重新加载
			loadResourceDefine();
		}
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		
//		AntPathRequestMatcher matcher;
		// 遍历权限表中的url
		for (String url : map.keySet()) {
			log.info("uri: ==> "+request.getRequestURI().concat(request.getMethod()));
			log.info("urlllllllllll: ==> "+url);
//			matcher = new AntPathRequestMatcher(url);
			// 与request对比，符合则说明权限表中有该请求的URL
			String uri = request.getRequestURI();
			if(url.contains("null")){
				uri = uri.concat(request.getMethod());
			}
			if (url.equals(uri)) {
				log.info("===============  SjSecurityMetadataSource.getAttributes {}  =============================",
						url);
				return map.get(url);
			}
		}
//		Collection<ConfigAttribute> array = new ArrayList<>();
//		ConfigAttribute cfg = new SecurityConfig("ROLE_ADMIN");
//		ConfigAttribute cfg1 = new SecurityConfig("ROLE_ANONYMOUS");
//		array.add(cfg1);
//		array.add(cfg);
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
