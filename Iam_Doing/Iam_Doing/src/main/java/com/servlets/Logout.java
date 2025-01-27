package com.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;


public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("userID");
		session.removeAttribute("password");
		session.invalidate();
		response.setHeader("Cache-Control", "no-cache , no-store , must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.sendRedirect("index.jsp");
		
	}

}
