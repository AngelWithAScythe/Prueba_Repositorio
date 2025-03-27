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
import com.crud.parcial.model.Evaluador;
import com.crud.parcial.repository.AnteproyectoRepository;
import com.crud.parcial.repository.EvaluadorRepository;

@Controller
@RequestMapping("/evaluadores")
public class EvaluadoresController {

	@Autowired
	EvaluadorRepository repository;
	@Autowired
	AnteproyectoRepository aRepository;
	
	@RequestMapping
	public String evaluadoresListTemplate(Model model) {
		model.addAttribute("evaluadores", repository.findAll());
		return "lista-evaluadores";
	}
	
	@RequestMapping("/vista")
	public String viewEvaluador(Model model) {
		model.addAttribute("anteproyectos", aRepository.findByAprobadoDTrue());
		return "vista-evaluador";
	}
	
	@GetMapping("/new")
	public String evaluadoresNewTemplate(Model model) {
		model.addAttribute("evaluador", new Evaluador());
		return "evaluadores-form";
	}
	
	@GetMapping("/edit/{cedula}")
	public String evaluadoresEditTemplate(@PathVariable("cedula") String cedula, Model model) {
		model.addAttribute("evaluador", repository.findById(cedula).orElseThrow(() -> new NotFoundException("Evaluador no encontrado")));
		return "evaluadores-form";
	}
	
	@PostMapping("/save")
	public String evaluadoresSave(@ModelAttribute("evaluadores") Evaluador evaluador) {
		if(evaluador.getCedula().isEmpty()) {
			evaluador.setCedula(null);
		}
		
		repository.save(evaluador);
		return "redirect:/evaluadores";
	}
	
	@GetMapping("/delete/{cedula}")
	public String evaluadoresDelete(@PathVariable("cedula") String cedula) {
		repository.deleteById(cedula);
		return "redirect:/evaluadores";
	}
}
