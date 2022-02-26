package com.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.Game;
import com.beans.Utilisateur;

public class Tools {
		private Connection connexion;
		private Statement statement;
		private ResultSet resultat;
		
		public List<Utilisateur> recupererUtilisateurs() {
	        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	        
	        loadDatabase();			
	        try {
	            statement = connexion.createStatement();

	            // Exécution de la requête
	            resultat = statement.executeQuery("SELECT username, password FROM users;");

	            // Récupération des données
	            while (resultat.next()) {
	                String username = resultat.getString("username");
	                String password = resultat.getString("password");
	                
	                Utilisateur utilisateur = new Utilisateur();
	                utilisateur.setUsername(username);
	                utilisateur.setPassword(password);
	                
	                utilisateurs.add(utilisateur);
	            }
	        } catch (SQLException e) {
	        } finally {
	            // Fermeture de la connexion	        	
	        	closeDatabase();
	        }  
	        return utilisateurs;
	    }
		
		
		public List<Game> recupererGames(String uname) {
	        List<Game> games = new ArrayList<Game>();
	        loadDatabase();	
	        
	        try {
	            PreparedStatement preparedStatement = connexion.prepareStatement("SELECT idgame, username, status, nbr_player FROM games where username= ?;");
	            preparedStatement.setString(1, uname);
				
	            resultat = preparedStatement.executeQuery();
	            
	            while (resultat.next()) {
	                int idgame = resultat.getInt("idgame");
	                String username = resultat.getString("username");
	                String status = resultat.getString("status");
	                int nbr_player = resultat.getInt("nbr_player");

	                Game game = new Game(idgame, username, status, nbr_player);
	                games.add(game);
	            }
	        } catch (SQLException e) {
	        } finally {
	        	closeDatabase();
	        }
	        return games;
	    }
		
	
		
		
		public boolean verifierAccount(String username, String userpass) {			
			List<Utilisateur> listUsers = recupererUtilisateurs();
			for(int i=0; i<listUsers.size(); i++) {
				if (listUsers.get(i).getUsername().equals(username) && listUsers.get(i).getPassword().equals(userpass))
					return true;
			}
			return false;
		}
		
		
		public boolean verifierUsername(String uname ) {
			 ArrayList<String> ListUsernames = new ArrayList<String>();
		        Statement statement = null;
		        ResultSet resultat = null;

		        loadDatabase();   
		        try {
		            statement = connexion.createStatement();
		            resultat = statement.executeQuery("SELECT username FROM users;");

		            while (resultat.next()) {
		                String username = resultat.getString("username");
		                ListUsernames.add(username);
		            }
		        } catch (SQLException e) {
		        } finally {
		        	closeDatabase();
		        }
		        
		        for( int i=0; i<ListUsernames.size(); i++) {
		        	if (ListUsernames.get(i).equals(uname)) {
		        		return true;
		        	}	
		        }	
		        return false;
		}
		
		
	   private void loadDatabase() {
	       try {
	            Class.forName("org.mariadb.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	        }

	        try {
	            connexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bomberman_jee", "root", "");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
		
	   private void closeDatabase() {
           try {
               if (resultat != null)
                   resultat.close();
               if (statement != null)
                   statement.close();
               if (connexion != null)
                   connexion.close();
           } catch (SQLException ignore) {
           }
	   }
	   
	   
		public void ajouterUtilisateur(Utilisateur utilisateur) {
			loadDatabase();
		
			try {
				PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO users (username, password) VALUES(?, ?);");		
				preparedStatement.setString(1, utilisateur.getUsername());
				preparedStatement.setString(2, utilisateur.getPassword());
				
				preparedStatement.executeUpdate();	
				
				System.out.println("Created");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		public boolean doublecheckPassword(String pass1, String pass2) {
			if (pass1.equals(pass2)) 
				return true;
			return false;
		}
	
}
