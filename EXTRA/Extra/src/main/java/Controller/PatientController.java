package main.java.Controller;
import main.java.Model.Patient;
import mg.itu.Controller;
import mg.itu.URLMapping;
import main.java.Service.*;
import mg.itu.Param;

@Controller
public class PatientController {
    // PatientService patientService;
    PatientService patientService = new PatientService();
    // CRUD 
    // Supprimer 
    @URLMapping(value="/delete", methode = "GET")
    public void Supprimer(@Param("IdPat") String id){
        patientService.supprimerPatient(id);
    }
    // Ajouter
    @URLMapping(value="/Ajouter", methode = "GET")
    public void Ajouter(@Param("patient") Patient patient){
        patientService.ajouterPatient(patient);
    }
    // Modifier
    @URLMapping(value="/Modifier", methode = "GET")
    public void Modifier(@Param("Patient") Patient p){
        patientService.modifierPatient(p.getIdPatient(),p);
    }
    
}
