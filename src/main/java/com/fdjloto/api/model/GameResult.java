// package com.fdjloto.api.model;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import java.time.LocalDate;
// import java.util.List;
// import java.util.UUID;

// @Document(collection = "loto_results")
// public class GameResult {

//     @Id
//     private UUID id;
//     private LocalDate drawDate;
//     private List<Integer> winningNumbers;
//     private int luckyNumber;

//     public GameResult() {
//         this.id = UUID.randomUUID();
//     }

//     public GameResult(LocalDate drawDate, List<Integer> winningNumbers, int luckyNumber) {
//         this.id = UUID.randomUUID();
//         this.drawDate = drawDate;
//         this.winningNumbers = winningNumbers;
//         this.luckyNumber = luckyNumber;
//     }

//     public UUID getId() {
//         return id;
//     }

//     public void setId(UUID id) {
//         this.id = id;
//     }

//     public LocalDate getDrawDate() {
//         return drawDate;
//     }

//     public void setDrawDate(LocalDate drawDate) {
//         this.drawDate = drawDate;
//     }

//     public List<Integer> getWinningNumbers() {
//         return winningNumbers;
//     }

//     public void setWinningNumbers(List<Integer> winningNumbers) {
//         this.winningNumbers = winningNumbers;
//     }

//     public int getLuckyNumber() {
//         return luckyNumber;
//     }

//     public void setLuckyNumber(int luckyNumber) {
//         this.luckyNumber = luckyNumber;
//     }
// }
