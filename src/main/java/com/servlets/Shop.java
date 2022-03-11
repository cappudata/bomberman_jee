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
import com.beans.ShopItem;


@WebServlet(name="Shop", urlPatterns="/buyitem")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HTTPDao httpdao;        
  
    public Shop() {
        super();
       
    }
    public void init() throws ServletException{
    	DaoFactory daofactory = DaoFactory.getInstance();
    	this.httpdao = daofactory.getMysqlDao();
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		List<ShopItem> items = new ArrayList<ShopItem>();
		try {
			items = this.httpdao.getShopItem();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("Items", items);
		if(username != null)
			this.getServletContext().getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
		else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
