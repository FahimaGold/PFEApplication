package com.example.pc.pfe;

/**
 * Created by pc on 18/09/2018.
 */

public class Poids {

    private int id;
    private String valeur;
    private String dateMesure;
    private int userId;

    public Poids(){

    }

    public Poids(int id, String valeur, String dateMesure, int userId) {
        this.id = id;
        this.valeur = valeur;
        this.dateMesure = dateMesure;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(String dateMesure) {
        this.dateMesure = dateMesure;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
