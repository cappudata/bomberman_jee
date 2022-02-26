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
 * Servlet implementation class menu
 */
@WebServlet("/menu")
public class history extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public history() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tools tool = new Tools();
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		request.setAttribute("games", tool.recupererGames(username));	
		this.getServletContext().getRequestDispatcher("/WEB-INF/history.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
