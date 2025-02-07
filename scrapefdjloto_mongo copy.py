import requests
import zipfile
import io
import pandas as pd
from pymongo import MongoClient

# Configuration
URL = "https://www.sto.api.fdj.fr/anonymous/service-draw-info/v3/documentations/1a2b3c4d-9876-4562-b3fc-2c963f66afp6"
MONGO_URI = "mongodb://localhost:27017/"
DB_NAME = "loto_database"
COLLECTION_NAME = "historique"

# Étape 1: Télécharger le fichier ZIP
response = requests.get(URL, stream=True)
if response.status_code == 200:
    zip_file = zipfile.ZipFile(io.BytesIO(response.content))
    csv_filename = [f for f in zip_file.namelist() if f.endswith(".csv")][0]

    # Étape 2: Extraire le CSV
    with zip_file.open(csv_filename) as csv_file:
        df = pd.read_csv(csv_file, delimiter=';', encoding='utf-8')

    # Vérification des données
    print("Aperçu des premières lignes du CSV:")
    print(df.head())

    # Étape 3: Insérer les données dans MongoDB
    client = MongoClient(MONGO_URI, serverSelectionTimeoutMS=5000)
    try:
        client.server_info()  # Vérifier la connexion à MongoDB
        print("Connexion MongoDB réussie !")
    except Exception as e:
        print(f"Erreur de connexion à MongoDB : {e}")

    db = client[DB_NAME]
    collection = db[COLLECTION_NAME]

    # Conversion du DataFrame en dictionnaire et insertion
    records = df.to_dict(orient='records')
    if records:
        collection.insert_many(records)
        print(f"{len(records)} documents insérés avec succès dans {DB_NAME}.{COLLECTION_NAME}")
    else:
        print("Le fichier CSV ne contient aucune donnée à insérer.")
else:
    print("Échec du téléchargement du fichier ZIP.")
