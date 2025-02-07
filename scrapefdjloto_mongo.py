import requests
import zipfile
import io
import pandas as pd
from pymongo import MongoClient
from flask import Flask, render_template_string

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

    # Étape 3: Effacer et remplacer les données dans MongoDB
    client = MongoClient(MONGO_URI, serverSelectionTimeoutMS=5000)
    try:
        client.server_info()  # Vérifier la connexion à MongoDB
        print("Connexion MongoDB réussie !")
    except Exception as e:
        print(f"Erreur de connexion à MongoDB : {e}")

    db = client[DB_NAME]
    collection = db[COLLECTION_NAME]

    # Supprimer l'ancienne collection et insérer les nouvelles données
    collection.drop()
    records = df.to_dict(orient='records')
    if records:
        collection.insert_many(records)
        print(f"{len(records)} documents insérés avec succès dans {DB_NAME}.{COLLECTION_NAME}")
    else:
        print("Le fichier CSV ne contient aucune donnée à insérer.")
else:
    print("Échec du téléchargement du fichier ZIP.")

# Flask App for displaying the results
app = Flask(__name__)

@app.route('/')
def display_results():
    client = MongoClient(MONGO_URI)
    db = client[DB_NAME]
    collection = db[COLLECTION_NAME]
    results = list(collection.find({}, {"_id": 0}))  # Fetch all documents without _id

    table_html = """
    <html>
    <head><title>Historique du Loto</title></head>
    <body>
    <h1>Historique des Résultats du Loto depuis 2019</h1>
    <table border="1">
    <tr>{}</tr>
    {}
    </table>
    </body>
    </html>
    """

    if results:
        headers = "".join(f"<th>{key}</th>" for key in results[0].keys())
        rows = "".join("<tr>" + "".join(f"<td>{val}</td>" for val in record.values()) + "</tr>" for record in results)
    else:
        headers = "<th>No Data Available</th>"
        rows = "<tr><td>No Data</td></tr>"

    return render_template_string(table_html.format(headers, rows))

if __name__ == "__main__":
    app.run(debug=True)
