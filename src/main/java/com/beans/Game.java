package com.beans;

public class Game {
	private int _idgame;
	private String _username;
	private String _statusGame;
	private int _nbrplayer;
	

	public Game (int idgame, String username, String statusGame, int nbrPlayer) {
		this._idgame = idgame;
		this._username = username;
		this._statusGame = statusGame;
		this._nbrplayer = nbrPlayer;
	}
	
	
	// Getter et Setter
	public int get_idgame() {
		return _idgame;
	}
	
	public void set_idgame(int _idgame) {
		this._idgame = _idgame;
	}
	
	public String get_username() {
		return _username;
	}
	
	public void set_username(String _username) {
		this._username = _username;
	}
	

	public String  get_statusGame() {
		return this._statusGame;
	}
	
	public int get_nbrplayer() {
		return _nbrplayer;
	}
	
	public void set_nbrplayer(int _nbrplayer) {
		this._nbrplayer = _nbrplayer;
	}
	
	
}
