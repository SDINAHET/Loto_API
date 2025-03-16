# import requests
# import uuid
# from datetime import datetime, timedelta

# # ✅ Configuration de l'API
# API_URL = "http://localhost:8082/api/tickets"
# USER_ID = "4548597d-85e8-4c6b-b1db-029a0e0cf276"

# # ✅ Ton token JWT (⚠️ Remplace "TON_TOKEN" par une variable sécurisée)
# JWT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MjBAaGJuYi5jb20iLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNzQxNzA4MzcyLCJleHAiOjE3NDE3OTQ3NzJ9._F4UixdluMEI5w4aFwompn5KJ3AMQfXw5AO3LMiPghs"

# # ✅ Fonction pour générer toutes les dates de tirage (lundi, mercredi, samedi)
# def generate_draw_dates(start_year=2019, end_year=2026):
#     draw_dates = []
#     start_date = datetime(start_year, 1, 1)
#     end_date = datetime(end_year, 12, 31)

#     while start_date <= end_date:
#         if start_date.weekday() in [0, 2, 5]:  # Lundi (0), Mercredi (2), Samedi (5)
#             draw_dates.append(start_date.strftime("%Y-%m-%d"))
#         start_date += timedelta(days=1)

#     return draw_dates

# # ✅ Fonction pour envoyer un ticket via POST avec le token JWT
# def post_ticket(draw_date):
#     ticket_data = {
#         "id": str(uuid.uuid4()),
#         "userId": USER_ID,
#         "numbers": "4-17-23-38-44",
#         "chanceNumber": "3",
#         "drawDate": draw_date,
#         "createdAt": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
#         "updatedAt": datetime.now().strftime("%Y-%m-%d %H:%M:%S")
#     }

#     headers = {
#         "Authorization": f"Bearer {JWT_TOKEN}",  # ✅ Ajout du token JWT dans le header
#         "Content-Type": "application/json"
#     }

#     try:
#         response = requests.post(API_URL, json=ticket_data, headers=headers)
#         if response.status_code in [200, 201]:
#             print(f"✅ Ticket ajouté pour {draw_date}")
#         else:
#             print(f"❌ Erreur {response.status_code} pour {draw_date} : {response.text}")
#     except Exception as e:
#         print(f"⚠️ Erreur lors de l'envoi du ticket pour {draw_date} : {e}")

# # ✅ Exécution du script
# if __name__ == "__main__":
#     draw_dates = generate_draw_dates()
#     print(f"Total des tirages générés : {len(draw_dates)}")

#     for date in draw_dates:
#         post_ticket(date)

# import requests
# import uuid
# import os
# import time
# from datetime import datetime, timedelta
# from requests.adapters import HTTPAdapter
# from requests.packages.urllib3.util.retry import Retry

# # ✅ Configuration de l'API
# API_URL = "http://localhost:8082/api/tickets"
# USER_ID = "4548597d-85e8-4c6b-b1db-029a0e0cf276"

# # ✅ Récupérer le token JWT depuis une variable d'environnement (meilleure sécurité)
# JWT_TOKEN = os.getenv("JWT_TOKEN", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MjBAaGJuYi5jb20iLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNzQxNzA4MzcyLCJleHAiOjE3NDE3OTQ3NzJ9._F4UixdluMEI5w4aFwompn5KJ3AMQfXw5AO3LMiPghs")  # ⚠️ Mets ton token ici si non défini

# # ✅ Vérification que le token est bien fourni
# if JWT_TOKEN == "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MjBAaGJuYi5jb20iLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNzQxNzA4MzcyLCJleHAiOjE3NDE3OTQ3NzJ9._F4UixdluMEI5w4aFwompn5KJ3AMQfXw5AO3LMiPghs":
#     print("⚠️ ERREUR : Le token JWT est manquant ! Définis la variable d'environnement JWT_TOKEN.")
#     exit(1)

# # ✅ Configuration des headers pour l'authentification JWT
# HEADERS = {
#     "Authorization": f"Bearer {JWT_TOKEN}",
#     "Content-Type": "application/json"
# }

# # ✅ Création d'une session HTTP avec retry automatique
# session = requests.Session()
# retry_strategy = Retry(
#     total=3,  # Nombre maximum de tentatives
#     backoff_factor=1,  # Temps d'attente avant retry (exponentiel)
#     status_forcelist=[500, 502, 503, 504]  # Codes d'erreur à re-tenter
# )
# adapter = HTTPAdapter(max_retries=retry_strategy)
# session.mount("http://", adapter)
# session.mount("https://", adapter)

# # ✅ Fonction pour générer les dates de tirage (lundi, mercredi, samedi)
# def generate_draw_dates(start_year=2019, end_year=2026):
#     draw_dates = []
#     start_date = datetime(start_year, 1, 1)
#     end_date = datetime(end_year, 12, 31)

#     while start_date <= end_date:
#         if start_date.weekday() in [0, 2, 5]:  # Lundi (0), Mercredi (2), Samedi (5)
#             draw_dates.append(start_date.strftime("%Y-%m-%d"))
#         start_date += timedelta(days=1)

#     return draw_dates

# # ✅ Fonction pour envoyer un ticket via POST avec gestion d'erreurs
# def post_ticket(draw_date):
#     ticket_data = {
#         "id": str(uuid.uuid4()),  # Génération d'un UUID unique
#         "userId": USER_ID,
#         "numbers": "4-17-23-38-44",
#         "chanceNumber": "3",
#         "drawDate": draw_date,
#         "createdAt": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
#         "updatedAt": datetime.now().strftime("%Y-%m-%d %H:%M:%S")
#     }

#     try:
#         response = session.post(API_URL, json=ticket_data, headers=HEADERS)

#         if response.status_code in [200, 201]:
#             print(f"✅ Ticket ajouté pour {draw_date}")
#         elif response.status_code == 403:
#             print(f"⛔ ERREUR 403 : Accès interdit pour {draw_date} - Vérifie les permissions !")
#         elif response.status_code == 401:
#             print(f"🚨 ERREUR 401 : Token JWT invalide ou expiré pour {draw_date} !")
#         else:
#             print(f"❌ Erreur {response.status_code} pour {draw_date} : {response.text}")

#     except requests.exceptions.RequestException as e:
#         print(f"⚠️ Erreur réseau pour {draw_date} : {e}")
#         time.sleep(2)  # Attendre avant de réessayer

# # ✅ Exécution du script
# if __name__ == "__main__":
#     draw_dates = generate_draw_dates()
#     print(f"🎯 Total des tirages générés : {len(draw_dates)}")

#     for date in draw_dates:
#         post_ticket(date)

#     print("🚀 Tous les tickets ont été envoyés !")

import sqlite3
import uuid
from datetime import datetime, timedelta

# ✅ Nom de la base de données SQLite
DB_NAME = "loto.db"

# ✅ Connexion à SQLite
conn = sqlite3.connect(DB_NAME)
cursor = conn.cursor()

# ✅ Création de la table si elle n'existe pas
cursor.execute("""
CREATE TABLE IF NOT EXISTS tickets (
    id TEXT PRIMARY KEY,
    user_id TEXT,
    numbers TEXT,
    chance_number TEXT,
    draw_date TEXT,
    created_at TEXT,
    updated_at TEXT
);
""")
conn.commit()

# ✅ Génération des dates de tirage (lundi, mercredi, samedi)
def generate_draw_dates(start_year=2019, end_year=2026):
    draw_dates = []
    start_date = datetime(start_year, 1, 1)
    end_date = datetime(end_year, 12, 31)

    while start_date <= end_date:
        if start_date.weekday() in [0, 2, 5]:  # Lundi (0), Mercredi (2), Samedi (5)
            draw_dates.append(start_date.strftime("%Y-%m-%d"))
        start_date += timedelta(days=1)

    return draw_dates

# ✅ Insertion des tickets dans la base SQLite
def insert_tickets():
    draw_dates = generate_draw_dates()
    print(f"🎯 Total des tirages générés : {len(draw_dates)}")

    # ✅ Début d'une transaction pour accélérer les insertions
    conn.execute("BEGIN TRANSACTION")
    for draw_date in draw_dates:
        ticket_data = (
            str(uuid.uuid4()),  # Génération d'un UUID unique
            "4548597d-85e8-4c6b-b1db-029a0e0cf276",  # User ID
            "4-17-23-38-44",  # Nombres joués
            "3",  # Numéro chance
            draw_date,  # Date du tirage
            datetime.now().strftime("%Y-%m-%d %H:%M:%S"),  # Date de création
            datetime.now().strftime("%Y-%m-%d %H:%M:%S")   # Date de mise à jour
        )

        cursor.execute("""
            INSERT INTO tickets (id, user_id, numbers, lucky_number, draw_date, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """, ticket_data)

    # ✅ Validation des insertions
    conn.commit()
    print("🚀 Tous les tickets ont été insérés dans SQLite !")

# ✅ Exécuter l'insertion
if __name__ == "__main__":
    insert_tickets()
    conn.close()
