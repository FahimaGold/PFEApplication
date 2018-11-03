package com.example.pc.pfe;

/**
 * Created by pc on 14/09/2018.
 */

public class Glucose {
    private int id;
    private String tauxGlucose;
    private String dateMesure;
    private String stamp;

    public Glucose(){

    }
    public Glucose(int id, String tauxGlucose, String dateMesure) {
        this.id = id;
        this.tauxGlucose = tauxGlucose;
        this.dateMesure = dateMesure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTauxGlucose() {
        return tauxGlucose;
    }

    public void setTauxGlucose(String tauxGlucose) {
        this.tauxGlucose = tauxGlucose;
    }

    public String getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(String dateMesure) {
        this.dateMesure = dateMesure;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}
