package com.brnokavarna.melounovycukr.app.Model.Tabulky;

import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;

/**
 * Created by Seky on 14. 4. 2014.
 * Trida obsahujici predpis tabulky pro celkovou denni trzbu
 */
public class CelkovaTrzba{

    private int id;
    private int id_polozky;
    private int druh_kavy;
    private int mnozstvi;

    /**
     * Constructor
     */
    public CelkovaTrzba(){}

    public CelkovaTrzba(int pol, int kava,  int mnoz)
    {
        this.id_polozky = pol;
        this.mnozstvi = mnoz;
        this.druh_kavy = kava;
     }

    public CelkovaTrzba(int idT, int pol, int kava,  int mnoz)
    {
        this.id = idT;
        this.id_polozky = pol;
        this.mnozstvi = mnoz;
        this.druh_kavy = kava;
    }

    /**
     * Getters and setters
     */
    public int getDruh_kavy() {
        return druh_kavy;
    }

    public void setDruh_kavy(int druh_kavy) {
        this.druh_kavy = druh_kavy;
    }

    public int getId_polozky() {
        return id_polozky;
    }

    public void setId_polozky(int id_polozky) {
        this.id_polozky = id_polozky;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(int mnozstvi) {
        this.mnozstvi = mnozstvi;
    }


    public String toString() {
        return "Seznam [id=" + id + ", pol_id=" + id_polozky +"druh_kavy=" + druh_kavy +   ",mnozstvi=" + mnozstvi
                + "]";
    }
}
