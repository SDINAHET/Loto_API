<!-- # 📦 GitHub Packages - Loto API Docker Image

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

or if you want:
```bash
docker pull ghcr.io/sdinahet/loto_api:latest && docker pull ghcr.io/sdinahet/loto_front:latest && docker-compose up -d
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

🚀 Your **Loto API** is now running locally! 🎰🔥 -->


# 📦 GitHub Packages - Loto API Docker Images

## 📌 Available on GitHub Packages:
🔗 [**GitHub Packages - SDINAHET**](https://github.com/SDINAHET?tab=packages)

## 📌 Repository Packages Section:
🔗 [**Loto API - GitHub Packages**](https://github.com/SDINAHET/Loto_API/packages)

---

## 👥 Pull and Run the Docker Containers Locally

### 🔹 **1️⃣ Backend (Spring Boot - Port 8082)**
To pull and run the backend container:
```bash
docker pull ghcr.io/sdinahet/loto_api_backend:latest
docker run -d -p 8082:8082 ghcr.io/sdinahet/loto_api_backend:latest
```
🔝 **API URL:** [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

🔗 **Dockerfile Backend:** [Dockerfile.backend](https://github.com/SDINAHET/Loto_API/blob/main/Dockerfile.backend)

---

### 🔹 **2️⃣ Frontend (Static Web - Port 5500)**
To pull and run the frontend container:
```bash
docker pull ghcr.io/sdinahet/loto_api_frontend:latest
docker run -d -p 5500:5500 ghcr.io/sdinahet/loto_api_frontend:latest
```
🔝 **Frontend URL:** [http://127.0.0.1:5500/](http://127.0.0.1:5500/)

🔗 **Dockerfile Frontend:** [Dockerfile.frontend](https://github.com/SDINAHET/Loto_API/blob/main/Dockerfile.frontend)

---

### 🔹 **3️⃣ MongoDB Database (Port 27017)**
Since MongoDB is now stored in **GitHub Packages**, you need to pull and run it:
```bash
docker pull ghcr.io/sdinahet/loto_mongodb:latest
docker run -d --name mongodb -p 27017:27017 ghcr.io/sdinahet/loto_mongodb:latest
```
🔝 **MongoDB URL (for Backend):** `mongodb://mongodb:27017/loto_database`

🔗 **Docker Compose File:** [compose.yaml](https://github.com/SDINAHET/Loto_API/blob/main/compose.yaml)

---

## 💡 Run Backend, Frontend & Database Together with Docker Compose
If you have `docker-compose.yml` configured, you can start everything with **one command**:
```bash
docker-compose up -d
```
Or, to **ensure all images are up to date** before starting:
```bash
docker pull ghcr.io/sdinahet/loto_api_backend:latest
docker pull ghcr.io/sdinahet/loto_api_frontend:latest
docker pull ghcr.io/sdinahet/loto_mongodb:latest
docker-compose up -d
```
This will start:
✅ **MongoDB** (`ghcr.io/sdinahet/loto_mongodb`)
✅ **Backend** (`ghcr.io/sdinahet/loto_api_backend`)
✅ **Frontend** (`ghcr.io/sdinahet/loto_api_frontend`)

---

## 🛠 Additional Docker Commands

### 🛡️ Stop and Remove Running Containers
```bash
docker-compose down
```

### 🚀 Rebuild and Restart Containers
```bash
docker-compose up -d --build
```

### 🗂️ View Running Containers
```bash
docker ps
```

### 🗑️ Remove Unused Docker Images
```bash
docker system prune -a
```

---

🚀 **Your Loto API is now fully operational localy with Backend, Frontend and MongoDB!** 🎠🔥



