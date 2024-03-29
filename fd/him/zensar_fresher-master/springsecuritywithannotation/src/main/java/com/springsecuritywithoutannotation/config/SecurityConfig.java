package com.springsecuritywithoutannotation.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("rajesh").password("upadhyay").roles("USER");
		auth.inMemoryAuthentication().withUser("hari").password("prasad").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("mohan").password("lal").roles("DBA");
	}
	
	/*@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username,password, enabled from users where username=?")
			.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}	*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
			.and().formLogin();
		
	}
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	      .csrf().disable();
		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('USER')")
			.and()
				.formLogin().loginPage("/employeeLogin").failureUrl("/employeeLogin?error")
					.usernameParameter("username").passwordParameter("password")
				
			.and()
				.logout().logoutSuccessUrl("/employeeLogin?logout")
			.and()
				.exceptionHandling().accessDeniedPage("/403")
			.and()
				.csrf(); 
		
	}*/
}