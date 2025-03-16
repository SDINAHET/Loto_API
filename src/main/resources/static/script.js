// document.addEventListener("DOMContentLoaded", async () => {
//   await loadAvailableDates();
//   updateClock();
//   updateCountdown();
//   setInterval(updateClock, 1000);
//   setInterval(updateCountdown, 1000);
// });

// // 🟡 Charger les dates disponibles depuis MongoDB pour le menu déroulant
// async function loadAvailableDates() {
//   try {
//       const response = await fetch("http://localhost:8080/api/tirages/dates");
//       const dates = await response.json();

//       const startSelect = document.getElementById("startSelect");
//       const endSelect = document.getElementById("endSelect");

//       dates.forEach(date => {
//           let option = new Option(date, date);
//           startSelect.add(option);
//           endSelect.add(option.cloneNode(true));
//       });

//   } catch (error) {
//       console.error("Erreur de chargement des dates :", error);
//   }
// }

// // 🟢 Requête API pour récupérer les tirages entre les dates sélectionnées
// document.getElementById("searchForm").addEventListener("submit", async function(event) {
//   event.preventDefault();

//   const startDate = document.getElementById("startSelect").value;
//   const endDate = document.getElementById("endSelect").value;

//   if (!startDate || !endDate) {
//       alert("Veuillez sélectionner une période valide.");
//       return;
//   }

//   try {
//       const response = await fetch(`http://localhost:8080/api/tirages?startDate=${startDate}&endDate=${endDate}`);
//       const data = await response.json();

//       displayResults(data);
//   } catch (error) {
//       console.error("Erreur lors de la récupération des données :", error);
//   }
// });

// // 🟡 Affichage des résultats
// function displayResults(results) {
//   const resultsList = document.getElementById("resultsList");
//   resultsList.innerHTML = "";

//   if (results.length === 0) {
//       resultsList.innerHTML = "<li>Aucun tirage trouvé pour cette période.</li>";
//       return;
//   }

//   results.forEach(tirage => {
//       const listItem = document.createElement("li");
//       listItem.innerHTML = `
//           <strong>${tirage.dateDeTirage}</strong> -
//           Numéros : ${tirage.boule1}, ${tirage.boule2}, ${tirage.boule3}, ${tirage.boule4}, ${tirage.boule5} -
//           Numéro Chance : ${tirage.numeroChance}
//       `;
//       resultsList.appendChild(listItem);
//   });
// }

// // 🕒 Mise à jour de l'horloge en temps réel
// function updateClock() {
//   const clock = document.getElementById("clock");
//   const now = new Date();
//   clock.innerHTML = `🕒 Heure actuelle : ${now.toLocaleTimeString()}`;
// }

// // ⏳ Compte à rebours jusqu’au prochain tirage
// function updateCountdown() {
//   const countdown = document.getElementById("countdown");
//   const now = new Date();
//   const nextDraw = getNextDrawDate(now);

//   const diff = nextDraw - now;

//   if (diff <= 0) {
//       countdown.innerHTML = "🎉 Le tirage a lieu maintenant !";
//   } else {
//       const hours = Math.floor(diff / (1000 * 60 * 60));
//       const minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
//       const seconds = Math.floor((diff % (1000 * 60)) / 1000);
//       countdown.innerHTML = `⏳ Prochain tirage dans : ${hours}h ${minutes}m ${seconds}s`;
//   }
// }

// // 📆 Trouver le prochain tirage (Lundi, Mercredi, Samedi à 20h)
// function getNextDrawDate(now) {
//   const drawDays = [1, 3, 6]; // Lundi (1), Mercredi (3), Samedi (6)
//   let nextDraw = new Date(now);

//   do {
//       nextDraw.setDate(nextDraw.getDate() + 1);
//   } while (!drawDays.includes(nextDraw.getDay()));

//   nextDraw.setHours(20, 0, 0, 0);
//   return nextDraw;
// }
