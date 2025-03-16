package com.fdjloto.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

/**
 * Model representing detailed lottery results for historical draws.
 */
@Document(collection = "historique")
public class Historique20Result {
    @Id
	@Schema(description = "Unique identifier for the draw result", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

	@Schema(description = "The draw date of the lottery result", example = "2025-03-15")
    @Field("dateDeTirage") // Pour s'assurer que MongoDB stocke sous ce nom
    private Date dateDeTirage;

    @Schema(description = "The first drawn number", example = "12")
	// private String combinaisonGagnante;
	private int boule1;

	@Schema(description = "The second drawn number", example = "25")
	private int boule2;

	@Schema(description = "The third drawn number", example = "37")
	private int boule3;

	@Schema(description = "The fourth drawn number", example = "42")
	private int boule4;

	@Schema(description = "The fifth drawn number", example = "49")
	private int boule5;

	@Schema(description = "The lucky number (Numéro Chance)", example = "7")
    private int numeroChance;

    public Historique20Result() {}

    // public Historique20Result(Date dateDeTirage, String combinaisonGagnante, int numeroChance) {
	public Historique20Result(Date dateDeTirage, int boule1, int boule2, int boule3, int boule4, int boule5, int numeroChance) {
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
    public void setId(String id) { this.id = id; }

    public Date getDateDeTirage() { return dateDeTirage; }
    public void setDateDeTirage(Date dateDeTirage) { this.dateDeTirage = dateDeTirage; }

    // public String getCombinaisonGagnante() { return combinaisonGagnante; }
    // public void setCombinaisonGagnante(String combinaisonGagnante) { this.combinaisonGagnante = combinaisonGagnante; }

	public int getBoule1() { return boule1; }
	public void setBoule1(int boule1) { this.boule1 = boule1; }

	public int getBoule2() { return boule2; }
	public void setBoule2(int boule2) { this.boule2 = boule2; }

	public int getBoule3() { return boule3; }
	public void setBoule3(int boule3) { this.boule3 = boule3; }

	public int getBoule4() { return boule4; }
	public void setBoule4(int boule4) { this.boule4 = boule4; }

	public int getBoule5() { return boule5; }
	public void setBoule5(int boule5) { this.boule5 = boule5; }

    public int getNumeroChance() { return numeroChance; }
    public void setNumeroChance(int numeroChance) { this.numeroChance = numeroChance; }
}


