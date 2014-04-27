package com.brnokavarna.melounovycukr.app.Model.Tabulky;

/**
 * Created by Seky on 14. 4. 2014.
 * Trida obsahujici predpis tabulky pro jednotlivy stul
 */
public class Stul {
    protected int id;
    protected int id_polozky;
    protected int mnozstvi;

    /**
     * Constructor
     */
    public Stul(){}

    public Stul(int id_pol, int mnoz){
        this.id_polozky = id_pol;
        this.mnozstvi = mnoz;
    }

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

    public String toString() {
        return "Seznam [id=" + id + ", polozka_id=" + id_polozky + ", mnozstvi_naz=" + mnozstvi + "]";
    }
}
