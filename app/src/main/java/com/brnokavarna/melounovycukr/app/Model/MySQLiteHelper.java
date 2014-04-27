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

import com.brnokavarna.melounovycukr.app.Model.Tabulky.CelkovaTrzba;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;



public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ItemsDB";

    public static final String TABLE_SEZNAM = "seznam";
    public static final String TABLE_STUL = "stul";
    public static final String TABLE_CELKOVA_TRZBA = "celkovaTrzba";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create seznam table
        String CREATE_SEZNAM_TABLE = "CREATE TABLE seznam ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "kategorie_id INTEGER, " +
                "kategorie_nazev TEXT, "+
                "cena FLOAT, "+
                "nazev_zbozi TEXT )";

        // SQL statement to create stul table
        String CREATE_STUL_TABLE = "CREATE TABLE stul ( " +
                "id_stul INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_polozka INTEGER, " +
                "mnozstvi INTEGER )";

        // SQL statement to create CelkovaTrzba table
        String CREATE_CELKOVA_TRZBA_TABLE = "CREATE TABLE celkovaTrzba ( " +
                "id_trzba INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_polozka INTEGER, " +
                "mnozstvi INTEGER )";


        // create books table
        db.execSQL(CREATE_SEZNAM_TABLE);
        db.execSQL(CREATE_STUL_TABLE);
        db.execSQL(CREATE_CELKOVA_TRZBA_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older items table if existed
        db.execSQL("DROP TABLE IF EXISTS seznam");
        db.execSQL("DROP TABLE IF EXISTS stul");
        db.execSQL("DROP TABLE IF EXISTS celkovaTrzba");

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
    private static final String KEY_KATEGORY_NAME = "kategorie_nazev";
    private static final String KEY_CENA = "cena";
    private static final String KEY_NAZEV_ZBOZI = "nazev_zbozi";

    private static final String[] COLUMNS = {KEY_ID, KEY_KATEGORY, KEY_KATEGORY_NAME, KEY_CENA, KEY_NAZEV_ZBOZI};

    public void addSeznam(Seznam seznam){
        Log.d("addSeznam", seznam.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_KATEGORY, seznam.getKategorie_id());
        values.put(KEY_KATEGORY_NAME, seznam.getKategorie_nazev());
        values.put(KEY_CENA, seznam.getCena());
        values.put(KEY_NAZEV_ZBOZI, seznam.getNazev_zbozi());

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

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Seznam seznam = new Seznam();
        seznam.setId(Integer.parseInt(cursor.getString(0)));
        seznam.setKategorie_id(Integer.parseInt(cursor.getString(1)));
        seznam.setKategorie_nazev(cursor.getString(2));
        seznam.setCena(cursor.getFloat(3));
        seznam.setNazev_zbozi(cursor.getString(4));

        Log.d("getSeznam("+id+")", seznam.toString());

        // 5. return book
        return seznam;
    }


    /**
     * Get list of all items
     * @return
     */
    public List<Seznam> getAllItemsSeznam() {
        List<Seznam> items = new LinkedList<Seznam>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SEZNAM;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Seznam item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new Seznam();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setKategorie_id(Integer.parseInt(cursor.getString(1)));
                item.setKategorie_nazev(cursor.getString(2));
                item.setCena(cursor.getFloat(3));
                item.setNazev_zbozi(cursor.getString(4));


                items.add(item);
            } while (cursor.moveToNext());
        }

        Log.d("getAllItems()", items.toString());

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
        values.put(KEY_KATEGORY_NAME, item.getKategorie_nazev());
        values.put(KEY_CENA, item.getCena());
        values.put(KEY_NAZEV_ZBOZI, item.getNazev_zbozi());

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
     * List of items within some category                     TODO
     * @return
     */
    public List<Seznam> showCategory()
    {
        return null;
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

        Log.d("deleteItem", "ID = "+itemID);

    }




    /**
     * Stul*****************************************************************************************
     */
    // Stul table name
    // Items Table Columns names
    private static final String KEY_ID_STUL = "id_stul";
    private static final String KEY_POLOZKA = "id_polozka";
    private static final String KEY_MNOZSTVI= "mnozstvi";


    private static final String[] COLUMNS_STUL = {KEY_ID_STUL, KEY_POLOZKA, KEY_MNOZSTVI};

    public void addStul(Stul stul){
        Log.d("addStul", stul.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_POLOZKA, stul.getId_polozky());
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

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Stul stul = new Stul();
        stul.setId(Integer.parseInt(cursor.getString(0)));
        stul.setId_polozky(Integer.parseInt(cursor.getString(1)));
        stul.setMnozstvi(Integer.parseInt(cursor.getString(2)));

        Log.d("getStul("+id+")", stul.toString());

        // 5. return book
        return stul;
    }

    /**
     * Get list of all items
     * @return
     */
    public List<Stul> getAllItemsStul() {
        List<Stul> items = new LinkedList<Stul>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_STUL;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
       Stul item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new Stul();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setId_polozky(Integer.parseInt(cursor.getString(1)));
                item.setMnozstvi(Integer.parseInt(cursor.getString(2)));

                items.add(item);
            } while (cursor.moveToNext());
        }

        Log.d("getAllItems()", items.toString());

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
        values.put(KEY_MNOZSTVI, item.getMnozstvi());

        // 3. updating row
        int i = db.update(TABLE_STUL, //table
                values, // column/value
                KEY_ID_STUL+" = ?", // selections
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

                KEY_ID_STUL+" = ?",
                new String[] { String.valueOf(itemID) });

        // 3. close
        db.close();

        Log.d("deleteItem", "ID = "+itemID);

    }


    /**
     * TRZBA*****************************************************************************************
     */
    // TRZBA table name
    // Items Table Columns names
    private static final String KEY_ID_TRZBA = "id_trzba";
    //private static final String KEY_POLOZKA = "id_polozka";
    //private static final String KEY_MNOZSTVI= "mnozstvi";


    private static final String[] COLUMNS_TRZBA = {KEY_ID_TRZBA, KEY_POLOZKA, KEY_MNOZSTVI};

    public void addTrzba(CelkovaTrzba stul){
        Log.d("addTrzba", stul.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_POLOZKA, stul.getId_polozky());
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

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        CelkovaTrzba stul = new CelkovaTrzba();
        stul.setId(Integer.parseInt(cursor.getString(0)));
        stul.setId_polozky(Integer.parseInt(cursor.getString(1)));
        stul.setMnozstvi(Integer.parseInt(cursor.getString(2)));

        Log.d("getTrzba("+id+")", stul.toString());

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

        // 3. go over each row, build book and add it to list
        CelkovaTrzba item = null;
        if (cursor.moveToFirst()) {
            do {
                item = new CelkovaTrzba();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setId_polozky(Integer.parseInt(cursor.getString(1)));
                item.setMnozstvi(Integer.parseInt(cursor.getString(2)));

                items.add(item);
            } while (cursor.moveToNext());
        }

        Log.d("getAllItems()", items.toString());

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

        Log.d("deleteItem", "ID = "+itemID);

    }




}