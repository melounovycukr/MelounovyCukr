package com.brnokavarna.melounovycukr.app.Model;

/**
 * Created by Seky on 14. 4. 2014.
 */
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.CelkovaTrzba;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.TagSeznam;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Tagy;


public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ItemsDB";

    public static final String TABLE_SEZNAM = "seznam";
    public static final String TABLE_STUL = "stul";
    public static final String TABLE_CELKOVA_TRZBA = "celkovaTrzba";
    public static final String TABLE_TAGY = "tagy";
    public static final String TABLE_TAGY_SEZNAM = "tagySeznam";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create seznam table
        String CREATE_SEZNAM_TABLE = "CREATE TABLE seznam ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "kategorie_id INTEGER, " +
                "cena FLOAT, "+
                "nazev_zbozi TEXT, "+
                "popularni BOOLEAN)";

        // SQL statement to create stul table
        String CREATE_STUL_TABLE = "CREATE TABLE stul ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_polozka INTEGER, " +
                "id_stul INTEGER, " +
                "druh_kavy INTEGER, "+
                "mnozstvi INTEGER)";

        // SQL statement to create CelkovaTrzba table
        String CREATE_CELKOVA_TRZBA_TABLE = "CREATE TABLE celkovaTrzba ( " +
                "id_trzba INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_polozka INTEGER, " +
                "druh_kavy INTEGER, "+
                "mnozstvi INTEGER )";

        String CREATE_TAGY = "CREATE TABLE tagy ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tag TEXT )";

        String CREATE_TAGY_SEZNAM = "CREATE TABLE tagySeznam ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_polozky INTEGER, " +
                "id_tagu INTEGER )";


        // create tables
        db.execSQL(CREATE_SEZNAM_TABLE);
        db.execSQL(CREATE_STUL_TABLE);
        db.execSQL(CREATE_CELKOVA_TRZBA_TABLE);
        db.execSQL(CREATE_TAGY);
        db.execSQL(CREATE_TAGY_SEZNAM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older items table if existed
        db.execSQL("DROP TABLE IF EXISTS seznam");
        db.execSQL("DROP TABLE IF EXISTS stul");
        db.execSQL("DROP TABLE IF EXISTS celkovaTrzba");
        db.execSQL("DROP TABLE IF EXISTS tagy");
        db.execSQL("DROP TABLE IF EXISTS tagySeznam");

        // create fresh books table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * Operace s tabulkami
     */

    /**
     * Seznam**************************************************************************************
     */
    // Seznam table name
    // Items Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_KATEGORY = "kategorie_id";
    private static final String KEY_CENA = "cena";
    private static final String KEY_NAZEV_ZBOZI = "nazev_zbozi";
    private static final String KEY_POPULARNI = "popularni";

    private static final String[] COLUMNS = {KEY_ID, KEY_KATEGORY, KEY_CENA, KEY_NAZEV_ZBOZI, KEY_POPULARNI};

    public void addSeznam(Seznam seznam){
        //Log.d("addSeznam", seznam.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_KATEGORY, seznam.getKategorie_id());
        values.put(KEY_CENA, seznam.getCena());
        values.put(KEY_NAZEV_ZBOZI, seznam.getNazev_zbozi());
        values.put(KEY_POPULARNI, seznam.isPopularni());

        // 3. insert
        db.insert(TABLE_SEZNAM, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    /**
     * Get item from database with ID
     * @param id
     * @return
     */
    public Seznam getItemSeznam(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_SEZNAM, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        Seznam seznam = new Seznam();
        try{
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object

        seznam.setId(Integer.parseInt(cursor.getString(0)));
        seznam.setKategorie_id(Integer.parseInt(cursor.getString(1)));
        seznam.setCena(cursor.getFloat(2));
        seznam.setNazev_zbozi(cursor.getString(3));
        seznam.setPopularni((Integer.parseInt(cursor.getString(4)) == 1)? true : false);
        } finally{
            cursor.close();
            db.close();
        }

        //Log.d("getSeznam("+id+")", seznam.toString());

        // 5. return book
        return seznam;
    }

    /**
     * Get item from database with ID
     * @param name
     * @return
     */
    public int getIDByName(String name){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Integer pom;
        // 2. build query
        Cursor cursor =
                db.query(TABLE_SEZNAM, // a. table
                        COLUMNS, // b. column names
                        " nazev_zbozi = ?", // c. selections
                        new String[] { name }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        try {
            // 3. if we got results get the first one
            if (cursor != null)
                cursor.moveToFirst();

            pom = Integer.parseInt(cursor.getString(0));
        } finally{
            cursor.close();
        }

        return pom;
        // 4. build book object
        /*Seznam seznam = new Seznam();
        seznam.setId(Integer.parseInt(cursor.getString(0)));
        seznam.setKategorie_id(Integer.parseInt(cursor.getString(1)));
        seznam.setCena(cursor.getFloat(2));
        seznam.setNazev_zbozi(cursor.getString(3));
        seznam.setPopularni((Integer.parseInt(cursor.getString(4)) == 1)? true : false);

        Log.d("getSeznam("+id+")", seznam.toString());

        // 5. return book
        return seznam;*/
    }


    /**
     * Zobrazi vsechny polozky dane kategorie
     * @param id
     * @return
     */
    public List<Seznam> getAllCategoryItemsSeznam(Controller.CategoryID id) {
        List<Seznam> items = new LinkedList<Seznam>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SEZNAM + " WHERE " + KEY_KATEGORY + " = ?";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,  new String[] { String.valueOf(id.ordinal()) });

        try {
            // 3. go over each row, build book and add it to list
            Seznam item = null;
            if (cursor.moveToFirst()) {
                do {
                    item = new Seznam();
                    item.setId(Integer.parseInt(cursor.getString(0)));
                    item.setKategorie_id(Integer.parseInt(cursor.getString(1)));
                    item.setCena(cursor.getFloat(2));
                    item.setNazev_zbozi(cursor.getString(3));
                    item.setPopularni((Integer.parseInt(cursor.getString(4)) == 1) ? true : false);


                    items.add(item);
                } while (cursor.moveToNext());
            }
        } finally{
            cursor.close();
            db.close();
        }

        //Log.d("getAllItems()", items.toString());

        // return books
        return items;
    }


    /**\
     *  Update single item
     * @param item item with concrete id and values to replace
     * @return
     */
    public int updateItemSeznam(Seznam item) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_KATEGORY, item.getKategorie_id());
        values.put(KEY_CENA, item.getCena());
        values.put(KEY_NAZEV_ZBOZI, item.getNazev_zbozi());
        values.put(KEY_POPULARNI, item.isPopularni());

        // 3. updating row
        int i = db.update(TABLE_SEZNAM, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(item.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }



    /**
     * Remove item from table
     * @param itemID
     */
    public void deleteItemSeznam(int itemID) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_SEZNAM,

                KEY_ID+" = ?",
                new String[] { String.valueOf(itemID) });

        // 3. close
        db.close();

        //Log.d("deleteItem", "ID = "+itemID);

    }




    /**
     * Stul*****************************************************************************************
     */
    // Stul table name
    // Items Table Columns names
    private static final String KEY_ID_STUL = "id_stul";
    private static final String KEY_POLOZKA = "id_polozka";
    private static final String KEY_DRUH_KAVY = "druh_kavy";
    private static final String KEY_MNOZSTVI= "mnozstvi";


    private static final String[] COLUMNS_STUL = {KEY_ID, KEY_POLOZKA, KEY_DRUH_KAVY, KEY_ID_STUL, KEY_MNOZSTVI};

    public void addStul(Stul stul){
        //Log.d("addStul", stul.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_POLOZKA, stul.getId_polozky());
        values.put(KEY_ID_STUL, stul.getId_stul());
        values.put(KEY_DRUH_KAVY, stul.getDruh_kavy());
        values.put(KEY_MNOZSTVI, stul.getMnozstvi());

        // 3. insert
        db.insert(TABLE_STUL, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    /**
     * Get item from database with ID
     * @param id
     * @return
     */
    public Stul getItemStul(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_STUL, // a. table
                        COLUMNS_STUL, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        Stul stul = new Stul();
        try{
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object

        stul.setId(Integer.parseInt(cursor.getString(0)));
        stul.setId_polozky(Integer.parseInt(cursor.getString(1)));
        stul.setId_stul(Integer.parseInt(cursor.getString(2)));
        stul.setDruh_kavy(Integer.parseInt(cursor.getString(3)));
        stul.setMnozstvi(Integer.parseInt(cursor.getString(4)));
        } finally{
            cursor.close();
            db.close();
        }

        //Log.d("getStul("+id+")", stul.toString());

        // 5. return book
        return stul;
    }

    /**
     * Ziska seznam vsech polozek na danem stole
     * @param id
     * @return
     */
    public List<Stul> getAllItemsStul(int id) {
        List<Stul> items = new LinkedList<Stul>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_STUL + " WHERE " + KEY_ID_STUL + " = ?";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[] { String.valueOf(id) });

        try {
            // 3. go over each row, build book and add it to list
            Stul item = null;
            if (cursor.moveToFirst()) {
                do {
                    item = new Stul();
                    item.setId(Integer.parseInt(cursor.getString(0)));
                    item.setId_polozky(Integer.parseInt(cursor.getString(1)));
                    item.setId_stul(Integer.parseInt(cursor.getString(2)));
                    item.setDruh_kavy(Integer.parseInt(cursor.getString(3)));
                    item.setMnozstvi(Integer.parseInt(cursor.getString(4)));

                    items.add(item);
                } while (cursor.moveToNext());
            }
        } finally{
            cursor.close();
            db.close();
        }

        //Log.d("getAllItems()", items.toString());

        // return books
        return items;
    }

    /**\
     *  Update single item
     * @param item item with concrete id and values to replace
     * @return
     */
    public int updateItemStul(Stul item) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_POLOZKA, item.getId_polozky());
        values.put(KEY_ID_STUL, item.getId_stul());
        values.put(KEY_DRUH_KAVY, item.getDruh_kavy());
        values.put(KEY_MNOZSTVI, item.getMnozstvi());

        // 3. updating row
        int i = db.update(TABLE_STUL, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(item.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    /**
     * Remove item from table
     * @param itemID
     */
    public void deleteItemStul(int itemID) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_STUL,

                KEY_ID+" = ?",
                new String[] { String.valueOf(itemID) });

        // 3. close
        db.close();

        //Log.d("deleteItem", "ID = "+itemID);

    }


    /**
     * Kontrola jestli uz dana kava u daneho stolu neni
     * @param itemID
     * @param kava
     * @return true jestli je a naopak
     */
    public boolean CheckTagTable(int itemID, Controller.TagKavy kava)
    {
        String query = "SELECT  * FROM " + TABLE_STUL + " ts, " + TABLE_TAGY_SEZNAM + " tg " + " WHERE ts." + KEY_ID + " = "
                + "tg." + KEY_TAG_POLOZKA_ID + " AND tg." + KEY_TAG_ID + " = " + 1;

        // 2. get reference to writable DB
        return true;
    }


    /**
     * TRZBA*****************************************************************************************
     */
    // TRZBA table name
    // Items Table Columns names
    private static final String KEY_ID_TRZBA = "id_trzba";
    //private static final String KEY_POLOZKA = "id_polozka";
    //private static final String KEY_MNOZSTVI= "mnozstvi";


    private static final String[] COLUMNS_TRZBA = {KEY_ID_TRZBA, KEY_DRUH_KAVY, KEY_POLOZKA, KEY_MNOZSTVI};

    public void addTrzba(CelkovaTrzba stul){
        //Log.d("addTrzba", stul.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_POLOZKA, stul.getId_polozky());
        values.put(KEY_DRUH_KAVY, stul.getDruh_kavy());
        values.put(KEY_MNOZSTVI, stul.getMnozstvi());

        // 3. insert
        db.insert(TABLE_CELKOVA_TRZBA, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    /**
     * Get item from database with ID
     * @param id
     * @return
     */
    public CelkovaTrzba getItemTrzba(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_CELKOVA_TRZBA, // a. table
                        COLUMNS_TRZBA, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        CelkovaTrzba stul = new CelkovaTrzba();
        try{
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object

        stul.setId(Integer.parseInt(cursor.getString(0)));
        stul.setId_polozky(Integer.parseInt(cursor.getString(1)));
        stul.setDruh_kavy(Integer.parseInt(cursor.getString(2)));
        stul.setMnozstvi(Integer.parseInt(cursor.getString(3)));
        } finally{
            cursor.close();
            db.close();
        }

        //Log.d("getTrzba("+id+")", stul.toString());

        // 5. return book
        return stul;
    }

    /**
     * Get list of all items
     * @return
     */
    public List<CelkovaTrzba> getAllItemsTrzba() {
        List<CelkovaTrzba> items = new LinkedList<CelkovaTrzba>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CELKOVA_TRZBA;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        try{
        // 3. go over each row, build book and add it to list
        CelkovaTrzba item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new CelkovaTrzba();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setId_polozky(Integer.parseInt(cursor.getString(1)));
                item.setDruh_kavy(Integer.parseInt(cursor.getString(2)));
                item.setMnozstvi(Integer.parseInt(cursor.getString(3)));

                items.add(item);
            } while (cursor.moveToNext());
        }
        } finally{
            cursor.close();
            db.close();
        }
        //Log.d("getAllItems()", items.toString());

        // return books
        return items;
    }

    /**\
     *  Update single item
     * @param item item with concrete id and values to replace
     * @return
     */
    public int updateItemTrzba(CelkovaTrzba item) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_POLOZKA, item.getId_polozky());
        values.put(KEY_DRUH_KAVY, item.getDruh_kavy());
        values.put(KEY_MNOZSTVI, item.getMnozstvi());

        // 3. updating row
        int i = db.update(TABLE_CELKOVA_TRZBA, //table
                values, // column/value
                KEY_ID_TRZBA+" = ?", // selections
                new String[] { String.valueOf(item.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    /**
     * Remove item from table
     * @param itemID
     */
    public void deleteItemTrzba(int itemID) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_CELKOVA_TRZBA,

                KEY_ID_TRZBA+" = ?",
                new String[] { String.valueOf(itemID) });

        // 3. close
        db.close();

        //Log.d("deleteItem", "ID = "+itemID);

    }

    /**
     * Delete whole table of celkova_trzba
     */
    public void deleteCelaTrzba()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CELKOVA_TRZBA, null, null);
        db.close();
    }



    /**
     * Tagy*****************************************************************************************
     */
    // TAGY table name
    // Items Table Columns names
    private static final String KEY_TAG = "tag";



    private static final String[] COLUMNS_TAGY = {KEY_TAG};

    /**
     * Pridani tagu
     * @param stul tag
     */
    public void addTag(Tagy stul){
        //Log.d("addTag", stul.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TAG, stul.getTag());

        // 3. insert
        db.insert(TABLE_TAGY, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    /**
     * Vrati nazev daneho tagu
     * @param id id pozadovaneho tagu
     * @return dany tag
     */
    public String getTag(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_TAGY, // a. table
                        COLUMNS_TAGY, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        String pom;
        try{
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
            pom = cursor.getString(0);
        } finally{
            cursor.close();
            db.close();
        }

        // 4. build book object
       // Tagy stul = new Tagy();
        //stul.setId(Integer.parseInt(cursor.getString(0)));
       // stul.setTag(cursor.getString(1));

        //Log.d("getTrzba("+id+")", stul.toString());

        // 5. return book
        return pom;
    }


    /**
     * TagSeznam*****************************************************************************************
     */
    // TAGY_SEZNAM table name
    // Items Table Columns names
    private static final String KEY_TAG_POLOZKA_ID = "id_polozky";
    private static final String KEY_TAG_ID = "id_tagu";



    private static final String[] COLUMNS_TAG_SEZNAM = {KEY_TAG_POLOZKA_ID, KEY_TAG_ID};

    /**
     * Pridani polozky do vazebni tabulky
     * @param stul tag seznam vazba
     */
    public void addTagSeznam(TagSeznam stul){
        //Log.d("addTag", stul.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TAG_POLOZKA_ID, stul.getId_polozky());
        values.put(KEY_TAG_ID, stul.getId_tagu());

        // 3. insert
        db.insert(TABLE_TAGY_SEZNAM, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    /**
     * Smazani vazby
     * @param itemID id vazby
     */
    public void deleteTagSeznam(int itemID) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_TAGY_SEZNAM,

                KEY_ID+" = ?",
                new String[] { String.valueOf(itemID) });

        // 3. close
        db.close();

        //Log.d("deleteItem", "ID = "+itemID);
    }

    /**
     * Popularni polozky dle tagu
     * @return seznam popularnich polozek
     */
    public List<Seznam> vratPopularni()
    {
        List<Seznam> items = new LinkedList<Seznam>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SEZNAM + " WHERE " + KEY_POPULARNI + " = "+ 1;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        try{
        // 3. go over each row, build book and add it to list
        Seznam item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new Seznam();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setKategorie_id(Integer.parseInt(cursor.getString(1)));
                item.setCena(cursor.getFloat(2));
                item.setNazev_zbozi(cursor.getString(3));
                item.setPopularni(true);

                items.add(item);
            } while (cursor.moveToNext());
        }
        } finally{
            cursor.close();
            db.close();
        }
        //Log.d("getAllItems()", items.toString());

        // return books
        return items;

    }




}