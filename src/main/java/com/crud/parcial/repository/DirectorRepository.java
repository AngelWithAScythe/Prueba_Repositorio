package com.crud.parcial.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.crud.parcial.model.Director;

@Repository
public interface DirectorRepository extends MongoRepository<Director, String>{

}
