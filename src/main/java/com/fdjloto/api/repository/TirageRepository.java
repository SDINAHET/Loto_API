package com.fdjloto.api.repository;

import com.fdjloto.api.model.Tirage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

@Repository
public interface TirageRepository extends MongoRepository<Tirage, String> {

// 	// Récupère les 100 derniers tirages triés par date (du plus récent au plus ancien)
// 	@Query("{}")
// 	List<Tirage> findTop20ByOrderByDateTirageDesc(Sort sort);
}


