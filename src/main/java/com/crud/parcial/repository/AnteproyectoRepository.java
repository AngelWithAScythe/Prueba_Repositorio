package com.crud.parcial.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.crud.parcial.model.Anteproyecto;

@Repository
public interface AnteproyectoRepository extends MongoRepository<Anteproyecto, String>{

	public List<Anteproyecto> findByAprobadoDTrue();
	
	public List<Anteproyecto> findByDirectorCedula(String cedula);
	
	public List<Anteproyecto> findByEstudianteCedula(String cedula);
	
	public List<Anteproyecto> findByEstudianteIsNull();
}
