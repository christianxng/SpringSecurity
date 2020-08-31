package com.curso.security.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// abrir pagina home
	@GetMapping({"/", "/home"})
	public String home() {
		return "home";
	}	
	
	// abrir página login
	@GetMapping({"/login"})
	public String login() {
		return "login";
	}	
	
	// mensagem de Erro no login 
	@GetMapping({"/login-error"})
	public String loginError(ModelMap model) {
		model.addAttribute("alerta","erro");
		model.addAttribute("titulo","Credenciais incorretas!");
		model.addAttribute("texto","Login ou Senha incorretos, tente novamente.");
		model.addAttribute("subtexto","Acesso permitido apenas para cadastros ja ativados.");
		return "login";
	}
	
	// mensagem de acesso negado 
	@GetMapping({"/acesso-negado"})
	public String acessoNegado(ModelMap model, HttpServletResponse response) {
		model.addAttribute("status",response.getStatus());
		model.addAttribute("error","Acesso Negado!");
		model.addAttribute("message","Desculpe, Você não tem permissão para acesso a esta página ou ação.");
		return "/error";
	}
	
}
