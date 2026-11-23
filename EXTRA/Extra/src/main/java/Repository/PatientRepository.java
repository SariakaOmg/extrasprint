package main.java.Repository;

import main.java.Connexion.DatabaseConnection;
import main.java.Model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PatientRepository {

    public String GetDernierIdPatient() {
    String sql = "SELECT IdPatient FROM Patient ORDER BY LENGTH(IdPatient) DESC, IdPatient DESC LIMIT 1";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            return rs.getString("IdPatient");
        }
        return ""; // table vide
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la récupération du dernier id patient : " + e.getMessage(), e);
    }
    }

    public void Ajouter(Patient patient) {
        String sql = "INSERT INTO Patient (IdPatient, Nom, Prenom, AgePatient, DateNaissance, Email, MotdePasse) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getIdPatient());
            stmt.setString(2, patient.getNom());
            stmt.setString(3, patient.getPrenom());
            stmt.setInt(4, patient.getAge());
            stmt.setTimestamp(5, patient.getDateNaissance() != null
                    ? Timestamp.valueOf(patient.getDateNaissance())
                    : null);
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getMotdePasse());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du patient", e);
        }
    }

    public void Modifier(String idPatient, Patient patient) {
        String sql = "UPDATE Patient SET Nom = ?, Prenom = ?, AgePatient = ?, DateNaissance = ?, Email = ?, MotdePasse = ? WHERE IdPatient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setInt(3, patient.getAge());
            stmt.setTimestamp(4, patient.getDateNaissance() != null
                    ? Timestamp.valueOf(patient.getDateNaissance())
                    : null);
            stmt.setString(5, patient.getEmail());
            stmt.setString(6, patient.getMotdePasse());
            stmt.setString(7, idPatient);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la modification du patient", e);
        }
    }

    public void Supprimer(String idPatient) {
        String sql = "DELETE FROM Patient WHERE IdPatient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPatient);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du patient", e);
        }
    }

    public boolean Compteexiste(Patient patient) {
    if (patient == null || patient.getEmail() == null || patient.getMotdePasse() == null) {
        return false;
    }

    String sql = "SELECT IdPatient FROM Patient WHERE Email = ? AND MotdePasse = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, patient.getEmail());
        stmt.setString(2, patient.getMotdePasse());

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {                                    // d'abord next()
                patient.setIdPatient(rs.getString("IdPatient")); // puis on lit
                return true;
            }
            return false;
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erreur lors de la vérification du compte patient : " + e.getMessage(), e);
    }
    }

    public Patient GetPatient(String idPatient) {
        String sql = "SELECT IdPatient, Nom, Prenom, AgePatient, DateNaissance, Email, MotdePasse FROM Patient WHERE IdPatient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPatient);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Timestamp dateNaissanceTs = rs.getTimestamp("DateNaissance");
                    Patient patient = new Patient();
                    patient.setIdPatient(rs.getString("IdPatient"));
                    patient.setNom(rs.getString("Nom"));
                    patient.setPrenom(rs.getString("Prenom"));
                    patient.setAge(rs.getInt("AgePatient"));
                    patient.setDateNaissance(dateNaissanceTs != null ? dateNaissanceTs.toLocalDateTime() : null);
                    patient.setEmail(rs.getString("Email"));
                    patient.setMotdePasse(rs.getString("MotdePasse"));
                    return patient;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération du patient avec l'id " + idPatient, e);
        }
        return null;
    }

    public ArrayList<Patient> GetAllPatient(){
        ArrayList<Patient> p = new ArrayList<>();
        String requette = "SELECT * FROM Patient";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(requette)){
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    Timestamp dateNaissanceTs = rs.getTimestamp("DateNaissance");
                    Patient patient = new Patient();
                    patient.setIdPatient(rs.getString("IdPatient"));
                    patient.setNom(rs.getString("Nom"));
                    patient.setPrenom(rs.getString("Prenom"));
                    patient.setAge(rs.getInt("AgePatient"));
                    patient.setDateNaissance(dateNaissanceTs != null ? dateNaissanceTs.toLocalDateTime() : null);
                    patient.setEmail(rs.getString("Email"));
                    patient.setMotdePasse(rs.getString("MotdePasse"));
                    p.add(patient);
                }
            } 
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Erreur lors de la recuperation de tous les patients ",e);
        }
        return p;
    }
}