package com.tapiax.samples.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tapiax.samples.model.AreaTown;

public interface AreaRepository extends MongoRepository<AreaTown, String>  {
	
}
