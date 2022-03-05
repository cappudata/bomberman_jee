package com.DAO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.beans.Game;
import com.beans.Utilisateur;

public interface DaoInterface {
	
	public void ajouterUtilisateur(Utilisateur user) throws DaoException ;
	public Utilisateur getUserByID(String Identifiant) throws DaoException ;
	public List<Game> getHistorique(String User) throws DaoException;
	public void addGame(Game game) throws DaoException; 
	public void updateUser(Utilisateur user) throws DaoException; 
}