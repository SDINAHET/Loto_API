# 🎰 Loto API - Gestion et Calcul des Gains

## 📌 Description du Projet

**Loto API** est une application permettant aux utilisateurs de gérer leurs tickets de loterie et de calculer automatiquement leurs gains en comparant leurs numéros avec les résultats officiels. Le projet inclut une **API sécurisée** avec authentification JWT, un système de gestion des tickets et une base de données hybride **SQL/NoSQL**.

## 🏗️ Architecture du Projet

L'application suit une architecture **Full-Stack** :

- **Frontend** : Interface web simple basée sur HTML, CSS et JavaScript.
- **Backend** : API REST développée avec **Spring Boot 3.4.2**.
- **Base de données** :
  - **SQLite** : Stockage des utilisateurs et des tickets.
  - **MongoDB** : Stockage des résultats historiques.
- **Automatisation** : Un script récupère et met à jour les résultats des tirages à partir des fichiers sources.

## 🚀 Fonctionnalités Principales

- ✔️ **Gestion des utilisateurs** (Inscription, Connexion, Authentification JWT).
- ✔️ **Soumission et gestion des tickets** (CRUD des tickets).
- ✔️ **Calcul automatique des gains**.
- ✔️ **Historique des résultats enregistrés en MongoDB**.
- ✔️ **Visualisation des résultats sous forme de graphiques**.
- ✔️ **Mise à jour automatique des résultats via un script**.
- ✔️ **Sécurisation des endpoints API avec Spring Security & JWT**.

## 🏗️ Installation & Configuration

### 1️⃣ Prérequis

- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Spring Boot 3.4.2](https://spring.io/projects/spring-boot)
- [SQLite 3.x](https://www.sqlite.org/download.html)
- [MongoDB](https://www.mongodb.com/try/download/community)
- [Node.js 18+](https://nodejs.org/)
- [Git](https://git-scm.com/)

### 2️⃣ Cloner le projet
```bash
git clone https://github.com/SDINAHET/Loto_API.git
cd Loto_API
```

### 3️⃣ Configuration de la base de données

#### SQLite (Utilisateurs & Tickets)

Configurer `application.properties` :
```properties
spring.datasource.url=jdbc:sqlite:loto.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.username=
spring.datasource.password=
spring.datasource.hikari.maximum-pool-size=5
```

#### MongoDB (Résultats des tirages)
Démarrer MongoDB et configurer `application.properties` :
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/loto_results
```

### 4️⃣ Lancer le Backend (Spring Boot)
```bash
mvn install
mvn spring-boot:run
```
L'API est disponible sur `http://localhost:8082/api`

### 5️⃣ Lancer le Frontend
L'interface web peut être servie via **Live Server** sur VSCode. Ouvrir `index.html` et démarrer Live Server pour accéder à `http://127.0.0.1:5500/src/main/resources/static/index.html`.

---

## 🔗 Endpoints de l'API

### 📌 Utilisateurs
| Méthode | Endpoint | Description |
|---------|---------|-------------|
| POST | `/api/users/register` | Inscription utilisateur |
| POST | `/api/auth/login` | Connexion et génération du JWT |
| GET  | `/api/auth/me` | Récupération des infos utilisateur |
| GET  | `/api/auth/me/firstname` | Récupérer le prénom de l'utilisateur |
| POST | `/api/auth/logout` | Déconnexion (suppression du JWT) |
| GET  | `/api/users` | Récupération de la liste des utilisateurs (Admin uniquement) |
| GET  | `/api/users/{id}` | Récupération des informations d’un utilisateur |
| DELETE | `/api/users/{id}` | Suppression d’un utilisateur (Admin uniquement) |
| PUT | `/api/users/{id}` | Mise à jour des informations utilisateur |

### 🎟️ Gestion des Tickets
| Méthode | Endpoint | Description |
|---------|---------|-------------|
| POST | `/api/tickets` | Ajouter un ticket |
| GET | `/api/tickets` | Lister les tickets de l'utilisateur |
| GET | `/api/tickets/{id}` | Détails d’un ticket |
| DELETE | `/api/tickets/{id}` | Supprimer un ticket |
| PUT | `/api/tickets/{id}` | Modifier un ticket |

### 🎰 Résultats des tirages
| Méthode | Endpoint | Description |
|---------|---------|-------------|
| GET | `/api/tirages` | Lister tous les tirages |
| GET | `/api/tirages/dates` | Récupérer les dates des tirages disponibles |
| GET | `/api/tirages?startDate={}&endDate={}` | Tirages entre deux dates |

### 🏆 Gestion des Gains
| Méthode | Endpoint | Description |
|---------|---------|-------------|
| GET | `/api/gains/calculate` | Calculer les gains des tickets |
| GET | `/api/gains` | Récupérer les gains enregistrés |
| GET | `/api/gains/{ticketId}` | Détails des gains d'un ticket |

### 📊 Historique des résultats
| Méthode | Endpoint | Description |
|---------|---------|-------------|
| GET | `/api/historique/last20` | Récupérer les 6 derniers résultats de l'historique des résultats officiel |
| GET | `/api/historique/last20/Detail/tirage/{date}` | Détails d’un tirage par date |
| GET | `/api/historique/last20/Detail/tirages?startDate={}&endDate={}` | Historique entre deux dates |

---

## 📡 Déploiement
Le projet peut être déployé sur **Alwaysdata, AWS ou un VPS** avec Docker.

### 📌 Déploiement avec Docker
Créer un `Dockerfile` pour le backend :
```dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/loto-api.jar /app/app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
```
Créer un fichier `docker-compose.yml` pour orchestrer l'application :
```yaml
version: '3.8'
services:
  loto-api:
    build: .
    ports:
      - "8082:8082"
    depends_on:
      - mongodb
  mongodb:
    image: mongo:latest
    ports:
      - "27017:27017"
```
Lancer les conteneurs :
```bash
docker-compose up -d
```

---

## 🚀 Roadmap & Améliorations Futures
✔️ **Ajout d’analyses avancées sur les résultats**
✔️ **Support de nouveaux jeux (Euromillions, Keno, etc.)**
✔️ **Notifications pour les résultats gagnants**
✔️ **Ajout d’un mode premium avec des fonctionnalités avancées**

---

## 👨‍💻 Auteur & Contributions
Développé par [@SDINAHET](https://github.com/SDINAHET)
Contributions bienvenues ! Ouvrez une issue ou proposez une PR 🚀

---

## 📜 Licence
Projet sous licence **MIT** - Libre d'utilisation et de modification.

---

🎉 **Merci d'utiliser Loto API !** Bonne chance 🍀



# 🎰 Loto API - Management and Calculation of Winnings

## 📌 Project Description

**Loto API** is an application that allows users to manage their lottery tickets and automatically calculate their winnings by comparing their numbers with official results. The project includes a **secure API** with JWT authentication, a ticket management system, and a hybrid **SQL/NoSQL** database.

## 🏗️ Project Architecture

The application follows a **Full-Stack** architecture:

- **Frontend**: Simple web interface based on HTML, CSS, and JavaScript.
- **Backend**: REST API developed with **Spring Boot 3.4.2**.
- **Database**:
  - **SQLite**: Stores users and tickets.
  - **MongoDB**: Stores historical results.
- **Automation**: A script retrieves and updates draw results from source files.

## 🚀 Key Features

- ✔️ **User management** (Registration, Login, JWT Authentication).
- ✔️ **Ticket submission and management** (CRUD operations on tickets).
- ✔️ **Automatic calculation of winnings**.
- ✔️ **Historical results stored in MongoDB**.
- ✔️ **Visualization of results through charts**.
- ✔️ **Automatic updates of results via a script**.
- ✔️ **API endpoint security with Spring Security & JWT**.

## 🏗️ Installation & Configuration

### 1️⃣ Prerequisites

- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Spring Boot 3.4.2](https://spring.io/projects/spring-boot)
- [SQLite 3.x](https://www.sqlite.org/download.html)
- [MongoDB](https://www.mongodb.com/try/download/community)
- [Node.js 18+](https://nodejs.org/)
- [Git](https://git-scm.com/)

### 2️⃣ Clone the Project
```bash
git clone https://github.com/SDINAHET/Loto_API.git
cd Loto_API
```

### 3️⃣ Database Configuration

#### SQLite (Users & Tickets)

Configure `application.properties`:
```properties
spring.datasource.url=jdbc:sqlite:loto.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.username=
spring.datasource.password=
spring.datasource.hikari.maximum-pool-size=5
```

#### MongoDB (Draw Results)
Start MongoDB and configure `application.properties`:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/loto_results
```

### 4️⃣ Start the Backend (Spring Boot)
```bash
mvn install
mvn spring-boot:run
```
The API is available at `http://localhost:8082/api`

### 5️⃣ Start the Frontend
The web interface can be served using **Live Server** on VSCode. Open `index.html` and start Live Server to access `http://127.0.0.1:5500/src/main/resources/static/index.html`.

---

## 🔗 API Endpoints

### 📌 Users
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/api/users/register` | User registration |
| POST | `/api/auth/login` | Login and JWT generation |
| GET  | `/api/auth/me` | Retrieve user info |
| GET  | `/api/auth/me/firstname` | Retrieve user's first name |
| POST | `/api/auth/logout` | Logout (JWT deletion) |
| GET  | `/api/users` | Retrieve list of users (Admin only) |
| GET  | `/api/users/{id}` | Retrieve a user's details |
| DELETE | `/api/users/{id}` | Delete a user (Admin only) |
| PUT | `/api/users/{id}` | Update user information |

### 🎟️ Ticket Management
| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/api/tickets` | Add a ticket |
| GET | `/api/tickets` | List user tickets |
| GET | `/api/tickets/{id}` | Ticket details |
| DELETE | `/api/tickets/{id}` | Delete a ticket |
| PUT | `/api/tickets/{id}` | Modify a ticket |

### 🎰 Draw Results
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/api/tirages` | List all draws |
| GET | `/api/tirages/dates` | Retrieve available draw dates |
| GET | `/api/tirages?startDate={}&endDate={}` | Draws between two dates |

### 🏆 Winnings Management
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/api/gains/calculate` | Calculate ticket winnings |
| GET | `/api/gains` | Retrieve recorded winnings |
| GET | `/api/gains/{ticketId}` | Details of a ticket's winnings |

### 📊 Results History
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/api/historique/last20` | Retrieve the last 20 results |
| GET | `/api/historique/last20/Detail/tirage/{date}` | Details of a draw by date |
| GET | `/api/historique/last20/Detail/tirages?startDate={}&endDate={}` | History between two dates |

---

## 📡 Deployment
The project can be deployed on **Alwaysdata, AWS, or a VPS** using Docker.

### 📌 Deployment with Docker
Create a `Dockerfile` for the backend:
```dockerfile
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/loto-api.jar /app/app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
```
Create a `docker-compose.yml` file to orchestrate the application:
```yaml
version: '3.8'
services:
  loto-api:
    build: .
    ports:
      - "8082:8082"
    depends_on:
      - mongodb
  mongodb:
    image: mongo:latest
    ports:
      - "27017:27017"
```
Start the containers:
```bash
docker-compose up -d
```

---

## 🚀 Roadmap & Future Improvements
✔️ **Addition of advanced analysis on results**
✔️ **Support for new games (Euromillions, Keno, etc.)**
✔️ **Notifications for winning results**
✔️ **Addition of a premium mode with advanced features**

---

## 👨‍💻 Author & Contributions
Developed by [@SDINAHET](https://github.com/SDINAHET)
Contributions are welcome! Open an issue or submit a PR 🚀

---

## 📜 License
Project under **MIT License** - Free to use and modify.

---

🎉 **Thank you for using Loto API!** Good luck 🍀
