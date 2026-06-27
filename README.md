# Projet Java - Gestion des utilisateurs

Ce projet Java gère des utilisateurs avec un modèle `Model`, un service `Service` et un DAO `Dao`.

## Base de données MySQL (XAMPP)

1. Démarrez XAMPP et activez Apache et MySQL.
2. Ouvrez `phpMyAdmin` ou un terminal MySQL.
3. Exécutez le script SQL : `database/create_userdb.sql`.

## Configuration de la base de données

Le fichier `DatabaseConnection.java` utilise les paramètres suivants :

- URL : `jdbc:mysql://localhost:3306/userdb?useSSL=false&serverTimezone=UTC`
- Utilisateur : `root`
- Mot de passe : `` (vide)

> Si votre installation XAMPP utilise un autre mot de passe MySQL, adaptez ces valeurs.

## Structure du projet

- `model` : classe `User`
- `dao` : interface `UserDao` et implémentation `UserDaoImpl`
- `service` : interface `UserService` et implémentation `UserServiceImpl`
- `app` : classe principale `Main`

## Commandes Maven

- Construire le projet : `mvn compile`
- Exécuter l'application : `mvn exec:java -Dexec.mainClass=com.example.usermanagement.app.Main`

## Fonctionnalités

- Ajouter un utilisateur
- Modifier un utilisateur
- Supprimer un utilisateur
- Rechercher un utilisateur par ID, login ou nom/prénom
- Authentification simple par login/mot de passe
