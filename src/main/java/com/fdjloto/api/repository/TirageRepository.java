package com.fdjloto.api.repository;

import com.fdjloto.api.model.Tirage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TirageRepository extends MongoRepository<Tirage, String> {
}


