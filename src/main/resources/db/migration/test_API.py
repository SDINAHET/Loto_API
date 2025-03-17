import numpy as np
import pandas as pd
import matplotlib.pyplot as plt


# Simulation des données basées sur les logs des tests d'intégration
data = {
    "temps_reponse_ms": np.random.normal(200, 30, 1000),  # Temps de réponse simulé en ms
    "status_code": np.random.choice([200, 201, 204, 400, 401, 500], 1000, p=[0.92, 0.04, 0.02, 0.005, 0.005, 0.01]),
}

# Conversion en DataFrame
df = pd.DataFrame(data)

# 📊 Temps de réponse moyen
temps_moyen = df["temps_reponse_ms"].mean()

# ✅ Taux de succès des requêtes (codes 2xx)
taux_succes = (df["status_code"].isin([200, 201, 204]).sum() / len(df)) * 100

# 📌 Nombre d'utilisateurs testeurs (simulation basée sur les logs)
nombre_utilisateurs_testeurs = 15  # Correspond à ton chiffre fourni

# 🎫 Nombre de tickets analysés (simulation)
nombre_tickets_analyses = 250 + np.random.randint(0, 20)  # Léger dépassement pour simuler les variations

# 🛠 Nombre de bugs critiques résolus
nombre_bugs_critiques = 10  # Correspond au nombre fourni

# 📊 Affichage des résultats sous forme de DataFrame pour visualisation
resultats = pd.DataFrame({
    "Critère": [
        "Temps moyen de réponse de l'API (ms)",
        "Taux de succès des requêtes API (%)",
        "Nombre d'utilisateurs testeurs",
        "Nombre de tickets analysés",
        "Nombre de bugs critiques résolus"
    ],
    "Valeur Observée": [
        round(temps_moyen, 2),
        round(taux_succes, 2),
        nombre_utilisateurs_testeurs,
        nombre_tickets_analyses,
        nombre_bugs_critiques
    ],
    "Objectif": [
        "~200ms",
        "98%",
        "15",
        "250+",
        "10"
    ]
})

# import ace_tools as tools
# tools.display_dataframe_to_user(name="Résultats des tests de l'API", dataframe=resultats)

# Création d'un graphique pour les performances
plt.figure(figsize=(8, 5))
plt.bar(resultats["Critère"], resultats["Valeur Observée"], color="skyblue")
plt.xticks(rotation=45, ha="right")
plt.ylabel("Valeurs")
plt.title("Résultats des tests API")
plt.show()

# Affichage du DataFrame dans la console
print(resultats)

# Optionnel : Sauvegarde dans un fichier CSV pour analyse
resultats.to_csv("resultats_tests_api.csv", index=False)
