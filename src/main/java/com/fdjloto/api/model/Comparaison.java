// // package com.fdjloto.api.model;

// // import jakarta.persistence.*;
// // import java.util.List;
// // import java.util.Objects;

// // @Entity
// // @Table(name = "comparaison")
// // public class Comparaison {

// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;

// //     @ManyToOne
// //     @JoinColumn(name = "ticket_id", nullable = false)
// //     private Ticket ticket;

// //     @ManyToOne
// //     @JoinColumn(name = "tirage_id", nullable = false)
// //     private Tirage tirage;

// //     private int numerosTrouves;
// //     private boolean chanceGagnante;
// //     private int rang;
// //     private double gain;
// //     private String statut; // GAGNANT ou PERDANT

// //     // ✅ Constructeurs
// //     public Comparaison() {}

// //     public Comparaison(Ticket ticket, Tirage tirage, int numerosTrouves, boolean chanceGagnante, int rang, double gain, String statut) {
// //         this.ticket = ticket;
// //         this.tirage = tirage;
// //         this.numerosTrouves = numerosTrouves;
// //         this.chanceGagnante = chanceGagnante;
// //         this.rang = rang;
// //         this.gain = gain;
// //         this.statut = statut;
// //     }

// //     // ✅ Getters et Setters
// //     public Long getId() { return id; }
// //     public void setId(Long id) { this.id = id; }

// //     public Ticket getTicket() { return ticket; }
// //     public void setTicket(Ticket ticket) { this.ticket = ticket; }

// //     public Tirage getTirage() { return tirage; }
// //     public void setTirage(Tirage tirage) { this.tirage = tirage; }

// //     public int getNumerosTrouves() { return numerosTrouves; }
// //     public void setNumerosTrouves(int numerosTrouves) { this.numerosTrouves = numerosTrouves; }

// //     public boolean isChanceGagnante() { return chanceGagnante; }
// //     public void setChanceGagnante(boolean chanceGagnante) { this.chanceGagnante = chanceGagnante; }

// //     public int getRang() { return rang; }
// //     public void setRang(int rang) { this.rang = rang; }

// //     public double getGain() { return gain; }
// //     public void setGain(double gain) { this.gain = gain; }

// //     public String getStatut() { return statut; }
// //     public void setStatut(String statut) { this.statut = statut; }

// //     // ✅ Comparaison d'égalité (utile pour éviter les doublons)
// //     @Override
// //     public boolean equals(Object o) {
// //         if (this == o) return true;
// //         if (o == null || getClass() != o.getClass()) return false;
// //         Comparaison that = (Comparaison) o;
// //         return Objects.equals(ticket, that.ticket) && Objects.equals(tirage, that.tirage);
// //     }

// //     @Override
// //     public int hashCode() {
// //         return Objects.hash(ticket, tirage);
// //     }

// //     @Override
// //     public String toString() {
// //         return "Comparaison{" +
// //                 "ticket=" + ticket +
// //                 ", tirage=" + tirage +
// //                 ", numerosTrouves=" + numerosTrouves +
// //                 ", chanceGagnante=" + chanceGagnante +
// //                 ", rang=" + rang +
// //                 ", gain=" + gain +
// //                 ", statut='" + statut + '\'' +
// //                 '}';
// //     }
// // }

// package com.fdjloto.api.model;

// import jakarta.persistence.*;
// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

// import java.time.Instant;
// import java.util.UUID;

// @Entity // Utilisé pour JPA (SQLite)
// @Table(name = "comparaison") // Table pour SQLite
// @Document(collection = "comparaisons") // Collection pour MongoDB
// public class Comparaison {

//     @jakarta.persistence.Id // JPA (SQLite)
//     @Id // MongoDB
//     @GeneratedValue(strategy = GenerationType.UUID) // Génération UUID pour SQLite
//     private String id;

//     private String ticketId;
//     private String tirageId;
//     private int numerosTrouves;
//     private boolean chanceGagnante;
//     private int rang;
//     private double gain;
//     private String statut; // "GAGNANT" ou "PERDANT"

//     private Instant dateComparaison; // Pour MongoDB (stocké en ISO 8601)

//     // ✅ Constructeur par défaut
//     public Comparaison() {}

//     // ✅ Constructeur principal
// 	public Comparaison(Ticket ticket, Tirage tirage, int numerosTrouves, boolean chanceGagnante, int rang, double gain, String statut) {
// 		this.id = UUID.randomUUID().toString(); // Génération UUID pour SQLite et MongoDB
// 		this.ticketId = ticket.getId();
// 		this.tirageId = tirage.getId();
// 		this.numerosTrouves = numerosTrouves;
// 		this.chanceGagnante = chanceGagnante;
// 		this.rang = rang;
// 		this.gain = gain;
// 		this.statut = statut;
// 		this.dateComparaison = Instant.now(); // Format ISO 8601
// 	}


//     // ✅ Getters et Setters
//     public String getId() { return id; }
//     public void setId(String id) { this.id = id; }

//     public String getTicketId() { return ticketId; }
//     public void setTicketId(String ticketId) { this.ticketId = ticketId; }

//     public String getTirageId() { return tirageId; }
//     public void setTirageId(String tirageId) { this.tirageId = tirageId; }

//     public int getNumerosTrouves() { return numerosTrouves; }
//     public void setNumerosTrouves(int numerosTrouves) { this.numerosTrouves = numerosTrouves; }

//     public boolean isChanceGagnante() { return chanceGagnante; }
//     public void setChanceGagnante(boolean chanceGagnante) { this.chanceGagnante = chanceGagnante; }

//     public int getRang() { return rang; }
//     public void setRang(int rang) { this.rang = rang; }

//     public double getGain() { return gain; }
//     public void setGain(double gain) { this.gain = gain; }

//     public String getStatut() { return statut; }
//     public void setStatut(String statut) { this.statut = statut; }

//     public Instant getDateComparaison() { return dateComparaison; }
//     public void setDateComparaison(Instant dateComparaison) { this.dateComparaison = dateComparaison; }

//     // ✅ Méthodes utilitaires (toString, equals, hashCode)
//     @Override
//     public String toString() {
//         return "Comparaison{" +
//                 "id='" + id + '\'' +
//                 ", ticketId='" + ticketId + '\'' +
//                 ", tirageId='" + tirageId + '\'' +
//                 ", numerosTrouves=" + numerosTrouves +
//                 ", chanceGagnante=" + chanceGagnante +
//                 ", rang=" + rang +
//                 ", gain=" + gain +
//                 ", statut='" + statut + '\'' +
//                 ", dateComparaison=" + dateComparaison +
//                 '}';
//     }
// }
