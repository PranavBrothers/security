package com.pranavbros.config.ldap.embedded;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.pranavbros.config.CustomAuthenticationFailureHandler;

//@EnableWebSecurity
public class LdapSecurityConfig extends WebSecurityConfigurerAdapter {

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
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
              .ldapAuthentication()
              .userDnPatterns("uid={0},ou=people")
              .groupSearchBase("ou=groups")
              .contextSource(contextSource())
              .passwordCompare()
              .passwordEncoder(new LdapShaPasswordEncoder())
              .passwordAttribute("userPassword");
  }

  @Bean
  public DefaultSpringSecurityContextSource contextSource() {
      return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"), "dc=springframework,dc=org");
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
