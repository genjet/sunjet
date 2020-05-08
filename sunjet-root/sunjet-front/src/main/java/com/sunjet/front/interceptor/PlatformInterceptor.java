package com.sunjet.front.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sunjet.front.common.services.TestService;
import com.sunjet.front.common.services.security.UserDetailsImpl;

@Component
public class PlatformInterceptor implements HandlerInterceptor {

	// @Autowired
	// private ModuleService moduleService;
	//
	// @Autowired
	// private RoleMenuService roleMenuService;
	//
	// @Autowired
	// private RoleUserService roleUserService;
	//
	@Autowired
	private TestService testService;
	private static int count = 1;

	/*
	 * 請求送到Controller前執行，回傳一個布林值，如果是true通過攔截器，反之則否。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String currentUser = "";
//	
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			currentUser = ((UserDetails) principal).getUsername();
		} else {
			currentUser = principal.toString();
		}
//System.out.println(currentUser);
		 HttpSession session = request.getSession();
		 if (session.getAttribute("sjMenus") == null && !"anonymousUser".equals(currentUser)) {
			 UserDetailsImpl userInfo =  (UserDetailsImpl)principal;
			 session.setAttribute("userInfo", userInfo);
			 session.setAttribute("sjMenus", userInfo.getMenus());
			System.out.println(count);
			count++;
		 }
		// Authentication authentication =
		// SecurityContextHolder.getContext().getAuthentication();
		// String name = authentication.getName();
		// System.out.println("================================= count : "+
		// name);
		// SjUser user = (SjUser) session.getAttribute("loginUser");
		// if (user != null) {
		// RoleUser roleUser = roleUserService.getUserByUserId(user.getId());
		// List<RoleMenu> roleMenus =
		// roleMenuService.getRoleMenuByRoleId(roleUser.getRoleId());
		// List<Menu> menus=new ArrayList<>();
		// for (RoleMenu roleMenu : roleMenus) {
		// Menu menu = menuService.getMenuById(roleMenu.getMenuId());
		// menus.add(menu);
		// }
		// if (session.getAttribute("indexMenus") == null) {
		// session.setAttribute("indexMenus", menus);
		// }
		return true;
		// } else {
		// //response.sendRedirect(request.getContextPath());
		// redirect(request, response);
		// return false;
		// }
	}

	/*
	 * Controller處理完後執行。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {

	}

	/*
	 * 整個請求及回應結束後執行。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {

	}

	// 对于请求是ajax请求重定向问题的处理方法
	public void redirect(HttpServletRequest request, HttpServletResponse response) {
		// 获取当前请求的路径
		String basePath = request.getContextPath();
		// 如果request.getHeader("X-Requested-With")
		// 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理
		// 否则直接重定向就可以了
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			// 告诉ajax我是重定向
			response.setHeader("REDIRECT", "REDIRECT");
			// 告诉ajax我重定向的路径
			response.setHeader("CONTENTPATH", basePath + "/");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else {
			try {
				response.sendRedirect(basePath + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
