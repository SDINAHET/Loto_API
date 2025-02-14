package com.fdjloto.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Document(collection = "historique") // Assurez-vous que "historique" est bien le nom de ta collection MongoDB
@Data // Génère automatiquement les Getters, Setters, toString(), equals(), et hashCode()
public class Tirage {

    @Id
    private String id;
    private String jourDeTirage;
    private String dateDeTirage;
    private int boule1;
    private int boule2;
    private int boule3;
    private int boule4;
    private int boule5;
    private int numeroChance;

    public List<Integer> getBoules() {
        return List.of(boule1, boule2, boule3, boule4, boule5);
    }
}
