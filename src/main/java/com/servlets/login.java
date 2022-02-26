package com.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.Utilisateur;
import com.tools.Tools;

/**
 * Servlet implementation class acceuil
 */
@WebServlet("/acceuil")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public login() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tools tool = new Tools();

		String username = request.getParameter("username");
		String userpass = request.getParameter("password");
			
		// session pour stocker les un utilisateur
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		
		if (tool.verifierAccount(username, userpass)) {	
			System.out.println("You are connected");
			this.getServletContext().getRequestDispatcher("/WEB-INF/connected.jsp").forward(request, response);
		} else {
			System.out.println("Username or password is not correct"); 
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}	
		
	}

}
