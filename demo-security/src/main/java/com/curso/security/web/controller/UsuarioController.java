package com.curso.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.security.domain.Usuario;

@Controller
@RequestMapping("u")
public class UsuarioController {
	
		@GetMapping("/novo/cadastro/usuario")
		public String cadastroPorAdminParaAdminMedicoPaciente(Usuario  usuario)
		{
			return "usuario/cadastro";
		}
		
		
		/*@GetMapping("/novo/cadastro/usuario")
		public String listarUsuarios()
		{
			return "usuario/cadastro";
		}*/
}
