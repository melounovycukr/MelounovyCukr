package com.brnokavarna.melounovycukr.app.Print;

/**
 * Created by Me on 14.5.2014.
 */

public class Polozka {

    private String nazev;
    private int mnozstvi;
    private int cena;

    public Polozka(String nazev, int mnozstvi, int cena) {
        super();
        this.nazev = nazev;
        this.mnozstvi = mnozstvi;
        this.cena = cena;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

}

