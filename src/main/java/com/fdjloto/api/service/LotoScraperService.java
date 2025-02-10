package com.fdjloto.api.service;

import com.fdjloto.api.model.LotoResult; // Ensure this class exists in the specified package
import com.fdjloto.api.repository.LotoRepository;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

@Service
public class LotoScraperService {

    private static final String ZIP_URL = "https://www.sto.api.fdj.fr/anonymous/service-draw-info/v3/documentations/1a2b3c4d-9876-4562-b3fc-2c963f66afp6";

    @Autowired
    private LotoRepository lotoRepository;

    // Programme automatique toutes les heures
    @Scheduled(fixedRate = 3600000)  // 1 heure
    public void scrapeData() {
        System.out.println("Démarrage du scraping...");

        try {
            // Étape 1: Télécharger le fichier ZIP
            URL url = new URL(ZIP_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStream inputStream = connection.getInputStream();
                 ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(inputStream)) {

                ZipArchiveEntry entry;
                while ((entry = zipInputStream.getNextZipEntry()) != null) {
                    if (entry.getName().endsWith(".csv")) {
                        System.out.println("Extraction du fichier : " + entry.getName());
                        parseCSV(zipInputStream);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du téléchargement : " + e.getMessage());
        }
    }

    // Fonction pour traiter le CSV
    private void parseCSV(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             CSVReader csvReader = new CSVReader(reader)) {

            List<String[]> records = csvReader.readAll();
            if (records.isEmpty()) {
                System.out.println("Le fichier CSV est vide.");
                return;
            }

            // Supprimer les anciennes données
            lotoRepository.deleteAll();

            // Lire les données
            List<LotoResult> lotoResults = new ArrayList<>();
            for (int i = 1; i < records.size(); i++) { // Ignorer l'en-tête
                String[] row = records.get(i);
                if (row.length >= 3) {
                    LotoResult lotoResult = new LotoResult(row[0], row[1], row[2]);
                    lotoResults.add(lotoResult);
                }
            }

            // Insérer dans MongoDB
            if (!lotoResults.isEmpty()) {
                lotoRepository.saveAll(lotoResults);
                System.out.println(lotoResults.size() + " documents insérés dans MongoDB.");
            }

        } catch (IOException | CsvException e) {
            System.err.println("Erreur lors du traitement du fichier CSV : " + e.getMessage());
        }
    }
}
