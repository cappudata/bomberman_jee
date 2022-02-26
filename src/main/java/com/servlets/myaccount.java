package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tools.Tools;

/**
 * Servlet implementation class myaccount
 */
@WebServlet("/myaccount")
public class myaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myaccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tools tool = new Tools();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		//Il faut faire une fonction pour récupérer les informations 
		//Nombre de game
		//Nombre game win + lose
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/myaccount.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
