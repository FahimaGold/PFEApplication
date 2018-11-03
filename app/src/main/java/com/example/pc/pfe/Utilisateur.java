package com.example.pc.pfe;

import java.sql.Date;

/**
 * Created by pc on 04/05/2018.
 */

public class Utilisateur {
    private static int idUtilisateur;
    private String prenom;
    private String nom;
    private String dateDeNaissance;
    private String email;
    private String motDePasse;
    private String genre;
    private String wilaya;



    public Utilisateur(){

    }

    public Utilisateur(int idUtilisateur, String prenom, String nom, String email, String motDePass, String genre, String wilaya) {
        this.idUtilisateur = idUtilisateur;
        this.prenom = prenom;
        this.nom = nom;
        this.dateDeNaissance = dateDeNaissance;
        this.email = email;
        this.motDePasse = motDePasse;
        this.genre = genre;
        this.wilaya = wilaya;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   public String getDateDeNaissance() {
       return this.dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }
}
