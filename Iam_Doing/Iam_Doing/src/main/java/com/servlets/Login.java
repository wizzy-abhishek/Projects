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


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Login() {
        super();
    }
	
	Connecting_Buddy_Dao loginDao = new Connecting_Buddy_Dao();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher requestDispatcher ;
		try {
			String uId = request.getParameter("uId");
			String password = request.getParameter("password");		
				
			if(loginDao.verify(uId, password)) 
			{
				HttpSession session = request.getSession();
				session.setAttribute("userID", uId);
				
				String userName = new CredInfo_internal().getYourName();
				System.out.println(userName);
				session.setAttribute("username", userName);
				requestDispatcher = request.getRequestDispatcher("LoggedIn");
				requestDispatcher.forward(request, response);
			
			}else {
				response.sendRedirect("index.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}