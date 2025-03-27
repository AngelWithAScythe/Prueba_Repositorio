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
import com.crud.parcial.model.Coordinador;
import com.crud.parcial.repository.CoordinadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/api/coordinadores")
public class CoordinadorRestController {

	@Autowired
	CoordinadorRepository repository;
	
	@GetMapping("/")
	public List<Coordinador> getAllCoordinadores(){
		return repository.findAll();
	}
	
	@GetMapping("/{cedula}")
	public Coordinador getCoordinadorById(@PathVariable String cedula) {
		return repository.findById(cedula).orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
	}
	
	
	
	@PostMapping("/")
	public Coordinador saveCoordinador(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Coordinador coordinador = mapper.convertValue(body, Coordinador.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		coordinador.setNombre(nombre);
			
		String cedula = (String) body.get("cedula");
		if (cedula == null || cedula.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'cedula' es obligatorio y no puede estar vacío.");
	    }
		
		coordinador.setCedula(cedula);
		return repository.save(coordinador);
	}
	
	@PutMapping("/{cedula}")
	public Coordinador upadteCoordinador(@PathVariable String cedula, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Coordinador coordinador = mapper.convertValue(body, Coordinador.class);
		coordinador.setCedula(cedula);
		return repository.save(coordinador);
	}

	@DeleteMapping("/{cedula}")
	public Coordinador deleteCoordinador(@PathVariable String cedula) {
		Coordinador coordinador = repository.findById(cedula).orElseThrow(() -> new NotFoundException("Coordinador no encontrado"));
		repository.deleteById(cedula);
		return coordinador;
	}
}
