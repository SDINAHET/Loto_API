// // // package com.loto.api.controllers;

// // // import com.loto.api.models.ResultatTirage;
// // // import com.loto.api.models.Ticket;
// // // import com.loto.api.repositories.ResultatTirageRepository;
// // // import com.loto.api.repositories.TicketRepository;
// // // import org.springframework.beans.factory.annotation.Autowired;
// // // import org.springframework.web.bind.annotation.*;

// // // import java.util.*;
// // // import java.util.stream.Collectors;

// // // @RestController
// // // @RequestMapping("/api/comparaison")
// // // public class ComparaisonController {

// // //     @Autowired
// // //     private TicketRepository ticketRepository;

// // //     @Autowired
// // //     private ResultatTirageRepository resultatTirageRepository;

// // //     @GetMapping("/tous")
// // //     public List<Map<String, Object>> comparerTousTickets() {
// // //         // 🔹 Récupération de tous les tirages FDJ
// // //         List<ResultatTirage> tirages = resultatTirageRepository.findAll();

// // //         // 🔹 Récupération de tous les tickets des utilisateurs
// // //         List<Ticket> tickets = ticketRepository.findAll();

// // //         // 🔥 Stockage des résultats de comparaison
// // //         List<Map<String, Object>> resultatsComparaison = new ArrayList<>();

// // //         // 🔄 Comparaison des tickets avec tous les tirages
// // //         for (Ticket ticket : tickets) {
// // //             Map<String, Object> resultatTicket = new HashMap<>();
// // //             resultatTicket.put("ticket", ticket);

// // //             List<Map<String, Object>> matchs = new ArrayList<>();

// // //             for (ResultatTirage tirage : tirages) {
// // //                 int numerosTrouves = compterNumerosGagnants(ticket, tirage);
// // //                 boolean chanceGagnante = ticket.getChanceNumber() == tirage.getNumeroChance();

// // //                 if (numerosTrouves > 0) {
// // //                     Map<String, Object> matchDetail = new HashMap<>();
// // //                     matchDetail.put("tirage", tirage);
// // //                     matchDetail.put("numerosTrouves", numerosTrouves);
// // //                     matchDetail.put("chanceGagnante", chanceGagnante);
// // //                     matchDetail.put("rang", determinerRang(numerosTrouves, chanceGagnante, tirage));

// // //                     matchs.add(matchDetail);
// // //                 }
// // //             }

// // //             resultatTicket.put("matchs", matchs);
// // //             resultatsComparaison.add(resultatTicket);
// // //         }

// // //         return resultatsComparaison;
// // //     }

// // //     // ✅ Compte combien de numéros du ticket sont dans le tirage
// // //     private int compterNumerosGagnants(Ticket ticket, ResultatTirage tirage) {
// // //         Set<Integer> numerosTicket = Arrays.stream(ticket.getNumbers().split("-"))
// // //                                            .map(Integer::parseInt)
// // //                                            .collect(Collectors.toSet());

// // //         Set<Integer> numerosTirage = new HashSet<>(Arrays.asList(
// // //                 tirage.getBou1(), tirage.getBou2(), tirage.getBou3(),
// // //                 tirage.getBou4(), tirage.getBou5()));

// // //         numerosTicket.retainAll(numerosTirage);
// // //         return numerosTicket.size();
// // //     }

// // //     // ✅ Détermine le rang de gain basé sur les numéros trouvés et le numéro chance
// // //     private int determinerRang(int numerosTrouves, boolean chanceGagnante, ResultatTirage tirage) {
// // //         if (numerosTrouves == 5 && chanceGagnante) return 1;
// // //         if (numerosTrouves == 5) return 2;
// // //         if (numerosTrouves == 4 && chanceGagnante) return 3;
// // //         if (numerosTrouves == 4) return 4;
// // //         if (numerosTrouves == 3 && chanceGagnante) return 5;
// // //         if (numerosTrouves == 3) return 6;
// // //         if (numerosTrouves == 2 && chanceGagnante) return 7;
// // //         if (numerosTrouves == 2) return 8;
// // //         if (numerosTrouves == 1 && chanceGagnante) return 9;
// // //         return 0; // Pas de gain
// // //     }
// // // }

// // package com.loto.api.controllers;

// // import com.loto.api.models.ResultatTirage;
// // import com.loto.api.models.Ticket;
// // import com.loto.api.repositories.ResultatTirageRepository;
// // import com.loto.api.repositories.TicketRepository;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.web.bind.annotation.*;

// // import java.util.*;
// // import java.util.stream.Collectors;

// // @RestController
// // @RequestMapping("/api/comparaison")
// // public class ComparaisonController {

// //     @Autowired
// //     private TicketRepository ticketRepository;

// //     @Autowired
// //     private ResultatTirageRepository resultatTirageRepository;

// //     @GetMapping("/derniers")
// //     public List<Map<String, Object>> comparerDerniersTickets() {
// //         // 🔹 Récupère les 100 derniers tirages FDJ (triés par date décroissante)
// //         List<ResultatTirage> tirages = resultatTirageRepository.findAll()
// //                 .stream()
// //                 .sorted(Comparator.comparing(ResultatTirage::getDateDeTirage).reversed())
// //                 .limit(100)
// //                 .toList();

// //         // 🔹 Récupère tous les tickets des utilisateurs
// //         List<Ticket> tickets = ticketRepository.findAll();

// //         // 🔥 Stockage des résultats de comparaison
// //         List<Map<String, Object>> resultatsComparaison = new ArrayList<>();

// //         // 🔄 Comparaison des tickets avec les 100 derniers tirages
// //         for (Ticket ticket : tickets) {
// //             Map<String, Object> resultatTicket = new HashMap<>();
// //             resultatTicket.put("ticket", ticket);

// //             List<Map<String, Object>> matchs = new ArrayList<>();
// //             double gainTotal = 0;
// //             String statut = "PERDU";

// //             for (ResultatTirage tirage : tirages) {
// //                 int numerosTrouves = compterNumerosGagnants(ticket, tirage);
// //                 boolean chanceGagnante = ticket.getChanceNumber() == tirage.getNumeroChance();
// //                 int rang = determinerRang(numerosTrouves, chanceGagnante, tirage);
// //                 double gain = calculerGain(rang, tirage);

// //                 if (rang > 0) {
// //                     Map<String, Object> matchDetail = new HashMap<>();
// //                     matchDetail.put("tirage", tirage);
// //                     matchDetail.put("numerosTrouves", numerosTrouves);
// //                     matchDetail.put("chanceGagnante", chanceGagnante);
// //                     matchDetail.put("rang", rang);
// //                     matchDetail.put("gain", gain);

// //                     matchs.add(matchDetail);
// //                     gainTotal += gain;
// //                     statut = "GAGNANT";
// //                 }
// //             }

// //             // ✅ Mise à jour en base SQLite du statut et du gain du ticket
// //             ticket.setStatut(statut);
// //             ticket.setGain(gainTotal);
// //             ticketRepository.save(ticket);

// //             resultatTicket.put("matchs", matchs);
// //             resultatTicket.put("gainTotal", gainTotal);
// //             resultatsComparaison.add(resultatTicket);
// //         }

// //         return resultatsComparaison;
// //     }

// //     // ✅ Compte combien de numéros du ticket sont dans le tirage
// //     private int compterNumerosGagnants(Ticket ticket, ResultatTirage tirage) {
// //         Set<Integer> numerosTicket = Arrays.stream(ticket.getNumbers().split("-"))
// //                                            .map(Integer::parseInt)
// //                                            .collect(Collectors.toSet());

// //         Set<Integer> numerosTirage = new HashSet<>(Arrays.asList(
// //                 tirage.getBou1(), tirage.getBou2(), tirage.getBou3(),
// //                 tirage.getBou4(), tirage.getBou5()));

// //         numerosTicket.retainAll(numerosTirage);
// //         return numerosTicket.size();
// //     }

// //     // ✅ Détermine le rang du ticket
// //     private int determinerRang(int numerosTrouves, boolean chanceGagnante, ResultatTirage tirage) {
// //         if (numerosTrouves == 5 && chanceGagnante) return 1;
// //         if (numerosTrouves == 5) return 2;
// //         if (numerosTrouves == 4 && chanceGagnante) return 3;
// //         if (numerosTrouves == 4) return 4;
// //         if (numerosTrouves == 3 && chanceGagnante) return 5;
// //         if (numerosTrouves == 3) return 6;
// //         if (numerosTrouves == 2 && chanceGagnante) return 7;
// //         if (numerosTrouves == 2) return 8;
// //         if (numerosTrouves == 1 && chanceGagnante) return 9;
// //         return 0;
// //     }

// //     // ✅ Calcule le gain du ticket en fonction du rang et du tirage FDJ
// //     private double calculerGain(int rang, ResultatTirage tirage) {
// //         switch (rang) {
// //             case 1: return tirage.getRapportDuRang1();
// //             case 2: return tirage.getRapportDuRang2();
// //             case 3: return tirage.getRapportDuRang3();
// //             case 4: return tirage.getRapportDuRang4();
// //             case 5: return tirage.getRapportDuRang5();
// //             case 6: return tirage.getRapportDuRang6();
// //             case 7: return tirage.getRapportDuRang7();
// //             case 8: return tirage.getRapportDuRang8();
// //             case 9: return tirage.getRapportDuRang9();
// //             default: return 0;
// //         }
// //     }
// // }


// // package com.fdjloto.api.controller;

// // import com.fdjloto.api.model.ResultatTirage;
// // import com.fdjloto.api.model.Ticket;
// // import com.fdjloto.api.model.Tirage;
// // import com.fdjloto.api.repository.ResultatTirageRepository;
// // import com.fdjloto.api.repository.TirageRepository;
// // import com.fdjloto.api.repository.TicketRepository;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;

// // import java.time.LocalDate;
// // import java.time.format.DateTimeFormatter;
// // import java.util.*;
// // import java.util.stream.Collectors;

// // @RestController
// // @RequestMapping("/api/comparaison")
// // public class ComparaisonController {

// //     @Autowired
// //     private TicketRepository ticketRepository;

// //     @Autowired
// //     private ResultatTirageRepository resultatTirageRepository;

// //     @Autowired
// //     private TirageRepository tirageRepository;

// //     /**
// //      * Comparaison de tous les tickets avec les résultats disponibles (SQL)
// //      */
// //     @GetMapping("/tous")
// //     public List<Map<String, Object>> comparerTousTickets() {
// //         return comparerTickets("sql", null, null);
// //     }

// //     /**
// //      * Comparaison des tickets sur une plage de dates (SQL)
// //      */
// //     @GetMapping("/tirages-comparaison")
// //     public ResponseEntity<List<Map<String, Object>>> comparerTicketsSurPlageDeDates(
// //             @RequestParam String startDate,
// //             @RequestParam(required = false) String endDate) {

// //         List<Map<String, Object>> resultats = comparerTickets("sql", startDate, endDate);
// //         return resultats.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
// //                                    : ResponseEntity.ok(resultats);
// //     }

// //     /**
// //      * Comparaison des tickets avec les tirages de MongoDB
// //      */
// //     @GetMapping("/tirages-mongodb")
// //     public ResponseEntity<List<Map<String, Object>>> comparerTicketsMongo() {
// //         List<Map<String, Object>> resultats = comparerTickets("mongodb", null, null);
// //         return ResponseEntity.ok(resultats);
// //     }

// //     /**
// //      * Méthode générique pour comparer les tickets avec les tirages de SQL ou MongoDB.
// //      */
// //     private List<Map<String, Object>> comparerTickets(String source, String startDate, String endDate) {
// //         List<Ticket> tickets = ticketRepository.findAll();
// //         if (tickets.isEmpty()) return Collections.emptyList();

// //         List<?> tirages;
// //         if ("mongodb".equals(source)) {
// //             tirages = tirageRepository.findAll();
// //         } else {
// //             tirages = (startDate != null) ? getTiragesParDate(startDate, endDate) : resultatTirageRepository.findAll();
// //         }

// //         if (tirages.isEmpty()) return Collections.emptyList();

// //         List<Map<String, Object>> resultatsComparaison = new ArrayList<>();

// //         for (Ticket ticket : tickets) {
// //             Map<String, Object> resultatTicket = new HashMap<>();
// //             resultatTicket.put("ticket", ticket);

// //             List<Map<String, Object>> matchs = new ArrayList<>();
// //             double gainTotal = 0;
// //             String statut = "PERDU";

// //             for (Object tirageObj : tirages) {
// //                 int numerosTrouves;
// //                 boolean chanceGagnante;
// //                 int rang;
// //                 double gain;

// //                 if (tirageObj instanceof Tirage) {
// //                     Tirage tirage = (Tirage) tirageObj;
// //                     numerosTrouves = compterNumerosGagnants(ticket, tirage);
// //                     chanceGagnante = ticket.getChanceNumber() == tirage.getNumeroChance();
// //                     rang = determinerRang(numerosTrouves, chanceGagnante);
// //                     gain = calculerGain(rang, tirage);
// //                 } else {
// //                     ResultatTirage tirage = (ResultatTirage) tirageObj;
// //                     numerosTrouves = ticket.getNumeros().containsAll(tirage.getNumeros()) ? 5 : 0;
// //                     chanceGagnante = false;
// //                     rang = (numerosTrouves == 5) ? 1 : 0;
// //                     gain = rang > 0 ? tirage.getGain() : 0;
// //                 }

// //                 if (rang >= 9) {
// //                     Map<String, Object> matchDetail = new HashMap<>();
// //                     matchDetail.put("tirage", tirageObj);
// //                     matchDetail.put("numerosTrouves", numerosTrouves);
// //                     matchDetail.put("chanceGagnante", chanceGagnante);
// //                     matchDetail.put("rang", rang);
// //                     matchDetail.put("gain", gain);
// //                     matchs.add(matchDetail);
// //                     gainTotal += gain;
// //                     statut = "GAGNANT";
// //                 }
// //             }

// //             ticket.setStatut(statut);
// //             ticket.setGain(gainTotal);
// //             ticketRepository.save(ticket);

// //             resultatTicket.put("matchs", matchs);
// //             resultatTicket.put("gainTotal", gainTotal);
// //             resultatsComparaison.add(resultatTicket);
// //         }

// //         return resultatsComparaison;
// //     }

// //     /**
// //      * Récupération des tirages SQL selon une plage de dates
// //      */
// //     private List<ResultatTirage> getTiragesParDate(String startDate, String endDate) {
// //         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
// //         LocalDate start = LocalDate.parse(startDate, formatter);
// //         LocalDate end = (endDate == null || endDate.isEmpty()) ? start : LocalDate.parse(endDate, formatter);
// //         return resultatTirageRepository.findByDateBetween(start.toString(), end.toString());
// //     }

// //     /**
// //      * Compte les numéros gagnants trouvés dans un ticket par rapport à un tirage MongoDB
// //      */
// //     private int compterNumerosGagnants(Ticket ticket, Tirage tirage) {
// //         Set<Integer> numerosTicket = Arrays.stream(ticket.getNumbers().split("-"))
// //                                            .map(Integer::parseInt)
// //                                            .collect(Collectors.toSet());

// //         Set<Integer> numerosTirage = new HashSet<>(Arrays.asList(
// //                 tirage.getBou1(), tirage.getBou2(), tirage.getBou3(),
// //                 tirage.getBou4(), tirage.getBou5()));

// //         numerosTicket.retainAll(numerosTirage);
// //         return numerosTicket.size();
// //     }

// //     /**
// //      * Détermine le rang du ticket en fonction des numéros trouvés et du numéro chance
// //      */
// //     private int determinerRang(int numerosTrouves, boolean chanceGagnante) {
// //         return switch (numerosTrouves) {
// //             case 5 -> chanceGagnante ? 1 : 2;
// //             case 4 -> chanceGagnante ? 3 : 4;
// //             case 3 -> chanceGagnante ? 5 : 6;
// //             case 2 -> chanceGagnante ? 7 : 8;
// //             case 1 -> chanceGagnante ? 9 : 0;
// //             default -> 0;
// //         };
// //     }

// //     /**
// //      * Calcule le gain en fonction du rang et du tirage MongoDB
// //      */
// //     private double calculerGain(int rang, Tirage tirage) {
// //         return switch (rang) {
// //             case 1 -> tirage.getRapportDuRang1();
// //             case 2 -> tirage.getRapportDuRang2();
// //             case 3 -> tirage.getRapportDuRang3();
// //             case 4 -> tirage.getRapportDuRang4();
// //             case 5 -> tirage.getRapportDuRang5();
// //             case 6 -> tirage.getRapportDuRang6();
// //             case 7 -> tirage.getRapportDuRang7();
// //             case 8 -> tirage.getRapportDuRang8();
// //             case 9 -> tirage.getRapportDuRang9();
// //             default -> 0;
// //         };
// //     }
// // }


// package com.fdjloto.api.controller;

// import com.fdjloto.api.model.Comparaison;
// import com.fdjloto.api.service.ComparaisonService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/comparaison")
// public class ComparaisonController {

//     private final ComparaisonService comparaisonService;

//     public ComparaisonController(ComparaisonService comparaisonService) {
//         this.comparaisonService = comparaisonService;
//     }

//     @GetMapping("/tous")
//     public ResponseEntity<List<Comparaison>> comparerTousTickets() {
//         List<Comparaison> resultats = comparaisonService.comparerTousLesTickets();
//         return ResponseEntity.ok(resultats);
//     }
// }

package com.fdjloto.api.controller;

import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.dto.TicketDTO;
import com.fdjloto.api.model.Draw;
import com.fdjloto.api.service.TicketService;
import com.fdjloto.api.service.DrawService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comparaison")
public class ComparaisonController {

    private static final Logger logger = LoggerFactory.getLogger(ComparaisonController.class);

    private final TicketService ticketService;
    private final DrawService drawService;

    public ComparaisonController(TicketService ticketService, DrawService drawService) {
        this.ticketService = ticketService;
        this.drawService = drawService;
    }

    @GetMapping("/calculate-gains/{userId}")
    public String calculateGains(@PathVariable String userId) {
        List<TicketDTO> ticketDTOs = ticketService.getTicketsByUserId(userId);
        List<Ticket> tickets = ticketDTOs.stream().map(this::convertDtoToEntity).toList();

        for (Ticket ticket : tickets) {
            Optional<Draw> drawOpt = drawService.getDrawByDate(ticket.getDrawDate().toString());

            if (drawOpt.isPresent()) {
                Draw draw = drawOpt.get();

                // ✅ Correction : Vérification du bon format des numéros
                Set<Integer> ticketNumbers = new HashSet<>(ticket.getNumerosAsList());
                Set<Integer> drawNumbers = new HashSet<>(draw.getNumbers());

                int matchedNumbers = countMatchingNumbers(ticketNumbers, drawNumbers);
                boolean luckyNumberMatched = ticket.getChanceNumber() == draw.getChanceNumber();
                int rank = determineRank(matchedNumbers, luckyNumberMatched);

                double gain = drawService.getGainForRank(ticket.getDrawDate().toString(), rank);

                ticket.setGains(gain);
                ticket.setStatus(rank > 0 ? "GAGNANT" : "PERDANT");
                ticketService.updateTicket(ticket.getId(), new TicketDTO(ticket));

                logger.info("🎯 Ticket ID: {} → Gain: {}€, Rang: {}", ticket.getId(), gain, rank);
            } else {
                logger.warn("❌ Aucun tirage trouvé pour la date: {}", ticket.getDrawDate());
            }
        }
        return "✅ Gains mis à jour avec succès !";
    }

    private int countMatchingNumbers(Set<Integer> ticketNumbers, Set<Integer> drawNumbers) {
        return (int) ticketNumbers.stream()
                .filter(drawNumbers::contains)
                .count();
    }

    private int determineRank(int matchedNumbers, boolean luckyNumberMatched) {
        if (matchedNumbers == 5 && luckyNumberMatched) return 1;
        if (matchedNumbers == 5) return 2;
        if (matchedNumbers == 4 && luckyNumberMatched) return 3;
        if (matchedNumbers == 4) return 4;
        if (matchedNumbers == 3 && luckyNumberMatched) return 5;
        if (matchedNumbers == 3) return 6;
        if (matchedNumbers == 2 && luckyNumberMatched) return 7;
        if (matchedNumbers == 2) return 8;
        if (luckyNumberMatched) return 9;
        return 0;
    }

    private Ticket convertDtoToEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(UUID.fromString(ticketDTO.getId()));
        ticket.setNumbers(ticketDTO.getNumbers());
        ticket.setChanceNumber(Integer.parseInt(ticketDTO.getChanceNumber())); // ✅ Convertit String → int
        ticket.setDrawDate(LocalDate.parse(ticketDTO.getDrawDate()));
        ticket.setGains(ticketDTO.getGains());
        ticket.setStatus(ticketDTO.getStatus());
        return ticket;
    }
}
