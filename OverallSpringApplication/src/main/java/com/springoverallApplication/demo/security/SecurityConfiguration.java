package com.springoverallApplication.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailService userDetailService;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**","/static/**","/images/**","/productimages/**","/css/**","/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.authorizeRequests().antMatchers("/","/login","/signup","/getCountryWiseState/**","/registeruser/**","/otpgeneration/**","/checkEmailIdExist/**","/checkOtp/**").permitAll()
			.anyRequest().authenticated()
			.and()
			/*.formLogin()
			.loginPage("/login").permitAll()
			.failureUrl("/login?error=true")
			.defaultSuccessUrl("/home")
			.usernameParameter("appUserEmailId")
			.passwordParameter("appUserPassword")
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logoutSuccess").permitAll();*/
			.formLogin()
			.loginPage("/login").permitAll()
			.failureUrl("/login?error=true")
			.defaultSuccessUrl("/home")
			.usernameParameter("appUserEmailId")
			.passwordParameter("appUserPassword")
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logoutSuccess").permitAll()
			.and()
			.exceptionHandling();

	}

}
