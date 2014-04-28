package com.brnokavarna.melounovycukr.app.Controller;

import com.brnokavarna.melounovycukr.app.Model.Tabulky.CelkovaTrzba;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;

import java.util.List;

/**
 * Created by Seky on 28. 4. 2014.
 */
interface ControllerInterface {

    public Controller.EnumErrors PridejPolozkuSeznam(String nazev, float cena, Controller.CategoryID kategorieID);
    public Controller.EnumErrors EditujPolozkuSeznam(int idPolozky, String nazev, float cena, Controller.CategoryID kategoieID);
    public Controller.EnumErrors SmazPolozkuSeznam(int idPolozky);//check
    public Controller.EnumErrors PridejPolozkuStul(int idPolozky, Controller.TagKavy druhKavy, int idStolu);
    public Controller.EnumErrors ZaplatPolozkuStul(int idPolozky, Controller.TagKavy druhKavy, int idStolu);
    public List<CelkovaTrzba> VypisTrzbu();
    public float ZjistiCenu(int idPolozky, Controller.TagKavy druhKavy);
    public Seznam ZobrazPolozkuSeznam(int idPolozky);// druh kavy se urcuje az u stolu
    public List<Stul> ZobrazVsechnyPolozkyStul(int idStolu);//check
    public List<Seznam> ZobrazKategoriiSeznam(Controller.CategoryID idKategorie);//check
    //vypsat popularni
}
