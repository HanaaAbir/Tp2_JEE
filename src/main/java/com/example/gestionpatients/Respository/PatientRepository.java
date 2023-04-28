package com.example.gestionpatients.Respository;

import com.example.gestionpatients.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    //public List<Patient> findByMalade(boolean m);

    public Page<Patient> findByMalade(boolean m, Pageable pageable);

    public List<Patient> findByMaladeAndScoreGreaterThan(boolean m, int s);

    /*@Query("select p from Patient p where p.dateNaissance between :d1 and :d2 and p.nom like :n")
    // chercher à l'aide d'une Query liée à la fonction chercherPatient
    public List<Patient> chercherPatient(@Param("d1") Date d1, @Param("d2") Date d2, @Param("d3") String nom);*/

    @Query("select p from Patient p where p.nom like :nom and p.score = :s")
    public List<Patient> patientByNameAndScore(@Param("nom") String nom, @Param("s") int s);


}
