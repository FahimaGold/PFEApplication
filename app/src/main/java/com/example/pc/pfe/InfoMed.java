package com.example.pc.pfe;

/**
 * Created by pc on 16/05/2018.
 */

public class InfoMed {
    private int idInfoMed;
    private String taille;
    private String poids;
    private String tourDeTaille;
    private String historique;
    private String sport;
    private String fumeur;
    private String stress;
    private String type;
    private String systolic;
    private String distolic;
    private int idUtilisateur;
    private int modifie;
    private int insere;

    public InfoMed(){

    }

    public InfoMed(int inInfoMed, String taille, String poids, String tourDeTaille, String historique,
                   String sport, String fumeur, String stress, String type, String systolic, String distolic, int idUtilisateur) {
        this.idInfoMed = inInfoMed;
        this.taille = taille;
        this.poids = poids;
        this.tourDeTaille = tourDeTaille;
        this.historique = historique;
        this.sport = sport;
        this.fumeur = fumeur;
        this.stress = stress;
        this.type = type;
        this.systolic = systolic;
        this.distolic = distolic;
        this.idUtilisateur = idUtilisateur;
    }

    public int getInInfoMed() {
        return idInfoMed;
    }

    public void setInInfoMed(int inInfoMed) {
        this.idInfoMed = inInfoMed;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getTourDeTaille() {
        return tourDeTaille;
    }

    public void setTourDeTaille(String tourDeTaille) {
        this.tourDeTaille = tourDeTaille;
    }

    public String getHistorique() {
        return historique;
    }

    public void setHistorique(String historique) {
        this.historique = historique;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getFumeur() {
        return fumeur;
    }

    public void setFumeur(String fumeur) {
        this.fumeur = fumeur;
    }

    public String getStress() {
        return stress;
    }

    public void setStress(String stress) {
        this.stress = stress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDistolic() {
        return distolic;
    }

    public void setDistolic(String distolic) {
        this.distolic = distolic;
    }

    public int getIdInfoMed() {
        return idInfoMed;
    }

    public void setIdInfoMed(int idInfoMed) {
        this.idInfoMed = idInfoMed;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getModifie() {
        return modifie;
    }

    public void setModifie(int modifie) {
        this.modifie = modifie;
    }

    public int getInsere() {
        return insere;
    }

    public void setInsere(int insere) {
        this.insere = insere;
    }
}
