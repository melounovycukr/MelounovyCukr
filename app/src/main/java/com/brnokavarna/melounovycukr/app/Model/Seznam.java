package com.brnokavarna.melounovycukr.app.Model;

/**
 * Created by Seky on 14. 4. 2014.
 * Trida obsahujici predpis tabulky pro seznam vsech polozek
 */
public class Seznam {
    private int id;
    private int kategorie_id;
    private String kategorie_nazev;
    private double cena;
    private String nazev_zbozi;

    /**
     * Constructor
     */
    public Seznam(){};

    /**
     * Getters and setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKategorie_id() {
        return kategorie_id;
    }

    public void setKategorie_id(int kategorie_id) {
        this.kategorie_id = kategorie_id;
    }

    public String getKategorie_nazev() {
        return kategorie_nazev;
    }

    public void setKategorie_nazev(String kategorie_nazev) {
        this.kategorie_nazev = kategorie_nazev;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazev_zbozi() {
        return nazev_zbozi;
    }

    public void setNazev_zbozi(String nazev_zbozi) {
        this.nazev_zbozi = nazev_zbozi;
    }
}
