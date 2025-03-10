// // package com.fdjloto.api.service;

// // import com.fdjloto.api.model.Comparaison;
// // import com.fdjloto.api.model.Ticket;
// // import com.fdjloto.api.model.Tirage;
// // import com.fdjloto.api.repository.ComparaisonRepository;
// // import com.fdjloto.api.repository.TicketRepository;
// // import com.fdjloto.api.repository.TirageRepository;
// // import org.springframework.stereotype.Service;

// // import java.util.*;

// // @Service
// // public class ComparaisonService {

// //     private final ComparaisonRepository comparaisonRepository;
// //     private final TicketRepository ticketRepository;
// //     private final TirageRepository tirageRepository;

// //     public ComparaisonService(ComparaisonRepository comparaisonRepository, TicketRepository ticketRepository, TirageRepository tirageRepository) {
// //         this.comparaisonRepository = comparaisonRepository;
// //         this.ticketRepository = ticketRepository;
// //         this.tirageRepository = tirageRepository;
// //     }

// //     public List<Comparaison> comparerTousLesTickets() {
// //         List<Ticket> tickets = ticketRepository.findAll();
// //         List<Tirage> tirages = tirageRepository.findAll();

// //         if (tickets.isEmpty() || tirages.isEmpty()) {
// //             return Collections.emptyList();
// //         }

// //         List<Comparaison> resultatsComparaison = new ArrayList<>();

// //         for (Ticket ticket : tickets) {
// //             for (Tirage tirage : tirages) {
// //                 int numerosTrouves = compterNumerosGagnants(ticket, tirage);
// //                 boolean chanceGagnante = ticket.getChanceNumber() == tirage.getNumeroChance();
// //                 int rang = determinerRang(numerosTrouves, chanceGagnante);
// //                 double gain = calculerGain(rang, tirage);

// //                 String statut = (rang >= 9) ? "GAGNANT" : "PERDANT";
// //                 Comparaison comparaison = new Comparaison(ticket, tirage, numerosTrouves, chanceGagnante, rang, gain, statut);
// //                 comparaisonRepository.save(comparaison);
// //                 resultatsComparaison.add(comparaison);
// //             }
// //         }
// //         return resultatsComparaison;
// //     }

// //     private int compterNumerosGagnants(Ticket ticket, Tirage tirage) {
// //         Set<Integer> numerosTicket = new HashSet<>(ticket.getNumerosAsList());
// //         // Set<Integer> numerosTirage = new HashSet<>(tirage.getNumerosAsList());
// // 		Set<Integer> numerosTirage = new HashSet<>(tirage.getBoules());


// //         numerosTicket.retainAll(numerosTirage);
// //         return numerosTicket.size();
// //     }

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

// //     private double calculerGain(int rang, Tirage tirage) {
// //         return tirage.getRapportDuRang(rang);
// //     }
// // }


// package com.fdjloto.api.service;

// import com.fdjloto.api.model.Comparaison;
// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.model.Tirage;
// import com.fdjloto.api.repository.ComparaisonRepository;
// import com.fdjloto.api.repository.TicketRepository;
// import com.fdjloto.api.repository.TirageRepository;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.stereotype.Service;

// import java.util.*;

// @Slf4j
// @Service
// @RequiredArgsConstructor
// public class ComparaisonService {

//     private final ComparaisonRepository comparaisonRepository;
//     private final TicketRepository ticketRepository;
//     private final TirageRepository tirageRepository;

//     /**
//      * Compare tous les tickets avec les tirages et enregistre les résultats.
//      */
//     public List<Comparaison> comparerTousLesTickets() {
//         log.info("🔍 Début de la comparaison des tickets avec les tirages...");

//         // Récupération des tickets et des tirages
//         List<Ticket> tickets = ticketRepository.findAll();
//         List<Tirage> tirages = tirageRepository.findAll();

//         log.info("📌 {} tickets récupérés.", tickets.size());
//         log.info("📌 {} tirages récupérés.", tirages.size());

//         if (tickets.isEmpty() || tirages.isEmpty()) {
//             log.warn("⚠️ Aucun ticket ou tirage trouvé.");
//             return Collections.emptyList();
//         }

//         List<Comparaison> resultatsComparaison = new ArrayList<>();

//         // Comparaison des tickets avec les tirages
//         for (Ticket ticket : tickets) {
//             Set<Integer> numerosTicket = new HashSet<>(ticket.getNumerosAsList());
//             for (Tirage tirage : tirages) {
//                 Set<Integer> numerosTirage = new HashSet<>(tirage.getBoules());

//                 // Calcul du nombre de correspondances
//                 int correspondances = (int) numerosTicket.stream().filter(numerosTirage::contains).count();
//                 boolean chanceGagnante = ticket.getChanceNumber() == tirage.getNumeroChance();

//                 int rang = determinerRang(correspondances, chanceGagnante);
//                 double gain = tirage.getRapportDuRang(rang);
//                 String statut = (rang >= 9) ? "GAGNANT" : "PERDANT";

//                 Comparaison comparaison = new Comparaison(ticket, tirage, correspondances, chanceGagnante, rang, gain, statut);
//                 comparaisonRepository.save(comparaison);
//                 resultatsComparaison.add(comparaison);

//                 log.info("🎟️ Ticket {} → {} correspondances{} → Rang: {} → Gain: {}€ → Statut: {}",
//                         ticket.getId(), correspondances, (chanceGagnante ? " + Numéro Chance" : ""), rang, gain, statut);
//             }
//         }

//         log.info("✅ Comparaison terminée. {} résultats enregistrés.", resultatsComparaison.size());
//         return resultatsComparaison;
//     }

//     /**
//      * Détermine le rang du ticket en fonction des numéros gagnants et du numéro chance.
//      */
//     private int determinerRang(int correspondances, boolean chanceGagnante) {
//         return switch (correspondances) {
//             case 5 -> chanceGagnante ? 1 : 2;
//             case 4 -> chanceGagnante ? 3 : 4;
//             case 3 -> chanceGagnante ? 5 : 6;
//             case 2 -> chanceGagnante ? 7 : 8;
//             case 1 -> chanceGagnante ? 9 : 0;
//             default -> 0;
//         };
//     }
// }
