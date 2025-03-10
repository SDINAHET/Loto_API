package com.fdjloto.api.controller;

import com.fdjloto.api.dto.GainResultDTO;
import com.fdjloto.api.model.TicketGain;
import com.fdjloto.api.repository.TicketGainRepository;
import com.fdjloto.api.service.GainCalculationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class GainController {

    @Autowired
    private GainCalculationService gainCalculationService;

    @Autowired
    private TicketGainRepository ticketGainRepository;

    /**
     * 🚀 Calcule et enregistre les gains des tickets.
     */
    @GetMapping("/calculate")
    public List<GainResultDTO> calculateGains() {
        return gainCalculationService.calculerGains();
    }

    /**
     * 🔍 Récupère tous les gains enregistrés en base.
     */
    @GetMapping
    public List<TicketGain> getAllGains() {
        return ticketGainRepository.findAll();
    }

    /**
     * 🔍 Récupère les gains d'un ticket spécifique par ID.
     */
    @GetMapping("/{ticketId}")
    public Optional<TicketGain> getGainByTicketId(@PathVariable String ticketId) {
        return Optional.ofNullable(ticketGainRepository.findByTicketId(ticketId));
    }
}
