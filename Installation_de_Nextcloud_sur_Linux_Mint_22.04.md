# 📥 Installation de Nextcloud sur Linux Mint 22.04

## 🚀 Prérequis
Avant de commencer, assurez-vous que votre système est à jour :

```bash
sudo apt update && sudo apt upgrade -y
```

Installez les paquets nécessaires :

```bash
sudo apt install apache2 mariadb-server libapache2-mod-php php php-cli php-mysql php-gd php-xml php-mbstring php-zip php-curl unzip wget -y
```

Activez et démarrez les services :

```bash
sudo systemctl enable --now apache2 mariadb
```

---
## 🗄 Configuration de la base de données

Sécurisez MariaDB :
```bash
sudo mysql_secure_installation
```

Créez une base de données et un utilisateur pour Nextcloud :

```sql
CREATE DATABASE nextcloud;
CREATE USER 'nextclouduser'@'localhost' IDENTIFIED BY 'MotDePasseSécurisé';
GRANT ALL PRIVILEGES ON nextcloud.* TO 'nextclouduser'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

---
## 📥 Installation de Nextcloud

Téléchargez et extrayez Nextcloud :

```bash
cd /var/www/
sudo wget https://download.nextcloud.com/server/releases/latest.zip
sudo unzip latest.zip
sudo mv nextcloud /var/www/html/
```

Attribuez les bons droits :

```bash
sudo chown -R www-data:www-data /var/www/html/nextcloud
sudo chmod -R 755 /var/www/html/nextcloud
```

---
## 🌐 Configuration d'Apache

Créez un fichier de configuration Apache :

```bash
sudo nano /etc/apache2/sites-available/nextcloud.conf
```

Ajoutez ceci :

```
<VirtualHost *:80>
    ServerAdmin admin@example.com
    DocumentRoot /var/www/html/nextcloud
    ServerName your_domain_or_IP

    <Directory /var/www/html/nextcloud/>
        Require all granted
        AllowOverride All
        Options FollowSymLinks MultiViews
    </Directory>

    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>
```

Activez la configuration et redémarrez Apache :

```bash
sudo a2ensite nextcloud.conf
sudo a2enmod rewrite headers env dir mime
sudo systemctl restart apache2
```

---
## 🔒 Accès à Nextcloud

Ouvrez votre navigateur et accédez à :

```
http://localhost/nextcloud
```

Suivez l’assistant d’installation.

---
## 🔧 (Facultatif) Activer HTTPS avec Let's Encrypt

Si vous avez un domaine, activez le SSL :

```bash
sudo apt install certbot python3-certbot-apache -y
sudo certbot --apache
```




# 📖 Guide d'Utilisation Générale de Nextcloud

## 🔹 Introduction
Nextcloud est une solution cloud auto-hébergée permettant le stockage, le partage et la synchronisation de fichiers de manière sécurisée. Il propose également des fonctionnalités avancées comme la gestion des utilisateurs, la collaboration en ligne et des applications tierces pour l'améliorer.

---
## 🛠 1. Installation et Connexion

### 📥 Accéder à Nextcloud
Après l'installation, ouvrez votre navigateur et entrez l'URL de votre instance :
```
http://votre_domaine/nextcloud
```
Créez un compte administrateur lors du premier démarrage, ou connectez-vous avec vos identifiants existants.

### 📡 Accès à distance via SSH
Si vous voulez gérer Nextcloud à distance, vous devez activer **SSH** sur votre serveur.

#### 📌 Activer SSH (Si non activé)
1. Installez OpenSSH si ce n'est pas encore fait :
   ```bash
   sudo apt update && sudo apt install openssh-server -y
   ```
2. Vérifiez que SSH est actif :
   ```bash
   sudo systemctl status ssh
   ```
3. Si nécessaire, démarrez et activez SSH au démarrage :
   ```bash
   sudo systemctl enable --now ssh
   ```

#### 📌 Se connecter à distance à votre serveur Nextcloud
Depuis un autre ordinateur, utilisez :
```bash
ssh utilisateur@IP_du_serveur
```
Exemple :
```bash
ssh admin@192.168.1.100
```
Si vous utilisez un port personnalisé (ex. 2222), utilisez :
```bash
ssh -p 2222 admin@192.168.1.100
```

#### 📌 Configurer l'accès distant sécurisé
1. Modifiez la configuration SSH :
   ```bash
   sudo nano /etc/ssh/sshd_config
   ```
2. Modifiez ou ajoutez ces lignes :
   ```
   PermitRootLogin no
   PasswordAuthentication no
   AllowUsers votre_utilisateur
   ```
3. Redémarrez SSH :
   ```bash
   sudo systemctl restart ssh
   ```

---
## 📂 2. Gestion des Fichiers

### 🔄 Synchronisation des fichiers
Nextcloud permet de synchroniser des fichiers via :
- L'interface web
- L'application de bureau
- L'application mobile

#### 📌 **Téléverser un fichier**
1. Connectez-vous à Nextcloud.
2. Cliquez sur l’icône **+** en haut à gauche.
3. Sélectionnez **Téléverser un fichier** et choisissez le fichier à importer.

#### 📌 **Créer un dossier**
1. Cliquez sur **+**.
2. Sélectionnez **Créer un dossier**.
3. Nommez le dossier et appuyez sur **Entrée**.

#### 📌 **Partager un fichier/dossier**
1. Cliquez sur l’icône **Partager** à côté d’un fichier ou d’un dossier.
2. Ajoutez un utilisateur ou un groupe Nextcloud.
3. Activez **Lien public** pour partager avec des personnes externes.
4. Définissez des permissions (lecture seule, modification, etc.).

---
## 👥 3. Gestion des Utilisateurs et des Groupes

### 📌 Ajouter un utilisateur
1. Allez dans **Paramètres** > **Utilisateurs**.
2. Cliquez sur **Ajouter un utilisateur**.
3. Remplissez les champs et attribuez des groupes/permissions.

### 📌 Gérer les groupes
- Les groupes permettent d’organiser les utilisateurs pour faciliter le partage.
- Un administrateur peut créer des groupes et assigner des utilisateurs.

---
## 📝 4. Collaboration en Temps Réel

### 📄 **Édition collaborative de documents**
- **Collabora Online** et **OnlyOffice** permettent de modifier des documents Word, Excel et PowerPoint en ligne.
- Activez ces applications via **Paramètres > Applications**.

### 📆 **Agenda et Contacts**
- Synchronisez votre agenda et vos contacts avec **Nextcloud Calendar** et **Nextcloud Contacts**.
- Compatible avec Google Agenda et Microsoft Outlook.

### 📅 **Tâches et Notes**
- **Nextcloud Tasks** permet de créer et gérer des listes de tâches.
- **Nextcloud Notes** stocke et synchronise vos notes.

---
## 🔒 5. Sécurité et Confidentialité

### 📌 **Authentification à deux facteurs (2FA)**
1. Allez dans **Paramètres** > **Sécurité**.
2. Activez **2FA** via une application comme Google Authenticator.

### 📌 **Chiffrement des fichiers**
- Activez **Nextcloud Encryption** pour chiffrer vos fichiers côté serveur.

### 📌 **Permissions et Accès**
- Définissez des règles d'accès par utilisateur/groupe.
- Configurez des **politiques de mot de passe**.

---
## 🔄 6. Applications et Extensions

Nextcloud propose un **App Store** avec de nombreuses extensions :
- **Talk** : Chat et visioconférence.
- **Mail** : Client e-mail intégré.
- **Deck** : Gestion de projet façon Kanban.

Installez-les via **Paramètres > Applications**.

---
## 🔧 7. Sauvegarde et Maintenance

### 📌 **Sauvegarder Nextcloud**
1. Sauvegardez les fichiers avec :
   ```bash
   tar -czvf nextcloud-backup.tar.gz /var/www/html/nextcloud/
   ```
2. Sauvegardez la base de données avec :
   ```bash
   mysqldump -u nextclouduser -p nextcloud > nextcloud-db.sql
   ```

### 📌 **Mettre à jour Nextcloud**
- Allez dans **Paramètres > Vue d’ensemble**.
- Cliquez sur **Mise à jour** si une nouvelle version est disponible.

---
## 📌 Conclusion
Nextcloud est un outil puissant pour stocker, partager et collaborer sur des fichiers en toute sécurité. Il est personnalisable avec des applications et sécurisé grâce aux options de chiffrement et de 2FA.

Besoin d'aide ? Consultez la documentation officielle :
🔗 [https://docs.nextcloud.com](https://docs.nextcloud.com)


# Nextcloud Server
You can download and install Nextcloud on your own Linux server, use the Web Installer to install it on shared Web hosting, try some prefab cloud or virtual machine images, or sign up for hosted Nextcloud services. See the Get Started page for more information.

The below are the currently maintained versions of Nextcloud Server.

## Nextcloud 32
This documents the upcoming version of Nextcloud (not released).

User Manual (Download PDF) https://docs.nextcloud.com/server/latest/Nextcloud_User_Manual.pdf
Administration Manual (Download PDF)  https://docs.nextcloud.com/server/latest/admin_manual/
Developer Manual  https://docs.nextcloud.com/server/latest/developer_manual/

# Nextcloud Desktop Client
Once you have a Nextcloud Server running, you can connect to it with various clients like our mobile and desktop client. Find documentation for the desktop client below.

User Manual https://docs.nextcloud.com/server/latest/user_manual/en/desktop/index.html
Administration Manual https://docs.nextcloud.com/server/latest/admin_manual/desktop/index.html
Developer Manual https://docs.nextcloud.com/server/latest/developer_manual/desktop/index.html