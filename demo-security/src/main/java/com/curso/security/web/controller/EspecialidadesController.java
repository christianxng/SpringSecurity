package com.curso.security.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.curso.security.domain.Especialidade;
import com.curso.security.service.EspecialidadeService;

@Controller
@RequestMapping("especialidades")
public class EspecialidadesController {
	
	@Autowired
	private EspecialidadeService serviceEspecialidade;
	
	@GetMapping({"","/"})
	public String abrirEspecilalidades(Especialidade especialidade) 
	{
		return "especialidade/especialidade";
		
	}
	
	@PostMapping("/salvar")
	public String salvar(Especialidade especialidade, RedirectAttributes attr)	
	{
		serviceEspecialidade.salvar(especialidade);
		attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
		return  "redirect:/especialidades";
	}
	
	@GetMapping("/datatables/server")
	public ResponseEntity<?> getEspecialidades(HttpServletRequest request) 
	{
		return ResponseEntity.ok(serviceEspecialidade.buscarEspecialidades(request));
		
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) 
	{
		model.addAttribute("especialidade", serviceEspecialidade.buscarPorId(id));
		return "especialidade/especialidade";
		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) 
	{
		serviceEspecialidade.remover(id);
		attr.addFlashAttribute("sucesso", "Especialidade excluída com sucesso!");
		return "redirect:/especialidades";
		
	}
	
}
