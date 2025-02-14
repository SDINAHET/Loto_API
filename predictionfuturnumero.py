from pymongo import MongoClient
import pandas as pd
import numpy as np
from collections import Counter

# Connexion à MongoDB
try:
    client = MongoClient("mongodb://localhost:27017/")
    db = client["loto_database"]
    collection = db["historique"]
    print("✅ Connexion MongoDB réussie")
except Exception as e:
    print(f"❌ Erreur de connexion à MongoDB : {e}")
    exit()

# Récupération des tirages
tirages = list(collection.find({}, {"boule1": 1, "boule2": 1, "boule3": 1, "boule4": 1, "boule5": 1, "numeroChance": 1, "_id": 0}))

# Vérification des données
if not tirages:
    print("❌ Aucune donnée trouvée dans la collection.")
    exit()

# Transformation en DataFrame
df = pd.DataFrame(tirages)

# Vérification de la structure du DataFrame
if df.empty:
    print("❌ Le DataFrame est vide. Vérifie tes données MongoDB.")
    exit()

# Calcul des fréquences des numéros principaux
all_numbers = df[["boule1", "boule2", "boule3", "boule4", "boule5"]].values.flatten()
number_counts = Counter(all_numbers)

# Calcul des fréquences du numéro chance
chance_counts = Counter(df["numeroChance"])

# Sélection des 5 numéros les plus fréquents
probable_numbers = [int(num) for num, count in number_counts.most_common(5)]
probable_chance = int(max(chance_counts, key=chance_counts.get))

# Calcul des écarts-types (avec conversion et clés en string)
std_dev_numbers = {
    str(int(num)): float(np.std(
        [tirage[col] for tirage in tirages for col in ["boule1", "boule2", "boule3", "boule4", "boule5"] if tirage[col] == num]
    )) for num in probable_numbers
}

std_dev_chance = float(np.std(df["numeroChance"]))

# Mise à jour des prévisions dans MongoDB
prediction = {
    "probable_numbers": probable_numbers,
    "probable_chance": probable_chance,
    "std_dev_numbers": std_dev_numbers,  # ✅ Les clés sont maintenant en string
    "std_dev_chance": std_dev_chance
}

# Insertion / Mise à jour
db["predictions"].update_one({}, {"$set": prediction}, upsert=True)

# Affichage des résultats
print(f"📊 Numéros probables : {probable_numbers}")
print(f"🎯 Numéro chance probable : {probable_chance}")
print(f"📈 Écarts-types des numéros : {std_dev_numbers}")
print(f"📊 Écart-type du numéro chance : {std_dev_chance:.2f}")
