package com.fdjloto.api.service;

import com.fdjloto.api.model.Historique20Detail;
import com.fdjloto.api.repository.Historique20DetailRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.List;

@Service
public class Historique20DetailService {

    private final Historique20DetailRepository repository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Historique20DetailService(Historique20DetailRepository repository) {
        this.repository = repository;
    }

    // 🔹 Recherche par date unique
    public Optional<Historique20Detail> getTirageByDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            Date parsedDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return repository.findByDateDeTirage(parsedDate);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    // 🔹 Recherche par plage de dates
    public List<Historique20Detail> getTiragesParPlageDeDates(String startDate, String endDate) {
        try {
            // 🔥 Parse les dates
            LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
            LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

            // 🔥 Soustrait 1 jour à la date de début pour inclure J-1
            LocalDate startLocalDateJMoinsUn = startLocalDate.minusDays(1);

            // 🔥 Convertit en Date pour MongoDB
            Date start = Date.from(startLocalDateJMoinsUn.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date end = Date.from(endLocalDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

            // 🔍 Log pour vérification
            System.out.println("🔍 Start Date J-1: " + start);
            System.out.println("🔍 End Date: " + end);

            // 🔥 Effectue la recherche avec J-1 inclus
            return repository.findByDateDeTirageBetween(start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Retourne une liste vide en cas d'erreur
        }
    }


}

