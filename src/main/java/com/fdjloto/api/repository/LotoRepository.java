package com.fdjloto.api.repository;

import com.fdjloto.api.model.LotoResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LotoRepository extends MongoRepository<LotoResult, String> {
}
