package com.brnokavarna.melounovycukr.app.Model.Tabulky;

/**
 * Created by Seky on 14. 4. 2014.
 * Trida obsahujici predpis tabulky pro jednotlivy stul
 */
public class Stul {
    private int id;
    private int id_polozky;
    private int mnozstvi;

    /**
     * Constructor
     */
    public Stul(){}

    /**
     * Getters and setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id_stolu) {
        this.id = id_stolu;
    }

    public int getId_polozky() {
        return id_polozky;
    }

    public void setId_polozky(int id_polozky) {
        this.id_polozky = id_polozky;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }
}
