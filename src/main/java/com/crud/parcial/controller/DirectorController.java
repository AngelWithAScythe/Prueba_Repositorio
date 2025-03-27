package com.crud.parcial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.parcial.exception.NotFoundException;
import com.crud.parcial.model.Director;
import com.crud.parcial.repository.AnteproyectoRepository;
import com.crud.parcial.repository.DirectorRepository;

@Controller
@RequestMapping("/directores")
public class DirectorController {

	@Autowired
	DirectorRepository repository;
	@Autowired
	AnteproyectoRepository aRepository;
	
	@RequestMapping
	public String directoresListTemplate(Model model) {
		model.addAttribute("directores", repository.findAll());
		return "lista-directores";
	}
	
	@GetMapping("/vista/{cedula}")
	public String viewDirector(Model model, @PathVariable("cedula") String cedula) {
		model.addAttribute("anteproyectos", aRepository.findByDirectorCedula(cedula));
		model.addAttribute("cedula", cedula);
		return "vista-director";
	}
	
	@GetMapping("/new")
	public String directoresNewTemplate(Model model) {
		model.addAttribute("director", new Director());
		return "directores-form";
	}
	
	@GetMapping("/edit/{cedula}")
	public String directoresEditTemplate(@PathVariable("cedula") String cedula, Model model) {
		model.addAttribute("director", repository.findById(cedula).orElseThrow(() -> new NotFoundException("Director no encontrado")));
		return "directores-form";
	}
	
	@PostMapping("/save")
	public String directoresSave(@ModelAttribute("directores") Director director) {
		if(director.getCedula().isEmpty()) {
			director.setCedula(null);
		}
		
		repository.save(director);
		return "redirect:/directores";
	}
	
	@GetMapping("/delete/{cedula}")
	public String directoresDelete(@PathVariable("cedula") String cedula) {
		repository.deleteById(cedula);
		return "redirect:/directores";
	}
}
