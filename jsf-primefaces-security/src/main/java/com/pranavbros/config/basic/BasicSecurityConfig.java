package com.pranavbros.config.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.pranavbros.config.CustomAuthenticationFailureHandler;

//@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
    	.antMatchers("/javax.faces.resource/**").permitAll()
    	.antMatchers("/favicon.ico").permitAll()
    	.anyRequest().authenticated();
   
    http.formLogin().loginPage("/login.xhtml").permitAll()
	   .failureHandler(authenticationFailureHandler())
	   .successHandler(authenticationSuccessHandler())
	   .and()
	    .exceptionHandling().accessDeniedPage("/accessDenied.jsp");
	   // .failureUrl("/login.xhtml?error=true");
	   // http.logout().logoutSuccessUrl("/login.xhtml");
	    http.csrf().disable();
  }	

  
  @Override
  public void configure(AuthenticationManagerBuilder auth)throws Exception {
    auth.inMemoryAuthentication()
    	.withUser("user").password("{noop}P@ssw0rd").roles("USER")
    	.and()
        .withUser("admin").password("{noop}P@ssw0rd").roles("ADMIN");
  }
  
  
  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
      return new CustomAuthenticationFailureHandler();
  }
  
  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
      return new CustomAuthenticationSuccessHandler();
  }
  
}
