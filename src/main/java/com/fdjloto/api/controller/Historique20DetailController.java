package com.fdjloto.api.controller;

import com.fdjloto.api.model.Historique20Detail;
import com.fdjloto.api.model.LotoResult;
import com.fdjloto.api.service.Historique20DetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.Date; // ✅ Import de Date

// import com.fdjloto.api.service.LotoService; // ✅ Import du service

@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Autorise CORS pour Live Server
@RestController
@RequestMapping("/api/historique/last20/Detail")
public class Historique20DetailController {

    private final Historique20DetailService lotoService;

    public Historique20DetailController(Historique20DetailService lotoService) {
        this.lotoService = lotoService;
    }

        // 🔹 Recherche par date unique
    @GetMapping("/tirage/{date}")
    public ResponseEntity<Historique20Detail> getTirage(@PathVariable String date) {
        Optional<Historique20Detail> result = lotoService.getTirageByDate(date);
        return result.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

     // 🔹 Recherche par plage de dates
    // @GetMapping("/tirages")
    // public ResponseEntity<List<Historique20Detail>> getTiragesParPlageDeDates(
    //         @RequestParam String startDate,
    //         @RequestParam String endDate) {

    //     List<Historique20Detail> result = lotoService.getTiragesParPlageDeDates(startDate, endDate);
    //     if (result.isEmpty()) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    //     }
    //     return ResponseEntity.ok(result);
    // }


@GetMapping("/tirages")
public ResponseEntity<List<Historique20Detail>> getTiragesParPlageDeDates(
        @RequestParam String startDate,
        @RequestParam(required = false) String endDate) {

    List<Historique20Detail> result;

    // ✅ Si endDate est null, ne tenir compte que de startDate
    if (endDate == null || endDate.isEmpty()) {
        result = lotoService.getTiragesParPlageDeDates(startDate, startDate); // Recherche pour un jour
    } else {
        result = lotoService.getTiragesParPlageDeDates(startDate, endDate);
    }

    if (result.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    return ResponseEntity.ok(result);
}

}
