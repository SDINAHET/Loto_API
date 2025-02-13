package com.fdjloto.api.repository;

import com.fdjloto.api.model.Historique20Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Historique20Repository extends MongoRepository<Historique20Result, String> {
    List<Historique20Result> findTop20ByOrderByDateDeTirageDesc();
}
