package com.operational_servlets;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.pojo.CredInfo_internal;
import com.pojo.RealInfo;

import jakarta.servlet.http.HttpSession;


public class Deleted extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RealInfo realinfo_delete ;
    
    private SessionFactory sessionFactory ;

	@Override
    public void init() throws ServletException {
        Configuration config = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(RealInfo.class)
            .addAnnotatedClass(CredInfo_internal.class);
        
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties()).build();
        
        sessionFactory = config.buildSessionFactory(registry);
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 HttpSession session = request.getSession(false);
	        
	        String userID = (String) session.getAttribute("userID");

	        if (userID == null) {
	            response.sendRedirect("index.jsp");
	            return;
	        }
	        try (Session hib_session = sessionFactory.openSession()) {
	            Transaction tx = hib_session.beginTransaction();
	            
	        long provided_number = Long.parseLong( request.getParameter("Provided_number"));
	        
            realinfo_delete = new RealInfo(provided_number);
            
            hib_session.remove(realinfo_delete);
            
            tx.commit();
            
            //RequestDispatcher req = request.getRequestDispatcher("View_passwords");
            //req.forward(request, response);
            response.sendRedirect("Info.jsp");
	        }catch(Exception e ) {
	        	response.sendRedirect("ProblemOccured.jsp");
	        }
	}
	
    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
	
}
