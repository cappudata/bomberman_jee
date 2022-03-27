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
import com.tools.Tools;


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
			response.sendRedirect("login?redirect=buyitem");
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String iditem_String = request.getParameter("item_game");
		String itemPrice_String = request.getParameter("item_price");
		
		int iditem = Integer.parseInt(iditem_String);  
		float itemPrice = Float.parseFloat(itemPrice_String);
		
		float bomcoin = 0;
		String message = "";
		
		try {
			List<ShopItem> Useritems = this.httpdao.getUserItem(username);
			if(!Tools.userNotHasItem(Useritems,iditem)) {
				bomcoin = this.httpdao.getBomcoin(username);
				if (bomcoin > 0 && bomcoin >= itemPrice) {
					this.httpdao.ajouterItem(username, iditem);
					message = "Votre achat a √©t√© bien effectu√©";
				} else {
					message = "Vous n'avez pas assez de bomcoin";
				}
			}
			else {
				message = "vous possedez dÈj‡ cet item";
			}
			
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		
		
		List<ShopItem> items = new ArrayList<ShopItem>();
		try {
			items = this.httpdao.getShopItem();
			request.setAttribute("buy_message",message);
		} catch (DaoException e) {
			request.setAttribute("buy_message",e.getMessage());
		}
		
		request.setAttribute("Items", items);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/shop.jsp").forward(request, response);
	}

}
