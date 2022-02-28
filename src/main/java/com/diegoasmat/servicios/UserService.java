package com.diegoasmat.servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.diegoasmat.modelos.LoginUser;
import com.diegoasmat.modelos.User;
import com.diegoasmat.repositorios.UserRespository;

@Service
public class UserService {
	
	@Autowired
	private UserRespository userRepo;
	
	public User getUserById(Long id) {
		Optional<User> user = userRepo.findById(id); 
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}
	
	//Podemos crear validaciones a nivel de servicio esto sirve para logins 
	//así como también para cosas como el confirm password o aceptar terminos y condiciones
	public User register(User newUser, BindingResult result) {
		Optional<User> usuarioObtenido = userRepo.findByEmail(newUser.getEmail());
		if(usuarioObtenido.isPresent()) {
			result.rejectValue("email", "Not Unique", "Email ya se encuentra registrado.");
		}
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Confirm password y password deben ser iguales.");
		}
		
		LocalDate fechaHoy = LocalDate.now();
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate nacimiento = LocalDate.parse(formato.format(newUser.getNacimiento()));
		
		int edad = Period.between(nacimiento, fechaHoy).getYears();
		
		if(edad<=10) {
			result.rejectValue("nacimiento", "Less Than minimum", "Eres muy joven para entrar a esta web.");
		}
		if(newUser.getTerminos().length<=0) {
			result.rejectValue("terminos", "No aceptado", "Debe aceptar los terminos y condiciones");
		}
		if(result.hasErrors()) {
			return null;
		}
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		
		User user = userRepo.save(newUser);
		
		return user;
	}
	
	public User login(LoginUser newLoginObject, BindingResult result) {
		Optional<User> usuarioObtenido = userRepo.findByEmail(newLoginObject.getEmail());
		User usuario=null;
		if(!usuarioObtenido.isPresent()) {
			result.rejectValue("email", "Existence", "Email no se encuentra registrado.");
		}
		else {
			usuario = usuarioObtenido.get();
			if(!BCrypt.checkpw(newLoginObject.getPassword(), usuario.getPassword())) {
				result.rejectValue("password", "Matches", "Contraseña Invalida!");
			}
		}
		if(result.hasErrors()) {
			return null;
		}
		
		return usuario;
	}
}
