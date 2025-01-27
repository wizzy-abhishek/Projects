package com.servlets;

import java.io.IOException;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.pojo.CredInfo_internal;
import com.pojo.RealInfo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class View_passwords extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(RealInfo.class).addAnnotatedClass(CredInfo_internal.class);
	
	private StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		
	private SessionFactory sessionFactory = config.buildSessionFactory(registry);
	
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		session.getAttribute("userName");
		
		
        
        String userID = (String)session.getAttribute("userID");

        if (userID == null ) {
            response.sendRedirect("index.jsp");
            return;
        }
       
        ArrayList<RealInfo> list = new ArrayList<>();
        
        try(Session hib_session = sessionFactory.getCurrentSession()){
        	Transaction tx = hib_session.beginTransaction();
        	CredInfo_internal cred = hib_session.load(CredInfo_internal.class, userID);
        	list.addAll(cred.getRealinfo());
        	tx.commit();
        }catch(Exception e ) {
        	System.out.println(e.getStackTrace());
        	response.sendRedirect("ProblemOccured.jsp");
        }
        session.setAttribute("realInfoList", list);
        RequestDispatcher reqDis = request.getRequestDispatcher("Details_Action.jsp");
        reqDis.forward(request, response);
        
	}
}
