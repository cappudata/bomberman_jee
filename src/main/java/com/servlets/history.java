package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.DaoException;
import com.DAO.DaoFactory;
import com.DAO.HTTPDao;
import com.beans.Game;
import com.tools.Tools;


/**
 * Servlet implementation class menu
 */
@WebServlet("/menu")
public class history extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HTTPDao mysqldao;  
   
    public history() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException{
    	DaoFactory daofactory = DaoFactory.getInstance();
    	this.mysqldao = daofactory.getMysqlDao();
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		List<Game> Games = new ArrayList<Game>();
		try {
			 Games = this.mysqldao.getHistorique(username);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		request.setAttribute("games", Games);
		request.setAttribute("nbrm", Tools.getNombreMort(Games));
		request.setAttribute("nbra", Tools.getNombreTue(Games));
		request.setAttribute("egal", Tools.getPartieNulle(Games));
		request.setAttribute("vict", Tools.getPartieGagne(Games));
		request.setAttribute("lose", Tools.getPartiePerdu(Games));
		if(username != null)
			this.getServletContext().getRequestDispatcher("/WEB-INF/history.jsp").forward(request, response);
		else 
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
