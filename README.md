# Gestion des Adhérents et Groupes

Ce projet est une application web développée dans le cadre du cours de **Programmation Web Avancée** pour gérer une association sportive. L'application permet de créer, modifier, supprimer et lister les adhérents ainsi que les groupes auxquels ils appartiennent. Elle inclut également une API REST et des tests automatisés avec Selenium.

## Table des Matières

1. [Fonctionnalités](#fonctionnalités)
2. [Structure du Projet](#structure-du-projet)
3. [Technologies Utilisées](#technologies-utilisées)
4. [Problèmes Résolus](#problèmes-résolus)
5. [Instructions de Déploiement](#instructions-de-déploiement)
6. [Améliorations Futures](#améliorations-futures)

## Fonctionnalités

### Gestion des Adhérents
- **Création** : Formulaire pour ajouter un adhérent avec des informations complètes (nom, prénom, date de naissance, etc.).
- **Modification** : Mise à jour des informations d'un adhérent existant.
- **Suppression** : Suppression d'un adhérent.
- **Liste** : Affichage de tous les adhérents avec leurs informations et le groupe associé.

### Gestion des Groupes
- **Création** : Ajout de groupes avec nom et description facultative.
- **Modification** : Mise à jour des informations d'un groupe.
- **Suppression** : Suppression uniquement si le groupe ne contient pas d'adhérents.
- **Liste** : Affichage des groupes avec leur description et le nombre d'adhérents.

### Webservice REST
- API REST permettant la gestion des adhérents et groupes via des endpoints pour créer, modifier, supprimer et lister.

### Tests Selenium
- Automatisation des tests pour les fonctionnalités critiques comme la création, la suppression et la consultation des adhérents.

## Structure du Projet

Le projet suit une architecture MVC avec une structure modulaire :

```
association-sportive/
├── src/main/java
│   ├── dao          # Opérations de persistance (DAO)
│   ├── entity       # Entités JPA (Adherent, Groupe)
│   ├── service      # Logique métier
│   ├── servlet      # Gestion des requêtes HTTP
│   ├── utils        # Outils (e.g., HibernateUtil)
│   └── webservice   # Web Services REST
├── src/main/resources
│   └── persistence.xml
├── src/main/webapp
│   ├── CSS          # Fichier style.css
│   ├── JSP          # Vues dans WEB-INF/views/
├── src/test/java
│   └── SeleniumTest # Tests automatisés
└── pom.xml          # Configuration Maven
```

## Technologies Utilisées

- **Langages** : Java (Servlets, JPA), JSP
- **Frameworks** : Hibernate, Maven
- **Base de Données** : MySQL
- **Tests** : Selenium WebDriver

## Problèmes Résolus

1. **LazyInitializationException** :
   - Solution : Initialisation explicite des collections avec `size()` avant leur affichage.
2. **Suppression d'un Groupe avec Adhérents** :
   - Solution : Validation côté backend pour empêcher la suppression et afficher un message d'erreur.
3. **Problèmes avec Selenium WebDriver** :
   - Solution : Configuration correcte du chemin du driver via la variable d'environnement `EDGE_DRIVER_PATH`.

## Instructions de Déploiement

### Prérequis
- Java 17 ou version supérieure
- Maven 3.x
- Navigateur Microsoft Edge et WebDriver configuré
- Serveur Tomcat

### Étapes
1. Clonez ce dépôt :
   ```bash
   git clone https://github.com/tlilicyrine/GestionAssociationSportive.git
   ```
2. Construisez le projet :
   ```bash
   mvn clean install
   ```
3. Déployez le fichier WAR généré dans le dossier `webapps` de Tomcat.
4. Accédez à l'application via [http://localhost:8080/association-sportive](http://localhost:8080/association-sportive).

## Améliorations Futures

- Intégration d'une gestion des paiements en ligne.
- Ajout d'un système d'authentification utilisateur.
- Développement d'une application mobile connectée à l'API REST.

---

**Auteurs** : Yosra Sassi & Sirine Tlili
