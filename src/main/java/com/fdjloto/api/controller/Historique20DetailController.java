package com.fdjloto.api.controller;

import com.fdjloto.api.model.Historique20Detail;
import com.fdjloto.api.service.Historique20DetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Autorise CORS pour Live Server
@RestController
@RequestMapping("/api/historique/last20/Detail")
public class Historique20DetailController {

    private final Historique20DetailService lotoService;

    public Historique20DetailController(Historique20DetailService lotoService) {
        this.lotoService = lotoService;
    }

    @GetMapping("/tirage/{date}")
    public ResponseEntity<Historique20Detail> getTirage(@PathVariable String date) {
        Optional<Historique20Detail> result = lotoService.getTirageByDate(date);
        return result.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
