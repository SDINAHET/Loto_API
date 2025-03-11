// package com.fdjloto.api.service;

// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.model.LotoResult;
// import com.fdjloto.api.dto.GainResultDTO;
// import com.fdjloto.api.repository.TicketRepository;
// import com.fdjloto.api.repository.LotoRepository;
// import com.fdjloto.api.repository.TicketGainRepository;
// import com.fdjloto.api.model.TicketGain;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.scheduling.annotation.EnableScheduling; // ✅ Import de @EnableScheduling


// import java.util.List;
// import java.util.Optional;
// import java.util.Arrays;
// import java.util.stream.Collectors;


// @SpringBootApplication
// @EnableScheduling // ✅ Active la planification
// @Service
// public class GainCalculationService {

//     private final TicketRepository ticketRepository;
//     private final LotoRepository lotoRepository;
//     private final TicketGainRepository ticketGainRepository;


//     @Autowired
//     public GainCalculationService(TicketRepository ticketRepository, LotoRepository lotoRepository, TicketGainRepository ticketGainRepository) {
//         this.ticketRepository = ticketRepository;
//         this.lotoRepository = lotoRepository;
//         this.ticketGainRepository = ticketGainRepository;
//     }

//     // ✅ Planification 2 fois par heure : 00 et 30 minutes de chaque heure
// 	@Scheduled(cron = "*/30 * * * * *", zone = "Europe/Paris")

//     /**
//      * 🔥 Calcule les gains pour tous les tickets et enregistre les résultats.
//      */
//     @Transactional
//     public List<GainResultDTO> calculerGains() {
//         List<Ticket> tickets = ticketRepository.findAll();
//         List<LotoResult> resultatsLoto = lotoRepository.findAll();

//         return tickets.stream()
//                 .map(ticket -> {
//                     Optional<LotoResult> tirageOptionnel = lotoRepository.findByDateDeTirage(ticket.getDrawDate());

//                     if (tirageOptionnel.isPresent()) {
//                         LotoResult tirageCorrespondant = tirageOptionnel.get();
//                         int nbCorrespondances = compareNumbers(ticket, tirageCorrespondant);
//                         boolean chanceMatch = ticket.getChanceNumber() == tirageCorrespondant.getNumeroChance();
//                         double gain = getGainAmount(nbCorrespondances, chanceMatch, tirageCorrespondant);

//                         // ✅ Enregistrement du gain dans la base SQLite
//                         saveOrUpdateGain(ticket, nbCorrespondances, chanceMatch, gain);

//                         return new GainResultDTO(ticket.getId(), nbCorrespondances, chanceMatch, gain);
//                     }
//                     return new GainResultDTO(ticket.getId(), 0, false, 0.0);
//                 })
//                 .toList();
//     }

//     /**
//      * 🔍 Compare les numéros d'un ticket avec ceux du tirage officiel.
//      */
//     private int compareNumbers(Ticket ticket, LotoResult lotoResult) {
//         List<Integer> ticketNumbers = convertStringToList(ticket.getNumbers());
//         List<Integer> winningNumbers = extractWinningNumbers(lotoResult);
//         return (int) ticketNumbers.stream().filter(winningNumbers::contains).count();
//     }

//     /**
//      * 💰 Détermine le montant du gain en fonction du nombre de correspondances.
//      */
//     private double getGainAmount(int correspondances, boolean chanceMatch, LotoResult lotoResult) {
//         if (correspondances == 5 && chanceMatch) return lotoResult.getRapportDuRang1();
//         if (correspondances == 5) return lotoResult.getRapportDuRang2();
//         if (correspondances == 4 && chanceMatch) return lotoResult.getRapportDuRang3();
//         if (correspondances == 4) return lotoResult.getRapportDuRang4();
//         if (correspondances == 3 && chanceMatch) return lotoResult.getRapportDuRang5();
//         if (correspondances == 3) return lotoResult.getRapportDuRang6();
//         if (correspondances == 2 && chanceMatch) return lotoResult.getRapportDuRang7();
//         if (correspondances == 2) return lotoResult.getRapportDuRang8();
//         if (correspondances == 0 && chanceMatch) return lotoResult.getRapportDuRang9();
//         return 0.0;
//     }

//     /**
//      * 📌 Enregistre ou met à jour les gains dans la base SQLite.
//      */
//     @Transactional
//     private void saveOrUpdateGain(Ticket ticket, int matchingNumbers, boolean luckyNumberMatch, double gainAmount) {
//         Optional<TicketGain> existingGain = ticketGainRepository.findById(ticket.getId());

//         if (existingGain.isPresent()) {
//             TicketGain ticketGain = existingGain.get();
//             ticketGain.setMatchingNumbers(matchingNumbers);
//             ticketGain.setLuckyNumberMatch(luckyNumberMatch);
//             ticketGain.setGainAmount(gainAmount);
//             ticketGainRepository.save(ticketGain);
//         } else {
//             TicketGain newTicketGain = new TicketGain(ticket.getId(), ticket, matchingNumbers, luckyNumberMatch, gainAmount);
//             ticketGainRepository.save(newTicketGain);
//         }
//     }

//     /**
//      * ✅ Convertit une chaîne de numéros en liste d'entiers.
//      */
//     private List<Integer> convertStringToList(String numbers) {
//         return Arrays.stream(numbers.split("-"))
//                 .map(Integer::parseInt)
//                 .collect(Collectors.toList());
//     }

//     /**
//      * ✅ Extrait les numéros gagnants d'un tirage officiel.
//      */
//     private List<Integer> extractWinningNumbers(LotoResult lotoResult) {
//         return List.of(
//                 lotoResult.getBoule1(),
//                 lotoResult.getBoule2(),
//                 lotoResult.getBoule3(),
//                 lotoResult.getBoule4(),
//                 lotoResult.getBoule5()
//         );
//     }
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.dto.TicketGainDTO;
// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.model.TicketGain;
// import com.fdjloto.api.repository.TicketGainRepository;
// import com.fdjloto.api.repository.TicketRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;
// import java.util.Random;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Service
// public class GainCalculationService {

//     private static final Logger logger = LoggerFactory.getLogger(GainCalculationService.class);
//     private final TicketGainRepository ticketGainRepository;
//     private final TicketRepository ticketRepository;
//     private final Random random = new Random(); // ✅ Réutilisation d'un seul objet Random

//     public GainCalculationService(TicketGainRepository ticketGainRepository, TicketRepository ticketRepository) {
//         this.ticketGainRepository = ticketGainRepository;
//         this.ticketRepository = ticketRepository;
//     }

//     /**
//      * 🚀 Calcule les gains pour tous les tickets et met à jour la base.
//      */
//     @Transactional
//     public List<TicketGainDTO> calculerGains() {
//         logger.info("🔄 Début du calcul des gains pour tous les tickets...");

//         List<Ticket> tickets = ticketRepository.findAll();

//         List<TicketGainDTO> gainsDTO = tickets.stream().map(ticket -> {
//             int matchingNumbers = random.nextInt(6); // Simule le nombre de numéros correspondants (0-5)
//             boolean luckyNumberMatch = random.nextBoolean();
//             double gainAmount = matchingNumbers * 10.0; // Exemple de gain basé sur le nombre de numéros

//             // Vérifier si un TicketGain existe déjà ou en créer un nouveau
//             TicketGain ticketGain = ticketGainRepository.findByTicketId(ticket.getId())
//                     .orElse(new TicketGain(ticket.getId(), matchingNumbers, luckyNumberMatch, gainAmount));

//             // Mettre à jour les valeurs existantes
//             ticketGain.setMatchingNumbers(matchingNumbers);
//             ticketGain.setLuckyNumberMatch(luckyNumberMatch);
//             ticketGain.setGainAmount(gainAmount);

//             ticketGainRepository.save(ticketGain);
//             return new TicketGainDTO(ticketGain);
//         }).toList(); // ✅ Utilisation de Stream.toList() pour une meilleure compatibilité

//         logger.info("✅ Calcul des gains terminé !");
//         return gainsDTO;
//     }
// }


// package com.fdjloto.api.service;

// import com.fdjloto.api.dto.TicketGainDTO;
// import com.fdjloto.api.model.Ticket;
// import com.fdjloto.api.model.LotoResult;
// import com.fdjloto.api.model.TicketGain;
// import com.fdjloto.api.repository.TicketRepository;
// import com.fdjloto.api.repository.LotoRepository;
// import com.fdjloto.api.repository.TicketGainRepository;
// import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import java.util.List;
// import java.util.Optional;

// @Service
// @EnableScheduling // ✅ Active la planification automatique
// public class GainCalculationService {

//     private static final Logger logger = LoggerFactory.getLogger(GainCalculationService.class);
//     private final TicketRepository ticketRepository;
//     private final LotoRepository lotoRepository;
//     private final TicketGainRepository ticketGainRepository;
//     private final TicketGainService ticketGainService; // 🔥 Injection du service pour éviter `this.` dans @Transactional

//     public GainCalculationService(TicketRepository ticketRepository, LotoRepository lotoRepository, TicketGainRepository ticketGainRepository, TicketGainService ticketGainService) {
//         this.ticketRepository = ticketRepository;
//         this.lotoRepository = lotoRepository;
//         this.ticketGainRepository = ticketGainRepository;
//         this.ticketGainService = ticketGainService;
//     }

//     // ✅ Planification : Exécution toutes les 30 minutes
//     @Scheduled(cron = "0 */30 * * * *", zone = "Europe/Paris")
//     @Transactional
//     public List<TicketGainDTO> calculerGains() {
//         logger.info("🔄 Début du calcul des gains pour tous les tickets...");

//         List<Ticket> tickets = ticketRepository.findAll();

//         List<TicketGainDTO> gainsDTO = tickets.stream().map(ticket -> {
//             Optional<LotoResult> tirageOptionnel = lotoRepository.findByDateDeTirage(ticket.getDrawDate());

//             if (tirageOptionnel.isPresent()) {
//                 LotoResult tirage = tirageOptionnel.get();
//                 int matchingNumbers = compareNumbers(ticket, tirage);
//                 boolean luckyNumberMatch = ticket.getChanceNumber() == tirage.getNumeroChance();
//                 double gainAmount = getGainAmount(matchingNumbers, luckyNumberMatch, tirage);

//                 // ✅ Appel via `ticketGainService` au lieu de `this.`
//                 ticketGainService.saveOrUpdateGain(ticket, matchingNumbers, luckyNumberMatch, gainAmount);

//                 // ✅ Correction : Utilisation du bon constructeur de `TicketGainDTO`
//                 return new TicketGainDTO(ticket.getId(), matchingNumbers, luckyNumberMatch, gainAmount);
//             }

//             return new TicketGainDTO(ticket.getId(), 0, false, 0.0);
//         }).toList();

//         logger.info("✅ Calcul des gains terminé !");
//         return gainsDTO;
//     }

//     /**
//      * 🔍 Compare les numéros d'un ticket avec ceux du tirage officiel.
//      */
//     private int compareNumbers(Ticket ticket, LotoResult lotoResult) {
//         List<Integer> ticketNumbers = convertStringToList(ticket.getNumbers());
//         List<Integer> winningNumbers = extractWinningNumbers(lotoResult);
//         return (int) ticketNumbers.stream().filter(winningNumbers::contains).count();
//     }

//     /**
//      * 💰 Détermine le montant du gain en fonction du nombre de correspondances.
//      */
//     private double getGainAmount(int correspondances, boolean chanceMatch, LotoResult lotoResult) {
//         if (correspondances == 5 && chanceMatch) return lotoResult.getRapportDuRang1();
//         if (correspondances == 5) return lotoResult.getRapportDuRang2();
//         if (correspondances == 4 && chanceMatch) return lotoResult.getRapportDuRang3();
//         if (correspondances == 4) return lotoResult.getRapportDuRang4();
//         if (correspondances == 3 && chanceMatch) return lotoResult.getRapportDuRang5();
//         if (correspondances == 3) return lotoResult.getRapportDuRang6();
//         if (correspondances == 2 && chanceMatch) return lotoResult.getRapportDuRang7();
//         if (correspondances == 2) return lotoResult.getRapportDuRang8();
//         if (correspondances == 0 && chanceMatch) return lotoResult.getRapportDuRang9();
//         return 0.0;
//     }

//     /**
//      * ✅ Convertit une chaîne de numéros en liste d'entiers.
//      */
//     private List<Integer> convertStringToList(String numbers) {
//         return numbers.isEmpty() ? List.of() : numbers.lines()
//                 .map(Integer::parseInt)
//                 .toList();
//     }

//     /**
//      * ✅ Extrait les numéros gagnants d'un tirage officiel.
//      */
//     private List<Integer> extractWinningNumbers(LotoResult lotoResult) {
//         return List.of(
//                 lotoResult.getBoule1(),
//                 lotoResult.getBoule2(),
//                 lotoResult.getBoule3(),
//                 lotoResult.getBoule4(),
//                 lotoResult.getBoule5()
//         );
//     }
// }

package com.fdjloto.api.service;

import com.fdjloto.api.dto.TicketGainDTO;
import com.fdjloto.api.model.Ticket;
import com.fdjloto.api.model.LotoResult;
import com.fdjloto.api.model.TicketGain;
import com.fdjloto.api.repository.TicketRepository;
import com.fdjloto.api.repository.LotoRepository;
import com.fdjloto.api.repository.TicketGainRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling // ✅ Active la planification automatique
public class GainCalculationService {

    private static final Logger logger = LoggerFactory.getLogger(GainCalculationService.class);
    private final TicketRepository ticketRepository;
    private final LotoRepository lotoRepository;
    private final TicketGainRepository ticketGainRepository;
    private final TicketGainService ticketGainService; // 🔥 Injection du service pour éviter `this.` dans @Transactional

    public GainCalculationService(TicketRepository ticketRepository, LotoRepository lotoRepository, TicketGainRepository ticketGainRepository, TicketGainService ticketGainService) {
        this.ticketRepository = ticketRepository;
        this.lotoRepository = lotoRepository;
        this.ticketGainRepository = ticketGainRepository;
        this.ticketGainService = ticketGainService;
    }

    // ✅ Planification : Exécution toutes les 30 minutes
    @Scheduled(cron = "0 */30 * * * *", zone = "Europe/Paris")
    @Transactional
    public List<TicketGainDTO> calculerGains() {
        logger.info("🔄 Début du calcul des gains pour tous les tickets...");

        List<Ticket> tickets = ticketRepository.findAll();

        List<TicketGainDTO> gainsDTO = tickets.stream().map(ticket -> {
            Optional<LotoResult> tirageOptionnel = lotoRepository.findByDateDeTirage(ticket.getDrawDate());

            if (tirageOptionnel.isPresent()) {
                LotoResult tirage = tirageOptionnel.get();
                int matchingNumbers = compareNumbers(ticket, tirage);
                boolean luckyNumberMatch = ticket.getChanceNumber() == tirage.getNumeroChance();
                double gainAmount = getGainAmount(matchingNumbers, luckyNumberMatch, tirage);

                // ✅ Appel via `ticketGainService` au lieu de `this.`
                ticketGainService.saveOrUpdateGain(ticket, matchingNumbers, luckyNumberMatch, gainAmount);

                // ✅ Correction : Utilisation du bon constructeur de `TicketGainDTO`
                return new TicketGainDTO(ticket.getId(), matchingNumbers, luckyNumberMatch, gainAmount);
            }

            return new TicketGainDTO(ticket.getId(), 0, false, 0.0);
        }).toList();

        logger.info("✅ Calcul des gains terminé !");
        return gainsDTO;
    }

    /**
     * 🔍 Compare les numéros d'un ticket avec ceux du tirage officiel.
     */
    private int compareNumbers(Ticket ticket, LotoResult lotoResult) {
        List<Integer> ticketNumbers = convertStringToList(ticket.getNumbers());
        List<Integer> winningNumbers = extractWinningNumbers(lotoResult);
        return (int) ticketNumbers.stream().filter(winningNumbers::contains).count();
    }

    /**
     * 💰 Détermine le montant du gain en fonction du nombre de correspondances.
     */
    private double getGainAmount(int correspondances, boolean chanceMatch, LotoResult lotoResult) {
        if (correspondances == 5 && chanceMatch) return lotoResult.getRapportDuRang1();
        if (correspondances == 5) return lotoResult.getRapportDuRang2();
        if (correspondances == 4 && chanceMatch) return lotoResult.getRapportDuRang3();
        if (correspondances == 4) return lotoResult.getRapportDuRang4();
        if (correspondances == 3 && chanceMatch) return lotoResult.getRapportDuRang5();
        if (correspondances == 3) return lotoResult.getRapportDuRang6();
        if (correspondances == 2 && chanceMatch) return lotoResult.getRapportDuRang7();
        if (correspondances == 2) return lotoResult.getRapportDuRang8();
        if (correspondances == 0 && chanceMatch) return lotoResult.getRapportDuRang9();
        return 0.0;
    }

    /**
     * ✅ Convertit une chaîne de numéros en liste d'entiers.
     */
    private List<Integer> convertStringToList(String numbers) {
        return numbers.isEmpty() ? List.of() : numbers.lines()
                .map(Integer::parseInt)
                .toList();
    }

    /**
     * ✅ Extrait les numéros gagnants d'un tirage officiel.
     */
    private List<Integer> extractWinningNumbers(LotoResult lotoResult) {
        return List.of(
                lotoResult.getBoule1(),
                lotoResult.getBoule2(),
                lotoResult.getBoule3(),
                lotoResult.getBoule4(),
                lotoResult.getBoule5()
        );
    }
}
