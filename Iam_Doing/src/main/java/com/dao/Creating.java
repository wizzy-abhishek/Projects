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
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(CredInfo_internal.class)
                    .addAnnotatedClass(RealInfo.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String userId = request.getParameter("userID");
        long userMob = Long.parseLong(request.getParameter("userMob"));
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");

        if (password.equals(confirm_password)) {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = session.beginTransaction();
                try {
                    String encryptedPassword = enc.encrypt(password);
                    CredInfo_internal newUser = new CredInfo_internal(userId, encryptedPassword, userName, userMob);
                    session.persist(newUser);
                    tx.commit();
                    response.sendRedirect("index.jsp");
                } catch (Exception e) {
                    if (tx != null) tx.rollback();
                    e.printStackTrace();
                    response.sendRedirect("ProblemOccured.jsp");
                }
            }
        } else {
            response.sendRedirect("PasswordMismatch.jsp");
        }
    }
}
