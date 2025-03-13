// package com.fdjloto.api.controller;

// import com.fdjloto.api.dto.GainResultDTO;
// import com.fdjloto.api.model.TicketGain;
// import com.fdjloto.api.repository.TicketGainRepository;
// import com.fdjloto.api.service.GainCalculationService;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/gains")
// @Tag(name = "Ticket Gains Management", description = "Endpoints for managing Tickets for searching and calculating gains.")
// @SecurityRequirement(name = "bearerAuth")
// @SecurityRequirement(name = "jwtCookieAuth")
// @CrossOrigin(
//     origins = "http://127.0.0.1:5500",
//     allowCredentials = "true",
//     allowedHeaders = "*",
//     methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
// )
// public class GainController {

//     @Autowired
//     private GainCalculationService gainCalculationService;

//     @Autowired
//     private TicketGainRepository ticketGainRepository;

//     /**
//      * 🚀 Calcule et enregistre les gains des tickets.
//      */
//     @GetMapping("/calculate")
//     public List<GainResultDTO> calculateGains() {
//         return gainCalculationService.calculerGains();
//     }

//     /**
//      * 🔍 Récupère tous les gains enregistrés en base.
//      */
//     @GetMapping
//     public List<TicketGain> getAllGains() {
//         return ticketGainRepository.findAll();
//     }

//     /**
//      * 🔍 Récupère les gains d'un ticket spécifique par ID.
//      */
//     @GetMapping("/{ticketId}")
//     public Optional<TicketGain> getGainByTicketId(@PathVariable String ticketId) {
//         return Optional.ofNullable(ticketGainRepository.findByTicketId(ticketId));
//     }
// }

package com.fdjloto.api.controller;

import com.fdjloto.api.dto.GainResultDTO;
import com.fdjloto.api.dto.TicketGainDTO;
import com.fdjloto.api.model.TicketGain;
import com.fdjloto.api.repository.TicketGainRepository;
import com.fdjloto.api.service.GainCalculationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gains")
@Tag(name = "Ticket Gains Management", description = "Endpoints for managing Tickets for searching and calculating gains.")
@SecurityRequirement(name = "bearerAuth")
@SecurityRequirement(name = "jwtCookieAuth")
@CrossOrigin(
    origins = "http://127.0.0.1:5500",
    allowCredentials = "true",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class GainController {

    private static final Logger logger = LoggerFactory.getLogger(GainController.class);


    private final GainCalculationService gainCalculationService;
    private final TicketGainRepository ticketGainRepository;

    // ✅ Correction : Injection via le constructeur
    public GainController(GainCalculationService gainCalculationService, TicketGainRepository ticketGainRepository) {
        this.gainCalculationService = gainCalculationService;
        this.ticketGainRepository = ticketGainRepository;
    }

    /**
     * 🚀 Calcule et enregistre les gains des tickets.
     */
    @GetMapping("/calculate")
    public ResponseEntity<List<GainResultDTO>> calculateGains() {
        logger.info("🔄 Lancement du calcul des gains...");
        List<GainResultDTO> results = gainCalculationService.calculerGains();
        logger.info("✅ Calcul des gains terminé ! {} résultats générés.", results.size());
        return ResponseEntity.ok(results);
    }

    @GetMapping
    public List<TicketGainDTO> getAllGains() {
        return ticketGainRepository.findAll()
            .stream()
            .map(TicketGainDTO::new) // ❗ On convertit en DTO pour éviter `User`
            .toList();
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketGainDTO> getGainByTicketId(@PathVariable String ticketId) {
        return ticketGainRepository.findByTicketId(ticketId)
            .map(TicketGainDTO::new)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
