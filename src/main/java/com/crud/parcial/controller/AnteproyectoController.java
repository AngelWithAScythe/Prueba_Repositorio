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
import com.crud.parcial.model.Anteproyecto;
import com.crud.parcial.model.Director;
import com.crud.parcial.model.Estudiante;
import com.crud.parcial.repository.AnteproyectoRepository;
import com.crud.parcial.repository.DirectorRepository;
import com.crud.parcial.repository.EstudianteRepository;

@Controller
@RequestMapping("/anteproyectos")
public class AnteproyectoController {

	@Autowired
	AnteproyectoRepository repository;
	@Autowired
	DirectorRepository dRepository;
	@Autowired
	EstudianteRepository eRepository;
	
	@RequestMapping
	public String anteproyectosListTemplate(Model model) {
		model.addAttribute("anteproyectos", repository.findAll());
		return "lista-anteproyectos";
	}
	
	
	@GetMapping("/new")
	public String anteproyectosNewTemplate(Model model) {
		model.addAttribute("anteproyecto", new Anteproyecto());
		List<Director> directoresList = dRepository.findAll();
		model.addAttribute("directoresList", directoresList);
		return "anteproyectos-form";
	}
	
	@GetMapping("/edit/{id}")
	public String anteproyectosEditTemplate(@PathVariable("id") String id, Model model) {
		model.addAttribute("anteproyecto", repository.findById(id).orElseThrow(() -> new NotFoundException("anteproyecto no encontrado")));
		List<Director> directoresList = dRepository.findAll();
		model.addAttribute("directoresList", directoresList);
		return "anteproyectos-form";
	}
	
	@PostMapping("/save")
	public String anteproyectosSave(@ModelAttribute("anteproyectos") Anteproyecto anteproyecto) {
		if(anteproyecto.getId().isEmpty()) {
			anteproyecto.setId(null);
		}
		
		repository.save(anteproyecto);
		return "redirect:/anteproyectos";
	}
	
	@PostMapping("/subir/{cedula}")
	public String anteproyectosSubir(@ModelAttribute("anteproyectos") Anteproyecto anteproyecto, @PathVariable("cedula") String cedula) {
		if(anteproyecto.getId().isEmpty()) {
			anteproyecto.setId(null);
		}
		
		repository.save(anteproyecto);
		return "redirect:/estudiantes/vista/{cedula}";
	}
	
	@GetMapping("/delete/{id}")
	public String anteproyectosDelete(@PathVariable("id") String id) {
		repository.deleteById(id);
		return "redirect:/anteproyectos";
	}
	
	@GetMapping("/asignarEstudiante/{id}/{cedula}")
	public String asignarEstudianteToProject(@PathVariable("id") String id, @PathVariable("cedula") String cedula) {
	    Anteproyecto anteproyecto = repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
	    Estudiante estudiante = eRepository.findById(cedula).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
	    
	    anteproyecto.setEstudiante(estudiante);
	    repository.save(anteproyecto);
	    
	    return "redirect:/estudiantes/vista/{cedula}"; 
	}
	
	@GetMapping("/subirAnteproyecto/{id}/{cedula}")
	public String subirAnteproyecto(@PathVariable("id") String id, Model model, @PathVariable String cedula) {
	    Anteproyecto anteproyecto = repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
	    model.addAttribute("anteproyecto",anteproyecto);
	    model.addAttribute("cedula",cedula);
	    return "anteproyectos-form-subir";
	}
	
	@GetMapping("/aprobarE/{id}")
	public String aprobarE(@PathVariable("id") String id) {
	    Anteproyecto anteproyecto = repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
	    anteproyecto.setAprobadoE(true);
	    repository.save(anteproyecto);
	    return "redirect:/evaluadores/vista";
	}
	
	@GetMapping("/aprobarD/{id}/{cedula}")
	public String aprobarD(@PathVariable("id") String id) {
	    Anteproyecto anteproyecto = repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
	    anteproyecto.setAprobadoD(true);
	    repository.save(anteproyecto);
	    return "redirect:/directores/vista/{cedula}";
	}
}
