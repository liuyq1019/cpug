package com.zxelec.cpug.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
//		.anyRequest()
//		.authenticated()
//		.and()
//		.exceptionHandling()
//		.authenticationEntryPoint(digestEntryPoint())//摘要认证入口端点
//		.and()
//		.addFilter(digestFilter());//在过滤链中添加摘要认证过滤器
	}
	
	@Bean
	public DigestAuthenticationEntryPoint digestEntryPoint() {
		DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
		entryPoint.setKey("CPUG");
		entryPoint.setRealmName("security");
		return entryPoint;
	}
	
	@Bean
	public DigestAuthenticationFilter digestFilter() throws Exception {
		DigestAuthenticationFilter digestFilter = new DigestAuthenticationFilter();
		digestFilter.setAuthenticationEntryPoint(digestEntryPoint());//必须配置
		digestFilter.setUserDetailsService(userDetailsService());
		return digestFilter;
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				String password = "25d55ad283aa400af464c76d713c07ad";
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority("auth"));
				return new User(username, password, true, true, true, true, authorities);
			}
		};
	}

}
