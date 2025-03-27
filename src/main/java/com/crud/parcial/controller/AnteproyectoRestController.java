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
import com.crud.parcial.model.Anteproyecto;
import com.crud.parcial.model.Director;
import com.crud.parcial.model.Estudiante;
import com.crud.parcial.repository.AnteproyectoRepository;
import com.crud.parcial.repository.DirectorRepository;
import com.crud.parcial.repository.EstudianteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/api/anteproyectos")
public class AnteproyectoRestController {

	@Autowired
	AnteproyectoRepository repository;
	@Autowired
	DirectorRepository dRepository;
	@Autowired
	EstudianteRepository eRepository;
	
	@GetMapping("/")
	public List<Anteproyecto> getAllAnteproyectos(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Anteproyecto getAnteproyectoById(@PathVariable String id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
	}
	
	
	
	@PostMapping("/")
	public Anteproyecto saveAnteproyecto(@RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Anteproyecto anteproyecto = mapper.convertValue(body, Anteproyecto.class);
		
		String nombre = (String) body.get("nombre");
		if (nombre == null || nombre.trim().isEmpty()) {
	        throw new IllegalArgumentException("El campo 'nombre' es obligatorio y no puede estar vacío.");
	    }
		
		anteproyecto.setNombre(nombre);
		anteproyecto.setEnlace(null);
		
		anteproyecto.setAprobadoD(false);
		anteproyecto.setAprobadoE(false);
		anteproyecto.setEstudiante(null);
		
		Object directorObject = body.get("director");
		
		if(directorObject instanceof String) {
			String directorCedula = (String) directorObject;
			Director director = dRepository.findById(directorCedula).orElseThrow(() -> new NotFoundException("Director no encontrado"));
			anteproyecto.setDirector(director);
		}
		else {
			throw new IllegalArgumentException("La propiedad 'director' debe ser una cadena");
		}
		
		return repository.save(anteproyecto);
	}
	
	@PutMapping("/{id}")
	public Anteproyecto upadteAnteproyecto(@PathVariable String id, @RequestBody Map<String, Object> body) {
		ObjectMapper mapper = new ObjectMapper();
		Anteproyecto anteproyecto = mapper.convertValue(body, Anteproyecto.class);
		anteproyecto.setId(id);
		return repository.save(anteproyecto);
	}

	@DeleteMapping("/{id}")
	public Anteproyecto deleteAnteproyecto(@PathVariable String id) {
		Anteproyecto anteproyecto = repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
		repository.deleteById(id);
		return anteproyecto;
	}
	
	@PutMapping("/{id}/asignarEstudiante/{cedula}")
	public Anteproyecto assignStudentToProject(@PathVariable String id, @PathVariable String cedula) {
	    Anteproyecto anteproyecto = repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
	    Estudiante estudiante = eRepository.findById(cedula).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
	    
	    anteproyecto.setEstudiante(estudiante);
	    return repository.save(anteproyecto);
	}
	
	@PutMapping("/SubirAnteproyecto/{id}/{enlace}")
	public Anteproyecto subirAnteproyecto(@PathVariable String id, @PathVariable String enlace) {
	    Anteproyecto anteproyecto = repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
	    anteproyecto.setEnlace(enlace);
	    return repository.save(anteproyecto);
	}
	
	@PutMapping("/{id}/AprobarE")
	public Anteproyecto aprobarE(@PathVariable String id) {
	    Anteproyecto anteproyecto = repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
	    
	    anteproyecto.setAprobadoE(true);
	    return repository.save(anteproyecto);
	}	
	
	@PutMapping("/{id}/AprobarD")
	public Anteproyecto aprobarD(@PathVariable String id) {
	    Anteproyecto anteproyecto = repository.findById(id).orElseThrow(() -> new NotFoundException("Anteproyecto no encontrado"));
	    
	    anteproyecto.setAprobadoD(true);
	    return repository.save(anteproyecto);
	}
}
