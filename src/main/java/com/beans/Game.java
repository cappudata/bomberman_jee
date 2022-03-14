package com.beans;

public class Game {
	private int _idgame;
	private String _username;
	private String _statusGame;
	private int _nbrplayer;
	private int adversaireTue;
	private int nbre_mort;


	public int getAdversaireTue() {
		return adversaireTue;
	}

	public void setAdversaireTue(int adversaireTue) {
		this.adversaireTue = adversaireTue;
	}

	public int getNbre_mort() {
		return nbre_mort;
	}

	public void setNbre_mort(int nbre_mort) {
		this.nbre_mort = nbre_mort;
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
	

	public void set_statusGame(String _statusGame) {
		this._statusGame = _statusGame;
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
