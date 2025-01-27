package com.operational_servlets;

import java.io.IOException;

import java.util.Date;

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

import com.encrption_decryption.EncryptDecrypt;
import com.pojo.CredInfo_internal;
import com.pojo.RealInfo;

import jakarta.servlet.http.HttpSession;


public class Modified extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RealInfo realinfo_modify ;
    
    private SessionFactory sessionFactory ;
    
    private CredInfo_internal credinfo_internal ;

	
	@Override
    public void init() throws ServletException {
        Configuration config = new Configuration().configure("hibernate.cfg.xml")
            .addAnnotatedClass(RealInfo.class)
            .addAnnotatedClass(CredInfo_internal.class);
        
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties()).build();
        
        sessionFactory = config.buildSessionFactory(registry);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
        
        String userID = (String) session.getAttribute("userID");

        if (userID == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        try (Session hib_session = sessionFactory.openSession()) {
            Transaction tx = hib_session.beginTransaction();
            
            long provided_number = Long.parseLong(request.getParameter("Provided_number"));
            String domain = request.getParameter("New_Domain");
            String domain_user = request.getParameter("New_UserId_of_Domain");
            String domain_user_password = request.getParameter("New_Password_of_User_ID");

            credinfo_internal = hib_session.find(CredInfo_internal.class, userID);
            
            EncryptDecrypt end = new EncryptDecrypt();
            
            String encryptedPasscode = end.encrypt(domain_user_password);
            
            realinfo_modify = new RealInfo(domain, domain_user, encryptedPasscode , new Date(), credinfo_internal);
            
            hib_session.persist(realinfo_modify);
            
            hib_session.remove(new RealInfo(provided_number)); 
        
        tx.commit();
        
        //RequestDispatcher req = request.getRequestDispatcher("View_passwords");
        //req.forward(request, response);
        response.sendRedirect("Info.jsp");
        }catch(Exception e ) {
        	e.printStackTrace();
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
