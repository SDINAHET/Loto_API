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
``
```

### 🗂️ View Running Containers
```bash
docker ps
```

### 🗑️ Remove Unused Docker Images
```bash
docker system prune -a
```

### Build dockerimages from Dockerfile
```bash
docker-compose up -d --build
```

---

🚀 **Your Loto API is now fully operational localy with Backend, Frontend and MongoDB!** 🎠🔥




Test 
```bash                                                                                                                                                      0.3s 
root@UID7E:/mnt/c/Users/steph/Documents/portfolio/16032025/Loto_API# docker pull ghcr.io/sdinahet/loto_api_backend:latest
docker pull ghcr.io/sdinahet/loto_api_frontend:latest
docker pull ghcr.io/sdinahet/loto_mongodb:latest
docker-compose up -d
latest: Pulling from sdinahet/loto_api_backend
0988f35b959c: Download complete
bd644c292ab8: Download complete
e1111267bedc: Download complete
246937326f7d: Download complete
Digest: sha256:520065ad7a3024ffaa14ff73e6d9b39c30ea5644dc2706e8a85f6fa8c9be9c0a
Status: Downloaded newer image for ghcr.io/sdinahet/loto_api_backend:latest
ghcr.io/sdinahet/loto_api_backend:latest
latest: Pulling from sdinahet/loto_api_frontend
c9d34309aa3a: Download complete
67ea9ea28bea: Download complete
9849e944531f: Download complete
Digest: sha256:e573037115bf10c55cd027102e88c0da65f36ab3dfd9fcd6729e22fdf5bdf74a
Status: Downloaded newer image for ghcr.io/sdinahet/loto_api_frontend:latest
ghcr.io/sdinahet/loto_api_frontend:latest
latest: Pulling from sdinahet/loto_mongodb
0c492c8e8cfd: Download complete
d5bafd14fbe8: Download complete
d67c4ebf9460: Download complete
7afa02f8c09e: Download complete
734719e891c0: Download complete
4e7ca17a42bd: Download complete
342a4f4728ff: Download complete
Digest: sha256:7a8a51bea1002c79d19b20ac85a310466b96982f303790fa12df616df538128d
Status: Downloaded newer image for ghcr.io/sdinahet/loto_mongodb:latest
ghcr.io/sdinahet/loto_mongodb:latest
WARN[0000] /mnt/c/Users/steph/Documents/portfolio/16032025/Loto_API/compose.yaml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion 
[+] Running 5/5
 ✔ Network loto_api_default        Created                                                                                                                                                         0.1s 
 ✔ Volume "loto_api_mongodb_data"  Created                                                                                                                                                         0.0s 
 ✔ Container mongodb               Started                                                                                                                                                         2.9s 
 ✔ Container loto_api-backend-1    Started                                                                                                                                                         2.3s 
 ✔ Container loto_api-frontend-1   Started
 root@UID7E:/mnt/c/Users/steph/Documents/portfolio/16032025/Loto_API# docker-compose down -v
WARN[0000] /mnt/c/Users/steph/Documents/portfolio/16032025/Loto_API/compose.yaml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion 
[+] Running 5/5
 ✔ Container loto_api-frontend-1  Removed                                                                                                                                                          0.8s 
 ✔ Container loto_api-backend-1   Removed                                                                                                                                                          1.2s 
 ✔ Container mongodb              Removed                                                                                                                                                          0.6s 
 ✔ Volume loto_api_mongodb_data   Removed                                                                                                                                                          0.0s 
 ✔ Network loto_api_default       Removed

root@UID7E:/mnt/c/Users/steph/Documents/portfolio/16032025/Loto_API# docker-compose up -d --build
WARN[0000] /mnt/c/Users/steph/Documents/portfolio/16032025/Loto_API/compose.yaml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion 
[+] Building 80.7s (21/21) FINISHED                                                                                                                                                      docker:default
 => [backend internal] load build definition from Dockerfile.backend                                                                                                                               0.1s
 => => transferring dockerfile: 418B                                                                                                                                                               0.1s 
 => [backend internal] load metadata for docker.io/library/openjdk:21-jdk-slim                                                                                                                     0.9s
 => [backend internal] load .dockerignore                                                                                                                                                          0.1s
 => => transferring context: 2B                                                                                                                                                                    0.0s 
 => [backend 1/5] FROM docker.io/library/openjdk:21-jdk-slim@sha256:7072053847a8a05d7f3a14ebc778a90b38c50ce7e8f199382128a53385160688                                                               0.0s 
 => => resolve docker.io/library/openjdk:21-jdk-slim@sha256:7072053847a8a05d7f3a14ebc778a90b38c50ce7e8f199382128a53385160688                                                                       0.0s 
 => [backend internal] load build context                                                                                                                                                          7.7s 
 => => transferring context: 109.99MB                                                                                                                                                              7.7s 
 => CACHED [backend 2/5] WORKDIR /app                                                                                                                                                              0.0s
 => [backend 3/5] COPY . /app                                                                                                                                                                      1.1s 
 => [backend 4/5] RUN chmod +x mvnw                                                                                                                                                                0.6s
 => [backend 5/5] RUN ./mvnw install                                                                                                                                                              41.8s
 => [backend] exporting to image                                                                                                                                                                  25.3s
 => => exporting layers                                                                                                                                                                           15.5s
 => => exporting manifest sha256:b966cf0c988b8f2d62843b9d16fbeb3b87d245e70386ea31e9e5b8cf75f661b1                                                                                                  0.0s
 => => exporting config sha256:381a55b94d558004cc728f3a12f672e9ff941a585c7a3d265b35c58da970bd03                                                                                                    0.0s
 => => exporting attestation manifest sha256:79082d7e53a9d7856a808ffa8a5844ef45487729718fcf2611417618e880d394                                                                                      0.0s
 => => exporting manifest list sha256:6d86bbafb82bcc9eba55b95f0ec57f14080c077cc040b5bf9273c3bcec969dd9                                                                                             0.0s
 => => naming to docker.io/library/loto_api-backend:latest                                                                                                                                         0.0s
 => => unpacking to docker.io/library/loto_api-backend:latest                                                                                                                                      9.6s
 => [backend] resolving provenance for metadata file                                                                                                                                               0.0s
 => [frontend internal] load build definition from Dockerfile.frontend                                                                                                                             0.1s
 => => transferring dockerfile: 411B                                                                                                                                                               0.1s 
 => [frontend internal] load metadata for docker.io/library/node:18-alpine                                                                                                                         0.9s 
 => [frontend internal] load .dockerignore                                                                                                                                                         0.0s
 => => transferring context: 2B                                                                                                                                                                    0.0s 
 => [frontend 1/4] FROM docker.io/library/node:18-alpine@sha256:e0340f26173b41066d68e3fe9bfbdb6571ab3cad0a4272919a52e36f4ae56925                                                                   0.0s 
 => => resolve docker.io/library/node:18-alpine@sha256:e0340f26173b41066d68e3fe9bfbdb6571ab3cad0a4272919a52e36f4ae56925                                                                            0.0s 
 => [frontend internal] load build context                                                                                                                                                         0.1s 
 => => transferring context: 1.48kB                                                                                                                                                                0.1s 
 => CACHED [frontend 2/4] RUN npm install -g http-server                                                                                                                                           0.0s 
 => CACHED [frontend 3/4] WORKDIR /app                                                                                                                                                             0.0s 
 => CACHED [frontend 4/4] COPY src/main/resources/static /app                                                                                                                                      0.0s 
 => [frontend] exporting to image                                                                                                                                                                  0.1s 
 => => exporting layers                                                                                                                                                                            0.0s 
 => => exporting manifest sha256:bb0a31cb22608e4ba2b8b48b6140a0b99de439673a80ad253d105fea13127114                                                                                                  0.0s 
 => => exporting config sha256:661125a7a15defef8420717ed826a6d201456751b5eb89f3136105bb85fb77f1                                                                                                    0.0s 
 => => exporting attestation manifest sha256:29a350324f04c0dff3bafe26c0c751c9756e6f72eb5a16f0ca395ffbef798e86                                                                                      0.0s 
 => => exporting manifest list sha256:da158133f11b4e0dc22f92c31425791107b5d70ca1ed2573525895ee7cde3c0a                                                                                             0.0s 
 => => naming to docker.io/library/loto_api-frontend:latest                                                                                                                                        0.0s 
 => => unpacking to docker.io/library/loto_api-frontend:latest                                                                                                                                     0.0s 
 => [frontend] resolving provenance for metadata file                                                                                                                                              0.0s 
[+] Running 5/5
 ✔ Network loto_api_default        Created                                                                                                                                                         0.1s 
 ✔ Volume "loto_api_mongodb_data"  Created                                                                                                                                                         0.0s 
 ✔ Container mongodb               Started                                                                                                                                                         2.2s 
 ✔ Container loto_api-backend-1    Started                                                                                                                                                         1.9s 
 ✔ Container loto_api-frontend-1   Started
root@UID7E:/mnt/c/Users/steph/Documents/portfolio/16032025/Loto_API# docker-compose down -v
WARN[0000] /mnt/c/Users/steph/Documents/portfolio/16032025/Loto_API/compose.yaml: the attribute `version` is obsolete, it will be ignored, please remove it to avoid potential confusion 
[+] Running 5/5
 ✔ Container loto_api-frontend-1  Removed                                                                                                                                                          1.1s 
 ✔ Container loto_api-backend-1   Removed                                                                                                                                                          1.8s 
 ✔ Container mongodb              Removed                                                                                                                                                          0.7s 
 ✔ Volume loto_api_mongodb_data   Removed                                                                                                                                                          0.0s 
 ✔ Network loto_api_default       Removed
 ```