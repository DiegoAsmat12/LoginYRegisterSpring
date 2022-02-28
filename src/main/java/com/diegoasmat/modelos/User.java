package com.diegoasmat.modelos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "El Username es necesario")
	@Size(min=3, max=30, message="Username debe tener entre 3 y 30 caracteres")
	private String userName;
	
	@NotBlank(message = "Se requiere un Email")
	@Email(message="Ingrese un email valido")
	private String email;
	
	@NotNull(message="La fecha de nacimiento no puede estar vacía")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date nacimiento;
	
	
	@NotBlank(message = "La contraseña no puede estar vacío")
	@Size(min = 8, max=128,message = "La contraseña debe tener entre 8 y 128 caracteres")
	private String password;
	
	@Transient
	@NotBlank(message = "La confirmación no puede estar vacía")
	@Size(min = 8, max=128,message = "La confirmación debe tener entre 8 y 128 caracteres")
	private String confirm;
	
	@Transient
	@NotNull(message= "Debe aceptar los terminos y condiciones.")
	private String[] terminos;
	
	public User() {
		
	}

	public User(String userName, String email,Date nacimiento, String password, String confirm) {
		this.userName = userName;
		this.email = email;
		this.nacimiento = nacimiento;
		this.password = password;
		this.confirm = confirm;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String[] getTerminos() {
		return terminos;
	}

	public void setTerminos(String[] terminos) {
		this.terminos = terminos;
	}
	
	
	
}
