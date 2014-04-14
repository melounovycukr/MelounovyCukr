package com.brnokavarna.melounovycukr.app.Model;

/**
 * Created by Seky on 14. 4. 2014.
 * Trida obsahujici predpis tabulky pro jednotlivy stul
 */
public class Stul {
    private int id_stolu;
    private int id_polozky;
    private int mnozstvi;

    /**
     * Constructor
     */
    public Stul(){}

    /**
     * Getters and setters
     */
    public int getId_stolu() {
        return id_stolu;
    }

    public void setId_stolu(int id_stolu) {
        this.id_stolu = id_stolu;
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
