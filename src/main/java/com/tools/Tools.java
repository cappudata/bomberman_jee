package com.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.beans.Game;
import com.beans.ShopItem;
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
	            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bomberman_jee", "ArisLord", "Luz10Empire1197");
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
	
		public static String HashPassword(String password) {
			try {
				   MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				   
				   messageDigest.update(password.getBytes());
				   
				   byte[] resultByteArray = messageDigest.digest();
				   
				   StringBuilder sb = new StringBuilder();
				   
				   for (byte b : resultByteArray) {
				    sb.append(String.format("%02x", b));
				   }
				   
				   return sb.toString();
				   
				  } catch (NoSuchAlgorithmException e) {
				   e.printStackTrace();
				  }
				  
				  return "";
				 
		}
		public static List<Game> toListOfGame(JSONArray array){
			List<Game> Games = new ArrayList<Game>();
			
			for(Object obj : array) {
				JSONObject json = (JSONObject) obj;
				long id = (long) json.get("idgame");
				long nbrplayer = (long) json.get("nbrplayer");
				long nbrm = (long) json.get("nbrm");
				long nbra = (long) json.get("nbra");

				Game game = new Game();
				game.set_username((String)json.get("username"));
				game.set_nbrplayer((int)nbrplayer);
				game.set_idgame((int)id);
				game.set_statusGame((String) json.get("gamestatus"));
				game.setAdversaireTue((int)nbra);
				game.setNbre_mort((int)nbrm);
				Games.add(game);				
			}
			
			return Games;
		}
		public static List<ShopItem> toListOfItem(JSONArray array){
			List<ShopItem> Items = new ArrayList<ShopItem>();
			
			for(Object obj : array) {
				JSONObject json = (JSONObject) obj;
				String name = (String)json.get("name");
				String desc = (String)json.get("desc");
				String image = (String)json.get("images");
				double price = (double)json.get("price");
				long rate = (long)json.get("rate");
				
				ShopItem item = new ShopItem();
				item.setName(name);
				item.setDescription(desc);
				item.setImage(image);
				item.setPrice((float)price);
				item.setRate((int)rate);
				
				Items.add(item);
			}
			
			return Items;
		}
		
		public static String getPostStatus(String body) {
				String[] part = body.split(" ");
	            String status = part[part.length-1];
	            
	            return status;
		}
		
		 public static String getNomFichier( Part part ) {
		        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
		            if ( contentDisposition.trim().startsWith( "filename" ) ) {
		                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
		            }
		        }
		        return null;
		    }


		public static void ecrireFichier(Part part, String nomFichier, String cheminFichiers,int TAILLE_TAMPON) {
		       BufferedInputStream entree = null;
		        BufferedOutputStream sortie = null;
		        try {
		            try {
						entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            try {
						sortie = new BufferedOutputStream(new FileOutputStream(new File(cheminFichiers + nomFichier)), TAILLE_TAMPON);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		            byte[] tampon = new byte[TAILLE_TAMPON];
		            int longueur;
		            try {
						while ((longueur = entree.read(tampon)) > 0) {
						    sortie.write(tampon, 0, longueur);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        } finally {
		            try {
		                entree.close();
		            } catch (IOException ignore) {
		            }
		        }
			
		}   
		
		public static int getNombreMort(List<Game> game) {
			int nbr = 0 ;
			for(Game jeu : game) {
				nbr += jeu.getNbre_mort();
			}
			
			return nbr;
		}
		
		public static int getNombreTue(List<Game> game) {
			int nbr = 0 ;
			for(Game jeu : game) {
				nbr += jeu.getAdversaireTue();
			}
			
			return nbr;
		}
		
		public static int getPartieGagne(List<Game> game) {
			int nbr = 0 ;
			for(Game jeu : game) {
				if(jeu.get_statusGame().equals("VICTOIRE"))
					++nbr;
			}
			
			return nbr;
		}
		public static int getPartiePerdu(List<Game> game) {
			int nbr = 0 ;
			for(Game jeu : game) {
				if(jeu.get_statusGame().equals("DEFAITE"))
					++nbr;
			}
			
			return nbr;
		}
		public static int getPartieNulle(List<Game> game) {
			int nbr = 0 ;
			for(Game jeu : game) {
				if(jeu.get_statusGame().equals("EGALITE"))
					++nbr;
			}
			
			return nbr;
		}
		
}
