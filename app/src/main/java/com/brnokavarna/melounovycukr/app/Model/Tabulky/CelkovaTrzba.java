package com.brnokavarna.melounovycukr.app.Model.Tabulky;

import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;

/**
 * Created by Seky on 14. 4. 2014.
 * Trida obsahujici predpis tabulky pro celkovou denni trzbu
 */
public class CelkovaTrzba extends Stul{
     /**
     * Constructor
     */
    public CelkovaTrzba(){}

    public CelkovaTrzba(int pol, int mnoz)
    {
        this.id_polozky = pol;
        this.mnozstvi = mnoz;
    }
}
