package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.DAO.DaoException;
import com.DAO.DaoFactory;
import com.DAO.HTTPDao;
import com.tools.Tools;

/**
 * Servlet implementation class manageSkin
 */
@WebServlet(name="manageSkin", urlPatterns="/manageskin")
public class manageSkin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HTTPDao mysqldao;  
	
    public manageSkin() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException{
    	DaoFactory daofactory = DaoFactory.getInstance();
    	this.mysqldao = daofactory.getMysqlDao();
    	
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("myaccount");
	}

	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject obj = Tools.getSkinSelected(request);
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		obj.put("username",username);
		
		try {
			this.mysqldao.udpdateSkin(obj);
			
		} catch (DaoException e) {
			
			e.printStackTrace();
		}
		response.sendRedirect("myaccount");
	}

}
