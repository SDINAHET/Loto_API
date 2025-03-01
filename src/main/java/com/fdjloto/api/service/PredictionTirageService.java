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

//     private inal TirageRepository tirageRepository;
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

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.PredictionTirageModel;
// import com.fdjloto.api.model.Tirage;
// import com.fdjloto.api.repository.PredictionRepository;
// import com.fdjloto.api.repository.TirageRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
// import org.springframework.data.domain.Sort;


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
//         // List<Tirage> tirages = tirageRepository.findAll();
//         List<Tirage> tirages = tirageRepository.findTop20ByOrderByDateTirageDesc(Sort.by(Sort.Direction.DESC, "dateTirage"));



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

//         // **🔹 Calcul de l'écart-type et Intervalle de Confiance**
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         Map<Integer, String> confidenceIntervals = new HashMap<>();

//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();

//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             int N = occurrences.size();
//             if (N > 1) { // Vérification pour éviter la division par zéro
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream()
//                         .mapToDouble(n -> Math.pow(n - mean, 2))
//                         .sum() / (N - 1);

//                 double stdDev = Math.sqrt(variance);
//                 stdDevNumbers.put(num, stdDev);

//                 // **Calcul de l'Intervalle de Confiance (95%)**
//                 double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                 double lowerBound = mean - marginError;
//                 double upperBound = mean + marginError;

//                 confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//             } else {
//                 stdDevNumbers.put(num, 0.0);
//                 confidenceIntervals.put(num, "Trop faible pour un IC");
//             }
//         }

//         // **🔹 Calcul de l'écart-type du numéro chance et IC**
//         double stdDevChance = 0.0;
//         String confidenceChance = "Trop faible pour un IC";
//         int N_chance = allChances.size();

//         if (N_chance > 1) {
//             double meanChance = allChances.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//             double varianceChance = allChances.stream()
//                     .mapToDouble(n -> Math.pow(n - meanChance, 2))
//                     .sum() / (N_chance - 1);

//             stdDevChance = Math.sqrt(varianceChance);

//             // Calcul IC pour numéro chance
//             double marginErrorChance = 1.96 * (stdDevChance / Math.sqrt(N_chance));
//             double lowerBoundChance = meanChance - marginErrorChance;
//             double upperBoundChance = meanChance + marginErrorChance;
//             confidenceChance = String.format("[%.2f, %.2f]", lowerBoundChance, upperBoundChance);
//         }

//         // **✅ Enregistrement de la prédiction**
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setStdDevChance(stdDevChance);
//         prediction.setConfidenceIntervals(confidenceIntervals);
//         prediction.setConfidenceChance(confidenceChance);

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
// import org.springframework.data.domain.Sort;

// import java.util.*;

// @Service
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

//     private final TirageRepository tirageRepository;
//     private final PredictionRepository predictionRepository;
//     private final Random random = new Random();

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public void generatePrediction() {
//         // Sélection dynamique de la taille de la fenêtre (10, 20 ou 30 derniers tirages)
//         int windowSize = List.of(10, 20, 30).get(random.nextInt(3));
//         logger.info("📊 Analyse des {} derniers tirages", windowSize);

//         List<Tirage> tirages = tirageRepository.findTop20ByOrderByDateTirageDesc(Sort.by(Sort.Direction.DESC, "dateTirage"))
//                 .subList(0, Math.min(windowSize, 20));

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return;
//         }

//         // Comptage des fréquences des numéros avec pondération temporelle
//         Map<Integer, Double> numberScores = new HashMap<>();
//         Map<Integer, Double> chanceScores = new HashMap<>();
//         List<Integer> allChances = new ArrayList<>();

//         int index = 0;
//         for (Tirage tirage : tirages) {
//             double weight = 1.0 / (1 + index++); // Pondération : plus récent = plus important

//             for (int boule : tirage.getBoules()) {
//                 numberScores.put(boule, numberScores.getOrDefault(boule, 0.0) + weight);
//             }
//             int numeroChance = tirage.getNumeroChance();
//             chanceScores.put(numeroChance, chanceScores.getOrDefault(numeroChance, 0.0) + weight);
//             allChances.add(numeroChance);
//         }

//         // Sélection des 5 numéros avec pondération + aléatoire
//         List<Integer> probableNumbers = numberScores.entrySet().stream()
//                 .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
//                 .limit(7) // On sélectionne 7 au lieu de 5 pour introduire un peu d'aléatoire
//                 .map(Map.Entry::getKey)
//                 .toList();

//         // Mélange et sélection finale
//         Collections.shuffle(probableNumbers);
//         probableNumbers = probableNumbers.subList(0, 5);

//         // Sélection du numéro chance
//         int probableChance = chanceScores.entrySet().stream()
//                 .max(Map.Entry.comparingByValue())
//                 .map(Map.Entry::getKey)
//                 .orElse(-1);

//         // Calcul de l'écart-type et Intervalle de Confiance
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         Map<Integer, String> confidenceIntervals = new HashMap<>();

//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();

//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             int N = occurrences.size();
//             if (N > 1) {
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream()
//                         .mapToDouble(n -> Math.pow(n - mean, 2))
//                         .sum() / (N - 1);

//                 double stdDev = Math.sqrt(variance);
//                 stdDevNumbers.put(num, stdDev);

//                 // Calcul de l'Intervalle de Confiance (95%)
//                 double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                 double lowerBound = mean - marginError;
//                 double upperBound = mean + marginError;
//                 confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//             } else {
//                 stdDevNumbers.put(num, 0.0);
//                 confidenceIntervals.put(num, "Trop faible pour un IC");
//             }
//         }

//         // Calcul de l'écart-type du numéro chance et IC
//         double stdDevChance = 0.0;
//         String confidenceChance = "Trop faible pour un IC";
//         int N_chance = allChances.size();

//         if (N_chance > 1) {
//             double meanChance = allChances.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//             double varianceChance = allChances.stream()
//                     .mapToDouble(n -> Math.pow(n - meanChance, 2))
//                     .sum() / (N_chance - 1);

//             stdDevChance = Math.sqrt(varianceChance);

//             // Calcul IC pour numéro chance
//             double marginErrorChance = 1.96 * (stdDevChance / Math.sqrt(N_chance));
//             double lowerBoundChance = meanChance - marginErrorChance;
//             double upperBoundChance = meanChance + marginErrorChance;
//             confidenceChance = String.format("[%.2f, %.2f]", lowerBoundChance, upperBoundChance);
//         }

//         // ✅ Enregistrement de la prédiction
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setStdDevChance(stdDevChance);
//         prediction.setConfidenceIntervals(confidenceIntervals);
//         prediction.setConfidenceChance(confidenceChance);

//         predictionRepository.save(prediction);
//         logger.info("✅ Nouvelle prédiction enregistrée !");
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
// import org.springframework.data.domain.Sort;

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
//         try {
//             // Récupération des 20 derniers tirages
//             List<Tirage> tirages = tirageRepository.findTop20ByOrderByDateTirageDesc(Sort.by(Sort.Direction.DESC, "dateTirage"));

//             if (tirages.isEmpty() || tirages.size() < 5) {
//                 logger.warn("❌ Pas assez de tirages disponibles pour une analyse fiable !");
//                 return;
//             }

//             // Comptage des fréquences des numéros et numéros chance
//             Map<Integer, Integer> numberCounts = new HashMap<>();
//             Map<Integer, Integer> chanceCounts = new HashMap<>();
//             List<Integer> allChances = new ArrayList<>();

//             for (Tirage tirage : tirages) {
//                 for (int boule : tirage.getBoules()) {
//                     numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
//                 }
//                 int numeroChance = tirage.getNumeroChance();
//                 chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
//                 allChances.add(numeroChance);
//             }

//             // Sélection des 5 numéros les plus fréquents
//             List<Integer> probableNumbers = numberCounts.entrySet().stream()
//                     .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                     .limit(5)
//                     .map(Map.Entry::getKey)
//                     .toList();

//             // Sélection du numéro chance le plus fréquent
//             int probableChance = chanceCounts.entrySet().stream()
//                     .max(Map.Entry.comparingByValue())
//                     .map(Map.Entry::getKey)
//                     .orElse(-1);

//             // **🔹 Calcul de l'écart-type et Intervalle de Confiance**
//             Map<Integer, Double> stdDevNumbers = new HashMap<>();
//             Map<Integer, String> confidenceIntervals = new HashMap<>();

//             for (int num : probableNumbers) {
//                 List<Integer> occurrences = new ArrayList<>();

//                 for (Tirage tirage : tirages) {
//                     if (tirage.getBoules().contains(num)) {
//                         occurrences.add(num);
//                     }
//                 }

//                 int N = occurrences.size();
//                 if (N > 1) { // Vérification pour éviter la division par zéro
//                     double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                     double variance = occurrences.stream()
//                             .mapToDouble(n -> Math.pow(n - mean, 2))
//                             .sum() / (N - 1);

//                     double stdDev = Math.sqrt(variance);
//                     stdDevNumbers.put(num, stdDev);

//                     // **Calcul de l'Intervalle de Confiance (95%)**
//                     double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                     double lowerBound = mean - marginError;
//                     double upperBound = mean + marginError;

//                     confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//                 } else {
//                     stdDevNumbers.put(num, 0.0);
//                     confidenceIntervals.put(num, "Trop faible pour un IC");
//                 }
//             }

//             // **🔹 Calcul de l'écart-type du numéro chance et IC**
//             double stdDevChance = 0.0;
//             String confidenceChance = "Trop faible pour un IC";
//             int N_chance = allChances.size();

//             if (N_chance > 1) {
//                 double meanChance = allChances.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double varianceChance = allChances.stream()
//                         .mapToDouble(n -> Math.pow(n - meanChance, 2))
//                         .sum() / (N_chance - 1);

//                 stdDevChance = Math.sqrt(varianceChance);

//                 // Calcul IC pour numéro chance
//                 double marginErrorChance = 1.96 * (stdDevChance / Math.sqrt(N_chance));
//                 double lowerBoundChance = meanChance - marginErrorChance;
//                 double upperBoundChance = meanChance + marginErrorChance;
//                 confidenceChance = String.format("[%.2f, %.2f]", lowerBoundChance, upperBoundChance);
//             }

//             // **✅ Enregistrement de la prédiction**
//             PredictionTirageModel prediction = new PredictionTirageModel();
//             prediction.setProbableNumbers(probableNumbers);
//             prediction.setProbableChance(probableChance);
//             prediction.setStdDevNumbers(stdDevNumbers);
//             prediction.setStdDevChance(stdDevChance);
//             prediction.setConfidenceIntervals(confidenceIntervals);
//             prediction.setConfidenceChance(confidenceChance);

//             predictionRepository.save(prediction);
//             logger.info("✅ Prédiction enregistrée avec succès !");
//         } catch (Exception e) {
//             logger.error("❌ Erreur lors de la génération de la prédiction :", e);
//         }
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
// import org.springframework.data.domain.Sort;

// import java.util.*;

// @Service
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

//     private final TirageRepository tirageRepository;
//     private final PredictionRepository predictionRepository;
//     private final Random random = new Random();

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public void generatePrediction() {
//         logger.info("📊 Récupération des 20 derniers tirages...");
//         List<Tirage> tirages = tirageRepository.findTop20ByOrderByDateTirageDesc(Sort.by(Sort.Direction.DESC, "dateTirage"));

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return;
//         }

//         // 🔹 Comptage des fréquences des numéros et numéros chance
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

//         // 🔹 Sélection des 5 numéros les plus fréquents
//         List<Integer> probableNumbers = numberCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(5)
//                 .map(Map.Entry::getKey)
//                 .toList();

//         // 🔹 Sélection du numéro chance le plus fréquent
//         int probableChance = chanceCounts.entrySet().stream()
//                 .max(Map.Entry.comparingByValue())
//                 .map(Map.Entry::getKey)
//                 .orElse(-1);

//         // 🔹 Calcul de l'écart-type et Intervalle de Confiance
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         Map<Integer, String> confidenceIntervals = new HashMap<>();

//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();

//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             int N = occurrences.size();
//             if (N > 1) {
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream()
//                         .mapToDouble(n -> Math.pow(n - mean, 2))
//                         .sum() / (N - 1);

//                 double stdDev = Math.sqrt(variance);
//                 stdDevNumbers.put(num, stdDev);

//                 double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                 double lowerBound = mean - marginError;
//                 double upperBound = mean + marginError;
//                 confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//             } else {
//                 stdDevNumbers.put(num, 0.5 + (random.nextDouble() * 0.5));
//                 confidenceIntervals.put(num, "Échantillon trop petit");
//             }
//         }

//         // 🔹 Calcul de l'écart-type du numéro chance et IC
//         double stdDevChance = 0.0;
//         String confidenceChance = "Trop faible pour un IC";
//         int N_chance = allChances.size();

//         if (N_chance > 1) {
//             double meanChance = allChances.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//             double varianceChance = allChances.stream()
//                     .mapToDouble(n -> Math.pow(n - meanChance, 2))
//                     .sum() / (N_chance - 1);

//             stdDevChance = Math.sqrt(varianceChance);

//             double marginErrorChance = 1.96 * (stdDevChance / Math.sqrt(N_chance));
//             double lowerBoundChance = meanChance - marginErrorChance;
//             double upperBoundChance = meanChance + marginErrorChance;
//             confidenceChance = String.format("[%.2f, %.2f]", lowerBoundChance, upperBoundChance);
//         }

//         // 🔹 Enregistrement de la prédiction
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setStdDevChance(stdDevChance);
//         prediction.setConfidenceIntervals(confidenceIntervals);
//         prediction.setConfidenceChance(confidenceChance);

//         predictionRepository.save(prediction);
//         logger.info("✅ Nouvelle prédiction enregistrée !");
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
// import org.springframework.data.domain.Sort;
// import java.util.*;

// @Service
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

//     private final TirageRepository tirageRepository;
//     private final PredictionRepository predictionRepository;
//     private final Random random = new Random();

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public PredictionTirageModel generatePrediction() {
//         List<Tirage> tirages = tirageRepository.findTop20ByOrderByDateTirageDesc(Sort.by(Sort.Direction.DESC, "dateTirage"));

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return null;
//         }

//         // Comptage des fréquences des numéros
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

//         // Calcul de l'écart-type et de l'intervalle de confiance
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         Map<Integer, String> confidenceIntervals = new HashMap<>();

//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();

//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             int N = occurrences.size();
//             if (N > 1) {
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream().mapToDouble(n -> Math.pow(n - mean, 2)).sum() / (N - 1);
//                 double stdDev = Math.sqrt(variance);
//                 stdDevNumbers.put(num, stdDev);

//                 // Calcul IC (95%)
//                 double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                 double lowerBound = mean - marginError;
//                 double upperBound = mean + marginError;
//                 confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//             } else {
//                 stdDevNumbers.put(num, 0.0);
//                 confidenceIntervals.put(num, "Trop faible pour un IC");
//             }
//         }

//         // ✅ Enregistrement de la prédiction
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setConfidenceIntervals(confidenceIntervals);

//         predictionRepository.save(prediction);
//         logger.info("✅ Nouvelle prédiction enregistrée avec succès !");
//         return prediction;
//     }
// }

// **************************************************************************************************

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.PredictionTirageModel;
// import com.fdjloto.api.model.Tirage;
// import com.fdjloto.api.repository.PredictionRepository;
// import com.fdjloto.api.repository.TirageRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
// import org.springframework.data.domain.Sort;
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

//     public PredictionTirageModel generatePrediction() {
//         // 🔹 Récupération de tous les tirages
//         List<Tirage> tirages = tirageRepository.findAll();
//         // List<Tirage> tirages = tirageRepository.findTop20ByOrderByDateTirageDesc(Sort.by(Sort.Direction.DESC, "dateTirage"));

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return null;
//         }

//         // Calcul des fréquences des numéros et numéros chance
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

//         // Calcul de l'écart-type et Intervalle de Confiance
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         Map<Integer, String> confidenceIntervals = new HashMap<>();

//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();
//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             int N = occurrences.size();
//             if (N > 1) {
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream().mapToDouble(n -> Math.pow(n - mean, 2)).sum() / (N - 1);
//                 double stdDev = Math.sqrt(variance);
//                 stdDevNumbers.put(num, stdDev);

//                 // Calcul de l'IC (95%)
//                 double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                 double lowerBound = mean - marginError;
//                 double upperBound = mean + marginError;
//                 confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//             } else {
//                 stdDevNumbers.put(num, 0.0);
//                 confidenceIntervals.put(num, "Trop faible pour un IC");
//             }
//         }

//         // Enregistrement et retour de la prédiction
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setConfidenceIntervals(confidenceIntervals);

//         predictionRepository.save(prediction);
//         logger.info("✅ Nouvelle prédiction enregistrée avec succès !");
//         return prediction;
//     }
// }

// **************************************************************************************************

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.PredictionTirageModel;
// import com.fdjloto.api.model.Tirage;
// import com.fdjloto.api.repository.PredictionRepository;
// import com.fdjloto.api.repository.TirageRepository;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
// import org.springframework.data.domain.Sort;
// import java.util.*;

// @Service
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);
//     private final TirageRepository tirageRepository;
//     private final PredictionRepository predictionRepository;
//     private final Random random = new Random();

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public PredictionTirageModel generatePrediction() {
//         // 🔹 Récupération de TOUS les tirages
//         List<Tirage> tirages = tirageRepository.findAll();

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return null;
//         }

//         // 🔹 Comptage des fréquences des numéros et numéros chance
//         Map<Integer, Integer> numberCounts = new HashMap<>();
//         Map<Integer, Integer> chanceCounts = new HashMap<>();
//         List<Integer> allChances = new ArrayList<>();
//         int totalNumbers = 0;

//         for (Tirage tirage : tirages) {
//             for (int boule : tirage.getBoules()) {
//                 numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
//                 totalNumbers++;
//             }
//             int numeroChance = tirage.getNumeroChance();
//             chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
//             allChances.add(numeroChance);
//         }

//         // 🔹 Calcul du taux de sortie (%) de chaque numéro
//         Map<Integer, Double> sortieRates = new HashMap<>();
//         for (Map.Entry<Integer, Integer> entry : numberCounts.entrySet()) {
//             int number = entry.getKey();
//             int frequency = entry.getValue();
//             double rate = (frequency * 100.0) / totalNumbers; // En pourcentage
//             sortieRates.put(number, rate);
//         }

//         // 🔹 Sélection des 8 numéros les plus fréquents
//         List<Integer> topNumbers = numberCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(8)
//                 .map(Map.Entry::getKey)
//                 .toList();

//         // 🔥 Mélange les numéros et sélectionne 5 numéros au hasard parmi les 8 meilleurs
//         Collections.shuffle(topNumbers);
//         List<Integer> probableNumbers = topNumbers.subList(0, 5);

//         // 🔹 Sélection des 3 numéros chance les plus fréquents
//         List<Integer> topChanceNumbers = chanceCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(3)
//                 .map(Map.Entry::getKey)
//                 .toList();

//         // 🔥 Choix aléatoire parmi les 3 meilleurs numéros chance
//         int probableChance = topChanceNumbers.isEmpty() ? -1 : topChanceNumbers.get(random.nextInt(topChanceNumbers.size()));

//         // 🔹 Calcul de l'écart-type et Intervalle de Confiance
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         Map<Integer, String> confidenceIntervals = new HashMap<>();

//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();
//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             int N = occurrences.size();
//             if (N > 1) {
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream().mapToDouble(n -> Math.pow(n - mean, 2)).sum() / (N - 1);
//                 double stdDev = Math.sqrt(variance);
//                 stdDevNumbers.put(num, stdDev);

//                 // Calcul de l'IC (95%)
//                 double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                 double lowerBound = mean - marginError;
//                 double upperBound = mean + marginError;
//                 confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//             } else {
//                 stdDevNumbers.put(num, 0.0);
//                 confidenceIntervals.put(num, "Trop faible pour un IC");
//             }
//         }

//         // ✅ Enregistrement et retour de la prédiction
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setConfidenceIntervals(confidenceIntervals);
//         prediction.setSortieRates(sortieRates); // Ajout du taux de sortie

//         predictionRepository.save(prediction);
//         logger.info("✅ Nouvelle prédiction enregistrée avec succès !");
//         return prediction;
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
// import org.springframework.web.bind.annotation.CrossOrigin;

// import java.util.*;

// @Service
// @CrossOrigin(origins = "*") // ✅ Permet l'accès depuis n'importe quelle origine
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);
//     private final TirageRepository tirageRepository;
//     private final PredictionRepository predictionRepository;
//     private final Random random = new Random();

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public PredictionTirageModel generatePrediction() {
//         // 🔹 Récupération de tous les tirages
//         List<Tirage> tirages = tirageRepository.findAll();

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return null;
//         }

//         // 🔹 Comptage des fréquences des numéros et numéros chance
//         Map<Integer, Integer> numberCounts = new HashMap<>();
//         Map<Integer, Integer> chanceCounts = new HashMap<>();
//         List<Integer> allChances = new ArrayList<>();
//         int totalNumbers = 0;

//         for (Tirage tirage : tirages) {
//             for (int boule : tirage.getBoules()) {
//                 numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
//                 totalNumbers++;
//             }
//             int numeroChance = tirage.getNumeroChance();
//             chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
//             allChances.add(numeroChance);
//         }

//         // 🔹 Calcul du taux de sortie (%) de chaque numéro
//         Map<Integer, Double> sortieRates = new HashMap<>();
//         for (Map.Entry<Integer, Integer> entry : numberCounts.entrySet()) {
//             int number = entry.getKey();
//             double rate = (entry.getValue() * 100.0) / totalNumbers;
//             sortieRates.put(number, rate);
//         }

//         // 🔹 Sélection des 8 numéros les plus fréquents
//         List<Integer> topNumbers = numberCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(8)
//                 .map(Map.Entry::getKey)
//                 .toList();

//         // 🔥 Mélange les numéros et sélectionne 5 numéros au hasard parmi les 8 meilleurs
//         Collections.shuffle(topNumbers);
//         List<Integer> probableNumbers = topNumbers.subList(0, 5);

//         // 🔹 Sélection des 3 numéros chance les plus fréquents
//         List<Integer> topChanceNumbers = chanceCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(3)
//                 .map(Map.Entry::getKey)
//                 .toList();

//         // 🔥 Choix aléatoire parmi les 3 meilleurs numéros chance
//         int probableChance = topChanceNumbers.isEmpty() ? -1 : topChanceNumbers.get(random.nextInt(topChanceNumbers.size()));

//         // 🔹 Calcul de l'écart-type et Intervalle de Confiance
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         Map<Integer, String> confidenceIntervals = new HashMap<>();

//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();
//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             int N = occurrences.size();
//             if (N > 1) {
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream().mapToDouble(n -> Math.pow(n - mean, 2)).sum() / (N - 1);
//                 double stdDev = Math.sqrt(variance);
//                 stdDevNumbers.put(num, stdDev);

//                 double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                 double lowerBound = mean - marginError;
//                 double upperBound = mean + marginError;
//                 confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//             } else {
//                 stdDevNumbers.put(num, 0.0);
//                 confidenceIntervals.put(num, "Trop faible pour un IC");
//             }
//         }

//         // ✅ Enregistrement et retour de la prédiction
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setConfidenceIntervals(confidenceIntervals);
//         prediction.setSortieRates(sortieRates); // Ajout du taux de sortie

//         predictionRepository.save(prediction);
//         logger.info("✅ Nouvelle prédiction enregistrée avec succès !");
//         return prediction;
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
// import org.springframework.data.domain.Sort;
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

//     public PredictionTirageModel generatePrediction() {
//         // 🔹 Récupération de tous les tirages
//         List<Tirage> tirages = tirageRepository.findAll();

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return null;
//         }

//         // 🔹 Comptage des fréquences des numéros et numéros chance
//         Map<Integer, Integer> numberCounts = new HashMap<>();
//         Map<Integer, Integer> chanceCounts = new HashMap<>();
//         List<Integer> allChances = new ArrayList<>();
//         int totalNumbers = 0;

//         for (Tirage tirage : tirages) {
//             for (int boule : tirage.getBoules()) {
//                 numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
//                 totalNumbers++;
//             }
//             int numeroChance = tirage.getNumeroChance();
//             chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
//             allChances.add(numeroChance);
//         }

//         // 🔹 Calcul du taux de sortie (%) de chaque numéro
//         Map<Integer, Double> sortieRates = new HashMap<>();
//         if (totalNumbers > 0) {
//             for (Map.Entry<Integer, Integer> entry : numberCounts.entrySet()) {
//                 int number = entry.getKey();
//                 double rate = (entry.getValue() * 100.0) / totalNumbers;
//                 sortieRates.put(number, rate);
//             }
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

//         // Calcul de l'écart-type et Intervalle de Confiance
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         Map<Integer, String> confidenceIntervals = new HashMap<>();

//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();
//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             int N = occurrences.size();
//             if (N > 1) {
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream().mapToDouble(n -> Math.pow(n - mean, 2)).sum() / (N - 1);
//                 double stdDev = Math.sqrt(variance);
//                 stdDevNumbers.put(num, stdDev);

//                 // Calcul de l'IC (95%)
//                 double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                 double lowerBound = mean - marginError;
//                 double upperBound = mean + marginError;
//                 confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//             } else {
//                 stdDevNumbers.put(num, 0.0);
//                 confidenceIntervals.put(num, "Trop faible pour un IC");
//             }
//         }

//         // Enregistrement et retour de la prédiction
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setConfidenceIntervals(confidenceIntervals);
//         prediction.setSortieRates(sortieRates); // Ajout du taux de sortie

//         predictionRepository.save(prediction);
//         logger.info("✅ Nouvelle prédiction enregistrée avec succès !");
//         return prediction;
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
// import org.springframework.data.domain.Sort;
// import java.util.*;

// @Service
// public class PredictionTirageService {

//     private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

//     private final TirageRepository tirageRepository;
//     private final PredictionRepository predictionRepository;
//     private final Random random = new Random();

//     public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
//         this.tirageRepository = tirageRepository;
//         this.predictionRepository = predictionRepository;
//     }

//     public PredictionTirageModel generatePrediction() {
//         // 🔹 Récupération de tous les tirages
//         List<Tirage> tirages = tirageRepository.findAll();

//         if (tirages.isEmpty()) {
//             logger.warn("❌ Aucun tirage trouvé !");
//             return null;
//         }

//         // 🔹 Comptage des fréquences des numéros et numéros chance
//         Map<Integer, Integer> numberCounts = new HashMap<>();
//         Map<Integer, Integer> chanceCounts = new HashMap<>();
//         List<Integer> allChances = new ArrayList<>();
//         int totalNumbers = 0;

//         for (Tirage tirage : tirages) {
//             for (int boule : tirage.getBoules()) {
//                 numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
//                 totalNumbers++;
//             }
//             int numeroChance = tirage.getNumeroChance();
//             chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
//             allChances.add(numeroChance);
//         }

//         // 🔹 Calcul du taux de sortie (%) de chaque numéro
//         Map<Integer, Double> sortieRates = new HashMap<>();
//         if (totalNumbers > 0) {
//             for (Map.Entry<Integer, Integer> entry : numberCounts.entrySet()) {
//                 int number = entry.getKey();
//                 double rate = (entry.getValue() * 100.0) / totalNumbers;
//                 sortieRates.put(number, rate);
//             }
//         }

//         // Sélection des 8 numéros les plus fréquents
//         List<Integer> topNumbers = numberCounts.entrySet().stream()
//                 .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
//                 .limit(8)
//                 .map(Map.Entry::getKey)
//                 .toList();

//         // Mélanger et sélectionner 5 numéros parmi les 8 calculés
//         Collections.shuffle(topNumbers);
//         List<Integer> probableNumbers = topNumbers.subList(0, Math.min(5, topNumbers.size()));

//         // Sélection du numéro chance le plus fréquent
//         int probableChance = chanceCounts.entrySet().stream()
//                 .max(Map.Entry.comparingByValue())
//                 .map(Map.Entry::getKey)
//                 .orElse(-1);

//         // Calcul de l'écart-type et Intervalle de Confiance
//         Map<Integer, Double> stdDevNumbers = new HashMap<>();
//         Map<Integer, String> confidenceIntervals = new HashMap<>();

//         for (int num : probableNumbers) {
//             List<Integer> occurrences = new ArrayList<>();
//             for (Tirage tirage : tirages) {
//                 if (tirage.getBoules().contains(num)) {
//                     occurrences.add(num);
//                 }
//             }

//             int N = occurrences.size();
//             if (N > 1) {
//                 double mean = occurrences.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//                 double variance = occurrences.stream().mapToDouble(n -> Math.pow(n - mean, 2)).sum() / (N - 1);
//                 double stdDev = Math.sqrt(variance);
//                 stdDevNumbers.put(num, stdDev);

//                 // Calcul de l'IC (95%)
//                 double marginError = 1.96 * (stdDev / Math.sqrt(N));
//                 double lowerBound = mean - marginError;
//                 double upperBound = mean + marginError;
//                 confidenceIntervals.put(num, String.format("[%.2f, %.2f]", lowerBound, upperBound));
//             } else {
//                 stdDevNumbers.put(num, 0.0);
//                 confidenceIntervals.put(num, "Trop faible pour un IC");
//             }
//         }

//         // Enregistrement et retour de la prédiction
//         PredictionTirageModel prediction = new PredictionTirageModel();
//         prediction.setProbableNumbers(probableNumbers);
//         prediction.setProbableChance(probableChance);
//         prediction.setStdDevNumbers(stdDevNumbers);
//         prediction.setConfidenceIntervals(confidenceIntervals);
//         prediction.setSortieRates(sortieRates); // Ajout du taux de sortie

//         predictionRepository.save(prediction);
//         logger.info("✅ Nouvelle prédiction enregistrée avec succès !");
//         return prediction;
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
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling // ✅ Active la planification

@Service
public class PredictionTirageService {

    private static final Logger logger = LoggerFactory.getLogger(PredictionTirageService.class);

    private final TirageRepository tirageRepository;
    private final PredictionRepository predictionRepository;
    private final Random random = new Random();

    public PredictionTirageService(TirageRepository tirageRepository, PredictionRepository predictionRepository) {
        this.tirageRepository = tirageRepository;
        this.predictionRepository = predictionRepository;
    }

    // ✅ Planification automatique à 00h00 chaque jour
    // @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Paris")
    // ✅ Planification 2 fois par heure : 00 et 30 minutes de chaque heure
	@Scheduled(cron = "0 1,31 * * * *", zone = "Europe/Paris")


    public void generatePredictionScheduled() {
        logger.info("⏰ Exécution planifiée de la génération de prédiction...");
        PredictionTirageModel prediction = generatePrediction();
        if (prediction != null) {
            logger.info("✅ Prédiction enregistrée avec succès : " + prediction);
        } else {
            logger.warn("❌ Aucune prédiction générée (probablement aucun tirage trouvé).");
        }
    }

    public PredictionTirageModel generatePrediction() {
        List<Tirage> tirages = tirageRepository.findAll();

        if (tirages.isEmpty()) {
            logger.warn("❌ Aucun tirage trouvé !");
            return null;
        }

        // 🔹 Comptage des fréquences
        Map<Integer, Integer> numberCounts = new HashMap<>();
        Map<Integer, Integer> chanceCounts = new HashMap<>();
        int totalNumbers = 0;

        for (Tirage tirage : tirages) {
            for (int boule : tirage.getBoules()) {
                numberCounts.put(boule, numberCounts.getOrDefault(boule, 0) + 1);
                totalNumbers++;
            }
            int numeroChance = tirage.getNumeroChance();
            chanceCounts.put(numeroChance, chanceCounts.getOrDefault(numeroChance, 0) + 1);
        }

        // 🔹 Calcul du taux de sortie sécurisé
        Map<Integer, Double> sortieRates = new HashMap<>();
        if (totalNumbers > 0) {
            for (Map.Entry<Integer, Integer> entry : numberCounts.entrySet()) {
                int number = entry.getKey();
                double rate = (entry.getValue() * 100.0) / totalNumbers;
                sortieRates.put(number, rate);
            }
        }

        // 🔹 Sélection des 8 meilleurs numéros
        List<Integer> topNumbers = numberCounts.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(8)
                .map(Map.Entry::getKey)
                .toList();

        // ✅ Sélection aléatoire de 5 numéros SANS provoquer d'exception
        List<Integer> probableNumbers = new ArrayList<>(topNumbers);
        Collections.shuffle(probableNumbers);
        probableNumbers = probableNumbers.subList(0, Math.min(5, probableNumbers.size()));

        // 🔹 Sélection du numéro chance le plus fréquent
        int probableChance = chanceCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);

        // 🔹 Création du modèle et enregistrement
        PredictionTirageModel prediction = new PredictionTirageModel();
        prediction.setProbableNumbers(probableNumbers);
        prediction.setProbableChance(probableChance);
        prediction.setSortieRates(sortieRates);

        predictionRepository.save(prediction);
        logger.info("✅ Nouvelle prédiction enregistrée avec succès !");
        return prediction;
    }
}
