package com.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;


public class Delete_account extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
		
		String userID = (String)session.getAttribute("userID");

        if (userID == null ) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        RequestDispatcher reqDis = request.getRequestDispatcher("GoodBye.jsp");
        reqDis.forward(request, response);
	}

}
