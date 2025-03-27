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
import com.crud.parcial.model.Director;
import com.crud.parcial.repository.DirectorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/api/directores")
public class DirectorRestController {

	@Autowired
	DirectorRepository repository;
	
	@GetMapping("/")
	public List<Director> getAllDirectores(){
		return repository.findAll();
	}
	
	@GetMapping("/{cedula}")
	public Director getDirectorById(@PathVariable String cedula) {
		return repository.findById(cedula).orElseThrow(() -> new NotFoundException("Director no encontrado"));
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Director>> getListDirectores(){
		List<Director> directores = repository.findAll();
		return ResponseEntity.ok(directores);
	}
	
	@PostMapping("/")
	public Director saveDirector(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Director director = mapper.convertValue(body, Director.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		director.setNombre(nombre);
			
		String cedula = (String) body.get("cedula");
		if (cedula == null || cedula.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'cedula' es obligatorio y no puede estar vacío.");
	    }
		
		director.setCedula(cedula);
		return repository.save(director);
	}
	
	@PutMapping("/{cedula}")
	public Director upadteDirector(@PathVariable String cedula, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Director director = mapper.convertValue(body, Director.class);
		director.setCedula(cedula);
		return repository.save(director);
	}

	@DeleteMapping("/{cedula}")
	public Director deleteDirector(@PathVariable String cedula) {
		Director director = repository.findById(cedula).orElseThrow(() -> new NotFoundException("Director no encontrado"));
		repository.deleteById(cedula);
		return director;
	}
}
