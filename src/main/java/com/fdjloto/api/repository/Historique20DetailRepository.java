package com.fdjloto.api.repository;

import com.fdjloto.api.model.Historique20Detail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Date;

@Repository
public interface Historique20DetailRepository extends MongoRepository<Historique20Detail, String> {
    Optional<Historique20Detail> findByDateDeTirage(Date dateDeTirage);
}
