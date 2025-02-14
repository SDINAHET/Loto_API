// package com.fdjloto.api.service;

// import com.fdjloto.api.model.PredictionTirageModel;
// import com.fdjloto.api.model.Tirage;
// import com.fdjloto.api.repository.PredictionTirageRepository;
// import com.fdjloto.api.repository.TirageRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;

// import java.util.*;
// import java.util.stream.Collectors;

// @Service
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

//     private final TirageRepository tirageRepository;
//     private final PredictionTirageRepository predictionRepository;

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionTirageRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public void generatePrediction() {
//         List<Tirage> tirages = tirageRepository.findAll();

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return;
//         }

//         // Fréquence des numéros
//         Map<Integer, Integer> numberCounts = new HashMap<>();
//         Map<Integer, Integer> chanceCounts = new HashMap<>();
//         List<Integer> allChances = new ArrayList<>();

//         for (Tirage tirage : tirages) {
//             for (int boule : tirage.getBoules()) {
//                 numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
//             }
//             int numeroChance = tirage.getNumeroChance();
//             chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
//             allChances.add(numeroChance);
//         }

//         // 5 numéros les plus fréquents
//         List<Integer> probableNumbers = numberCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(5)
//                 .map(Map.Entry::getKey)
//                 .toList(); // Remplace `.collect(Collectors.toList())`

//         // Numéro chance le plus fréquent
//         int probableChance = chanceCounts.entrySet().stream()
//                 .max(Map.Entry.comparingByValue())
//                 .map(Map.Entry::getKey)
//                 .orElse(-1);

//         // Écart-type des numéros
//         Map<String, Double> stdDevNumbers = new HashMap<>();
//         for (int num : probableNumbers) {
//             List<Integer> occurrences = tirages.stream()
//                     .flatMap(t -> t.getBoules().stream())
//                     .filter(b -> b == num)
//                     .toList();

//             double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//             double variance = occurrences.stream().mapToDouble(n -> Math.pow(n - mean, 2)).average().orElse(0.0);
//             stdDevNumbers.put(String.valueOf(num), Math.sqrt(variance));
//         }

//         // Écart-type du numéro chance
//         double meanChance = allChances.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//         double varianceChance = allChances.stream().mapToDouble(n -> Math.pow(n - meanChance, 2)).average().orElse(0.0);
//         double stdDevChance = Math.sqrt(varianceChance);

//         // Sauvegarde dans MongoDB
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setStdDevChance(stdDevChance);

//         predictionRepository.save(prediction);
//         logger.info("✅ Prédiction enregistrée avec succès !");
//     }
// }

// @Service
// public class PredictionService {
//     @Autowired
//     private PredictionRepository predictionRepository;

//     public PredictionTirageModel getLatestPrediction() {
//         return predictionRepository.findTopByOrderByIdDesc();
//     }
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.PredictionTirageModel;
// import com.fdjloto.api.model.Tirage;
// import com.fdjloto.api.repository.PredictionRepository;
// import com.fdjloto.api.repository.TirageRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;

// import java.util.*;
// import java.util.stream.Collectors;

// @Service
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

//     private final TirageRepository tirageRepository;
//     private final PredictionRepository predictionRepository;

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public void generatePrediction() {
//         List<Tirage> tirages = tirageRepository.findAll();

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return;
//         }

//         Map<Integer, Integer> numberCounts = new HashMap<>();
//         Map<Integer, Integer> chanceCounts = new HashMap<>();
//         List<Integer> allChances = new ArrayList<>();

//         for (Tirage tirage : tirages) {
//             for (int boule : tirage.getBoules()) {
//                 numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
//             }
//             int numeroChance = tirage.getNumeroChance();
//             chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
//             allChances.add(numeroChance);
//         }

//         List<Integer> probableNumbers = numberCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(5)
//                 .map(Map.Entry::getKey)
//                 .toList();

//         int probableChance = chanceCounts.entrySet().stream()
//                 .max(Map.Entry.comparingByValue())
//                 .map(Map.Entry::getKey)
//                 .orElse(-1);

//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         for (int num : probableNumbers) {
//             List<Integer> occurrences = tirages.stream()
//                     .flatMap(t -> t.getBoules().stream())
//                     .filter(b -> b == num)
//                     .toList();

//             double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//             double variance = occurrences.stream().mapToDouble(n -> Math.pow(n - mean, 2)).average().orElse(0.0);
//             stdDevNumbers.put(num, Math.sqrt(variance));
//         }

//         double meanChance = allChances.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//         double varianceChance = allChances.stream().mapToDouble(n -> Math.pow(n - meanChance, 2)).average().orElse(0.0);
//         double stdDevChance = Math.sqrt(varianceChance);

//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setStdDevChance(stdDevChance);

//         predictionRepository.save(prediction);
//         logger.info("✅ Prédiction enregistrée avec succès !");
//     }
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.PredictionTirageModel;
// import com.fdjloto.api.model.Tirage;
// import com.fdjloto.api.repository.PredictionRepository;
// import com.fdjloto.api.repository.TirageRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;

// import java.util.*;

// @Service
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

//     private final TirageRepository tirageRepository;
//     private final PredictionRepository predictionRepository;

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public void generatePrediction() {
//         List<Tirage> tirages = tirageRepository.findAll();

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return;
//         }

//         // Comptage des fréquences des numéros et des numéros chance
//         Map<Integer, Integer> numberCounts = new HashMap<>();
//         Map<Integer, Integer> chanceCounts = new HashMap<>();
//         List<Integer> allChances = new ArrayList<>();

//         for (Tirage tirage : tirages) {
//             for (int boule : tirage.getBoules()) {
//                 numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
//             }
//             int numeroChance = tirage.getNumeroChance();
//             chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
//             allChances.add(numeroChance);
//         }

//         // Sélection des 5 numéros les plus fréquents
//         List<Integer> probableNumbers = numberCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(5)
//                 .map(Map.Entry::getKey)
//                 .toList();

//         // Sélection du numéro chance le plus fréquent
//         int probableChance = chanceCounts.entrySet().stream()
//                 .max(Map.Entry.comparingByValue())
//                 .map(Map.Entry::getKey)
//                 .orElse(-1);

//         // Calcul de l'écart-type des numéros
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();

//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             if (occurrences.size() > 1) { // Vérification pour éviter la division par zéro
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream()
//                         .mapToDouble(n -> Math.pow(n - mean, 2))
//                         .sum() / (occurrences.size() - 1); // Correction: division par (N-1)

//                 stdDevNumbers.put(num, Math.sqrt(variance));
//             } else {
//                 stdDevNumbers.put(num, 0.0); // Si une seule occurrence, écart-type = 0
//             }
//         }

//         // Calcul de l'écart-type du numéro chance
//         double stdDevChance = 0.0;
//         if (allChances.size() > 1) {
//             double meanChance = allChances.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//             double varianceChance = allChances.stream()
//                     .mapToDouble(n -> Math.pow(n - meanChance, 2))
//                     .sum() / (allChances.size() - 1); // Correction: division par (N-1)

//             stdDevChance = Math.sqrt(varianceChance);
//         }

//         // Enregistrement de la prédiction dans MongoDB
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setStdDevChance(stdDevChance);

//         predictionRepository.save(prediction);
//         logger.info("✅ Prédiction enregistrée avec succès !");
//     }
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.PredictionTirageModel;
// import com.fdjloto.api.model.Tirage;
// import com.fdjloto.api.repository.PredictionRepository;
// import com.fdjloto.api.repository.TirageRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;

// import java.util.*;

// @Service
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

//     private final TirageRepository tirageRepository;
//     private final PredictionRepository predictionRepository;

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public void generatePrediction() {
//         List<Tirage> tirages = tirageRepository.findAll();

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return;
//         }

//         // Comptage des fréquences des numéros et numéros chance
//         Map<Integer, Integer> numberCounts = new HashMap<>();
//         Map<Integer, Integer> chanceCounts = new HashMap<>();
//         List<Integer> allChances = new ArrayList<>();

//         for (Tirage tirage : tirages) {
//             for (int boule : tirage.getBoules()) {
//                 numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
//             }
//             int numeroChance = tirage.getNumeroChance();
//             chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
//             allChances.add(numeroChance);
//         }

//         // Sélection des 5 numéros les plus fréquents
//         List<Integer> probableNumbers = numberCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(5)
//                 .map(Map.Entry::getKey)
//                 .toList();

//         // Sélection du numéro chance le plus fréquent
//         int probableChance = chanceCounts.entrySet().stream()
//                 .max(Map.Entry.comparingByValue())
//                 .map(Map.Entry::getKey)
//                 .orElse(-1);

//         // **🔹 Calcul de l'écart-type des numéros probables**
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();

//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             if (occurrences.size() > 1) { // Vérification pour éviter la division par zéro
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream()
//                         .mapToDouble(n -> Math.pow(n - mean, 2))
//                         .sum() / (occurrences.size() - 1); // ⚠️ Correction: division par (N-1)

//                 stdDevNumbers.put(num, Math.sqrt(variance));
//             } else {
//                 stdDevNumbers.put(num, 0.0); // Si une seule occurrence, écart-type = 0
//             }
//         }

//         // **🔹 Calcul de l'écart-type du numéro chance**
//         double stdDevChance = 0.0;
//         if (allChances.size() > 1) {
//             double meanChance = allChances.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//             double varianceChance = allChances.stream()
//                     .mapToDouble(n -> Math.pow(n - meanChance, 2))
//                     .sum() / (allChances.size() - 1); // ⚠️ Division par (N-1)

//             stdDevChance = Math.sqrt(varianceChance);
//         }

//         // **✅ Enregistrement de la prédiction dans MongoDB**
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setStdDevChance(stdDevChance);

//         predictionRepository.save(prediction);
//         logger.info("✅ Prédiction enregistrée avec succès !");
//     }
// }

package com.fdjloto.api.service;

import com.fdjloto.api.model.PredictionTirageModel;
import com.fdjloto.api.model.Tirage;
import com.fdjloto.api.repository.PredictionRepository;
import com.fdjloto.api.repository.TirageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PredictionTirageService {

    private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

    private final TirageRepository tirageRepository;
    private final PredictionRepository predictionRepository;

    public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
        this.tirageRepository = tirageRepository;
        this.predictionRepository = predictionRepository;
    }

    public void generatePrediction() {
        List<Tirage> tirages = tirageRepository.findAll();

        if (tirages.isEmpty()) {
            logger.warn("❌ Aucun tirage trouvé !");
            return;
        }

        // Comptage des fréquences des numéros et numéros chance
        Map<Integer, Integer> numberCounts = new HashMap<>();
        Map<Integer, Integer> chanceCounts = new HashMap<>();
        List<Integer> allChances = new ArrayList<>();

        for (Tirage tirage : tirages) {
            for (int boule : tirage.getBoules()) {
                numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
            }
            int numeroChance = tirage.getNumeroChance();
            chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
            allChances.add(numeroChance);
        }

        // Sélection des 5 numéros les plus fréquents
        List<Integer> probableNumbers = numberCounts.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();

        // Sélection du numéro chance le plus fréquent
        int probableChance = chanceCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);

        // **🔹 Calcul de l'écart-type et Intervalle de Confiance**
        Map<Integer, Double> stdDevNumbers = new HashMap<>();
        Map<Integer, String> confidenceIntervals = new HashMap<>();

        for (int num : probableNumbers) {
            List<Integer> occurrences = new ArrayList<>();

            for (Tirage tirage : tirages) {
                if (tirage.getBoules().contains(num)) {
                    occurrences.add(num);
                }
            }

            int N = occurrences.size();
            if (N > 1) { // Vérification pour éviter la division par zéro
                double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
                double variance = occurrences.stream()
                        .mapToDouble(n -> Math.pow(n - mean, 2))
                        .sum() / (N - 1);

                double stdDev = Math.sqrt(variance);
                stdDevNumbers.put(num, stdDev);

                // **Calcul de l'Intervalle de Confiance (95%)**
                double marginError = 1.96 * (stdDev / Math.sqrt(N));
                double lowerBound = mean - marginError;
                double upperBound = mean + marginError;

                confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
            } else {
                stdDevNumbers.put(num, 0.0);
                confidenceIntervals.put(num, "Trop faible pour un IC");
            }
        }

        // **🔹 Calcul de l'écart-type du numéro chance et IC**
        double stdDevChance = 0.0;
        String confidenceChance = "Trop faible pour un IC";
        int N_chance = allChances.size();

        if (N_chance > 1) {
            double meanChance = allChances.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            double varianceChance = allChances.stream()
                    .mapToDouble(n -> Math.pow(n - meanChance, 2))
                    .sum() / (N_chance - 1);

            stdDevChance = Math.sqrt(varianceChance);

            // Calcul IC pour numéro chance
            double marginErrorChance = 1.96 * (stdDevChance / Math.sqrt(N_chance));
            double lowerBoundChance = meanChance - marginErrorChance;
            double upperBoundChance = meanChance + marginErrorChance;
            confidenceChance = String.format("[%.2f, %.2f]", lowerBoundChance, upperBoundChance);
        }

        // **✅ Enregistrement de la prédiction**
        PredictionTirageModel prediction = new PredictionTirageModel();
        prediction.setProbableNumbers(probableNumbers);
        prediction.setProbableChance(probableChance);
        prediction.setStdDevNumbers(stdDevNumbers);
        prediction.setStdDevChance(stdDevChance);
        prediction.setConfidenceIntervals(confidenceIntervals);
        prediction.setConfidenceChance(confidenceChance);

        predictionRepository.save(prediction);
        logger.info("✅ Prédiction enregistrée avec succès !");
    }
}
