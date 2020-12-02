package com.pranavbros.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranavbros.model.jpa.Employee;
import com.pranavbros.service.DataService;

@RestController
public class EmployeeController {

	@Autowired
	private DataService dataService;
	
	@GetMapping("/save")
	public List<Employee> save() {
		int empCount = dataService.getEmployees().size();
		Employee e = new Employee();
    	e.setFirstName("Pranav");
    	e.setLastName("Brothers");
    	e.setId(empCount+1);
    	dataService.saveEmployee(e);
		return list();
	}
	
	@GetMapping("/list")
	public List<Employee> list() {
		return dataService.getEmployees();
	}
}
