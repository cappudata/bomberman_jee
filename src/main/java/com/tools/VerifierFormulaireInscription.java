package com.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.DAO.DaoException;
import com.DAO.DaoFactory;
import com.DAO.HTTPDao;
import com.beans.Utilisateur;

public class VerifierFormulaireInscription {
	private boolean allOK;
	
	public VerifierFormulaireInscription() {
		this.allOK = false;
	}
	
	public void verifie(HttpServletRequest request) throws DaoException {
		String username = request.getParameter("username");
		String pass1 = request.getParameter("password1");
		String pass2 = request.getParameter("password2");
		
		DaoFactory daofactory = DaoFactory.getInstance();
    	HTTPDao mysqldao = daofactory.getMysqlDao();
    	
    	//verification que le nom soit libre
    	try {
			Utilisateur user = mysqldao.getUserByID(username);
			if(user.getUsername() != null)
				throw new DaoException("Ce pseudo n'est pas libre");
		} catch (DaoException e) {
			throw new DaoException(e.getMessage());
		}
    	
    	//verification que les deux mot de passe correspondent 
    	if(!pass1.equals(pass2)) 
    		throw new DaoException("Vos deux mots de passe ne correspondent pas");
    	else {
    		Pattern passwordpattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
    		Matcher match = passwordpattern.matcher(pass1);
    		boolean result = match.matches();
    		
    		if(!result) {
    			String patternerror = "Le mot de passe doit contenir:";
    			if(pass1.length() < 8)
    				patternerror += "\n -au moins 8 caractères alphanumériques";
    			
    			patternerror +="\n -au moins une majiscule et une miniscule";
    			throw new DaoException(patternerror);
    		}
    	}
    	this.allOK = true;
	}
	
	public void verifieUpdate(HttpServletRequest request) throws DaoException{
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		String mail = request.getParameter("useremail");
		
		if(mail != null) {
			Pattern passwordpattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    		Matcher match = passwordpattern.matcher(mail);
    		boolean result = match.matches();
    		if(!result) {
    			throw new DaoException("mail invalide");
    		}
		}
		if(pass1 != null) {
			if(!pass1.equals(pass2)) 
	    		throw new DaoException("Vos deux mots de passe ne correspondent pas");
	    	else {
	    		Pattern passwordpattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
	    		Matcher match = passwordpattern.matcher(pass1);
	    		boolean result = match.matches();
	    		
	    		if(!result) {
	    			String patternerror = "Le mot de passe doit contenir:";
	    			if(pass1.length() < 8)
	    				patternerror += "\n -au moins 8 caractères alphanumériques";
	    			
	    			patternerror +="\n -au moins une majiscule et une miniscule";
	    			throw new DaoException(patternerror);
	    		}
	    	}
		}
		
		
		this.allOK = true;
			
	}
	public boolean isAllOK() {
		return allOK;
	}
	

	
}
