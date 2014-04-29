package com.brnokavarna.melounovycukr.app.Controller;

import com.brnokavarna.melounovycukr.app.Model.Tabulky.CelkovaTrzba;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;

import java.util.List;

/**
 * Created by Seky on 28. 4. 2014.
 */
interface ControllerInterface {

    public Controller.EnumErrors PridejPolozkuSeznam(Seznam polozka);//check
    public Controller.EnumErrors EditujPolozkuSeznam(Seznam polozka);//check je potreba i ID
    public Controller.EnumErrors SmazPolozkuSeznam(int idPolozky);//check
    public Controller.EnumErrors PridejPolozkuStul(int idPolozky, Controller.TagKavy druhKavy, int idStolu);
    public Controller.EnumErrors ZaplatPolozkuStul(int idPolozky, Controller.TagKavy druhKavy, int idStolu);
    public List<CelkovaTrzba> VypisTrzbu();//check
    public float ZjistiCenu(int idPolozky, Controller.TagKavy druhKavy);
    public Seznam ZobrazPolozkuSeznam(int idPolozky);// druh kavy se urcuje az u stolu
    public List<Stul> ZobrazVsechnyPolozkyStul(int idStolu);//check //dodelat tagy
    public List<Seznam> ZobrazKategoriiSeznam(Controller.CategoryID idKategorie);//check
    public List<Seznam> ZobrazPopularni();//check
    //vypsat popularni
}
