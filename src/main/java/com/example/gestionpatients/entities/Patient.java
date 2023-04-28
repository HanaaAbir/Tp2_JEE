package com.example.gestionpatients.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Name;
import java.util.Date;


@Entity // declarer classe en tant que entité (obligatoire)
@Data // generer les getters et les setters automatiquement
@NoArgsConstructor // generer automatiquement constructeur sans parametres
@AllArgsConstructor // generer automatiquement constructeur avec parametres
public class Patient {
    @Id // definir champ id en tant que clé primaire (obligatoire)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // champ id auto-incrementé
    private Long id;

    @Column(name = "Nom", length = 50) // preciser le nom et la taille lors du stockage à la BD
    private String nom;
    @Temporal(TemporalType.DATE) // preciser type date jj-mm-aaaa
    private Date dateNaissance;

    private boolean malade;
    private int score;


}
