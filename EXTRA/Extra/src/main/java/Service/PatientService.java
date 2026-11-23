package main.java.Service;

import main.java.Model.Patient;
import main.java.Repository.PatientRepository;

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientService() {
        this.patientRepository = new PatientRepository();
    }

    public void GenererIdNouveau(Patient patient){
        String dernierId = patientRepository.GetDernierIdPatient();
        String nouveauId = "";
        if(dernierId.equals("")){
            nouveauId = "PAT0";
        }else{
            // on split patient et on prend le chiffre
            String nombre = dernierId.replaceAll("\\D+", "");
            // on le cast en int
            int ind = Integer.parseInt(nombre);;
            ind++;
            nouveauId = "PAT"+ind;
        }
        patient.setIdPatient(nouveauId);
    }

    public void ajouterPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Le patient ne peut pas être nul.");
        }
        if (patient.getEmail() == null || patient.getEmail().isBlank()) {
            throw new IllegalArgumentException("L'email du patient est obligatoire.");
        }
        if (patient.getMotdePasse() == null || patient.getMotdePasse().isBlank()) {
            throw new IllegalArgumentException("Le mot de passe est obligatoire.");
        }

        patientRepository.Ajouter(patient);
    }

    public void modifierPatient(String idPatient, Patient patient) {
        // if (idPatient <= 0) {
        //     throw new IllegalArgumentException("L'identifiant du patient est invalide.");
        // }
        // if (patient == null) {
        //     throw new IllegalArgumentException("Les données du patient ne peuvent pas être nulles.");
        // }

        patientRepository.Modifier(idPatient, patient);
    }

    public void supprimerPatient(String idPatient) {
        // if (idPatient <= 0) {
        //     throw new IllegalArgumentException("L'identifiant du patient est invalide.");
        // }

        patientRepository.Supprimer(idPatient);
    }

    public  boolean verifierConnexion(Patient patient) {
        // if (patient == null || patient.getEmail() == null || patient.getEmail().isBlank()
        //         || patient.getMotdePasse() == null || patient.getMotdePasse().isBlank()) {
        //     return false;
        // }

        return patientRepository.Compteexiste(patient);
    }

    public Patient getPatient(String idPatient) {
        // if (idPatient <= 0) {
        //     throw new IllegalArgumentException("L'identifiant du patient est invalide.");
        // }

        return patientRepository.GetPatient(idPatient);
    }
}