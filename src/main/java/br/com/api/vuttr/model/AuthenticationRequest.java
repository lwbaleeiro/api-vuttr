package br.com.api.vuttr.model;

public class AuthenticationRequest {

	private String username;
	private String password;

	public AuthenticationRequest() {

	}
	
	public Boolean isCredentialsValid() {
		if (this.username == null ||  this.password == null 
				|| this.username.trim().equals("") || this.password.trim().equals("")) {
			return false;
		}
		
		return true;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
