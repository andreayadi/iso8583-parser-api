package com.personal.utility.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.personal.utility.model.IsoResult;

public interface IsoResultRepository extends MongoRepository<IsoResult, String>{
}
