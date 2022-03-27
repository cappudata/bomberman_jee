package com.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.DaoException;
import com.DAO.DaoFactory;
import com.DAO.HTTPDao;

/**
 * Servlet implementation class acceuil
 */
@WebServlet("/acceuil")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HTTPDao mysqldao;
  
    public login() {
        super();

    }

    public void init() throws ServletException{
    	DaoFactory daofactory = DaoFactory.getInstance();
    	this.mysqldao = daofactory.getMysqlDao();
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirect = request.getParameter("redirect");
		request.setAttribute("redirect", redirect);
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String username = request.getParameter("username");
		String userpass = request.getParameter("password");
		String redirect = request.getParameter("redirect");;
		System.out.println(redirect);
		
		try {
			if (this.mysqldao.verifierAccount(username, userpass)) {
				// session pour stocker les un utilisateur
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				if(redirect != null && !redirect.isEmpty())
					response.sendRedirect(redirect);
				else
					response.sendRedirect("connected");
			} else {
				
				throw new DaoException("Username or password is not correct");
			}
		} catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}
