package com.crud.parcial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.crud.parcial.exception.NotFoundException;
import com.crud.parcial.model.Estudiante;
import com.crud.parcial.model.Anteproyecto;
import com.crud.parcial.repository.AnteproyectoRepository;
import com.crud.parcial.repository.EstudianteRepository;

@Controller
@RequestMapping("/estudiantes")
public class EstudiantesController {

	@Autowired
	EstudianteRepository repository;
	@Autowired
	AnteproyectoRepository aRepository;
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping
	public String estudiantesListTemplate(Model model) {
		model.addAttribute("estudiantes", repository.findAll());
		return "lista-estudiantes";
	}
	
	@GetMapping("/vista/{cedula}")
	public String viewEstudiante(Model model, @PathVariable("cedula") String cedula) {
		List<Anteproyecto> anteproyectos = aRepository.findByEstudianteCedula(cedula);
		model.addAttribute("cedula", cedula);
		if(anteproyectos.isEmpty()) {
			model.addAttribute("anteproyectos", aRepository.findByEstudianteIsNull());
			return "vista-estudiante";
		}
		else {
			model.addAttribute("anteproyectos", anteproyectos);
			return "vista-estudiante-proyecto";
		}
	}
	
	@GetMapping("/new")
	public String estudiantesNewTemplate(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		return "estudiantes-form";
	}
	
	@GetMapping("/edit/{cedula}")
	public String estudiantesEditTemplate(@PathVariable("cedula") String cedula, Model model) {
		model.addAttribute("estudiante", repository.findById(cedula).orElseThrow(() -> new NotFoundException("Estudiante no encontrado")));
		return "estudiantes-form";
	}
	
	@PostMapping("/save")
	public String estudiantesSave(@ModelAttribute("estudiantes") Estudiante estudiante) {
		if(estudiante.getCedula().isEmpty()) {
			estudiante.setCedula(null);
		}
		
		repository.save(estudiante);
		return "redirect:/estudiantes";
	}
	
	@GetMapping("/delete/{cedula}")
	public String estudiantesDelete(@PathVariable("cedula") String cedula) {
		repository.deleteById(cedula);
		return "redirect:/estudiantes";
	}
	
}
