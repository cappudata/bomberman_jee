package com.DAO;

import java.util.List;

import org.json.simple.JSONObject;

import com.beans.Game;
import com.beans.ShopItem;
import com.beans.Utilisateur;

public interface DaoInterface {
	
	public void ajouterUtilisateur(Utilisateur user) throws DaoException ;
	public Utilisateur getUserByID(String Identifiant) throws DaoException ;
	public List<Game> getHistorique(String User) throws DaoException;
	public void addGame(Game game) throws DaoException; 
	public void updateUser(Utilisateur user) throws DaoException;
	public List<ShopItem> getShopItem() throws DaoException;
	public List<ShopItem> getUserItem(String username) throws DaoException;
	public void ajouterItem(String username, int iditem) throws DaoException;
	public void udpdateSkin(JSONObject obj) throws DaoException;
	public float getBomcoin(String username) throws DaoException;
}
