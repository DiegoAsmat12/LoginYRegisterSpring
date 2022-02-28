package com.diegoasmat.modelos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginUser {
	@NotBlank(message = "Se requiere un Email")
	@Email(message="Ingrese un email valido")
	private String email;
	
	@NotBlank(message = "La contraseña no puede estar vacío")
	@Size(min = 8, max=128,message = "La contraseña debe tener entre 8 y 128 caracteres")
	private String password;
	
	public LoginUser() {
		
	}

	public LoginUser(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
