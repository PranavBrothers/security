package com.pranavbros.config.ldap.remote;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		LdapUserDetails principal = (LdapUserDetails) authentication.getPrincipal();
		System.out.println("principal" + principal.getUsername());
		boolean isAdmin = false;
		Iterator<? extends GrantedAuthority> grantedAuthorityIterator = principal.getAuthorities().iterator();
		while (grantedAuthorityIterator.hasNext()) {
			if (grantedAuthorityIterator.next().getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}
		
		//request.getSession().setAttribute("username", principal.getUsername());
		//response.sendRedirect("/index.jsp");
		
		if (isAdmin) {
			response.sendRedirect("/pages/admin.xhtml");
		} else {
			response.sendRedirect("/user.xhtml");
		}
	}

}
