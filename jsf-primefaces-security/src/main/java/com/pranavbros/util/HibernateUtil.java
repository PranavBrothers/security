package com.pranavbros.util;

import java.net.URL;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.pranavbros.model.jpa.Employee;
import com.pranavbros.service.DataService;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory = buildSessionFactory();

	static {
		buildSessionFactory();
	}
	
	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				Configuration configuration = null;
				URL hibernateResource = new URL("");
				configuration = (new Configuration()).configure();
				configuration.setProperty("hibernate.default_schema", "");
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				sessionFactory = metaData.getSessionFactoryBuilder().build();
			}
			return sessionFactory;
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
	
	public static void main(String[] args) {
		DataService dataService  = new DataService();
		int empCount = dataService.getEmployees().size();
		Employee e = new Employee();
    	e.setFirstName("Pranav");
    	e.setLastName("Brothers");
    	e.setId(empCount+1);
		dataService.saveEmployee(e);
		
		
	}
}