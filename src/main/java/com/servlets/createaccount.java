package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Utilisateur;
import com.tools.Tools;


@WebServlet("/createaccount")
public class createaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public createaccount() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/createaccount.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tools tool = new Tools();
		
		String username = request.getParameter("username");
		String pass1 = request.getParameter("password1");
		String pass2 = request.getParameter("password2");
		
		
		
		if (tool.doublecheckPassword(pass1, pass2)) {
			if (!tool.verifierUsername(username))  {	
				Utilisateur utilisateur = new Utilisateur();			
				utilisateur.setUsername(request.getParameter("username"));
				utilisateur.setPassword(request.getParameter("password1"));
				
				tool.ajouterUtilisateur(utilisateur);
			} else {
				System.out.println("Username is already taken"); 
			}
		} else {
			System.out.println("Your password is not match");
		}
		
		//request.setAttribute("users" , users.recupererUtilisateurs());
		this.getServletContext().getRequestDispatcher("/WEB-INF/createaccount.jsp").forward(request, response);
		
		
	}



}
