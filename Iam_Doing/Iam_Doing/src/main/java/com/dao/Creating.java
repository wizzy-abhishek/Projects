package com.dao;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.encrption_decryption.EncryptDecrypt;
import com.pojo.CredInfo_internal;
import com.pojo.RealInfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Creating extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private EncryptDecrypt enc = new EncryptDecrypt();

    private Configuration config = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(CredInfo_internal.class).addAnnotatedClass(RealInfo.class);
    private ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties()).build();
    private SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String userName = request.getParameter("userName");
        String userId = request.getParameter("userID");
        long userMob = Long.parseLong(request.getParameter("userMob"));
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");

        try(Session session = sessionFactory.openSession();) {
    
            if(password.equals(confirm_password)) {
            	String encryptedPassword = enc.encrypt(password);
                CredInfo_internal adding = new CredInfo_internal(userId, encryptedPassword , userName, userMob);
                Transaction tx = session.beginTransaction();
                session.persist(adding);
                tx.commit();
               
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("PasswordMismatch.jsp");
            }
        } catch(Exception e) {
            e.printStackTrace(); 
            response.sendRedirect("ProblemOccured.jsp");
        } finally {
            sessionFactory.close();
        }
    }
}
