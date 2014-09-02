package com.brnokavarna.melounovycukr.app.Controller;

import android.content.Context;
import android.util.Log;

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
        Ostatni,
        Popularni;
    }

    /**
     * Tag pro druh kavy
     */
    public enum TagKavy{
        Ethyopia,
        Keňa,
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
        //db.addSeznam(new Seznam(CategoryID.Alkohol.ordinal(),50,"Pivo",true));
        //db.addSeznam(new Seznam(CategoryID.Alkohol.ordinal(),100,"Fernet",true));
        //db.addTagSeznam(new TagSeznam(1,1));//popularni
       // db.deleteTagSeznam(1);
        //db.updateItemStul(new Stul())
        //return db.getTag(1)+db.getTag(2)+db.getTag(3);
        return null;
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
    public EnumErrors PridejPolozkuStul(int idStolu, int idPolozky, TagKavy druhKavy )
    {
        Stul current;

        if((current = ZobrazPolozkuStul(idPolozky, idStolu, druhKavy)) == null)
            db.addStul(new Stul(idPolozky, idStolu, druhKavy.ordinal(), 1));
        else
            db.updateItemStul(new Stul(current.getId(), idPolozky, idStolu, druhKavy.ordinal(), current.getMnozstvi() + 1));

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
    public EnumErrors ZaplatPolozkuStul(int idStolu, int idPolozky, TagKavy druhKavy)
    {
        Stul current;

        if((current = ZobrazPolozkuStul(idPolozky, idStolu, druhKavy)) != null) {
            if(current.getMnozstvi() - 1 <= 0)//kdyz jsme na 0 tak smazat
                db.deleteItemStul(current.getId());
            else
                db.updateItemStul(new Stul(current.getId(), idPolozky, idStolu, druhKavy.ordinal(), current.getMnozstvi() - 1));
        }


        //pridat do celkove trzby
        List<CelkovaTrzba> currentList = ZobrazTrzbu();
        CelkovaTrzba currentTrzba = null;

        for(int i  = 0; i < currentList.size(); i++)
        {
            if(currentList.get(i).getId_polozky() == idPolozky && currentList.get(i).getDruh_kavy() == druhKavy.ordinal())
                currentTrzba = currentList.get(i);
        }
        if(currentTrzba != null)
            db.updateItemTrzba(new CelkovaTrzba(currentTrzba.getId(), idPolozky, druhKavy.ordinal(), currentTrzba.getMnozstvi() + 1));
        else
            db.addTrzba(new CelkovaTrzba(idPolozky, druhKavy.ordinal(), 1));
        //db.addTrzba();

        return EnumErrors.Success;
    }

    /**
     * Odstraneni polozek u stolu pri chybe obsluhy
     * @param idPolozky
     * @param druhKavy
     * @param idStolu
     * @return
     */
    public EnumErrors OdstranPolozkuStul(int idStolu, int idPolozky, TagKavy druhKavy)
    {
        Stul current;

        if((current = ZobrazPolozkuStul(idPolozky, idStolu, druhKavy)) != null) {
            if(current.getMnozstvi() - 1 <= 0)//kdyz jsme na 0 tak smazat
                db.deleteItemStul(current.getId());
            else
                db.updateItemStul(new Stul(current.getId(), idPolozky, idStolu, druhKavy.ordinal(), current.getMnozstvi() - 1));
        }

        return EnumErrors.Success;
    }

    /**
     * Vypsani celkove denni trzby
     * @return Seznam polozek z denni trzby
     */
    public List<CelkovaTrzba>  ZobrazTrzbu()
    {
        return db.getAllItemsTrzba();
    }

    /**
     * Zobrazi polozku ze sortimentu
     * @param idPolozky
     * @return
     */
    public Seznam ZobrazPolozkuSeznam(int idPolozky)
    {
        return db.getItemSeznam(idPolozky);
    }

    /**
     * Zobrazi polozku ze sortimentu
     * @param nazev
     * @return
     */
    public int ZobrazIDPolozkySeznamPodleNazvu(String nazev)
    {
        return db.getIDByName(nazev);
    }

    /**
     * Zobrazi polozku u daneho stolu
     * @param idPolozky
     * @param idStolu
     * @return stul nebo null
     */
    public Stul ZobrazPolozkuStul(int idPolozky, int idStolu, TagKavy kava)
    {

        List<Stul> temp  = db.getAllItemsStul(idStolu);
        for(int i  = 0; i < temp.size(); i++)
        {
            if(temp.get(i).getId_polozky() == idPolozky && temp.get(i).getDruh_kavy() == kava.ordinal())
                return temp.get(i);
        }
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


    /**
     * Vymazani trzby
     */
    public void VymazTrzbu() {
        db.deleteCelaTrzba();
    }

    /**
     * Pridani popularni
     * @param id existujici ID v seznamu
     */
    public void PridejPopularni(int id)
    {
        Seznam current = ZobrazPolozkuSeznam(id);
        db.updateItemSeznam(new Seznam(id, current.getKategorie_id(), (float)current.getCena(), current.getNazev_zbozi(), true));
    }

    /**
     * Odebrani popularniho
     * @param id existujici ID v seznamu
     */
    public void SmazPopularni(int id)
    {
        Seznam current = ZobrazPolozkuSeznam(id);
        db.updateItemSeznam(new Seznam(id, current.getKategorie_id(), (float)current.getCena(), current.getNazev_zbozi(), false));
    }
}
