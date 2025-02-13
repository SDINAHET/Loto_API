package com.fdjloto.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class LotoResultDTO {
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Date dateDeTirage;

    // private String combinaisonGagnante;
	private int boule1;
    private int boule2;
    private int boule3;
    private int boule4;
    private int boule5;
    private int numeroChance;

    public LotoResultDTO(String id, Date dateDeTirage, int boule1, int boule2, int boule3, int boule4, int boule5, int numeroChance) {
        this.id = id;
        this.dateDeTirage = dateDeTirage;
        // this.combinaisonGagnante = combinaisonGagnante;
		this.boule1 = boule1;
        this.boule2 = boule2;
        this.boule3 = boule3;
        this.boule4 = boule4;
        this.boule5 = boule5;
        this.numeroChance = numeroChance;
    }

    public String getId() { return id; }
    public Date getDateDeTirage() { return dateDeTirage; }
    // public String getCombinaisonGagnante() { return combinaisonGagnante; }
	public int getBoule1() { return boule1; }
	public int getBoule2() { return boule2; }
	public int getBoule3() { return boule3; }
	public int getBoule4() { return boule4; }
	public int getBoule5() { return boule5; }
    public int getNumeroChance() { return numeroChance; }
}


// 💡 Explication :

// LotoResultDTO est utilisé uniquement pour envoyer des données formatées à l'API.
// L’annotation @JsonFormat applique le format "dd/MM/yyyy" sans modifier LotoResult.
