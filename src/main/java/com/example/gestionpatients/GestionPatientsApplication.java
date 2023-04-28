package com.example.gestionpatients;

import com.example.gestionpatients.Respository.PatientRepository;
import com.example.gestionpatients.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GestionPatientsApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository; // injection de dependance de l'interface PatientRepository

    public static void main(String[] args) {
        SpringApplication.run(GestionPatientsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null, "Hanaa", new Date(), false, 200));
        patientRepository.save(new Patient(null, "Mohamed", new Date(), true, 800));
        patientRepository.save(new Patient(null, "Rayane", new Date(), false, 900));

        System.out.println("------------------Avoir tous les malades------------------");
        List<Patient> listep = patientRepository.findAll(); // retourner liste des patients
        Page<Patient> patientPage = patientRepository.findAll(PageRequest.of(0, 5));
        //retourner les 5 premiers patients de la page 0

        System.out.println("nbr total de pages" + patientPage.getTotalPages()); // nbr total des pages
        System.out.println("nbr total d'elements" + patientPage.getTotalElements()); // nbr total d'elements
        System.out.println("nbr total d'elements" + patientPage.getNumber()); // numero de page
        System.out.println("contenu de la page" + patientPage.getContent()); // avoir contenu de la page

        Page<Patient> malades = patientRepository.findByMalade(true, PageRequest.of(0, 5));
        // appeler methode definie dans PatientRepository et passer valeur true à Malade,
        // et afficher 5 malades dans la 1ere page

        List<Patient> function2 = patientRepository.findByMaladeAndScoreGreaterThan(false, 200);

        function2.forEach(p -> {
            System.out.println("------------------");
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.getScore());
        });

        List<Patient> listepa = patientRepository.patientByNameAndScore("Hamza", 200);

        listepa.forEach(p -> {
            System.out.println("---------patient recherché est---------");
            System.out.println(p.getNom());
            System.out.println(p.getScore());
        });

        System.out.println("------------------Avoir malade par id------------------");
        Patient patient = patientRepository.findById(1L).orElseThrow(() -> new RuntimeException("Patient introuvable"));
        // retourner un patient par id si trouvé sinon retourner exception de Patient introuvable

        if (patient != null) {
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }

        patient.setScore(400);
        patientRepository.save(patient); // modifier score du patient à 900 (ici save joue un role de update car on specifie l'ID)

        System.out.println(patient.getScore());

        patientRepository.deleteById(1L); // supprimer patient par id

    }
}
