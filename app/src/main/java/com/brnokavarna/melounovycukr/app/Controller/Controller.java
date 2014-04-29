package com.brnokavarna.melounovycukr.app.Controller;

import android.content.Context;

import com.brnokavarna.melounovycukr.app.Model.MySQLiteHelper;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.*;

import java.util.List;

/**
 * Created by Seky on 14. 4. 2014.
 * Spojuje datovy model s pohledem
 */
public class Controller implements ControllerInterface {
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
        Zadna;//kdyz polozka neni kafe
    }

    /**
     * Constructor
     * @param actual kontext
     */
    public Controller(Context actual){
       db = new MySQLiteHelper(actual);
       //jen jednou              **************************************""""""""""""""""""!!!!!!!!!!!

       //db.addTag(new Tagy( "Popular"));
       //db.addTag(new Tagy( "Ethiopia"));
       //db.addTag(new Tagy( "Kena"));


    }

    public String test(){
        //db.addSeznam(new Seznam(CategoryID.Alkohol.ordinal(),50,"Pivo"));
        //db.addTagSeznam(new TagSeznam(1,1));//popularni
       // db.deleteTagSeznam(1);
        return db.getTag(1)+db.getTag(2)+db.getTag(3);

    }

    /**
     * Pridani polozka do sortimentu
     * @param polozka ID se zde v polozce nespecifikuje
     * @return
     */
    public EnumErrors PridejPolozkuSeznam(Seznam polozka)
    {
        try {
                db.addSeznam(polozka);
            }
        catch(Exception e)
             {
                 return EnumErrors.AddItemError;
             }
        return EnumErrors.Success;
    }

    /**
     * Editace polozky v sortimentu
     * @param polozka polozka v seznamu musi mi definovane i ID
     * @return
     */
    public EnumErrors EditujPolozkuSeznam(Seznam polozka)
    {
        try{
            db.updateItemSeznam(polozka);
        }
        catch(Exception e) {
            return EnumErrors.EditItemError;
        }
        return EnumErrors.Success;
    }

    /**
     * Smazani polozky ze sortimentu
     * @param idPolozky
     * @return
     */
    public EnumErrors SmazPolozkuSeznam(int idPolozky)
    {
        db.deleteItemSeznam(idPolozky);
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
        switch(druhKavy){
            case Ethyopie:

                db.addStul(new Stul());
                break;
            case Kena:
                break;
            case Zadna:
                break;
        }
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
        return db.getAllItemsTrzba();
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

    /**
     * Zobrazi polozku ze sortimentu
     * @param idPolozky
     * @return
     */
    public Seznam ZobrazPolozkuSeznam(int idPolozky)
    {
        return null;
    }

      /**
     * Zobrazi seznam vsech polozek od daneho stolu
     * @param idStolu
     * @return
     */
    public List<Stul> ZobrazVsechnyPolozkyStul(int idStolu)
    {
        return db.getAllItemsStul(idStolu);
    }

    /**
     * Zobrani polozky dane kategorie
     * @param idKategorie
     * @return
     */
    public List<Seznam> ZobrazKategoriiSeznam(CategoryID idKategorie) {
        return db.getAllCategoryItemsSeznam(idKategorie);
    }

    /**
     * Zobrazi seznam popularni polozek
     * @return
     */
    public List<Seznam> ZobrazPopularni()
    {
        return db.vratPopularni();
    }
}
