package com.fdjloto.api.controller;

import com.fdjloto.api.dto.LotoResultDTO;
import com.fdjloto.api.model.Historique20Result;
import com.fdjloto.api.service.Historique20Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historique")
@CrossOrigin(origins = "*") // Permet les requêtes depuis le frontend
public class Historique20Controller {

    private final Historique20Service historique20Service;

    public Historique20Controller(Historique20Service historique20Service) {
        this.historique20Service = historique20Service;
    }

    /**
     * Endpoint pour récupérer les 20 derniers résultats du loto avec une date formatée
     * @return Liste des 20 derniers résultats avec date formatée
     */
    @GetMapping("/last20")
    public List<LotoResultDTO> getLast20Results() {
        List<Historique20Result> results = historique20Service.getLast20Results();
        return results.stream()
                .map(result -> new LotoResultDTO(
                        result.getId(),
                        result.getDateDeTirage(), // ✅ On garde Date, car @JsonFormat gère le formatage
                        // result.getCombinaisonGagnante(),
						result.getBoule1(),
						result.getBoule2(),
						result.getBoule3(),
						result.getBoule4(),
						result.getBoule5(),
                        result.getNumeroChance()))
                .toList(); // ✅ SonarLint recommande d'utiliser `.toList()` au lieu de `Collectors.toList()`
    }
}



