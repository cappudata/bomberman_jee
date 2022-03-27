package com.DAO;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.beans.Game;
import com.beans.ShopItem;
import com.beans.Utilisateur;
import com.tools.Tools;
import io.github.cdimascio.dotenv.Dotenv;

public class HTTPDao implements DaoInterface {
	
	//Dotenv env = Dotenv.load();
	private final static String IP="172.18.93.221";
	@SuppressWarnings("unchecked")
	@Override
	public void ajouterUtilisateur(Utilisateur user) throws DaoException {
	  
	    try {
	    	
	        JSONObject json = new JSONObject();
	        json.put("username", user.getUsername());
	        json.put("password", user.getPassword());
	        json.put("profilepic", user.getProfilepic());

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest requests = HttpRequest.newBuilder()
                    .uri(URI.create("http://"+IP+":8080/projet-bomberman-api/user"))
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();
            HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
            String body = result.toString();
           
            if(!Tools.getPostStatus(body).equals("200")) {
            	throw new DaoException("Une erreur est survenue !");
            }
        }catch (IOException | InterruptedException e) {
        	throw new DaoException("Impossible de communiquer avec la base de données");
        }

	    
	}

	@Override
	public Utilisateur getUserByID(String Identifiant) throws DaoException  {
		    Utilisateur user = new Utilisateur();
		    Dotenv env =  Dotenv.configure()
		    		  .directory("C:/Users/arist/Documents/Bomberman_JEE/bomberman-with-vi/conf")
		    		  .load();
		    try {
	            HttpClient client = HttpClient.newHttpClient();

	            HttpRequest requests = HttpRequest.newBuilder()
	                    .uri(URI.create("http://"+IP+":8080/projet-bomberman-api/user?name="+Identifiant))
	                    .GET()
	                    .header("Accept", "application.json")
	                    .build();
	            HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
	            JSONParser parser = new JSONParser();
	            JSONObject paquet = (JSONObject)parser.parse(result.body());
	            
	            user.setUsername((String)paquet.get("username"));
	            user.setPassword((String)paquet.get("password"));
	            user.setProfilepic((String)paquet.get("profilepic"));
	            user.setMail((String)paquet.get("mail"));
	        }catch (IOException | InterruptedException e) {
	        	throw new DaoException("Impossible de communiquer avec la base de données");
	        } catch (ParseException e) {
	        	e.printStackTrace();
	        	throw new DaoException("Impossible de communiquer avec la base de données");
			}

		
		return user;
	}

	@Override
	public List<Game> getHistorique(String User) throws DaoException {
		List<Game> games = new ArrayList<Game>();
		try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest requests = HttpRequest.newBuilder()
                    .uri(URI.create("http://"+IP+":8080/projet-bomberman-api/game?name="+User))
                    .GET()
                    .header("Accept", "application.json")
                    .build();
            HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
            JSONParser parser = new JSONParser();
            JSONObject paquet = (JSONObject)parser.parse(result.body());
            JSONArray listOfGame= (JSONArray) paquet.get("listOfGame");
            
            games = Tools.toListOfGame(listOfGame);
            
        }catch (IOException | InterruptedException e) {
        	throw new DaoException("Impossible de communiquer avec la base de données");
        } catch (ParseException e) {			
        	throw new DaoException("Impossible de communiquer avec la base de données");
		}
        return games;
	}

	   public boolean verifierAccount(String username, String userpass) throws DaoException {			
			Utilisateur user;
			
			try {
				user = this.getUserByID(username);
				
			} catch (DaoException e) {
				e.printStackTrace();
				throw new DaoException(e.getMessage());
			}
				if (user.getPassword() != null) {
					
					if(user.getPassword().equals(Tools.HashPassword(userpass)))
						return true;
					else
						return false;
				}
				else
					return false;
				
		}

	@SuppressWarnings("unchecked")
	@Override
	public void addGame(Game stat) throws DaoException {
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("idgame", stat.get_idgame());
		jsonobject.put("username", stat.get_username());
		jsonobject.put("nbrplayer", stat.get_nbrplayer());
		jsonobject.put("gamestatus", stat.get_statusGame());
		
		try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest requests = HttpRequest.newBuilder()
                    .uri(URI.create("http://"+IP+":8080/projet-bomberman-api/game"))
                    .POST(HttpRequest.BodyPublishers.ofString(jsonobject.toString()))
                    .build();
            HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
            String body = result.toString();
            
            
            if(!Tools.getPostStatus(body).equals("200")) {
            	throw new DaoException("Une erreur est survenue !");
            }          
        }catch (IOException | InterruptedException e) {
        	throw new DaoException("Impossible de communiquer avec la base de données");
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUser(Utilisateur user) throws DaoException {
		
		 try {
		    	
		        JSONObject json = new JSONObject();
		        json.put("username", user.getUsername());
		        json.put("password", user.getPassword());
		        json.put("profilepic", user.getProfilepic());
		        json.put("mail", user.getMail());

	            HttpClient client = HttpClient.newHttpClient();

	            HttpRequest requests = HttpRequest.newBuilder()
	                    .uri(URI.create("http://"+IP+":8080/projet-bomberman-api/user"))
	                    .PUT(HttpRequest.BodyPublishers.ofString(json.toString()))
	                    .build();
	            HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
	            String body = result.toString();
	            if(!Tools.getPostStatus(body).equals("200")) {
	            	throw new DaoException("Une erreur est survenue !");
	            }
	        }catch (IOException | InterruptedException e) {
	        	throw new DaoException("Impossible de communiquer avec la base de données");
	        }

	}

	@Override
	public List<ShopItem> getShopItem() throws DaoException {
		List<ShopItem> items = new ArrayList<ShopItem>();
		try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest requests = HttpRequest.newBuilder()
                    .uri(URI.create("http://"+IP+":8080/projet-bomberman-api/shop"))
                    .GET()
                    .header("Accept", "application.json")
                    .build();
            HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
            JSONParser parser = new JSONParser();
            JSONObject paquet = (JSONObject)parser.parse(result.body());
           
            items = Tools.toListOfItem((JSONArray)paquet.get("listOfItem"));
           
            
        } catch (IOException | InterruptedException e) {
        	throw new DaoException("Impossible de communiquer avec la base de données");
        } catch (ParseException e) {			
        	throw new DaoException("Impossible de communiquer avec la base de données");
		}
		return items;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void ajouterItem(String username, int iditem) throws DaoException {
	
		
		try {
			JSONObject json = new JSONObject();
			json.put("username", username);
			json.put("iditem", iditem);
			
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest requests = HttpRequest.newBuilder()
					.uri(URI.create("http://"+IP+":8080/projet-bomberman-api/shop"))
					.POST(HttpRequest.BodyPublishers.ofString(json.toString()))
					.build();
			
	       HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
           String body = result.toString();
           
           
           if(!Tools.getPostStatus(body).equals("200")) {
        	   throw new DaoException("Une erreur est survenue !");
           }
       } catch (IOException | InterruptedException e) {
       		throw new DaoException("Impossible de communiquer avec la base de données");
       }		
	}
	
	
	public float getBomcoin(String username) throws DaoException {
		float bomcoin = 0;
		
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest requests = HttpRequest.newBuilder()
					.uri(URI.create("http://"+IP+":8080/projet-bomberman-api/bomcoin?name="+username))
					.GET()
                    .header("Accept", "application.json")
                    .build();
			
	         HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
	         JSONParser parser = new JSONParser();
	         JSONObject paquet = (JSONObject)parser.parse(result.body());
	            	         
	        double bomcoin_double = (double)paquet.get("bomcoin");
	        bomcoin = (float)bomcoin_double;
	         
         } catch (IOException | InterruptedException e) {
         	throw new DaoException("Impossible de communiquer avec la base de données");
         } catch (ParseException e) {
        	 e.printStackTrace();
         	throw new DaoException("Impossible de communiquer avec la base de données");
 		}
		
		return bomcoin;
		
	}

	@Override
	public List<ShopItem> getUserItem(String username) throws DaoException {
		 List<ShopItem> UserItems = new ArrayList<ShopItem>();
		 try {
	            HttpClient client = HttpClient.newHttpClient();

	            HttpRequest requests = HttpRequest.newBuilder()
	                    .uri(URI.create("http://"+IP+":8080/projet-bomberman-api/userItem?name="+username))
	                    .GET()
	                    .header("Accept", "application.json")
	                    .build();
	            HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
	            JSONParser parser = new JSONParser();
	            JSONObject paquet = (JSONObject)parser.parse(result.body());
	           
	            UserItems = Tools.toListOfItem((JSONArray)paquet.get("listOfItem"));
	           
	            
	        } catch (IOException | InterruptedException e) {
	        	throw new DaoException("Impossible de communiquer avec la base de données");
	        } catch (ParseException e) {	
	        	e.printStackTrace();
	        	throw new DaoException("Impossible de communiquer avec la base de données");
			}
		 
		return UserItems;
	}

	@Override
	public void udpdateSkin(JSONObject obj) throws DaoException {
		 try {

	            HttpClient client = HttpClient.newHttpClient();

	            HttpRequest requests = HttpRequest.newBuilder()
	                    .uri(URI.create("http://"+IP+":8080/projet-bomberman-api/userItem"))
	                    .PUT(HttpRequest.BodyPublishers.ofString(obj.toString()))
	                    .build();
	            HttpResponse<String> result = client.send(requests, HttpResponse.BodyHandlers.ofString());
	            String body = result.toString();
	            if(!Tools.getPostStatus(body).equals("200")) {
	            	throw new DaoException("Une erreur est survenue !");
	            }
	        }catch (IOException | InterruptedException e) {
	        	throw new DaoException("Impossible de communiquer avec la base de données");
	        }
		
	}
}
