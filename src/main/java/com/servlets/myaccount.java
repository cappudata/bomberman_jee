package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.DAO.DaoException;
import com.DAO.DaoFactory;
import com.DAO.HTTPDao;
import com.beans.ShopItem;
import com.beans.Utilisateur;
import com.tools.Tools;
import com.tools.VerifierFormulaireInscription;



@WebServlet("/myaccount")
@MultipartConfig(
        fileSizeThreshold   = 1048576,  // 1 MB
        maxFileSize         = 10485760, // 10 MB
        maxRequestSize      = 52428800, // 5*10 MB
        location            = "C:\\Users\\arist\\Documents\\Bomberman_JEE\\bomberman-with-vi\\src\\main\\webapp\\images\\profilepic"
)
public class myaccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public static final int TAILLE_TAMPON = 10240;
	 public static final String CHEMIN_FICHIERS = "C:\\Users\\arist\\Documents\\Bomberman_JEE\\bomberman-with-vi\\src\\main\\webapp\\images\\";
	
    private HTTPDao mysqldao;   
   
    public myaccount() {
        super();
    }

    public void init() throws ServletException{
    	DaoFactory daofactory = DaoFactory.getInstance();
    	this.mysqldao = daofactory.getMysqlDao();
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		if(username != null) {
			try {
				Utilisateur user = this.mysqldao.getUserByID(username);
				float coins = this.mysqldao.getBomcoin(username);
				List<ShopItem> Useritems = this.mysqldao.getUserItem(username);
				//categorisation 
				List<ShopItem> wall = new ArrayList<ShopItem>();
				List<ShopItem> wallEx = new ArrayList<ShopItem>();
				List<ShopItem> map = new ArrayList<ShopItem>();
				List<ShopItem> all = new ArrayList<ShopItem>();
				for(ShopItem item : Useritems) {
					if(item.getCategorie().equals("WALL"))
						wall.add(item);
					else if(item.getCategorie().equals("WALL EXT"))
						wallEx.add(item);
					else if(item.getCategorie().equals("MAP"))
						map.add(item);
					else
						all.add(item);
						
				}
				
				if(user.getMail() == null)
					user.setMail("");
				request.setAttribute("user", user);
				request.setAttribute("coins", coins);
				request.setAttribute("wall", wall);
				request.setAttribute("wallEx", wallEx);
				request.setAttribute("map", map);
				request.setAttribute("all", all);
			} catch (DaoException e) {
				
				e.printStackTrace();
			}
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/myaccount.jsp").forward(request, response);
		}		
		else 
			response.sendRedirect("login?redirect=myaccount");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part = request.getPart("photodeprofil");
		String nomFichier = Tools.getNomFichier(part);
		
		if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
             Tools.ecrireFichier(part, nomFichier, CHEMIN_FICHIERS,TAILLE_TAMPON);

            
        }
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		VerifierFormulaireInscription verification = new VerifierFormulaireInscription();
		try {
			verification.verifieUpdate(request);
			Utilisateur user = new Utilisateur();
			user.setUsername(username);
			if(!nomFichier.isEmpty())
				user.setProfilepic("images/"+nomFichier);
			
			if(verification.isAllOK()) {
				
				
				String pass1 = request.getParameter("pass1");
				if(pass1 != null)
					user.setPassword(Tools.HashPassword(pass1));
				String mail = request.getParameter("useremail");
				
				if(mail != null)
					user.setMail(mail);
				
				try {
					
					this.mysqldao.updateUser(user);
					request.setAttribute("message", "vos données ont bien été mises à jour ");
				} catch (DaoException e) {
					request.setAttribute("message", e.getMessage());
					e.printStackTrace();
				}
			}
		} catch (DaoException e1) {
			
			request.setAttribute("erreur", e1.getMessage());
			this.doGet(request, response);
		}
	
		
		
		
		this.doGet(request, response);
		
	}

}
