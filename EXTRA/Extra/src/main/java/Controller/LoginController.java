package main.java.Controller;
import mg.itu.Controller;
import mg.itu.Param;
import mg.itu.URLMapping;
import utils.MaSession;
import utils.ModelView;
import main.java.Model.Patient;
import  main.java.Service.*;
import utils.MaSession;
import mg.itu.Param;

@Controller
public class LoginController {
    // PatientService patientService;
    PatientService patientService = new PatientService();


    @URLMapping(value = "/", methode = "GET")
    public ModelView formUpload() {
        return new ModelView("SignInPatient", null);
    }

    @URLMapping(value = "/pageconnexe", methode = "GET")
    public ModelView formLogin() {
        return new ModelView("LoginPatient", null);
    }

    @URLMapping(value="/login", methode = "GET")
    public ModelView loginPatient(@Param("patient") Patient patient, MaSession session ) {
        boolean a = patientService.verifierConnexion(patient);
        if (a == true){
            Patient pa = patientService.getPatient(patient.getIdPatient());
            session.setAttribute("userConnecte",patient.getIdPatient());
            session.setAttribute("role","patient");
            return new ModelView("LoginPatient", null);
        }else{
            // mankany Page inscription
            return new ModelView("SignInPatient",null);
        }
    }

    @URLMapping(value="/logout", methode = "GET")
    public void logout(MaSession session){
        session.removeAttribute("userConnecte");
        session.removeAttribute("role");
    }

    @URLMapping(value="/inscription", methode = "GET")
    public ModelView signinPatient(@Param("patient") Patient patient, MaSession session ) {
        boolean a = patientService.verifierConnexion(patient);
        if (a == false){
            patientService.GenererIdNouveau(patient);
            patientService.ajouterPatient(patient);
            session.setAttribute("userConnecte",patient.getIdPatient());
            session.setAttribute("role","patient");
            return new ModelView("SignInPatient",null);
        }else{
            // Compte existe deja
            return new ModelView("LoginPatient",null);
        }
    }
}
