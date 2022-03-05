package com.beans;

public class Utilisateur {
	private String _username;
	private String _password;
	private String  profilepic;
	private String mail;
		
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setUsername(String username) {
		this._username = username;
	}
	
	public void setPassword(String password) {
		this._password = password;
	}
	
	public String getUsername() {
		return this._username;
	}
	
	public String getPassword() {
		return this._password;
	}

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}
}
