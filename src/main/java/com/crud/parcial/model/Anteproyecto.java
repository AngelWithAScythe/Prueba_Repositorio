package com.crud.parcial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection="anteproyectos")
public class Anteproyecto {

	@Id
	private String id;
	private String nombre;
	private String enlace;
	private boolean aprobadoD;
	private boolean aprobadoE;
	@DocumentReference
	private Director director;
	@DocumentReference
	private Estudiante estudiante;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public boolean isAprobadoD() {
		return aprobadoD;
	}
	public void setAprobadoD(boolean aprobadoD) {
		this.aprobadoD = aprobadoD;
	}
	public boolean isAprobadoE() {
		return aprobadoE;
	}
	public void setAprobadoE(boolean aprobadoE) {
		this.aprobadoE = aprobadoE;
	}
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	public Estudiante getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	
	
}
