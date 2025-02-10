package com.fdjloto.api.controller;

import com.fdjloto.api.service.LotoScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loto")
public class LotoController {

    @Autowired
    private LotoScraperService lotoScraperService;

    @GetMapping("/scrape")
    public String triggerScraping() {
        lotoScraperService.scrapeData();
        return "Scraping lancé avec succès !";
    }
}
