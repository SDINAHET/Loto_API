// package com.fdjloto.api.model;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import lombok.Data;
// import java.util.List;

// @Document(collection = "historique") // Assurez-vous que "historique" est bien le nom de ta collection MongoDB
// @Data // Génère automatiquement les Getters, Setters, toString(), equals(), et hashCode()
// public class Tirage {

//     @Id
//     private String id;
//     private String jourDeTirage;
//     private String dateDeTirage;
//     private int boule1;
//     private int boule2;
//     private int boule3;
//     private int boule4;
//     private int boule5;
//     private int numeroChance;

//     public List<Integer> getBoules() {
//         return List.of(boule1, boule2, boule3, boule4, boule5);
//     }
// }

// package com.fdjloto.api.model;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import lombok.Data;
// import java.util.List;

// @Document(collection = "historique") // Assurez-vous que "historique" est bien le nom de ta collection MongoDB
// @Data // Génère automatiquement les Getters, Setters, toString(), equals(), et hashCode()
// public class Tirage {

//     @Id
//     private String id;
//     private String jourDeTirage;
//     private String dateDeTirage;
//     private int boule1;
//     private int boule2;
//     private int boule3;
//     private int boule4;
//     private int boule5;
//     private int numeroChance;

//     private double rapportDuRang1;
//     private double rapportDuRang2;
//     private double rapportDuRang3;
//     private double rapportDuRang4;
//     private double rapportDuRang5;
//     private double rapportDuRang6;
//     private double rapportDuRang7;
//     private double rapportDuRang8;
//     private double rapportDuRang9;

//     // ✅ Méthode pour retourner la liste des boules en tant que `List<Integer>`
//     public List<Integer> getNumerosAsList() {
//         return List.of(boule1, boule2, boule3, boule4, boule5);
//     }

//     // ✅ Méthode pour récupérer le gain selon le rang
//     public double getRapportDuRang(int rang) {
//         return switch (rang) {
//             case 1 -> rapportDuRang1;
//             case 2 -> rapportDuRang2;
//             case 3 -> rapportDuRang3;
//             case 4 -> rapportDuRang4;
//             case 5 -> rapportDuRang5;
//             case 6 -> rapportDuRang6;
//             case 7 -> rapportDuRang7;
//             case 8 -> rapportDuRang8;
//             case 9 -> rapportDuRang9;
//             default -> 0;
//         };
//     }
// }

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

    private double rapportDuRang1;
    private double rapportDuRang2;
    private double rapportDuRang3;
    private double rapportDuRang4;
    private double rapportDuRang5;
    private double rapportDuRang6;
    private double rapportDuRang7;
    private double rapportDuRang8;
    private double rapportDuRang9;

    // ✅ Méthode pour retourner la liste des boules en tant que `List<Integer>`
    public List<Integer> getBoules() {
        return List.of(boule1, boule2, boule3, boule4, boule5);
    }

    // ✅ Méthode pour récupérer le gain selon le rang
    public double getRapportDuRang(int rang) {
        return switch (rang) {
            case 1 -> rapportDuRang1;
            case 2 -> rapportDuRang2;
            case 3 -> rapportDuRang3;
            case 4 -> rapportDuRang4;
            case 5 -> rapportDuRang5;
            case 6 -> rapportDuRang6;
            case 7 -> rapportDuRang7;
            case 8 -> rapportDuRang8;
            case 9 -> rapportDuRang9;
            default -> 0;
        };
    }
}
