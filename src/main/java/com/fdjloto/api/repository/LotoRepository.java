package com.fdjloto.api.repository;

import com.fdjloto.api.model.LotoResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface LotoRepository extends MongoRepository<LotoResult, String> {
	// Optional<GameResult> findByDrawDate(String drawDate);

	// 🟢 Correction : Ajout de la méthode pour filtrer les tirages par date
    List<LotoResult> findByDateDeTirageBetween(Date startDate, Date endDate);
}
