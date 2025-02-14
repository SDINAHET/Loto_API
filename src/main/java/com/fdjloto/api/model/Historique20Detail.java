package com.fdjloto.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Document(collection = "historique")
public class Historique20Detail {
//     @Id
//     private String id;

//     private int anneeNumeroDeTirage;

//     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Paris")
//     private Date dateDeTirage;

//     private String dateDeForclusion;
//     private int boule1, boule2, boule3, boule4, boule5, numeroChance;
//     private String combinaisonGagnante;

//     private int boule1SecondTirage, boule2SecondTirage, boule3SecondTirage, boule4SecondTirage, boule5SecondTirage;
//     private String combinaisonGagnanteSecondTirage;

//     private int nombreDeGagnantAuRang1, nombreDeGagnantAuRang2, nombreDeGagnantAuRang3;
//     private double rapportDuRang1, rapportDuRang2, rapportDuRang3;

//     private String codesGagnants;
//     private int numeroJokerplus;
//     private String devise;

//     // ✅ Constructeur vide
//     public Historique20Detail() {}

//     // ✅ Getters et Setters
//     public String getId() { return id; }
//     public void setId(String id) { this.id = id; }

//     public Date getDateDeTirage() { return dateDeTirage; }
//     public void setDateDeTirage(Date dateDeTirage) { this.dateDeTirage = dateDeTirage; }

//     public String getCombinaisonGagnante() { return combinaisonGagnante; }
//     public void setCombinaisonGagnante(String combinaisonGagnante) { this.combinaisonGagnante = combinaisonGagnante; }

//     public int getNumeroJokerplus() { return numeroJokerplus; }
//     public void setNumeroJokerplus(int numeroJokerplus) { this.numeroJokerplus = numeroJokerplus; }

//     public String getCodesGagnants() { return codesGagnants; }
//     public void setCodesGagnants(String codesGagnants) { this.codesGagnants = codesGagnants; }
// }
@Id
    private String id;
    private int anneeNumeroDeTirage;
    private String jourDeTirage;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Date dateDeTirage; // ✅ Maintenant stocké en Date

    private String dateDeForclusion;
    private int boule1;
    private int boule2;
    private int boule3;
    private int boule4;
    private int boule5;
    private int numeroChance;
    private String combinaisonGagnante;
    private int nombreDeGagnantAuRang1;
    private double rapportDuRang1;
    private int nombreDeGagnantAuRang2;
    private double rapportDuRang2;
    private int nombreDeGagnantAuRang3;
    private double rapportDuRang3;
    private int nombreDeGagnantAuRang4;
    private double rapportDuRang4;
    private int nombreDeGagnantAuRang5;
    private double rapportDuRang5;
    private int nombreDeGagnantAuRang6;
    private double rapportDuRang6;
    private int nombreDeGagnantAuRang7;
    private double rapportDuRang7;
    private int nombreDeGagnantAuRang8;
    private double rapportDuRang8;
    private int nombreDeGagnantAuRang9;
    private double rapportDuRang9;
    private int nombreDeCodesGagnants;
    private int rapportCodesGagnants;
    private String codesGagnants;
    private int boule1SecondTirage;
    private int boule2SecondTirage;
    private int boule3SecondTirage;
    private int boule4SecondTirage;
    private int boule5SecondTirage;
    private String combinaisonGagnanteSecondTirage;
    private int nombreDeGagnantAuRang1SecondTirage;
    private double rapportDuRang1SecondTirage;
    private int nombreDeGagnantAuRang2SecondTirage;
    private double rapportDuRang2SecondTirage;
    private int nombreDeGagnantAuRang3SecondTirage;
    private double rapportDuRang3SecondTirage;
    private int nombreDeGagnantAuRang4SecondTirage;
    private double rapportDuRang4SecondTirage;
    private int numeroJokerplus;
    private String devise;

	// ✅ Constructeur vide (nécessaire pour Spring Boot)
	public Historique20Detail() {}

	// ✅ Getters et Setters
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public int getAnneeNumeroDeTirage() { return anneeNumeroDeTirage; }
	public void setAnneeNumeroDeTirage(int anneeNumeroDeTirage) { this.anneeNumeroDeTirage = anneeNumeroDeTirage; }

	public String getJourDeTirage() { return jourDeTirage; }
	public void setJourDeTirage(String jourDeTirage) { this.jourDeTirage = jourDeTirage; }


    public Date getDateDeTirage() { return dateDeTirage; }  // ✅ Correction : retourne une Date
    public void setDateDeTirage(Date dateDeTirage) { this.dateDeTirage = dateDeTirage; } // ✅ Accepte une Date


	public String getDateDeForclusion() { return dateDeForclusion; }
	public void setDateDeForclusion(String dateDeForclusion) { this.dateDeForclusion = dateDeForclusion; }

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

	public String getCombinaisonGagnante() { return combinaisonGagnante; }
	public void setCombinaisonGagnante(String combinaisonGagnante) { this.combinaisonGagnante = combinaisonGagnante; }

	// Nombre de gagnants et rapports des rangs 1
	public int getNombreDeGagnantAuRang1() { return nombreDeGagnantAuRang1; }
	public void setNombreDeGagnantAuRang1(int nombreDeGagnantAuRang1) { this.nombreDeGagnantAuRang1 = nombreDeGagnantAuRang1; }

	public double getRapportDuRang1() { return rapportDuRang1; }
	public void setRapportDuRang1(double rapportDuRang1) { this.rapportDuRang1 = rapportDuRang1; }

		// Nombre de gagnants et rapports des rangs 2
	public int getNombreDeGagnantAuRang2() { return nombreDeGagnantAuRang2; }
	public void setNombreDeGagnantAuRang2(int nombreDeGagnantAuRang2) { this.nombreDeGagnantAuRang2 = nombreDeGagnantAuRang2; }

	public double getRapportDuRang2() { return rapportDuRang2; }
	public void setRapportDuRang2(double rapportDuRang2) { this.rapportDuRang2 = rapportDuRang2; }

	// Nombre de gagnants et rapports des rangs 3
	public int getNombreDeGagnantAuRang3() { return nombreDeGagnantAuRang3; }
	public void setNombreDeGagnantAuRang3(int nombreDeGagnantAuRang3) { this.nombreDeGagnantAuRang3 = nombreDeGagnantAuRang3; }

	public double getRapportDuRang3() { return rapportDuRang3; }
	public void setRapportDuRang3(double rapportDuRang3) { this.rapportDuRang3 = rapportDuRang3; }

	// Nombre de gagnants et rapports des rangs 4
	public int getNombreDeGagnantAuRang4() { return nombreDeGagnantAuRang4; }
	public void setNombreDeGagnantAuRang4(int nombreDeGagnantAuRang4) { this.nombreDeGagnantAuRang4 = nombreDeGagnantAuRang4; }

	public double getRapportDuRang4() { return rapportDuRang4; }
	public void setRapportDuRang4(double rapportDuRang4) { this.rapportDuRang4 = rapportDuRang4; }

	// Nombre de gagnants et rapports des rangs 5
	public int getNombreDeGagnantAuRang5() { return nombreDeGagnantAuRang5; }
	public void setNombreDeGagnantAuRang5(int nombreDeGagnantAuRang5) { this.nombreDeGagnantAuRang5 = nombreDeGagnantAuRang5; }

	public double getRapportDuRang5() { return rapportDuRang5; }
	public void setRapportDuRang5(double rapportDuRang5) { this.rapportDuRang5 = rapportDuRang5; }

	// Nombre de gagnants et rapports des rangs 6
	public int getNombreDeGagnantAuRang6() { return nombreDeGagnantAuRang6; }
	public void setNombreDeGagnantAuRang6(int nombreDeGagnantAuRang6) { this.nombreDeGagnantAuRang6 = nombreDeGagnantAuRang6; }

	public double getRapportDuRang6() { return rapportDuRang6; }
	public void setRapportDuRang6(double rapportDuRang6) { this.rapportDuRang6 = rapportDuRang6; }

	// Nombre de gagnants et rapports des rangs 7
	public int getNombreDeGagnantAuRang7() { return nombreDeGagnantAuRang7; }
	public void setNombreDeGagnantAuRang7(int nombreDeGagnantAuRang7) { this.nombreDeGagnantAuRang7 = nombreDeGagnantAuRang7; }

	public double getRapportDuRang7() { return rapportDuRang7; }
	public void setRapportDuRang7(double rapportDuRang7) { this.rapportDuRang7 = rapportDuRang7; }

	// Nombre de gagnants et rapports des rangs 8
	public int getNombreDeGagnantAuRang8() { return nombreDeGagnantAuRang8; }
	public void setNombreDeGagnantAuRang8(int nombreDeGagnantAuRang8) { this.nombreDeGagnantAuRang8 = nombreDeGagnantAuRang8; }

	public double getRapportDuRang8() { return rapportDuRang8; }
	public void setRapportDuRang8(double rapportDuRang8) { this.rapportDuRang8 = rapportDuRang8; }

	// Nombre de gagnants et rapports des rangs 9
	public int getNombreDeGagnantAuRang9() { return nombreDeGagnantAuRang9; }
	public void setNombreDeGagnantAuRang9(int nombreDeGagnantAuRang9) { this.nombreDeGagnantAuRang9 = nombreDeGagnantAuRang9; }

	public double getRapportDuRang9() { return rapportDuRang9; }
	public void setRapportDuRang9(double rapportDuRang9) { this.rapportDuRang9 = rapportDuRang9; }

	// Codes gagnants
	public int getNombreDeCodesGagnants() { return nombreDeCodesGagnants; }
	public void setNombreDeCodesGagnants(int nombreDeCodesGagnants) { this.nombreDeCodesGagnants = nombreDeCodesGagnants; }

	public int getRapportCodesGagnants() { return rapportCodesGagnants; }
	public void setRapportCodesGagnants(int rapportCodesGagnants) { this.rapportCodesGagnants = rapportCodesGagnants; }

	public String getCodesGagnants() { return codesGagnants; }
	public void setCodesGagnants(String codesGagnants) { this.codesGagnants = codesGagnants; }

	// Boules Second Tirage
	public int getBoule1SecondTirage() { return boule1SecondTirage; }
	public void setBoule1SecondTirage(int boule1SecondTirage) { this.boule1SecondTirage = boule1SecondTirage; }

	public int getBoule2SecondTirage() { return boule2SecondTirage; }
	public void setBoule2SecondTirage(int boule2SecondTirage) { this.boule2SecondTirage = boule2SecondTirage; }

	public int getBoule3SecondTirage() { return boule3SecondTirage; }
	public void setBoule3SecondTirage(int boule3SecondTirage) { this.boule3SecondTirage = boule3SecondTirage; }

	public int getBoule4SecondTirage() { return boule4SecondTirage; }
	public void setBoule4SecondTirage(int boule4SecondTirage) { this.boule4SecondTirage = boule4SecondTirage; }

	public int getBoule5SecondTirage() { return boule5SecondTirage; }
	public void setBoule5SecondTirage(int boule5SecondTirage) { this.boule5SecondTirage = boule5SecondTirage; }

	public String getCombinaisonGagnanteSecondTirage() { return combinaisonGagnanteSecondTirage; }
	public void setCombinaisonGagnanteSecondTirage(String combinaisonGagnanteSecondTirage) { this.combinaisonGagnanteSecondTirage = combinaisonGagnanteSecondTirage; }

	// Nombre de gagnants et rapports Second Tirage
	public int getNombreDeGagnantAuRang1SecondTirage() { return nombreDeGagnantAuRang1SecondTirage; }
	public void setNombreDeGagnantAuRang1SecondTirage(int nombreDeGagnantAuRang1SecondTirage) { this.nombreDeGagnantAuRang1SecondTirage = nombreDeGagnantAuRang1SecondTirage; }

	public double getRapportDuRang1SecondTirage() { return rapportDuRang1SecondTirage; }
	public void setRapportDuRang1SecondTirage(double rapportDuRang1SecondTirage) { this.rapportDuRang1SecondTirage = rapportDuRang1SecondTirage; }

	public int getNombreDeGagnantAuRang2SecondTirage() { return nombreDeGagnantAuRang2SecondTirage; }
	public void setNombreDeGagnantAuRang2SecondTirage(int nombreDeGagnantAuRang2SecondTirage) { this.nombreDeGagnantAuRang2SecondTirage = nombreDeGagnantAuRang2SecondTirage; }

	public double getRapportDuRang2SecondTirage() { return rapportDuRang2SecondTirage; }
	public void setRapportDuRang2SecondTirage(double rapportDuRang2SecondTirage) { this.rapportDuRang2SecondTirage = rapportDuRang2SecondTirage; }

	public int getNombreDeGagnantAuRang3SecondTirage() { return nombreDeGagnantAuRang3SecondTirage; }
	public void setNombreDeGagnantAuRang3SecondTirage(int nombreDeGagnantAuRang3SecondTirage) { this.nombreDeGagnantAuRang3SecondTirage = nombreDeGagnantAuRang3SecondTirage; }

	public double getRapportDuRang3SecondTirage() { return rapportDuRang3SecondTirage; }
	public void setRapportDuRang3SecondTirage(double rapportDuRang3SecondTirage) { this.rapportDuRang3SecondTirage = rapportDuRang3SecondTirage; }

	public int getNombreDeGagnantAuRang4SecondTirage() { return nombreDeGagnantAuRang4SecondTirage; }
	public void setNombreDeGagnantAuRang4SecondTirage(int nombreDeGagnantAuRang4SecondTirage) { this.nombreDeGagnantAuRang4SecondTirage = nombreDeGagnantAuRang4SecondTirage; }

	public double getRapportDuRang4SecondTirage() { return rapportDuRang4SecondTirage; }
	public void setRapportDuRang4SecondTirage(double rapportDuRang4SecondTirage) { this.rapportDuRang4SecondTirage = rapportDuRang4SecondTirage; }

	public int getNumeroJokerplus() { return numeroJokerplus; }
	public void setNumeroJokerplus(int numeroJokerplus) { this.numeroJokerplus = numeroJokerplus; }

	public String getDevise() { return devise; }
	public void setDevise(String devise) { this.devise = devise; }
}
