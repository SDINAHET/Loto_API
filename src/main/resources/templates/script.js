async function fetchResults() {
    const date = document.getElementById("searchDate").value;
    const combination = document.getElementById("searchCombination").value;
    let url = `http://localhost:8080/api/results?date=${date}&combination=${combination}`;

    try {
        const response = await fetch(url);
        const data = await response.json();
        displayResults(data);
    } catch (error) {
        console.error("Erreur lors de la récupération des résultats", error);
    }
}

function displayResults(results) {
    const resultsContainer = document.getElementById("results");
    resultsContainer.innerHTML = "";

    results.forEach(result => {
        let numbers = result.combinaisonGagnanteEnOrdreCroissant.split("+");
        let mainNumbers = numbers[0].split("-").map(num => `<img src='blue_ball.png' alt='${num}'>`).join(' ');
        let bonusNumber = `<img src='red_ball.png' alt='${numbers[1]}'>`;

        resultsContainer.innerHTML += `
            <div class="result">
                <h2>${result.jourDeTirage} - ${result.dateDeTirage}</h2>
                <p>Numéros gagnants : ${mainNumbers} ${bonusNumber}</p>
            </div>
        `;
    });
}

document.getElementById("searchDate").addEventListener("change", fetchResults);
document.getElementById("searchCombination").addEventListener("input", fetchResults);
