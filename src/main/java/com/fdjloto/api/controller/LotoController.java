package com.fdjloto.api.controller;

import com.fdjloto.api.service.LotoScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loto")
// @CrossOrigin(origins = "*") // Permet les requêtes depuis le frontend
@CrossOrigin(origins = "http://127.0.0.1:5500") // 🔥 Autorise CORS pour Live Server
public class LotoController {

    @Autowired
    private LotoScraperService lotoScraperService;

    @GetMapping("/scrape")
    public String triggerScraping() {
        lotoScraperService.scrapeData();
        return "Scraping lancé avec succès !";
    }
}
