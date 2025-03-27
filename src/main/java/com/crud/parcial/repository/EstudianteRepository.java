package com.crud.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.crud.parcial.model.Estudiante;

@Repository
public interface EstudianteRepository extends MongoRepository<Estudiante, String>{

}
