package com.fdjloto.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fdjloto.api.repository.HistoriqueRepository;
import com.fdjloto.api.model.Historique;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins = "*")
public class HistoriqueController {

    @Autowired
    private HistoriqueRepository repository;

    @GetMapping
    public List<Historique> getResults(@RequestParam(required = false) String date,
                                       @RequestParam(required = false) String combination) {
        if (date != null) {
            return repository.findByDateDeTirage(date);
        } else if (combination != null) {
            return repository.findByCombinaisonGagnanteEnOrdreCroissant(combination);
        }
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Historique> getResultById(@PathVariable String id) {
        return repository.findById(id);
    }

    @PostMapping
    public Historique addResult(@RequestBody Historique result) {
        return repository.save(result);
    }
}

