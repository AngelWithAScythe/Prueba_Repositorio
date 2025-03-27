package com.crud.parcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.parcial.exception.NotFoundException;
import com.crud.parcial.model.Coordinador;
import com.crud.parcial.repository.CoordinadorRepository;

@Controller
@RequestMapping("/coordinadores")
public class CoordinadorController {

	@Autowired
	CoordinadorRepository repository;
	
	@RequestMapping
	public String coordinadoresListTemplate(Model model) {
		model.addAttribute("coordinadores", repository.findAll());
		return "lista-coordinadores";
	}
	
	
	@GetMapping("/new")
	public String coordinadoresNewTemplate(Model model) {
		model.addAttribute("coordinador", new Coordinador());
		return "coordinadores-form";
	}
	
	@GetMapping("/edit/{cedula}")
	public String coordinadoresEditTemplate(@PathVariable("cedula") String cedula, Model model) {
		model.addAttribute("coordinador", repository.findById(cedula).orElseThrow(() -> new NotFoundException("Coordinador no encontrado")));
		return "coordinadores-form";
	}
	
	@PostMapping("/save")
	public String coordinadoresSave(@ModelAttribute("coordinadores") Coordinador coordinador) {
		if(coordinador.getCedula().isEmpty()) {
			coordinador.setCedula(null);
		}
		
		repository.save(coordinador);
		return "redirect:/coordinadores";
	}
	
	@GetMapping("/delete/{cedula}")
	public String coordinadoresDelete(@PathVariable("cedula") String cedula) {
		repository.deleteById(cedula);
		return "redirect:/coordinadores";
	}
}
