package com.brnokavarna.melounovycukr.app.Model.Tabulky;

/**
 * Created by Seky on 29. 4. 2014.
 */
//tag jen pro urceni popularni
public class TagSeznam {
    private int id;
    private int id_polozky;
    private int id_tagu;

    /**
     * Constructor
     */
    //public TagSeznam(){}
    public TagSeznam(int idPo, int idT)
    {
        this.id_polozky = idPo;
        this.id_tagu = idT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_polozky() {
        return id_polozky;
    }

    public void setId_polozky(int id_polozky) {
        this.id_polozky = id_polozky;
    }

    public int getId_tagu() {
        return id_tagu;
    }

    public void setId_tagu(int id_tagu) {
        this.id_tagu = id_tagu;
    }
}
