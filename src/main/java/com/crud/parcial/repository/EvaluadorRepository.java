package com.crud.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.crud.parcial.model.Evaluador;

@Repository
public interface EvaluadorRepository extends MongoRepository<Evaluador, String>{

}
