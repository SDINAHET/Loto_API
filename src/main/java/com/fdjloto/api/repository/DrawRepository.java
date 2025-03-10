package com.fdjloto.api.repository;

import com.fdjloto.api.model.Draw;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrawRepository extends MongoRepository<Draw, String> {

    // ✅ Recherche un tirage spécifique par date
    Optional<Draw> findByDrawDate(String drawDate);

    // ✅ Récupère le dernier tirage disponible (par date décroissante)
    Optional<Draw> findTopByOrderByDrawDateDesc();
}
