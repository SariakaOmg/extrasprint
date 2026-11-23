mysql -u root -p

CREATE DATABASE Extra_Medicale;

Use Extra_Medicale;

CREATE TABLE Patient(
    IdPatient VARCHAR(100),
    Nom VARCHAR(100),
    Prenom VARCHAR(100),
    AgePatient INT,
    DateNaissance DATE,
    Email VARCHAR(100),
    MotDePasse VARCHAR(100)
);

