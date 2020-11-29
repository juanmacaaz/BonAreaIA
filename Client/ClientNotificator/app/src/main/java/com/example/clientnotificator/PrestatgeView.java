package com.example.clientnotificator;

public class PrestatgeView {
    private String nom;
    private String ubicacio;
    private int nFallos;

    public PrestatgeView(String nom, String ubicacio, int nFallos) {
        this.nom = nom;
        this.ubicacio = ubicacio;
        this.nFallos = nFallos;
    }

    public PrestatgeView() {}

    public String getNom() {
        return nom;
    }

    public String getUbicacio() {
        return ubicacio;
    }

    public int getnFallos() {
        return nFallos;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUbicacio(String ubicacio) {
        this.ubicacio = ubicacio;
    }

    public void setnFallos(int nFallos) {
        this.nFallos = nFallos;
    }

}
