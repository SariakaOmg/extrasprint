package main;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
 
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    static class PatientDto {
        String IdPatient;
    }

    static final String API_GETALL  = "http://localhost:8081/Extra/Getall";
    static final String URL_FICHE   = "http://localhost:77/patient/";   // contenu du QR code
    static final String DOSSIER_CDN = "/usr/local/cdn/patient";         // où sauver les images

    public static void main(String[] args) throws Exception {
        HttpRequest req = HttpRequest.newBuilder(URI.create(API_GETALL)).GET().build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> rep = client.send(req, HttpResponse.BodyHandlers.ofString());

        if (rep.statusCode() != 200) {
            System.err.println("Erreur API : HTTP " + rep.statusCode());
            return;
        }

        PatientDto[] patients = new Gson().fromJson(rep.body(), PatientDto[].class);
        if (patients == null || patients.length == 0) {
            System.out.println("Aucun patient reçu.");
            return;
        }

        for (PatientDto p : patients) {
            Path dossier = Path.of(DOSSIER_CDN, p.IdPatient);
            Files.createDirectories(dossier);
 
            BitMatrix matrice = new MultiFormatWriter()
                    .encode(URL_FICHE + p.IdPatient, BarcodeFormat.QR_CODE, 300, 300);
            MatrixToImageWriter.writeToPath(matrice, "JPG", dossier.resolve("qrcode.jpeg"));
 
            System.out.println("OK : " + p.IdPatient + " -> " + dossier.resolve("qrcode.jpeg"));
        }
    }
}
