package com.ppteam.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource; 

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		/*auth.inMemoryAuthentication()
			.withUser("abc").password("123").roles("a");*/
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select a.username,b.password,true from user_security as b join user as a on a.id=b.id where a.username=?")
			.authoritiesByUsernameQuery("select username,role from user where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
			.antMatchers("/user/userinfo").authenticated()
			//.antMatchers("/user/basicinfo").hasAnyAuthority("admin")
			//.antMatchers("/test.html").authenticated()
			.anyRequest().permitAll()
		.and()
			.csrf().disable()
			.formLogin()
			.loginPage("/login.html")
			.loginProcessingUrl("/p_login")
			.failureUrl("/failure.html")
			.defaultSuccessUrl("/index.html")
		.and()
			.logout()
			.logoutUrl("/p_logout")
			.logoutSuccessUrl("/index.html")
			;
	}
	

}
