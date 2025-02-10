// package com.fdjloto.api.service;

// import com.fdjloto.api.model.LotoResult; // Ensure this class exists in the specified package
// import com.fdjloto.api.repository.LotoRepository;
// import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
// import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.io.*;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;
// import java.util.List;

// import com.opencsv.CSVReader;
// import com.opencsv.exceptions.CsvException;

// @Service
// public class LotoScraperService {

//     private static final String ZIP_URL = "https://www.sto.api.fdj.fr/anonymous/service-draw-info/v3/documentations/1a2b3c4d-9876-4562-b3fc-2c963f66afp6";

//     @Autowired
//     private LotoRepository lotoRepository;

//     // Programme automatique toutes les heures
//     @Scheduled(fixedRate = 3600000)  // 1 heure
//     public void scrapeData() {
//         System.out.println("Test connexion MongoDB...");
//     try {
//         lotoRepository.count();  // Vérifie si la collection est accessible
//         System.out.println("Connexion MongoDB réussie !");
//     } catch (Exception e) {
//         System.err.println("Erreur de connexion MongoDB : " + e.getMessage());
//     }
//         System.out.println("Démarrage du scraping...");

//         try {
//             // Étape 1: Télécharger le fichier ZIP
//             URL url = new URL(ZIP_URL);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//             connection.setRequestMethod("GET");

//             try (InputStream inputStream = connection.getInputStream();
//                  ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(inputStream)) {

//                 ZipArchiveEntry entry;
//                 while ((entry = zipInputStream.getNextZipEntry()) != null) {
//                     if (entry.getName().endsWith(".csv")) {
//                         System.out.println("Extraction du fichier : " + entry.getName());
//                         parseCSV(zipInputStream);
//                         break;
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             System.err.println("Erreur lors du téléchargement : " + e.getMessage());
//         }
//     }

//     // Fonction pour traiter le CSV
//     private void parseCSV(InputStream inputStream) {
//         try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//              CSVReader csvReader = new CSVReader(reader)) {

//             List<String[]> records = csvReader.readAll();
//             if (records.isEmpty()) {
//                 System.out.println("Le fichier CSV est vide.");
//                 return;
//             }

//             // Supprimer les anciennes données
//             lotoRepository.deleteAll();

//             // Lire les données
//             List<LotoResult> lotoResults = new ArrayList<>();
//             for (int i = 1; i < records.size(); i++) { // Ignorer l'en-tête
//                 String[] row = records.get(i);
//                 // if (row.length >= 3) {
//                     // LotoResult lotoResult = new LotoResult(row[0], row[1], row[2]);
//                 if (row.length >= 50) { // Vérifie que la ligne a bien toutes les colonnes
//                     LotoResult lotoResult = new LotoResult();
//                     lotoResult.setAnneeNumeroDeTirage(Integer.parseInt(row[0]));
//                     lotoResult.setJourDeTirage(row[1]);
//                     lotoResult.setDateDeTirage(row[2]);
//                     lotoResult.setDateDeForclusion(row[3]);
//                     lotoResult.setBoule1(Integer.parseInt(row[4]));
//                     lotoResult.setBoule2(Integer.parseInt(row[5]));
//                     lotoResult.setBoule3(Integer.parseInt(row[6]));
//                     lotoResult.setBoule4(Integer.parseInt(row[7]));
//                     lotoResult.setBoule5(Integer.parseInt(row[8]));
//                     lotoResult.setNumeroChance(Integer.parseInt(row[9]));
//                     lotoResult.setCombinaisonGagnante(row[10]);
//                     lotoResult.setNombreDeGagnantAuRang1(Integer.parseInt(row[11]));
//                     lotoResult.setRapportDuRang1(Double.parseDouble(row[12].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang2(Integer.parseInt(row[13]));
//                     lotoResult.setRapportDuRang2(Double.parseDouble(row[14].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang3(Integer.parseInt(row[15]));
//                     lotoResult.setRapportDuRang3(Double.parseDouble(row[16].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang4(Integer.parseInt(row[17]));
//                     lotoResult.setRapportDuRang4(Double.parseDouble(row[18].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang5(Integer.parseInt(row[19]));
//                     lotoResult.setRapportDuRang5(Double.parseDouble(row[20].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang6(Integer.parseInt(row[21]));
//                     lotoResult.setRapportDuRang6(Double.parseDouble(row[22].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang7(Integer.parseInt(row[23]));
//                     lotoResult.setRapportDuRang7(Double.parseDouble(row[24].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang8(Integer.parseInt(row[25]));
//                     lotoResult.setRapportDuRang8(Double.parseDouble(row[26].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang9(Integer.parseInt(row[27]));
//                     lotoResult.setRapportDuRang9(Double.parseDouble(row[28].replace(",", ".")));
//                     lotoResult.setNombreDeCodesGagnants(Integer.parseInt(row[29]));
//                     lotoResult.setRapportCodesGagnants(Integer.parseInt(row[30]));
//                     lotoResult.setCodesGagnants(row[31]);
//                     lotoResult.setBoule1SecondTirage(Integer.parseInt(row[32]));
//                     lotoResult.setBoule2SecondTirage(Integer.parseInt(row[33]));
//                     lotoResult.setBoule3SecondTirage(Integer.parseInt(row[34]));
//                     lotoResult.setBoule4SecondTirage(Integer.parseInt(row[35]));
//                     lotoResult.setBoule5SecondTirage(Integer.parseInt(row[36]));
//                     lotoResult.setCombinaisonGagnanteSecondTirage(row[37]);
//                     lotoResult.setNombreDeGagnantAuRang1SecondTirage(Integer.parseInt(row[38]));
//                     lotoResult.setRapportDuRang1SecondTirage(Double.parseDouble(row[39].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang2SecondTirage(Integer.parseInt(row[40]));
//                     lotoResult.setRapportDuRang2SecondTirage(Double.parseDouble(row[41].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang3SecondTirage(Integer.parseInt(row[42]));
//                     lotoResult.setRapportDuRang3SecondTirage(Double.parseDouble(row[43].replace(",", ".")));
//                     lotoResult.setNombreDeGagnantAuRang4SecondTirage(Integer.parseInt(row[44]));
//                     lotoResult.setRapportDuRang4SecondTirage(Double.parseDouble(row[45].replace(",", ".")));
//                     lotoResult.setNumeroJokerplus(Integer.parseInt(row[46]));
//                     lotoResult.setDevise(row[47]);

//                     lotoResults.add(lotoResult);
//                 }
//             }

//             // Insérer dans MongoDB
//             if (!lotoResults.isEmpty()) {
//                 lotoRepository.saveAll(lotoResults);
//                 System.out.println(lotoResults.size() + " documents insérés dans MongoDB.");
//             }

//         } catch (IOException | CsvException e) {
//             System.err.println("Erreur lors du traitement du fichier CSV : " + e.getMessage());
//         }
//     }
// }
// package com.fdjloto.api.service;

// import com.fdjloto.api.model.LotoResult;
// import com.fdjloto.api.repository.LotoRepository;
// import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
// import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.io.*;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Arrays;

// import com.opencsv.CSVParser;
// import com.opencsv.CSVParserBuilder;
// import com.opencsv.CSVReader;
// import com.opencsv.CSVReaderBuilder;
// import com.opencsv.exceptions.CsvException;

// @Service
// public class LotoScraperService {

//     private static final String ZIP_URL = "https://www.sto.api.fdj.fr/anonymous/service-draw-info/v3/documentations/1a2b3c4d-9876-4562-b3fc-2c963f66afp6";

//     @Autowired
//     private LotoRepository lotoRepository;

//     // Programme automatique toutes les heures
//     @Scheduled(fixedRate = 3600000)  // 1 heure
//     public void scrapeData() {
//         System.out.println("🟢 Démarrage du scraping...");

//         // Vérification de la connexion MongoDB
//         System.out.println("🟡 Test de connexion à MongoDB...");
//         try {
//             lotoRepository.count();  // Vérifie si la collection est accessible
//             System.out.println("✅ Connexion MongoDB réussie !");
//         } catch (Exception e) {
//             System.err.println("🚨 Erreur de connexion MongoDB : " + e.getMessage());
//             return;
//         }

//         try {
//             // Étape 1: Télécharger le fichier ZIP
//             System.out.println("🟡 Téléchargement du fichier ZIP...");
//             URL url = new URL(ZIP_URL);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//             connection.setRequestMethod("GET");

//             try (InputStream inputStream = connection.getInputStream();
//                  ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(inputStream)) {

//                 ZipArchiveEntry entry;
//                 while ((entry = zipInputStream.getNextZipEntry()) != null) {
//                     if (entry.getName().endsWith(".csv")) {
//                         System.out.println("✅ Fichier trouvé et extrait : " + entry.getName());
//                         parseCSV(zipInputStream);
//                         break;
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             System.err.println("🚨 Erreur lors du téléchargement : " + e.getMessage());
//         }
//     }

//     // Fonction pour traiter le CSV
//     private void parseCSV(InputStream inputStream) {
//         try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){
//             // CSVReader csvReader = new CSVReader(reader)) {
//             // CSVReader csvReader = new CSVReader(reader, ';')) {
//             // ✅ Création du parser avec le bon séparateur
//             CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
//             CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build();

//             List<String[]> records = csvReader.readAll();
//             System.out.println("🟡 Nombre total de lignes (y compris l'en-tête) : " + records.size());

//             if (records.isEmpty()) {
//                 System.out.println("🚨 Le fichier CSV est vide.");
//                 return;
//             }

//             // Supprimer les anciennes données
//             System.out.println("🟡 Suppression des anciennes données MongoDB...");
//             lotoRepository.deleteAll();

//             // Vérifier les colonnes
//             System.out.println("🔎 Vérification du nombre de colonnes...");
//             if (records.get(0).length < 50) {
//                 System.err.println("🚨 Erreur : Le CSV ne contient pas 50 colonnes, il en a " + records.get(0).length);
//                 return;
//             }

//             // Lire les données
//             List<LotoResult> lotoResults = new ArrayList<>();
//             for (int i = 1; i < records.size(); i++) { // Ignorer l'en-tête
//                 String[] row = records.get(i);

//                 // Log des 5 premières lignes
//                 if (i <= 5) {
//                     System.out.println("🔹 Ligne " + i + " : " + Arrays.toString(row));
//                 }

//                 // Vérifier si la ligne contient toutes les colonnes
//                 if (row.length >= 50) {
//                     try {
//                         LotoResult lotoResult = new LotoResult();
//                         lotoResult.setAnneeNumeroDeTirage(Integer.parseInt(row[0]));
//                         lotoResult.setJourDeTirage(row[1]);
//                         lotoResult.setDateDeTirage(row[2]);
//                         lotoResult.setDateDeForclusion(row[3]);
//                         lotoResult.setBoule1(Integer.parseInt(row[4]));
//                         lotoResult.setBoule2(Integer.parseInt(row[5]));
//                         lotoResult.setBoule3(Integer.parseInt(row[6]));
//                         lotoResult.setBoule4(Integer.parseInt(row[7]));
//                         lotoResult.setBoule5(Integer.parseInt(row[8]));
//                         lotoResult.setNumeroChance(Integer.parseInt(row[9]));
//                         lotoResult.setCombinaisonGagnante(row[10]);
//                         lotoResult.setNombreDeGagnantAuRang1(Integer.parseInt(row[11]));
//                         lotoResult.setRapportDuRang1(Double.parseDouble(row[12].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang2(Integer.parseInt(row[13]));
//                         lotoResult.setRapportDuRang2(Double.parseDouble(row[14].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang3(Integer.parseInt(row[15]));
//                         lotoResult.setRapportDuRang3(Double.parseDouble(row[16].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang4(Integer.parseInt(row[17]));
//                         lotoResult.setRapportDuRang4(Double.parseDouble(row[18].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang5(Integer.parseInt(row[19]));
//                         lotoResult.setRapportDuRang5(Double.parseDouble(row[20].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang6(Integer.parseInt(row[21]));
//                         lotoResult.setRapportDuRang6(Double.parseDouble(row[22].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang7(Integer.parseInt(row[23]));
//                         lotoResult.setRapportDuRang7(Double.parseDouble(row[24].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang8(Integer.parseInt(row[25]));
//                         lotoResult.setRapportDuRang8(Double.parseDouble(row[26].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang9(Integer.parseInt(row[27]));
//                         lotoResult.setRapportDuRang9(Double.parseDouble(row[28].replace(",", ".")));
//                         lotoResult.setNombreDeCodesGagnants(Integer.parseInt(row[29]));
//                         lotoResult.setRapportCodesGagnants(Integer.parseInt(row[30]));
//                         lotoResult.setCodesGagnants(row[31]);
//                         lotoResult.setBoule1SecondTirage(Integer.parseInt(row[32]));
//                         lotoResult.setBoule2SecondTirage(Integer.parseInt(row[33]));
//                         lotoResult.setBoule3SecondTirage(Integer.parseInt(row[34]));
//                         lotoResult.setBoule4SecondTirage(Integer.parseInt(row[35]));
//                         lotoResult.setBoule5SecondTirage(Integer.parseInt(row[36]));
//                         lotoResult.setCombinaisonGagnanteSecondTirage(row[37]);
//                         lotoResult.setNombreDeGagnantAuRang1SecondTirage(Integer.parseInt(row[38]));
//                         lotoResult.setRapportDuRang1SecondTirage(Double.parseDouble(row[39].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang2SecondTirage(Integer.parseInt(row[40]));
//                         lotoResult.setRapportDuRang2SecondTirage(Double.parseDouble(row[41].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang3SecondTirage(Integer.parseInt(row[42]));
//                         lotoResult.setRapportDuRang3SecondTirage(Double.parseDouble(row[43].replace(",", ".")));
//                         lotoResult.setNombreDeGagnantAuRang4SecondTirage(Integer.parseInt(row[44]));
//                         lotoResult.setRapportDuRang4SecondTirage(Double.parseDouble(row[45].replace(",", ".")));
//                         lotoResult.setNumeroJokerplus(Integer.parseInt(row[46]));
//                         lotoResult.setDevise(row[47]);

//                         // Ajout des données restantes
//                         // Vérifier les conversions des nombres
//                         try {
//                             lotoResult.setNombreDeGagnantAuRang2(Integer.parseInt(row[13]));
//                             lotoResult.setRapportDuRang2(Double.parseDouble(row[14].replace(",", ".")));
//                         } catch (NumberFormatException e) {
//                             System.err.println("🚨 Erreur conversion rang 2 ligne " + i + " : " + e.getMessage());
//                         }

//                         // Ajout à la liste
//                         lotoResults.add(lotoResult);
//                     } catch (Exception e) {
//                         System.err.println("🚨 Erreur lors du parsing de la ligne " + i + " : " + e.getMessage());
//                     }
//                 } else {
//                     System.err.println("⚠️ Ligne ignorée (colonne manquante) : " + Arrays.toString(row));
//                 }
//             }

//             // Insérer dans MongoDB
//             if (!lotoResults.isEmpty()) {
//                 lotoRepository.saveAll(lotoResults);
//                 System.out.println("✅ " + lotoResults.size() + " documents insérés dans MongoDB !");
//             } else {
//                 System.out.println("🚨 Aucun document inséré !");
//             }

//         } catch (IOException | CsvException e) {
//             System.err.println("🚨 Erreur lors du traitement du fichier CSV : " + e.getMessage());
//         }
//     }
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.LotoResult;
// import com.fdjloto.api.repository.LotoRepository;
// import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
// import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.io.*;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Arrays;

// import com.opencsv.CSVParser;
// import com.opencsv.CSVParserBuilder;
// import com.opencsv.CSVReader;
// import com.opencsv.CSVReaderBuilder;
// import com.opencsv.exceptions.CsvException;

// @Service
// public class LotoScraperService {

//     private static final String ZIP_URL = "https://www.sto.api.fdj.fr/anonymous/service-draw-info/v3/documentations/1a2b3c4d-9876-4562-b3fc-2c963f66afp6";

//     @Autowired
//     private LotoRepository lotoRepository;

//     // Programme automatique toutes les heures
//     @Scheduled(fixedRate = 3600000)  // 1 heure
//     public void scrapeData() {
//         System.out.println("🟢 Démarrage du scraping...");

//         // Vérification de la connexion MongoDB
//         System.out.println("🟡 Test de connexion à MongoDB...");
//         try {
//             lotoRepository.count();  // Vérifie si la collection est accessible
//             System.out.println("✅ Connexion MongoDB réussie !");
//         } catch (Exception e) {
//             System.err.println("🚨 Erreur de connexion MongoDB : " + e.getMessage());
// 			return;
// 		}

//         try {
//             // Étape 1: Télécharger le fichier ZIP
//             System.out.println("🟡 Téléchargement du fichier ZIP...");
//             URL url = new URL(ZIP_URL);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//             connection.setRequestMethod("GET");

//             try (InputStream inputStream = connection.getInputStream();
//                  ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(inputStream)) {

//                 ZipArchiveEntry entry;
//                 while ((entry = zipInputStream.getNextZipEntry()) != null) {
//                     if (entry.getName().endsWith(".csv")) {
//                         System.out.println("✅ Fichier trouvé et extrait : " + entry.getName());
//                         parseCSV(zipInputStream);
//                         break;
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             System.err.println("🚨 Erreur lors du téléchargement : " + e.getMessage());
//         }
//     }
// 	private void parseCSV(InputStream inputStream) {
// 		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){

// 			CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
// 			CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build();

// 			List<String[]> records = csvReader.readAll();
// 			System.out.println("🟡 Nombre total de lignes (y compris l'en-tête) : " + records.size());

// 			if (records.isEmpty()) {
// 				System.out.println("🚨 Le fichier CSV est vide.");
// 				return;
// 			}

// 			// Supprimer les anciennes données
// 			System.out.println("🟡 Suppression des anciennes données MongoDB...");
// 			lotoRepository.deleteAll();

// 			// Vérifier les colonnes
// 			System.out.println("🔎 Vérification du nombre de colonnes...");
// 			if (records.get(0).length < 50) {
// 				System.err.println("🚨 Erreur : Le CSV ne contient pas 50 colonnes, il en a " + records.get(0).length);
// 				return;
// 			}

// 			// Lire les données
// 			List<LotoResult> lotoResults = new ArrayList<>();
// 			for (int i = 1; i < records.size(); i++) { // Ignorer l'en-tête
// 				String[] row = records.get(i);

// 				// Log des 5 premières lignes
// 				if (i <= 5) {
// 					System.out.println("🔹 Ligne " + i + " : " + Arrays.toString(row));
// 				}

// 				try {
// 					LotoResult lotoResult = new LotoResult();
// 					lotoResult.setAnneeNumeroDeTirage(Integer.parseInt(row[0]));
// 					lotoResult.setJourDeTirage(row[1]);
// 					lotoResult.setDateDeTirage(row[2]);
// 					lotoResult.setDateDeForclusion(row[3]);
// 					lotoResult.setBoule1(Integer.parseInt(row[4]));
// 					lotoResult.setBoule2(Integer.parseInt(row[5]));
// 					lotoResult.setBoule3(Integer.parseInt(row[6]));
// 					lotoResult.setBoule4(Integer.parseInt(row[7]));
// 					lotoResult.setBoule5(Integer.parseInt(row[8]));
// 					lotoResult.setNumeroChance(Integer.parseInt(row[9]));
// 					lotoResult.setCombinaisonGagnante(row[10]);
// 					lotoResult.setNombreDeGagnantAuRang1(Integer.parseInt(row[11]));

// 					// ✅ Correction : Suppression des espaces avant conversion des montants
// 					lotoResult.setRapportDuRang1(Double.parseDouble(row[12].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang2(Integer.parseInt(row[13]));
// 					lotoResult.setRapportDuRang2(Double.parseDouble(row[14].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang3(Integer.parseInt(row[15]));
// 					lotoResult.setRapportDuRang3(Double.parseDouble(row[16].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang4(Integer.parseInt(row[17]));
// 					lotoResult.setRapportDuRang4(Double.parseDouble(row[18].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang5(Integer.parseInt(row[19]));
// 					lotoResult.setRapportDuRang5(Double.parseDouble(row[20].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang6(Integer.parseInt(row[21]));
// 					lotoResult.setRapportDuRang6(Double.parseDouble(row[22].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang7(Integer.parseInt(row[23]));
// 					lotoResult.setRapportDuRang7(Double.parseDouble(row[24].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang8(Integer.parseInt(row[25]));
// 					lotoResult.setRapportDuRang8(Double.parseDouble(row[26].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang9(Integer.parseInt(row[27]));
// 					lotoResult.setRapportDuRang9(Double.parseDouble(row[28].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeCodesGagnants(Integer.parseInt(row[29]));
// 					lotoResult.setRapportCodesGagnants(Integer.parseInt(row[30]));
// 					lotoResult.setCodesGagnants(row[31]);
// 					lotoResult.setBoule1SecondTirage(Integer.parseInt(row[32]));
// 					lotoResult.setBoule2SecondTirage(Integer.parseInt(row[33]));
// 					lotoResult.setBoule3SecondTirage(Integer.parseInt(row[34]));
// 					lotoResult.setBoule4SecondTirage(Integer.parseInt(row[35]));
// 					lotoResult.setBoule5SecondTirage(Integer.parseInt(row[36]));

// 					// ✅ Nouvelle colonne ajoutée (peut être vide)
// 					String promotionSecondTirage = row[37].isEmpty() ? null : row[37];

// 					// ✅ Décalage des indices pour tenir compte de la nouvelle colonne
// 					lotoResult.setCombinaisonGagnanteSecondTirage(row[38]);
// 					lotoResult.setNombreDeGagnantAuRang1SecondTirage(Integer.parseInt(row[39]));
// 					lotoResult.setRapportDuRang1SecondTirage(Double.parseDouble(row[40].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang2SecondTirage(Integer.parseInt(row[41]));
// 					lotoResult.setRapportDuRang2SecondTirage(Double.parseDouble(row[42].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang3SecondTirage(Integer.parseInt(row[43]));
// 					lotoResult.setRapportDuRang3SecondTirage(Double.parseDouble(row[44].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang4SecondTirage(Integer.parseInt(row[45]));
// 					lotoResult.setRapportDuRang4SecondTirage(Double.parseDouble(row[46].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNumeroJokerplus(Integer.parseInt(row[47]));
// 					lotoResult.setDevise(row[48]); // ✅ Correction de l'index pour éviter une erreur de dépassement


// 					lotoResults.add(lotoResult);
// 				} catch (Exception e) {
// 					System.err.println("🚨 Erreur lors du parsing de la ligne " + i + " : " + e.getMessage());
// 				}
// 			}

// 			// Insérer dans MongoDB
// 			if (!lotoResults.isEmpty()) {
// 				lotoRepository.saveAll(lotoResults);
// 				System.out.println("✅ " + lotoResults.size() + " documents insérés dans MongoDB !");
// 			} else {
// 				System.out.println("🚨 Aucun document inséré !");
// 			}

// 		} catch (IOException | CsvException e) {
// 			System.err.println("🚨 Erreur lors du traitement du fichier CSV : " + e.getMessage());
// 		}
// 	}
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.LotoResult;
// import com.fdjloto.api.repository.LotoRepository;
// import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
// import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.io.*;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Arrays;

// import com.opencsv.CSVParser;
// import com.opencsv.CSVParserBuilder;
// import com.opencsv.CSVReader;
// import com.opencsv.CSVReaderBuilder;
// import com.opencsv.exceptions.CsvException;

// @Service
// public class LotoScraperService {

//     private static final String ZIP_URL = "https://www.sto.api.fdj.fr/anonymous/service-draw-info/v3/documentations/1a2b3c4d-9876-4562-b3fc-2c963f66afp6";

//     @Autowired
//     private LotoRepository lotoRepository;

//     @Scheduled(fixedRate = 3600000)  // 1 heure
//     public void scrapeData() {
//         System.out.println("\uD83D\uDFE2 Démarrage du scraping...");

//         System.out.println("\uD83D\uDFE1 Test de connexion à MongoDB...");
//         try {
//             lotoRepository.count();
//             System.out.println("✅ Connexion MongoDB réussie !");
//         } catch (Exception e) {
//             System.err.println("\uD83D\uDEA8 Erreur de connexion MongoDB : " + e.getMessage());
//             return;
//         }

//         try {
//             System.out.println("\uD83D\uDFE1 Téléchargement du fichier ZIP...");
//             URL url = new URL(ZIP_URL);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//             connection.setRequestMethod("GET");

//             try (InputStream inputStream = connection.getInputStream();
//                  ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(inputStream)) {
//                 ZipArchiveEntry entry;
//                 while ((entry = zipInputStream.getNextZipEntry()) != null) {
//                     if (entry.getName().endsWith(".csv")) {
//                         System.out.println("✅ Fichier trouvé et extrait : " + entry.getName());
//                         parseCSV(zipInputStream);
//                         break;
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             System.err.println("\uD83D\uDEA8 Erreur lors du téléchargement : " + e.getMessage());
//         }
//     }

// 	// Méthode pour nettoyer et convertir un nombre au format correct
// 	private double parseDouble(String value) {
// 		if (value == null || value.trim().isEmpty()) {
// 			return 0.0; // Valeur par défaut en cas de champ vide
// 		}
// 		value = value.replace(" ", "").replace(",", "."); // Nettoyage
// 		return Double.parseDouble(value);
// 	}

//     private void parseCSV(InputStream inputStream) {
//         try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){

//             CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
//             CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build();

//             List<String[]> records = csvReader.readAll();
//             System.out.println("\uD83D\uDFE1 Nombre total de lignes : " + records.size());

//             if (records.isEmpty()) {
//                 System.out.println("\uD83D\uDEA8 Le fichier CSV est vide.");
//                 return;
//             }

//             System.out.println("\uD83D\uDFE1 Suppression des anciennes données MongoDB...");
//             lotoRepository.deleteAll();

//             if (records.get(0).length < 50) {
//                 System.err.println("\uD83D\uDEA8 Erreur : Le CSV contient " + records.get(0).length + " colonnes au lieu de 50.");
//                 return;
//             }

//             List<LotoResult> lotoResults = new ArrayList<>();
//             for (int i = 1; i < records.size(); i++) {
//                 String[] row = records.get(i);

//                 if (i <= 5) {
//                     System.out.println("\uD83D\uDD39 Ligne " + i + " : " + Arrays.toString(row));
//                 }

//                 try {
//                     LotoResult lotoResult = new LotoResult();
//                     lotoResult.setAnneeNumeroDeTirage(Integer.parseInt(row[0]));
//                     lotoResult.setJourDeTirage(row[1]);
//                     lotoResult.setDateDeTirage(row[2]);
//                     lotoResult.setDateDeForclusion(row[3]);
//                     lotoResult.setBoule1(Integer.parseInt(row[4]));
//                     lotoResult.setBoule2(Integer.parseInt(row[5]));
//                     lotoResult.setBoule3(Integer.parseInt(row[6]));
//                     lotoResult.setBoule4(Integer.parseInt(row[7]));
//                     lotoResult.setBoule5(Integer.parseInt(row[8]));
//                     lotoResult.setNumeroChance(Integer.parseInt(row[9]));
//                     lotoResult.setCombinaisonGagnante(row[10]);
//                     lotoResult.setNombreDeGagnantAuRang1(Integer.parseInt(row[11]));
//                     // lotoResult.setRapportDuRang1(Double.parseDouble(row[12].replace(" ", "").replace(",", ".")));

//                     // Ajout des autres valeurs ici...
// 					// ✅ Correction : Suppression des espaces avant conversion des montants
// 					lotoResult.setRapportDuRang1(Double.parseDouble(row[12].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang2(Integer.parseInt(row[13]));
// 					lotoResult.setRapportDuRang2(Double.parseDouble(row[14].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang3(Integer.parseInt(row[15]));
// 					lotoResult.setRapportDuRang3(Double.parseDouble(row[16].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang4(Integer.parseInt(row[17]));
// 					lotoResult.setRapportDuRang4(Double.parseDouble(row[18].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang5(Integer.parseInt(row[19]));
// 					lotoResult.setRapportDuRang5(Double.parseDouble(row[20].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang6(Integer.parseInt(row[21]));
// 					lotoResult.setRapportDuRang6(Double.parseDouble(row[22].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang7(Integer.parseInt(row[23]));
// 					lotoResult.setRapportDuRang7(Double.parseDouble(row[24].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang8(Integer.parseInt(row[25]));
// 					lotoResult.setRapportDuRang8(Double.parseDouble(row[26].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang9(Integer.parseInt(row[27]));
// 					lotoResult.setRapportDuRang9(Double.parseDouble(row[28].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeCodesGagnants(Integer.parseInt(row[29]));
// 					lotoResult.setRapportCodesGagnants(Integer.parseInt(row[30]));
// 					lotoResult.setCodesGagnants(row[31]);
// 					lotoResult.setBoule1SecondTirage(Integer.parseInt(row[32]));
// 					lotoResult.setBoule2SecondTirage(Integer.parseInt(row[33]));
// 					lotoResult.setBoule3SecondTirage(Integer.parseInt(row[34]));
// 					lotoResult.setBoule4SecondTirage(Integer.parseInt(row[35]));
// 					lotoResult.setBoule5SecondTirage(Integer.parseInt(row[36]));

// 					// ✅ Nouvelle colonne ajoutée (peut être vide)
// 					String promotionSecondTirage = row[37].isEmpty() ? null : row[37];

// 					// ✅ Décalage des indices pour tenir compte de la nouvelle colonne
// 					lotoResult.setCombinaisonGagnanteSecondTirage(row[38]);
// 					lotoResult.setNombreDeGagnantAuRang1SecondTirage(Integer.parseInt(row[39]));
// 					lotoResult.setRapportDuRang1SecondTirage(Double.parseDouble(row[40].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang2SecondTirage(Integer.parseInt(row[41]));
// 					lotoResult.setRapportDuRang2SecondTirage(Double.parseDouble(row[42].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang3SecondTirage(Integer.parseInt(row[43]));
// 					lotoResult.setRapportDuRang3SecondTirage(Double.parseDouble(row[44].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNombreDeGagnantAuRang4SecondTirage(Integer.parseInt(row[45]));
// 					lotoResult.setRapportDuRang4SecondTirage(Double.parseDouble(row[46].replace(" ", "").replace(",", ".")));
// 					lotoResult.setNumeroJokerplus(Integer.parseInt(row[47]));
// 					lotoResult.setDevise(row[48]); // ✅ Correction de l'index pour éviter une erreur de dépassement

//                     lotoResults.add(lotoResult);
//                 } catch (Exception e) {
//                     System.err.println("\uD83D\uDEA8 Erreur parsing ligne " + i + " : " + e.getMessage());
//                 }
//             }

//             if (!lotoResults.isEmpty()) {
//                 lotoRepository.saveAll(lotoResults);
//                 System.out.println("✅ " + lotoResults.size() + " documents insérés dans MongoDB !");
//             } else {
//                 System.out.println("\uD83D\uDEA8 Aucun document inséré !");
//             }

//         } catch (IOException | CsvException e) {
//             System.err.println("\uD83D\uDEA8 Erreur lors du traitement du fichier CSV : " + e.getMessage());
//         }
//     }
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.LotoResult;
// import com.fdjloto.api.repository.LotoRepository;
// import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
// import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.io.*;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Arrays;

// import com.opencsv.CSVParser;
// import com.opencsv.CSVParserBuilder;
// import com.opencsv.CSVReader;
// import com.opencsv.CSVReaderBuilder;
// import com.opencsv.exceptions.CsvException;

// @Service
// public class LotoScraperService {

//     private static final String ZIP_URL = "https://www.sto.api.fdj.fr/anonymous/service-draw-info/v3/documentations/1a2b3c4d-9876-4562-b3fc-2c963f66afp6";

//     @Autowired
//     private LotoRepository lotoRepository;

//     @Scheduled(fixedRate = 3600000)  // 1 heure
//     public void scrapeData() {
//         System.out.println("\uD83D\uDFE2 Démarrage du scraping...");

//         System.out.println("\uD83D\uDFE1 Test de connexion à MongoDB...");
//         try {
//             lotoRepository.count();
//             System.out.println("✅ Connexion MongoDB réussie !");
//         } catch (Exception e) {
//             System.err.println("\uD83D\uDEA8 Erreur de connexion MongoDB : " + e.getMessage());
//             return;
//         }

//         try {
//             System.out.println("\uD83D\uDFE1 Téléchargement du fichier ZIP...");
//             URL url = new URL(ZIP_URL);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//             connection.setRequestMethod("GET");

//             if (connection.getResponseCode() != 200) {
//                 System.err.println("\uD83D\uDEA8 Erreur HTTP : " + connection.getResponseCode());
//                 return;
//             }

//             try (InputStream inputStream = connection.getInputStream();
//                  ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(inputStream)) {
//                 ZipArchiveEntry entry;
//                 while ((entry = zipInputStream.getNextZipEntry()) != null) {
//                     if (entry.getName().endsWith(".csv")) {
//                         System.out.println("✅ Fichier trouvé et extrait : " + entry.getName());
//                         parseCSV(zipInputStream);
//                         break;
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             System.err.println("\uD83D\uDEA8 Erreur lors du téléchargement : " + e.getMessage());
//         }
//     }

//     // Méthodes utilitaires pour éviter les erreurs de conversion
//     private int parseInteger(String value, int defaultValue) {
//         try {
//             return Integer.parseInt(value.trim());
//         } catch (NumberFormatException e) {
//             System.err.println("\uD83D\uDEA8 Erreur de conversion en entier : " + value);
//             return defaultValue;
//         }
//     }

//     private double parseDouble(String value, double defaultValue) {
//         try {
//             if (value == null || value.trim().isEmpty()) {
//                 return defaultValue;
//             }
//             return Double.parseDouble(value.replace(" ", "").replace(",", "."));
//         } catch (NumberFormatException e) {
//             System.err.println("\uD83D\uDEA8 Erreur de conversion en double : " + value);
//             return defaultValue;
//         }
//     }

//     private void parseCSV(InputStream inputStream) {
//         try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

//             CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
//             CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build();

//             List<String[]> records = csvReader.readAll();
//             System.out.println("\uD83D\uDFE1 Nombre total de lignes : " + records.size());

//             if (records.isEmpty()) {
//                 System.out.println("\uD83D\uDEA8 Le fichier CSV est vide.");
//                 return;
//             }

//             System.out.println("\uD83D\uDFE1 Suppression des anciennes données MongoDB...");
//             lotoRepository.deleteAll();

//             if (records.get(0).length < 50) {
//                 System.err.println("\uD83D\uDEA8 Erreur : Le CSV contient " + records.get(0).length + " colonnes au lieu de 50.");
//                 return;
//             }

//             List<LotoResult> lotoResults = new ArrayList<>();
//             for (int i = 1; i < records.size(); i++) {
//                 String[] row = records.get(i);

//                 if (row.length < 49) {
//                     System.err.println("\uD83D\uDEA8 Ligne " + i + " ignorée (trop courte, seulement " + row.length + " colonnes).");
//                     continue;
//                 }

//                 if (i <= 5) {
//                     System.out.println("\uD83D\uDD39 Ligne " + i + " : " + Arrays.toString(row));
//                 }

//                 try {
//                     LotoResult lotoResult = new LotoResult();
//                     lotoResult.setAnneeNumeroDeTirage(parseInteger(row[0], 0));
//                     lotoResult.setJourDeTirage(row[1]);
//                     lotoResult.setDateDeTirage(row[2]);
//                     lotoResult.setDateDeForclusion(row[3]);
//                     lotoResult.setBoule1(parseInteger(row[4], 0));
//                     lotoResult.setBoule2(parseInteger(row[5], 0));
//                     lotoResult.setBoule3(parseInteger(row[6], 0));
//                     lotoResult.setBoule4(parseInteger(row[7], 0));
//                     lotoResult.setBoule5(parseInteger(row[8], 0));
//                     lotoResult.setNumeroChance(parseInteger(row[9], 0));
//                     lotoResult.setCombinaisonGagnante(row[10]);
//                     lotoResult.setNombreDeGagnantAuRang1(parseInteger(row[11], 0));
//                     lotoResult.setRapportDuRang1(parseDouble(row[12], 0.0));

//                     // Ajout des autres valeurs
//                     for (int j = 13; j <= 28; j += 2) {
//                         lotoResult.getClass().getMethod("setNombreDeGagnantAuRang" + ((j - 11) / 2 + 2), int.class)
//                                 .invoke(lotoResult, parseInteger(row[j], 0));
//                         lotoResult.getClass().getMethod("setRapportDuRang" + ((j - 11) / 2 + 2), double.class)
//                                 .invoke(lotoResult, parseDouble(row[j + 1], 0.0));
//                     }

//                     lotoResult.setNombreDeCodesGagnants(parseInteger(row[29], 0));
//                     lotoResult.setRapportCodesGagnants(parseInteger(row[30], 0));
//                     lotoResult.setCodesGagnants(row[31]);
//                     lotoResult.setBoule1SecondTirage(parseInteger(row[32], 0));
//                     lotoResult.setBoule2SecondTirage(parseInteger(row[33], 0));
//                     lotoResult.setBoule3SecondTirage(parseInteger(row[34], 0));
//                     lotoResult.setBoule4SecondTirage(parseInteger(row[35], 0));
//                     lotoResult.setBoule5SecondTirage(parseInteger(row[36], 0));

//                     lotoResult.setCombinaisonGagnanteSecondTirage(row[38]);
//                     lotoResult.setNombreDeGagnantAuRang1SecondTirage(parseInteger(row[39], 0));
//                     lotoResult.setRapportDuRang1SecondTirage(parseDouble(row[40], 0.0));
//                     lotoResult.setNumeroJokerplus(parseInteger(row[47], 0));
//                     lotoResult.setDevise(row[48]);

//                     lotoResults.add(lotoResult);
//                 } catch (Exception e) {
//                     System.err.println("\uD83D\uDEA8 Erreur parsing ligne " + i + " : " + e.getMessage());
//                 }
//             }

//             if (!lotoResults.isEmpty()) {
//                 lotoRepository.saveAll(lotoResults);
//                 System.out.println("✅ " + lotoResults.size() + " documents insérés dans MongoDB !");
//             } else {
//                 System.out.println("\uD83D\uDEA8 Aucun document inséré !");
//             }

//         } catch (IOException | CsvException e) {
//             System.err.println("\uD83D\uDEA8 Erreur CSV : " + e.getMessage());
//         }
//     }
// }

// package com.fdjloto.api.service;

// import com.fdjloto.api.model.LotoResult;
// import com.fdjloto.api.repository.LotoRepository;
// import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
// import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import java.io.*;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Arrays;

// import com.opencsv.*;
// import com.opencsv.exceptions.CsvException;

// @Service
// public class LotoScraperService {

//     private static final String ZIP_URL = "https://www.sto.api.fdj.fr/anonymous/service-draw-info/v3/documentations/1a2b3c4d-9876-4562-b3fc-2c963f66afp6";

//     @Autowired
//     private LotoRepository lotoRepository;

//     // @Scheduled(fixedRate = 3600000)  // Exécution toutes les heures
// 	@Scheduled(fixedDelay = 300000)  // Attendre 5 minutes après la fin de l'exécution précédente
//     public void scrapeData() {
//         System.out.println("🟢 Démarrage du scraping...");

//         System.out.println("🟡 Test de connexion à MongoDB...");
//         try {
//             lotoRepository.count();
//             System.out.println("✅ Connexion MongoDB réussie !");
//         } catch (Exception e) {
//             System.err.println("🚨 Erreur de connexion MongoDB : " + e.getMessage());
//             return;
//         }

//         try {
//             System.out.println("🟡 Téléchargement du fichier ZIP...");
//             URL url = new URL(ZIP_URL);
//             HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//             connection.setRequestMethod("GET");

//             if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
//                 System.err.println("🚨 Erreur HTTP : " + connection.getResponseCode());
//                 return;
//             }

//             try (InputStream inputStream = connection.getInputStream();
//                  ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(inputStream)) {

//                 ZipArchiveEntry entry;
//                 while ((entry = zipInputStream.getNextZipEntry()) != null) {
//                     if (entry.getName().endsWith(".csv")) {
//                         System.out.println("✅ Fichier trouvé et extrait : " + entry.getName());
//                         parseCSV(zipInputStream);
//                         break;
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             System.err.println("🚨 Erreur lors du téléchargement : " + e.getMessage());
//         }
//     }

//     private int parseInteger(String value, int defaultValue) {
//         try {
//             return Integer.parseInt(value.trim());
//         } catch (NumberFormatException e) {
//             System.err.println("🚨 Erreur de conversion en entier : " + value);
//             return defaultValue;
//         }
//     }

//     private double parseDouble(String value, double defaultValue) {
//         try {
//             if (value == null || value.trim().isEmpty()) {
//                 return defaultValue;
//             }
//             return Double.parseDouble(value.replace(" ", "").replace(",", "."));
//         } catch (NumberFormatException e) {
//             System.err.println("🚨 Erreur de conversion en double : " + value);
//             return defaultValue;
//         }
//     }

//     private void parseCSV(InputStream inputStream) {
//         try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

//             CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
//             CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build();

//             List<String[]> records = csvReader.readAll();
//             System.out.println("🟡 Nombre total de lignes : " + records.size());

//             if (records.isEmpty()) {
//                 System.out.println("🚨 Le fichier CSV est vide.");
//                 return;
//             }

//             System.out.println("🟡 Suppression des anciennes données MongoDB...");
//             lotoRepository.deleteAll();

//             if (records.get(0).length < 50) {
//                 System.err.println("🚨 Erreur : Le CSV contient " + records.get(0).length + " colonnes au lieu de 50.");
//                 return;
//             }

//             List<LotoResult> lotoResults = new ArrayList<>();
//             for (int i = 1; i < records.size(); i++) {
//                 String[] row = records.get(i);

//                 if (row.length < 49) {
//                     System.err.println("🚨 Ligne " + i + " ignorée (trop courte, seulement " + row.length + " colonnes).");
//                     continue;
//                 }

//                 if (i <= 5) {
//                     System.out.println("🔹 Ligne " + i + " : " + Arrays.toString(row));
//                 }

//                 try {
//                     LotoResult lotoResult = new LotoResult();

//                     lotoResult.setAnneeNumeroDeTirage(parseInteger(row[0], 0));
//                     lotoResult.setJourDeTirage(row[1]);
//                     lotoResult.setDateDeTirage(row[2]);
//                     lotoResult.setDateDeForclusion(row[3]);
//                     lotoResult.setBoule1(parseInteger(row[4], 0));
//                     lotoResult.setBoule2(parseInteger(row[5], 0));
//                     lotoResult.setBoule3(parseInteger(row[6], 0));
//                     lotoResult.setBoule4(parseInteger(row[7], 0));
//                     lotoResult.setBoule5(parseInteger(row[8], 0));
//                     lotoResult.setNumeroChance(parseInteger(row[9], 0));
//                     lotoResult.setCombinaisonGagnante(row[10]);
//                     lotoResult.setNombreDeGagnantAuRang1(parseInteger(row[11], 0));
//                     lotoResult.setRapportDuRang1(parseDouble(row[12], 0.0));

//                     // Gestion dynamique des autres rangs
//                     for (int j = 13; j <= 28; j += 2) {
//                         int rank = ((j - 11) / 2 + 2);
//                         lotoResult.getClass().getMethod("setNombreDeGagnantAuRang" + rank, int.class)
//                                 .invoke(lotoResult, parseInteger(row[j], 0));
//                         lotoResult.getClass().getMethod("setRapportDuRang" + rank, double.class)
//                                 .invoke(lotoResult, parseDouble(row[j + 1], 0.0));
//                     }

//                     lotoResult.setNombreDeCodesGagnants(parseInteger(row[29], 0));
//                     lotoResult.setRapportCodesGagnants(parseInteger(row[30], 0));
//                     lotoResult.setCodesGagnants(row[31]);
//                     lotoResult.setBoule1SecondTirage(parseInteger(row[32], 0));
//                     lotoResult.setBoule2SecondTirage(parseInteger(row[33], 0));
//                     lotoResult.setBoule3SecondTirage(parseInteger(row[34], 0));
//                     lotoResult.setBoule4SecondTirage(parseInteger(row[35], 0));
//                     lotoResult.setBoule5SecondTirage(parseInteger(row[36], 0));

//                     lotoResult.setCombinaisonGagnanteSecondTirage(row[38]);
//                     lotoResult.setNombreDeGagnantAuRang1SecondTirage(parseInteger(row[39], 0));
//                     lotoResult.setRapportDuRang1SecondTirage(parseDouble(row[40], 0.0));
//                     lotoResult.setNumeroJokerplus(parseInteger(row[47], 0));
//                     lotoResult.setDevise(row[48]);

//                     lotoResults.add(lotoResult);
//                 } catch (Exception e) {
//                     System.err.println("🚨 Erreur parsing ligne " + i + " : " + e.getMessage());
//                 }
//             }

//             if (!lotoResults.isEmpty()) {
//                 lotoRepository.saveAll(lotoResults);
//                 System.out.println("✅ " + lotoResults.size() + " documents insérés dans MongoDB !");
//             } else {
//                 System.out.println("🚨 Aucun document inséré !");
//             }

//         } catch (IOException | CsvException e) {
//             System.err.println("🚨 Erreur CSV : " + e.getMessage());
//         }
//     }
// }

package com.fdjloto.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fdjloto.api.model.LotoResult;
import com.fdjloto.api.repository.LotoRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

@Service
public class LotoScraperService {

    private static final String ZIP_URL = "https://www.sto.api.fdj.fr/anonymous/service-draw-info/v3/documentations/1a2b3c4d-9876-4562-b3fc-2c963f66afp6";

    @Autowired
    private LotoRepository lotoRepository;

    // @Scheduled(fixedRate = 3600000)  // Exécution toutes les heures
	// @Scheduled(fixedDelay = 300000)  // Attendre 5 minutes après la fin de l'exécution précédente
	@Scheduled(fixedRate = 300000)  // Exécution toutes les 5 minutes
    public void scrapeData() {
        System.out.println("🟢 Démarrage du scraping...");

        System.out.println("🟡 Test de connexion à MongoDB...");
        try {
            lotoRepository.count();
            System.out.println("✅ Connexion MongoDB réussie !");
        } catch (Exception e) {
            System.err.println("🚨 Erreur de connexion MongoDB : " + e.getMessage());
            return;
        }

        try {
            System.out.println("🟡 Téléchargement du fichier ZIP...");
            URL url = new URL(ZIP_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.err.println("🚨 Erreur HTTP : " + connection.getResponseCode());
                return;
            }

            try (InputStream inputStream = connection.getInputStream();
                 ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(inputStream)) {

                ZipArchiveEntry entry;
                while ((entry = zipInputStream.getNextZipEntry()) != null) {
                    if (entry.getName().endsWith(".csv")) {
                        System.out.println("✅ Fichier trouvé et extrait : " + entry.getName());
                        parseCSV(zipInputStream);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("🚨 Erreur lors du téléchargement : " + e.getMessage());
        }
    }

	private int parseInteger(String value, int defaultValue) {
		try {
			return Integer.parseInt(value.trim());
		} catch (NumberFormatException | NullPointerException e) {
			// System.err.println("🚨 Erreur de conversion en entier : " + value);
			return defaultValue;
		}
	}

	private double parseDouble(String value, double defaultValue) {
		try {
			if (value == null || value.trim().isEmpty()) {
				return defaultValue;
			}
			return Double.parseDouble(value.replace(" ", "").replace(",", ".")); // Remplacement "," -> "." pour décimaux
		} catch (NumberFormatException e) {
			System.err.println("🚨 Erreur de conversion en double : " + value);
			return defaultValue;
		}
	}

    private void parseCSV(InputStream inputStream) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

			CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
			CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(csvParser).build();

			List<String[]> records = csvReader.readAll();
			for (String[] row : records) {
				if (row.length > 49) { // Ignore la dernière colonne si elle est vide
					row = Arrays.copyOf(row, 49);
				}
			}
			System.out.println("🟡 Nombre total de lignes : " + records.size());

			if (records.isEmpty()) {
				System.out.println("🚨 Le fichier CSV est vide.");
				return;
			}

			System.out.println("🟡 Suppression des anciennes données MongoDB...");
			lotoRepository.deleteAll();

			if (records.get(0).length < 50) {
				System.err.println("🚨 Erreur : Le CSV contient " + records.get(0).length + " colonnes au lieu de 50.");
				return;
			}

			List<LotoResult> lotoResults = new ArrayList<>();
			for (int i = 1; i < records.size(); i++) {
				String[] row = records.get(i);

				if (row.length < 49) {
					System.err.println("🚨 Ligne " + i + " ignorée (trop courte, seulement " + row.length + " colonnes).");
					continue;
				}

				if (i <= 5) {
					System.out.println("🔹 Ligne " + i + " : " + Arrays.toString(row));
				}

				try {
                    LotoResult lotoResult = new LotoResult();

					lotoResult.setAnneeNumeroDeTirage(parseInteger(row[0], 0));
					lotoResult.setJourDeTirage(row[1]);
					lotoResult.setDateDeTirage(row[2]);
					lotoResult.setDateDeForclusion(row[3]);
					lotoResult.setBoule1(parseInteger(row[4], 0));
					lotoResult.setBoule2(parseInteger(row[5], 0));
					lotoResult.setBoule3(parseInteger(row[6], 0));
					lotoResult.setBoule4(parseInteger(row[7], 0));
					lotoResult.setBoule5(parseInteger(row[8], 0));
					lotoResult.setNumeroChance(parseInteger(row[9], 0));
					lotoResult.setCombinaisonGagnante(row[10]);
					lotoResult.setNombreDeGagnantAuRang1(parseInteger(row[11], 0));
					lotoResult.setRapportDuRang1(parseDouble(row[12], 0.0));

					// Gestion dynamique des autres rangs
					// for (int j = 13; j <= 28; j += 2) {
					// 	int rank = ((j - 11) / 2 + 2);
					// 	lotoResult.getClass().getMethod("setNombreDeGagnantAuRang" + rank, int.class)
					// 			.invoke(lotoResult, parseInteger(row[j], 0));
					// 	lotoResult.getClass().getMethod("setRapportDuRang" + rank, double.class)
					// 			.invoke(lotoResult, parseDouble(row[j + 1], 0.0));
					// }
					for (int j = 13; j <= 28 && j < row.length; j += 2) { // Vérifie que j reste dans les limites
						int rank = ((j - 11) / 2 + 2);
						try {
							lotoResult.getClass().getMethod("setNombreDeGagnantAuRang" + rank, int.class)
									.invoke(lotoResult, parseInteger(row[j], 0));
							lotoResult.getClass().getMethod("setRapportDuRang" + rank, double.class)
									.invoke(lotoResult, parseDouble(row[j + 1], 0.0));
						} catch (NoSuchMethodException e) {
							// System.err.println("⚠️ Méthode setNombreDeGagnantAuRang" + rank + " non trouvée, ignorée.");
						}
					}

					lotoResult.setNombreDeCodesGagnants(parseInteger(row[29], 0));
					lotoResult.setRapportCodesGagnants(parseInteger(row[30], 0));
					lotoResult.setCodesGagnants(row[31]);
					lotoResult.setBoule1SecondTirage(parseInteger(row[32], 0));
					lotoResult.setBoule2SecondTirage(parseInteger(row[33], 0));
					lotoResult.setBoule3SecondTirage(parseInteger(row[34], 0));
					lotoResult.setBoule4SecondTirage(parseInteger(row[35], 0));
					lotoResult.setBoule5SecondTirage(parseInteger(row[36], 0));

					lotoResult.setCombinaisonGagnanteSecondTirage(row[38]);
					lotoResult.setNombreDeGagnantAuRang1SecondTirage(parseInteger(row[39], 0));
					lotoResult.setRapportDuRang1SecondTirage(parseDouble(row[40], 0.0));
					lotoResult.setNumeroJokerplus(parseInteger(row[47], 0));

					// ✅ Vérification avant d'accéder à la colonne "Devise"
					if (row.length >= 49 && !row[48].isEmpty()) {
						lotoResult.setDevise(row[48]);  // Devise correcte si elle existe
					} else {
						lotoResult.setDevise("EUR");  // Valeur par défaut si absente
					}

					lotoResults.add(lotoResult);
                } catch (Exception e) {
                    System.err.println("🚨 Erreur parsing ligne " + i + " : " + e.getMessage());
                }
            }

            if (!lotoResults.isEmpty()) {
                lotoRepository.saveAll(lotoResults);
                System.out.println("✅ " + lotoResults.size() + " documents insérés dans MongoDB !");
            } else {
                System.out.println("🚨 Aucun document inséré !");
            }

        } catch (IOException | CsvException e) {
            System.err.println("🚨 Erreur CSV : " + e.getMessage());
        }
    }
}
