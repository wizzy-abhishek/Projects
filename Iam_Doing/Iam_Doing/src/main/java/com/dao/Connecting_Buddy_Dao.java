package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.encrption_decryption.EncryptDecrypt;
import com.pojo.CredInfo_internal;
import com.pojo.RealInfo;

interface CredentialsDao {
    public boolean verify(String uID, String password) throws Exception;
}

public class Connecting_Buddy_Dao implements CredentialsDao {
	
	private EncryptDecrypt end = new EncryptDecrypt();
	
private Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(RealInfo.class).addAnnotatedClass(CredInfo_internal.class);

private StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
	
private SessionFactory sessionFactory = config.buildSessionFactory(registry);
	
	
	@Override
    public boolean verify(String userID, String password) throws Exception {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction tx = session.beginTransaction();
        		try{
        		CredInfo_internal credinfo = session.get(CredInfo_internal.class, userID);
        		tx.commit();
        		return credinfo.getPassword().equals(end.encrypt(password));
        		}catch(Exception e) {
        			e.printStackTrace();
        		}finally {
        			session.close();
        		}
        		return false;
        	}
    }
	
	
}
