# 📦 GitHub Packages - Loto API Docker Image

📍 **Available on GitHub Packages:**
➝ [**GitHub Packages - SDINAHET**](https://github.com/SDINAHET?tab=packages)

📍 **Repository Packages Section:**
➝ [**Loto API - GitHub Packages**](https://github.com/SDINAHET/Loto_API/packages)

---

## 📥 Pull and Run the Docker Containers Locally

### 🔹 **Backend (Spring Boot - Port 8082)**
To pull and run the backend container:
```bash
docker pull ghcr.io/sdinahet/loto_api:latest
docker run -d -p 8082:8082 ghcr.io/sdinahet/loto_api:latest
```
The API will be available at:
➡️ **`http://localhost:8082/swagger-ui/index.html`**

🔗 **Dockerfile Backend:** [Dockerfile.backend](https://github.com/SDINAHET/Loto_API/blob/main/Dockerfile.backend)

---

### 🔹 **Frontend (Static Web - Port 5500)**
If your frontend is also packaged as a Docker image, you can pull and run it like this:
```bash
docker pull ghcr.io/sdinahet/loto_front:latest
docker run -d -p 5500:5500 ghcr.io/sdinahet/loto_front:latest
```
The frontend will be available at:
➡️ **`http://127.0.0.1:5500/`**

🔗 **Dockerfile Frontend:** [Dockerfile.frontend](https://github.com/SDINAHET/Loto_API/blob/main/Dockerfile.frontend)

---

### 🔄 **Run Backend & Frontend Together with Docker Compose**
If you have a `docker-compose.yml` file configured for both services, run:
```bash
docker-compose up -d
```

🔗 **Docker Compose File:** [compose.yaml](https://github.com/SDINAHET/Loto_API/blob/main/compose.yaml)

---

## 🔧 **Additional Docker Commands**

### 🛑 Stop Running Containers
To stop and remove running containers:
```bash
docker-compose down
```

### 🚀 **Rebuild and Restart Containers**
To rebuild images and restart the containers:
```bash
docker-compose up -d --build
```

### 📜 **View Running Containers**
```bash
docker ps
```

### 🗑️ **Remove Unused Docker Images**
```bash
docker system prune -a
```

---

🚀 Your **Loto API** is now running locally! 🎰🔥

