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
import jakarta.servlet.http.HttpSession;


public class Good_BYE extends HttpServlet {
	private static final long serialVersionUID = 1L;

	    private Configuration config = new Configuration().configure("hibernate.cfg.xml")
	            .addAnnotatedClass(CredInfo_internal.class).addAnnotatedClass(RealInfo.class);
	    private ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	            .applySettings(config.getProperties()).build();
	    private SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    		
	    	HttpSession session = request.getSession(false);

	        try(Session hib_session = sessionFactory.openSession();) {
	        	
	        	String userID = (String)session.getAttribute("userID");
	        	
	        	String password_final = request.getParameter("password_final");
	        	String userId_final = request.getParameter("userId_final");
	        	EncryptDecrypt end = new EncryptDecrypt();
	        	CredInfo_internal going_user = hib_session.get(CredInfo_internal.class, userID);
	    
	            if(userID.equals(userId_final) && password_final.equals(end.decrypt(going_user.getPassword()))) {
	                Transaction tx = hib_session.beginTransaction();
	                hib_session.remove(going_user);
	                tx.commit();
	               
	                response.sendRedirect("index.jsp");
	            } else {
	                response.sendRedirect("ProblemOccured.jsp");
	            }
	        } catch(Exception e) {
	            e.printStackTrace(); 
	            response.sendRedirect("ProblemOccured.jsp");
	        } finally {
	            sessionFactory.close();
	        }
	    }
}

