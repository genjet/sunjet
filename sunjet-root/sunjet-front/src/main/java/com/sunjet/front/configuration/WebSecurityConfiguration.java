package com.sunjet.front.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationProvider authenticationService;
	@Override
	public void configure(WebSecurity web) throws Exception {
//		// For cors options permission
//		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		  web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
	    }
//
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll()
//		.and().authorizeRequests().antMatchers("/h2/**").permitAll();
//	}
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
//	    	http.authorizeRequests().anyRequest().hasAnyRole("ADMIN")
//			.and().authorizeRequests().antMatchers("/login**").permitAll()
//			.and().formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
//			.and().logout().logoutSuccessUrl("/login").permitAll();
	    	
		http
		//.authorizeRequests().anyRequest().permitAll().and().logout().permitAll()
				.formLogin().loginPage("/login").loginProcessingUrl("/loginAction").failureUrl("/login-error")
				.permitAll() // 表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面

				//.and().authorizeRequests().antMatchers("/").permitAll()

				.and().authorizeRequests().antMatchers("/h2/**").hasRole("ADMIN")
				.and().authorizeRequests().anyRequest().authenticated()
				 
				.and().headers().frameOptions().disable()
				.and()

				.csrf().disable();
	    }

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//
////		 http.formLogin() // 定義當需要提交表單進行用戶登錄時候，轉到的登錄頁面。
////		 .and().authorizeRequests() // 定義哪些URL需要被保護、哪些不需要被保護
////		 .anyRequest() // 任何請求,登錄後可以訪問
////		 .authenticated();
//
//		// http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		// // don't create session
//		// .and().anonymous().disable();
//		  http
//          .authorizeRequests()
//              .anyRequest().authenticated() 
//              .and()
//          .formLogin()
//              .loginPage("/views/login.jsp")
//              .permitAll()
//              .and()
//          .httpBasic();
//		
////		http.authorizeRequests().anyRequest().hasAnyRole("ADMIN")
////		.and().authorizeRequests().antMatchers("/login**").permitAll()
////		.and().formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
////		.and().logout().logoutSuccessUrl("/login").permitAll();
//	}
//
////	/** * 配置一個userDetailsService Bean * 不再生成默認security.user用戶 */
////	@Bean
////	@Override
////	protected UserDetailsService userDetailsService() {
////		return super.userDetailsService();
////	}
//	
//
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("123456").authorities("USER").build());
//        manager.createUser(User.withUsername("admin").password("123456").authorities("ADMIN").build());
//        return manager;
//    }
//
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin")
//				.password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
		auth.authenticationProvider(authenticationService);
//		auth.inMemoryAuthentication()
//        .passwordEncoder(NoOpPasswordEncoder.getInstance())     
//            .withUser("user").password("{noop}user").roles("USER")
//        .and()
//            .withUser("admin").password("{noop}admin").roles("ADMIN");

	}
//
//	// @Bean
//	// CorsConfigurationSource corsConfigurationSource() {
//	// CorsConfiguration configuration = new CorsConfiguration();
//	// configuration.setAllowCredentials(true);
//	// configuration.addAllowedOrigin("*");
//	// configuration.addAllowedHeader("*");
//	// configuration.addAllowedMethod("*");
//	// configuration.addExposedHeader("Location");
//	// UrlBasedCorsConfigurationSource source = new
//	// UrlBasedCorsConfigurationSource();
//	// source.registerCorsConfiguration("/**", configuration);
//	// return source;
//	// }

}
