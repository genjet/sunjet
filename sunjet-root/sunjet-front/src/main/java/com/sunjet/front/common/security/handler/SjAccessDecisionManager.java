package com.sunjet.front.common.security.handler;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
/**
 * <p>Title: SjAccessDecisionManager</p>
 * <p>Description: 对访问url进行权限认证处理</p>
 * @author Andy-Wang
 * @Date 2020年6月12日
 */
@Component
@Slf4j
public class SjAccessDecisionManager implements AccessDecisionManager {

    /**
     * @param authentication: 当前登录用户的角色信息
     * @param object: 请求url信息
     * @param collection: `UrlFilterInvocationSecurityMetadataSource`中的getAttributes方法传来的，表示当前请求需要的角色（可能有多个）
     * @return: void
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, AuthenticationException {
        // 遍历角色
        for (ConfigAttribute ca : collection) {
            // ① 当前url请求需要的权限
            String needRole = ca.getAttribute();
            log.info("================= SjAccessDecisionManager.needRole : {}",needRole);
//            if (Constants.ROLE_LOGIN.equals(needRole)) {
            if ("".equals(needRole)) {
            
                if (authentication instanceof AnonymousAuthenticationToken) {
                	log.info("================= 未登录!   ===============");
                    throw new BadCredentialsException("未登录!");
                } else {
                	log.info("================= 未授权该url！ ===============");
                    throw new AccessDeniedException("未授权该url！");
                }
            }

            // ② 当前用户所具有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
            	log.info("============= authority =============== {}",authority.getAuthority());
                // 只要包含其中一个角色即可访问
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        log.info("================= 请联系管理员分配权限！ ===============");
        throw new AccessDeniedException("请联系管理员分配权限！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
