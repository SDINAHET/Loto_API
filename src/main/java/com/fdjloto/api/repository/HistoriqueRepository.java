package com.fdjloto.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import com.fdjloto.api.model.Historique;

public interface HistoriqueRepository extends MongoRepository<Historique, String> {
    List<Historique> findByDateDeTirage(String date);
    List<Historique> findByCombinaisonGagnanteEnOrdreCroissant(String combinaisonGagnanteEnOrdreCroissant);
}
