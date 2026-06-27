CREATE DATABASE IF NOT EXISTS userdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE userdb;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

INSERT INTO users (nom, prenom, login, password)
VALUES
    ('Dupont', 'Jean', 'jdupont', 'pass123'),
    ('Martin', 'Claire', 'cmartin', 'secret01');
