package com.sunjet.front.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationProvider authenticationService;

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin().loginPage("/login")//.loginProcessingUrl("/login")
			.failureUrl("/login?error")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
			//.and().rememberMe() // 开启记住密码功能
			.and().authorizeRequests().antMatchers("/h2/**").hasRole("ADMIN")
			.and().authorizeRequests().anyRequest().permitAll()
			.and().headers().frameOptions().disable()
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().csrf().disable()
			;
	}
	
//	 .loginPage("/login") // 自定义用户登入页面
//     .failureUrl("/login?error") // 自定义登入失败页面，前端可以通过url中是否有error来提供友好的用户登入提示
//     .and()
//     .logout()
//     .logoutUrl("/logout")// 自定义用户登出页面
//     .logoutSuccessUrl("/")
//     .and()
//     .rememberMe() // 开启记住密码功能
//     .rememberMeServices(getRememberMeServices()) // 必须提供
//     .key(SECRET_KEY) // 此SECRET需要和生成TokenBasedRememberMeServices的密钥相同
//     .and()
//     /*
//      * 默认允许所有路径所有人都可以访问，确保静态资源的正常访问。
//      * 后面再通过方法注解的方式来控制权限。
//      */
//     .authorizeRequests().anyRequest().permitAll()
//     .and()
//     .exceptionHandling().accessDeniedPage("/403"); // 权限不足自动跳转403

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationService);

	}

}
