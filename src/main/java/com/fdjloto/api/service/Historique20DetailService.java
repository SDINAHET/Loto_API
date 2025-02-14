package com.fdjloto.api.service;

import com.fdjloto.api.model.Historique20Detail;
import com.fdjloto.api.repository.Historique20DetailRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
public class Historique20DetailService {

    private final Historique20DetailRepository repository;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Historique20DetailService(Historique20DetailRepository repository) {
        this.repository = repository;
    }

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
}

