package com.sunjet.front.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sunjet.front.common.security.handler.SjAuthenticationEntryPoint;
import com.sunjet.front.common.security.handler.SjAuthenticationSuccessHandler;
import com.sunjet.front.common.security.handler.SjLogoutSuccessHandler;
import com.sunjet.front.common.services.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService defaultUserDetailsService;

	@Autowired
	private SjAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private SjAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private SjLogoutSuccessHandler sjLogoutSuccessHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(defaultUserDetailsService)
		// .passwordEncoder(passwordEncoder());
		;
	}

	// @Bean
	// public PasswordEncoder passwordEncoder() {
	// return new BCryptPasswordEncoder();
	// }

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.formLogin().loginPage("/login").loginProcessingUrl("/auth/login").failureUrl("/login?error").and().logout()
	// .logoutUrl("/logout").logoutSuccessUrl("/login")
	// // .and().rememberMe() // 开启记住密码功能
	// .and().authorizeRequests().antMatchers("/h2/**").hasRole("ADMIN").and().authorizeRequests().anyRequest()
	// .permitAll().and().headers().frameOptions().disable().and().exceptionHandling().accessDeniedPage("/403")
	// .and().csrf().disable();
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
		// registry = http.antMatcher("/**").authorizeRequests();

		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
				//// 配置取消session管理,又Jwt来获取用户状态,否则即使token无效,也会有session信息,依旧判断用户为登录状态
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests()
				.antMatchers("/api/auth/**", "/h2/**", "/groupChat/**", "/logout1").permitAll().anyRequest().authenticated()
				// .and()
				// //配置登录,检测到用户未登录时跳转的url地址,登录放行
				// .formLogin()
				// //需要跟前端表单的action地址一致
				// .loginProcessingUrl("/login").successHandler(authenticationSuccessHandler)
				.and().headers().frameOptions().disable()
				//.and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry())
				 //配置登出,登出放行
	            .and()
	            .logout().logoutUrl("/api/auth/logout")
	            .logoutSuccessHandler(sjLogoutSuccessHandler)
	            .permitAll()
				;

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
		return roleHierarchy;
	}

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
}
