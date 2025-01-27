package com.servlets;

import java.io.IOException;


import com.dao.Connecting_Buddy_Dao;
import com.pojo.CredInfo_internal;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(Login.class);
    private Connecting_Buddy_Dao loginDao;

    public Login() {
        super();
    }

    @Override
    public void init() throws ServletException {
        loginDao = new Connecting_Buddy_Dao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uId = request.getParameter("uId");
        String password = request.getParameter("password");
        RequestDispatcher requestDispatcher;

        try {
            if (loginDao.verify(uId, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("userID", uId);

                CredInfo_internal userInfo = loginDao.getUserInfo(uId);
                String userName = userInfo.getYourName();
                logger.info("User logged in: {}", userName);
                session.setAttribute("username", userName);

                requestDispatcher = request.getRequestDispatcher("LoggedIn");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            logger.error("Error during login", e);
            request.setAttribute("errorMessage", "An internal error occurred. Please try again later.");
            requestDispatcher = request.getRequestDispatcher("error.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
