package com.fdjloto.api.service;

import com.fdjloto.api.model.Draw;
import com.fdjloto.api.repository.DrawRepository;
import com.fdjloto.api.repository.HistoryRepository;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class DrawService {
    private final DrawRepository drawRepository;
    private final HistoryRepository historyRepository;

    public DrawService(DrawRepository drawRepository, HistoryRepository historyRepository) {
        this.drawRepository = drawRepository;
        this.historyRepository = historyRepository;
    }

    public Optional<Draw> getDrawByDate(String drawDate) {
        return drawRepository.findByDrawDate(drawDate);
    }

    public Optional<Draw> getLatestDraw() { // ✅ Utilisation de Optional pour éviter une erreur si aucun tirage n'est trouvé
        return drawRepository.findTopByOrderByDrawDateDesc();
    }

    /**
     * 🔥 Récupère le gain en fonction du rang depuis MongoDB (Historique).
     */
    public double getGainForRank(String drawDate, int rank) {
        Optional<Map<Integer, Double>> gainsOpt = historyRepository.findGainsByDrawDate(drawDate);

        return gainsOpt.map(gainsMap -> gainsMap.getOrDefault(rank, 0.0)).orElse(0.0);
    }
}

