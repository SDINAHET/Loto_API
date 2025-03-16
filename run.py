import subprocess
import webbrowser
import time

def start_backend():
    print("Démarrage du backend...")
    backend_process = subprocess.Popen(["mvn", "install"], cwd=".", shell=True)
    backend_process.wait()  # Attendre la fin de l'installation
    subprocess.Popen(["mvn", "spring-boot:run"], cwd=".", shell=True)

def start_frontend():
    print("Démarrage du frontend...")
    subprocess.Popen(["http-server", "src/main/resources/static", "-p", "5500"], shell=True)

def open_browser():
    time.sleep(5)  # Attendre quelques secondes pour que les services démarrent
    webbrowser.open("http://localhost:8082/swagger-ui/index.html")
    webbrowser.open("http://localhost:5500/")

if __name__ == "__main__":
    start_backend()
    start_frontend()
    open_browser()
