package com.fdjloto.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "historique")
public class Historique {
    @Id
    private String id;
    private int anneeNumeroDeTirage;
    private String jourDeTirage;
    private String dateDeTirage;
    private String combinaisonGagnanteEnOrdreCroissant;
    private int numeroChance;
    private String devise;

    // Constructeur par défaut
    public Historique() {}

    // Constructeur avec paramètres
    public Historique(int anneeNumeroDeTirage, String jourDeTirage, String dateDeTirage,
                      String combinaisonGagnanteEnOrdreCroissant, int numeroChance, String devise) {
        this.anneeNumeroDeTirage = anneeNumeroDeTirage;
        this.jourDeTirage = jourDeTirage;
        this.dateDeTirage = dateDeTirage;
        this.combinaisonGagnanteEnOrdreCroissant = combinaisonGagnanteEnOrdreCroissant;
        this.numeroChance = numeroChance;
        this.devise = devise;
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getAnneeNumeroDeTirage() { return anneeNumeroDeTirage; }
    public void setAnneeNumeroDeTirage(int anneeNumeroDeTirage) { this.anneeNumeroDeTirage = anneeNumeroDeTirage; }
    public String getJourDeTirage() { return jourDeTirage; }
    public void setJourDeTirage(String jourDeTirage) { this.jourDeTirage = jourDeTirage; }
    public String getDateDeTirage() { return dateDeTirage; }
    public void setDateDeTirage(String dateDeTirage) { this.dateDeTirage = dateDeTirage; }
    public String getCombinaisonGagnanteEnOrdreCroissant() { return combinaisonGagnanteEnOrdreCroissant; }
    public void setCombinaisonGagnanteEnOrdreCroissant(String combinaisonGagnanteEnOrdreCroissant) { this.combinaisonGagnanteEnOrdreCroissant = combinaisonGagnanteEnOrdreCroissant; }
    public int getNumeroChance() { return numeroChance; }
    public void setNumeroChance(int numeroChance) { this.numeroChance = numeroChance; }
    public String getDevise() { return devise; }
    public void setDevise(String devise) { this.devise = devise; }
}
