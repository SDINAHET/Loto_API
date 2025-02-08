package com.fdjloto.api.service;

import com.fdjloto.api.model.Historique;
import com.fdjloto.api.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class HistoriqueService {

    @Autowired
    private HistoriqueRepository historiqueRepository;

    // public Optional<Historique> getLatestResult() {
    //     return historiqueRepository.findFirstByOrderByDateDesc();
    // }
}
