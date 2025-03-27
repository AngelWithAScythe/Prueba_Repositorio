package com.crud.parcial.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.parcial.exception.NotFoundException;
import com.crud.parcial.model.Estudiante;
import com.crud.parcial.model.Evaluador;
import com.crud.parcial.repository.EvaluadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value ="/api/evaluadores")
public class EvaluadorRestController {

	@Autowired
	EvaluadorRepository repository;
	
	@GetMapping("/")
	public List<Evaluador> getAllEvaluadores(){
		return repository.findAll();
	}
	
	@GetMapping("/{cedula}")
	public Evaluador getEvaluadorById(@PathVariable String cedula) {
		return repository.findById(cedula).orElseThrow(() -> new NotFoundException("Evaluador no encontrado"));
	}
	
	@PostMapping("/")
	public Evaluador saveEvaluador(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Evaluador evaluador = mapper.convertValue(body, Evaluador.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		evaluador.setNombre(nombre);
		
		String cedula = (String) body.get("cedula");
		if (cedula == null || cedula.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'cedula' es obligatorio y no puede estar vacío.");
	    }
		
		evaluador.setCedula(cedula);
		
		return repository.save(evaluador);
	}
	
	@PutMapping("/{cedula}")
	public Evaluador upadteEvaluador(@PathVariable String cedula, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Evaluador evaluador = mapper.convertValue(body, Evaluador.class);
		evaluador.setCedula(cedula);
		return repository.save(evaluador);
	}
	
	@DeleteMapping("/{cedula}")
	public Evaluador deleteEstudiante(@PathVariable String cedula) {
		Evaluador evaluador = repository.findById(cedula).orElseThrow(() -> new NotFoundException("Evaluador no encontrado"));
		repository.deleteById(cedula);
		return evaluador;
	}
}
