package com.pranavbros.model;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
@ViewScoped
public class UserBean {

  private String firstName = "";
  private String lastName = "";

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String welcome() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return "Welcome User -->" + authentication.getName();
  }
}
