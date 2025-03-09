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

// //     @GetMapping("/tous")
// //     public List<Map<String, Object>> comparerTousTickets() {
// //         // 🔹 Récupération de tous les tirages FDJ
// //         List<ResultatTirage> tirages = resultatTirageRepository.findAll();

// //         // 🔹 Récupération de tous les tickets des utilisateurs
// //         List<Ticket> tickets = ticketRepository.findAll();

// //         // 🔥 Stockage des résultats de comparaison
// //         List<Map<String, Object>> resultatsComparaison = new ArrayList<>();

// //         // 🔄 Comparaison des tickets avec tous les tirages
// //         for (Ticket ticket : tickets) {
// //             Map<String, Object> resultatTicket = new HashMap<>();
// //             resultatTicket.put("ticket", ticket);

// //             List<Map<String, Object>> matchs = new ArrayList<>();

// //             for (ResultatTirage tirage : tirages) {
// //                 int numerosTrouves = compterNumerosGagnants(ticket, tirage);
// //                 boolean chanceGagnante = ticket.getChanceNumber() == tirage.getNumeroChance();

// //                 if (numerosTrouves > 0) {
// //                     Map<String, Object> matchDetail = new HashMap<>();
// //                     matchDetail.put("tirage", tirage);
// //                     matchDetail.put("numerosTrouves", numerosTrouves);
// //                     matchDetail.put("chanceGagnante", chanceGagnante);
// //                     matchDetail.put("rang", determinerRang(numerosTrouves, chanceGagnante, tirage));

// //                     matchs.add(matchDetail);
// //                 }
// //             }

// //             resultatTicket.put("matchs", matchs);
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

// //     // ✅ Détermine le rang de gain basé sur les numéros trouvés et le numéro chance
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
// //         return 0; // Pas de gain
// //     }
// // }

// package com.loto.api.controllers;

// import com.loto.api.models.ResultatTirage;
// import com.loto.api.models.Ticket;
// import com.loto.api.repositories.ResultatTirageRepository;
// import com.loto.api.repositories.TicketRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.*;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/comparaison")
// public class ComparaisonController {

//     @Autowired
//     private TicketRepository ticketRepository;

//     @Autowired
//     private ResultatTirageRepository resultatTirageRepository;

//     @GetMapping("/derniers")
//     public List<Map<String, Object>> comparerDerniersTickets() {
//         // 🔹 Récupère les 100 derniers tirages FDJ (triés par date décroissante)
//         List<ResultatTirage> tirages = resultatTirageRepository.findAll()
//                 .stream()
//                 .sorted(Comparator.comparing(ResultatTirage::getDateDeTirage).reversed())
//                 .limit(100)
//                 .toList();

//         // 🔹 Récupère tous les tickets des utilisateurs
//         List<Ticket> tickets = ticketRepository.findAll();

//         // 🔥 Stockage des résultats de comparaison
//         List<Map<String, Object>> resultatsComparaison = new ArrayList<>();

//         // 🔄 Comparaison des tickets avec les 100 derniers tirages
//         for (Ticket ticket : tickets) {
//             Map<String, Object> resultatTicket = new HashMap<>();
//             resultatTicket.put("ticket", ticket);

//             List<Map<String, Object>> matchs = new ArrayList<>();
//             double gainTotal = 0;
//             String statut = "PERDU";

//             for (ResultatTirage tirage : tirages) {
//                 int numerosTrouves = compterNumerosGagnants(ticket, tirage);
//                 boolean chanceGagnante = ticket.getChanceNumber() == tirage.getNumeroChance();
//                 int rang = determinerRang(numerosTrouves, chanceGagnante, tirage);
//                 double gain = calculerGain(rang, tirage);

//                 if (rang > 0) {
//                     Map<String, Object> matchDetail = new HashMap<>();
//                     matchDetail.put("tirage", tirage);
//                     matchDetail.put("numerosTrouves", numerosTrouves);
//                     matchDetail.put("chanceGagnante", chanceGagnante);
//                     matchDetail.put("rang", rang);
//                     matchDetail.put("gain", gain);

//                     matchs.add(matchDetail);
//                     gainTotal += gain;
//                     statut = "GAGNANT";
//                 }
//             }

//             // ✅ Mise à jour en base SQLite du statut et du gain du ticket
//             ticket.setStatut(statut);
//             ticket.setGain(gainTotal);
//             ticketRepository.save(ticket);

//             resultatTicket.put("matchs", matchs);
//             resultatTicket.put("gainTotal", gainTotal);
//             resultatsComparaison.add(resultatTicket);
//         }

//         return resultatsComparaison;
//     }

//     // ✅ Compte combien de numéros du ticket sont dans le tirage
//     private int compterNumerosGagnants(Ticket ticket, ResultatTirage tirage) {
//         Set<Integer> numerosTicket = Arrays.stream(ticket.getNumbers().split("-"))
//                                            .map(Integer::parseInt)
//                                            .collect(Collectors.toSet());

//         Set<Integer> numerosTirage = new HashSet<>(Arrays.asList(
//                 tirage.getBou1(), tirage.getBou2(), tirage.getBou3(),
//                 tirage.getBou4(), tirage.getBou5()));

//         numerosTicket.retainAll(numerosTirage);
//         return numerosTicket.size();
//     }

//     // ✅ Détermine le rang du ticket
//     private int determinerRang(int numerosTrouves, boolean chanceGagnante, ResultatTirage tirage) {
//         if (numerosTrouves == 5 && chanceGagnante) return 1;
//         if (numerosTrouves == 5) return 2;
//         if (numerosTrouves == 4 && chanceGagnante) return 3;
//         if (numerosTrouves == 4) return 4;
//         if (numerosTrouves == 3 && chanceGagnante) return 5;
//         if (numerosTrouves == 3) return 6;
//         if (numerosTrouves == 2 && chanceGagnante) return 7;
//         if (numerosTrouves == 2) return 8;
//         if (numerosTrouves == 1 && chanceGagnante) return 9;
//         return 0;
//     }

//     // ✅ Calcule le gain du ticket en fonction du rang et du tirage FDJ
//     private double calculerGain(int rang, ResultatTirage tirage) {
//         switch (rang) {
//             case 1: return tirage.getRapportDuRang1();
//             case 2: return tirage.getRapportDuRang2();
//             case 3: return tirage.getRapportDuRang3();
//             case 4: return tirage.getRapportDuRang4();
//             case 5: return tirage.getRapportDuRang5();
//             case 6: return tirage.getRapportDuRang6();
//             case 7: return tirage.getRapportDuRang7();
//             case 8: return tirage.getRapportDuRang8();
//             case 9: return tirage.getRapportDuRang9();
//             default: return 0;
//         }
//     }
// }
