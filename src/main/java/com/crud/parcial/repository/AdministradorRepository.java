package com.crud.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.crud.parcial.model.Administrador;

@Repository
public interface AdministradorRepository extends MongoRepository<Administrador, String>{

}
