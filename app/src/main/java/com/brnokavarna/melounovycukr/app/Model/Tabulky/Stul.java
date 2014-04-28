package com.brnokavarna.melounovycukr.app.Model.Tabulky;

/**
 * Created by Seky on 14. 4. 2014.
 * Trida obsahujici predpis tabulky pro jednotlivy stul
 */
public class Stul {
    private int id;
    private int id_polozky;
    private  int id_stul;
    private int druh_kavy;
    private int mnozstvi;

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

    public int getId_stul() {
        return id_stul;
    }

    public void setId_stul(int id_stul) {
        this.id_stul = id_stul;
    }

    public int getDruh_kavy() {
        return druh_kavy;
    }

    public void setDruh_kavy(int druh_kavy) {
        this.druh_kavy = druh_kavy;
    }


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
