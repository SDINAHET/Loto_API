package com.fdjloto.api.service;

import com.fdjloto.api.model.Historique20Result;
import com.fdjloto.api.repository.Historique20Repository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Historique20Service {

    private final Historique20Repository historique20Repository;

    public Historique20Service(Historique20Repository historique20Repository) {
        this.historique20Repository = historique20Repository;
    }

    public List<Historique20Result> getLast20Results() {
        return historique20Repository.findTop6ByOrderByDateDeTirageDesc();
    }
}
