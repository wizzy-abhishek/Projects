package com.confirmation_dao;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.pojo.CredInfo_internal;

public class AccountConfirmation {
	
	static Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CredInfo_internal.class);
	
	static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		
	static SessionFactory sf = config.buildSessionFactory(registry);
	
	static Session session = sf.openSession();
	
	Transaction tx = session.beginTransaction();
	
	public static boolean verify(String uId , String password) {
		
		CredInfo_internal verification = session.get(CredInfo_internal.class, uId);
		return verification.getPassword().equals(password);
		
	}
}
