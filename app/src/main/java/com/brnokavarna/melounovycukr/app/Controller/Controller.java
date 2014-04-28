package com.brnokavarna.melounovycukr.app.Controller;

import android.content.Context;

import com.brnokavarna.melounovycukr.app.Model.MySQLiteHelper;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.*;

import java.util.List;

/**
 * Created by Seky on 14. 4. 2014.
 * Spojuje datovy model s pohledem
 */
public class Controller {
    private MySQLiteHelper db;

    /**
     * Chyby
     */
    public enum EnumErrors{
        Success,
        AddItemError,
        DeleteItemError,
        EditItemError,
        ReadError;
    }

    /**
     * Kategorie
     */
    public enum CategoryID{
        Popularni,
        Kava,
        Dobroty,
        Alkohol,
        Ostatni;
    }

    /**
     * Tag pro druh kavy
     */
    public enum TagKavy{
        Ethyopie,
        Kena,
        Zadne;//kdyz polozka neni kafe
    }

    public Controller(Context actual){
       db = new MySQLiteHelper(actual);
    }

    public void test(){

        //MySQLiteHelper db = new MySQLiteHelper(this);

/*
         db.addSeznam(new Seznam(1 , 50, "cen"));
         db.addStul(new Stul(2, 50));
         db.addTrzba(new CelkovaTrzba(5, 60));*/
        //db.addSeznam(new Seznam(1 ,"Pepa", 50, "cen"));
        //Seznam test = db.getItem(1);
/*
        Seznam tral = new Seznam();
        tral.setId(1);
        tral.setKategorie_id(5444);
        tral.setKategorie_nazev("hovnooo");
        tral.setNazev_zbozi("bobeeeeek");
        tral.setCena(500000);
        db.updateItemSeznam(tral);
*/

        //CelkovaTrzba test = new CelkovaTrzba();
        // test.setId(0);
        // test.setId_polozky(510);
        // test.setMnozstvi(3);


      /*
        List<Seznam> list = db.getAllItemsSeznam();
        List<Stul> list2 = db.getAllItemsStul();
        List<CelkovaTrzba> list3 = db.getAllItemsTrzba();
*/
        // db.deleteItem(2,db.TABLE_SEZNAM)
        //db.getAllItems();
       /* List<Seznam> list = db.getAllItemsSeznam();
        List<Stul> list2 = db.getAllItemsStul();
        List<CelkovaTrzba> list3 = db.getAllItemsTrzba();*/
    }

    /**
     * Pridani polozky do sortimentu
     * @param nazev
     * @param cena
     * @param kategorieID vybrat Katerogii z CategoryID
     * @return chyba nebo success
     */
    public EnumErrors PridejPolozkuSeznam(String nazev, float cena, CategoryID kategorieID)
    {
        return EnumErrors.Success;
    }

    /**
     * Editace polozky v sortimentu
     * @param idPolozky
     * @param nazev
     * @param cena
     * @param kategoieID vybrat Katerogii z CategoryID
     * @return
     */
    public EnumErrors EditujPolozkuSeznam(int idPolozky, String nazev, float cena, CategoryID kategoieID)
    {
        return EnumErrors.Success;
    }

    /**
     * Smazazni polozky ze sortimentu
     * @param idPolozky
     * @return
     */
    public EnumErrors SmazPolozkuSeznam(int idPolozky)
    {
        return EnumErrors.Success;
    }

    /**
     *  Pridani polozky k danemu stolu
     * @param idPolozky
     * @param druhKavy
     * @param idStolu
     * @return
     */
    public EnumErrors PridejPolozkuStul(int idPolozky, TagKavy druhKavy, int idStolu)
    {
        //mnozstvi daneho ID++
        return EnumErrors.Success;
    }

    /**
     * Odstraneni polozek u stolu (pro zaplaceni casti uctu) a pridani do celkove trzby
     * @param idPolozky
     * @param druhKavy
     * @param idStolu
     * @return
     */
    public EnumErrors ZaplatPolozkuStul(int idPolozky, TagKavy druhKavy, int idStolu)
    {
        //pridat do celkove trzby
        return EnumErrors.Success;
    }

    /**
     * Vypsani celkove denni trzby
     * @return Seznam polozek z denni trzby
     */
    public List<CelkovaTrzba>  VypisTrzbu()
    {
        return null;
    }

    /**
     * Pro zjiseni ceny dane polozky
     * @param idPolozky
     * @return
     */
    public float ZjistiCenu(int idPolozky, TagKavy druhKavy)
    {
        return 0;
    }


}
