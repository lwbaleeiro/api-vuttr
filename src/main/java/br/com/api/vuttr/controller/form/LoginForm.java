package br.com.api.vuttr.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}


}
