package com.brnokavarna.melounovycukr.app.Model.Tabulky;

import com.brnokavarna.melounovycukr.app.Controller.Controller;

/**
 * Created by Seky on 14. 4. 2014.
 * Trida obsahujici predpis tabulky pro seznam vsech polozek
 */
public class Seznam {
    private int id;
    private int kategorie_id;
    private double cena;
    private String nazev_zbozi;

    /**
     * Constructors
     */
    public Seznam(){}

    public Seznam(int katId, float cen, String zboz){
        //  super();
        this.kategorie_id = katId;
        this.cena = cen;
        this.nazev_zbozi = zboz;
    };

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

    public String toString() {
        return "Seznam [id=" + id + ", kat_id=" + kategorie_id +  ", cena=" + cena + ", nazev=" + nazev_zbozi
                + "]";
    }
}
