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
import com.beans.Utilisateur;
import com.tools.Tools;
import com.tools.VerifierFormulaireInscription;


@WebServlet("/createaccount")
public class createaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HTTPDao mysqldao;   
  
    public createaccount() {
        super();
    }

    public void init() throws ServletException{
    	DaoFactory daofactory = DaoFactory.getInstance();
    	this.mysqldao = daofactory.getMysqlDao();
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/createaccount.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("username");
		String pass1 = request.getParameter("password1");
		
		
		VerifierFormulaireInscription verification = new VerifierFormulaireInscription();
		try {
			verification.verifie(request);
			if(verification.isAllOK()) {
				Utilisateur utilisateur = new Utilisateur();	
				String password = Tools.HashPassword(pass1);
				utilisateur.setUsername(request.getParameter("username"));
				utilisateur.setPassword(password);
				utilisateur.setProfilepic("images/img-02.png");
				
				this.mysqldao.ajouterUtilisateur(utilisateur);
				
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				this.getServletContext().getRequestDispatcher("/WEB-INF/connected.jsp").forward(request, response);
			}
		} catch (DaoException e) {
			request.setAttribute("erreur", e.getMessage());
			this.getServletContext().getRequestDispatcher("/WEB-INF/createaccount.jsp").forward(request, response);
		}
		
		
	
		
		
	}



}
