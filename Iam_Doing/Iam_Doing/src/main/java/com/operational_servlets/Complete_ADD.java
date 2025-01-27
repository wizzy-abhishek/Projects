package com.operational_servlets;

import java.io.IOException;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.encrption_decryption.EncryptDecrypt;
import com.pojo.CredInfo_internal;
import com.pojo.RealInfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Complete_ADD extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private RealInfo realinfo_add;
    private CredInfo_internal credinfo_detail;
    
    private SessionFactory sessionFactory;

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

            String domain = request.getParameter("Domain");
            String domain_user = request.getParameter("UserId_of_Domain");
            String domain_user_password = request.getParameter("Password_of_User_ID");

            credinfo_detail = hib_session.get(CredInfo_internal.class, userID);
            
            EncryptDecrypt end = new EncryptDecrypt();
            
            String encryptedPasscode = end.encrypt(domain_user_password);
            
            realinfo_add = new RealInfo(domain, domain_user, encryptedPasscode , new Date(), credinfo_detail);

            hib_session.persist(realinfo_add);

            tx.commit();

            //RequestDispatcher req = request.getRequestDispatcher("View_passwords");
            response.sendRedirect("Info.jsp");
        } catch (Exception e) {
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
