package com.pranavbros.model;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class Car implements Serializable {

	private static final long serialVersionUID = 4672207931321758371L;
	private String cname;
	private String color;
	private String Id;
	private String model;
	private String regno;

	public Car() {
	}

	public Car(String cname, String color, String Id, String model, String regno) {
		this.cname = cname;
		this.color = color;
		this.Id = Id;
		this.model = model;
		this.regno = regno;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCname() {

		System.out.println("car name is" + cname);
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String directFolder() {
		return "pagenav";
	}
	
	public String xmlNav() {
		return "success";
	}
	
	public String nestedFolder() {//Not Working
		return "pages/pagenav";
	}

	public String view() {
		return "success";
	}

}