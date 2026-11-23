package main.java.Model;

import java.time.LocalDateTime;

public class Patient {
    private String IdPatient;
    private String Nom;
    private String Prenom;
    private int Age;
    private LocalDateTime DateNaissance;
    private String email;
    private String MotdePasse;
    
    public Patient(String idPatient, String nom, String prenom, int age, LocalDateTime dateNaissance, String email,
            String motdePasse) {
        IdPatient = idPatient;
        Nom = nom;
        Prenom = prenom;
        Age = age;
        DateNaissance = dateNaissance;
        this.email = email;
        MotdePasse = motdePasse;
    }
    public Patient() {
    }
    public String getIdPatient() {
        return IdPatient;
    }
    public void setIdPatient(String idPatient) {
        IdPatient = idPatient;
    }
    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }
    public String getPrenom() {
        return Prenom;
    }
    public void setPrenom(String prenom) {
        Prenom = prenom;
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        Age = age;
    }
    public LocalDateTime getDateNaissance() {
        return DateNaissance;
    }
    public void setDateNaissance(LocalDateTime dateNaissance) {
        DateNaissance = dateNaissance;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMotdePasse() {
        return MotdePasse;
    }
    public void setMotdePasse(String motdePasse) {
        MotdePasse = motdePasse;
    }
}
