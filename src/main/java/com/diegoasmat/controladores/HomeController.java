package com.diegoasmat.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.diegoasmat.modelos.LoginUser;
import com.diegoasmat.modelos.User;
import com.diegoasmat.servicios.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		
		//Registrar usuario
		User usuario = userService.register(newUser, result);
		if(usuario==null) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		
		session.setAttribute("id", usuario.getId());
		
		return "redirect:/home";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		User usuario = userService.login(newLogin, result);
		if(usuario==null) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		
		session.setAttribute("id", usuario.getId());
		//session.setAttribute("id", newUser.getId());
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String dashboard(HttpSession session, Model model) {
		if(session.getAttribute("id")!=null) {
			User usuario = userService.getUserById((Long)session.getAttribute("id"));
			model.addAttribute("usuario", usuario);
			return "home.jsp";
		}
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("id")!=null) {
			session.removeAttribute("id");
		}
		return "redirect:/";
	}
}
