package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.encrption_decryption.EncryptDecrypt;
import com.pojo.CredInfo_internal;
import com.pojo.RealInfo;

interface CredentialsDao {
    boolean verify(String uID, String password) throws Exception;
}

public class Connecting_Buddy_Dao implements CredentialsDao {
    
    private EncryptDecrypt end = new EncryptDecrypt();
    private SessionFactory sessionFactory;

    public Connecting_Buddy_Dao() {
        Configuration config = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(RealInfo.class)
            .addAnnotatedClass(CredInfo_internal.class);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties())
            .build();
        
        sessionFactory = config.buildSessionFactory(registry);
    }
    
    public CredInfo_internal getUserInfo(String uId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        CredInfo_internal userInfo = null;
        try {
            transaction = session.beginTransaction();
            userInfo = session.get(CredInfo_internal.class, uId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userInfo;
    }

    @Override
    public boolean verify(String userID, String password) throws Exception {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            CredInfo_internal credinfo = session.get(CredInfo_internal.class, userID);
            tx.commit();
            
            if (credinfo != null) {
                return credinfo.getPassword().equals(end.encrypt(password));
            } else {
                return false;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }
}
