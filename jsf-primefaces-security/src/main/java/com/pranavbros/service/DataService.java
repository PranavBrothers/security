package com.pranavbros.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.pranavbros.model.jpa.Employee;
import com.pranavbros.util.HibernateUtil;

@Service
public class DataService {

    public Map<Integer, Double> getLineChartData() {
        Map<Integer, Double> map = new LinkedHashMap<>();
        map.put(1, 5.20);
        map.put(2, 19.63);
        map.put(3, 59.01);
        map.put(4, 139.76);
        map.put(5, 300.4);
        map.put(6, 630.0);
       return map;
    }
    
    public void saveEmployee(Employee e) {
    	Transaction transaction = null;
    	try(Session session = HibernateUtil.getSessionFactory().openSession()){
    		transaction = session.beginTransaction();
    		session.save(e);
    		transaction.commit();
    	}
    }
    
    public List<Employee> getEmployees(){
    	List<Employee> employees = null;
    	try(Session session = HibernateUtil.getSessionFactory().openSession()){
    		employees = session.createQuery("from Employee", Employee.class).list();
    	}
    	return employees;
    }
}