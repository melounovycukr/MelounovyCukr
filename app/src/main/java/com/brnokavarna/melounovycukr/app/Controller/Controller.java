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

    public Controller(Context actual){
       db = new MySQLiteHelper(actual);
    }

    public void test(){

        //MySQLiteHelper db = new MySQLiteHelper(this);


        // db.addSeznam(new Seznam(1 ,"Pepa", 50, "cen"));
        // db.addStul(new Stul(2, 50));
        // db.addTrzba(new CelkovaTrzba(5, 60));
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
        List<Seznam> list = db.getAllItemsSeznam();
        List<Stul> list2 = db.getAllItemsStul();
        List<CelkovaTrzba> list3 = db.getAllItemsTrzba();
    }
}
