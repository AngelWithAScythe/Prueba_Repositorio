package com.crud.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.crud.parcial.model.Coordinador;

@Repository
public interface CoordinadorRepository extends MongoRepository<Coordinador, String>{

}
