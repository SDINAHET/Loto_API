// mongo-init.js

print("🔹 Initialisation de la base de données loto_database...");

// Sélection de la base de données
db = db.getSiblingDB("loto_database");

// Création de la collection historique avec un index sur dateDeTirage
db.historique.createIndex({ dateDeTirage: 1 }, { unique: true });

// Insertion de données initiales (exemple)
db.historique.insertMany([
    {
        anneeNumeroDeTirage: 25031,
        jourDeTirage: "MERCREDI",
        dateDeTirage: ISODate("2025-03-11T23:00:00Z"),
        dateDeForclusion: "10/06/2025",
        boule1: 21,
        boule2: 10,
        boule3: 24,
        boule4: 32,
        boule5: 16,
        numeroChance: 7,
        combinaisonGagnante: "10-16-21-24-32+7",
        nombreDeGagnantAuRang1: 0
    }
]);

print("✅ Base de données loto_database initialisée avec succès !");
