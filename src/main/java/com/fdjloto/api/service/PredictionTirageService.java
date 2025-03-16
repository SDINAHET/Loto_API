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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.event.EventListener;



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

    @EventListener(ApplicationReadyEvent.class)
    public void scheduleInitialPrediction() {
        logger.info("🕒 Génération de la première prédiction planifiée dans 1 minute...");

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(this::generatePredictionScheduled, 40, TimeUnit.SECONDS);
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
