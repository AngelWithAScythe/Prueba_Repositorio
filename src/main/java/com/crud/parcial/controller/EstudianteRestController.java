package com.crud.parcial.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.crud.parcial.repository.EstudianteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/api/estudiantes")
public class EstudianteRestController {

	@Autowired
	EstudianteRepository repository;
	
	@GetMapping("/")
	public List<Estudiante> getAllEstudiantes(){
		return repository.findAll();
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Estudiante>> getListEstudiantes() {
	    List<Estudiante> estudiantes = repository.findAll();
	    return ResponseEntity.ok(estudiantes);
	}

	
	@GetMapping("/{cedula}")
	public Estudiante getEstudianteById(@PathVariable String cedula) {
		return repository.findById(cedula).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
	}
	
	
	
	@PostMapping("/")
	public Estudiante saveEstudiante(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Estudiante estudiante = mapper.convertValue(body, Estudiante.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		estudiante.setNombre(nombre);
		
		String cedula = (String) body.get("cedula");
		if (cedula == null || cedula.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'cedula' es obligatorio y no puede estar vacío.");
	    }
		
		estudiante.setCedula(cedula);
				
		return repository.save(estudiante);
	}
	
	@PutMapping("/{cedula}")
	public Estudiante upadteEstudiante(@PathVariable String cedula, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Estudiante estudiante = mapper.convertValue(body, Estudiante.class);
		estudiante.setCedula(cedula);
		return repository.save(estudiante);
	}

	@DeleteMapping("/{cedula}")
	public Estudiante deleteEstudiante(@PathVariable String cedula) {
		Estudiante estudiante = repository.findById(cedula).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
		repository.deleteById(cedula);
		return estudiante;
	}
	
}
