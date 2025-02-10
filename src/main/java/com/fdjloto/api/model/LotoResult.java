package com.fdjloto.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "historique")
public class LotoResult {
    @Id
    private String id;
    private String date;
    private String numeros;
    private String etoiles;

    public LotoResult() {}

    public LotoResult(String date, String numeros, String etoiles) {
        this.date = date;
        this.numeros = numeros;
        this.etoiles = etoiles;
    }

    // Getters et Setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getNumeros() { return numeros; }
    public void setNumeros(String numeros) { this.numeros = numeros; }

    public String getEtoiles() { return etoiles; }
    public void setEtoiles(String etoiles) { this.etoiles = etoiles; }
}
