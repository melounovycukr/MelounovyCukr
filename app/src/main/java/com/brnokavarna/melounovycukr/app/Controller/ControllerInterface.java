package com.brnokavarna.melounovycukr.app.Controller;

import com.brnokavarna.melounovycukr.app.Model.Tabulky.CelkovaTrzba;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;

import java.util.List;

/**
 * Created by Seky on 28. 4. 2014.
 */
interface ControllerInterface {

    public Controller.EnumErrors PridejPolozkuSeznam(Seznam polozka);//check Seznam(int katId, float cen, String zboz, boolean pop)
    public Controller.EnumErrors EditujPolozkuSeznam(Seznam polozka);//check je potreba i ID Seznam(int idPol, int katId, float cen, String zboz, boolean pop)
    public Controller.EnumErrors SmazPolozkuSeznam(int idPolozky);//check
    public Controller.EnumErrors PridejPolozkuStul(int idStolu, int idPolozky, Controller.TagKavy druhKavy);//check
    public Controller.EnumErrors ZaplatPolozkuStul(int idStolu, int idPolozky, Controller.TagKavy druhKavy);//check
    public List<CelkovaTrzba> ZobrazTrzbu();//check vypsani celkove trzby
    //public float ZjistiCenu(int idPolozky);//mozna precist z polozky...resp funkce ZobrazPolozku
    public Seznam ZobrazPolozkuSeznam(int idPolozky);//check druh kavy se urcuje az u stolu
    public Stul ZobrazPolozkuStul(int idPolozky, int idStolu, Controller.TagKavy kava);//check druh kavy se urcuje az u stolu
    public List<Stul> ZobrazVsechnyPolozkyStul(int idStolu);//check
    public List<Seznam> ZobrazKategoriiSeznam(Controller.CategoryID idKategorie);//check
    public List<Seznam> ZobrazPopularni();//check
    public void VymazTrzbu();//check vymazani cele trzby
    public void PridejPopularni(int id);//pridej popularni skrze jeho ID
    public void SmazPopularni(int id);//odeber popularni skrze jeho ID
    //pridat popularni resp nastavit? ?
    //zaplaceni lze i z prazdneho stolu OSETRIT ZDE NEBO NA VIEW CASTI?
}
