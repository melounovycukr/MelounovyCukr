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
     * Seznam*******************************************
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
    public Seznam getItem(int id){

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
    public List<Seznam> getAllItems() {
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
    public int updateItem(Seznam item) {

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
     * Delete single time from database
     * @param itemID id
     * @param TABLE which table
     */
    public void deleteItem(int itemID, String TABLE) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE,

                KEY_ID+" = ?",
                new String[] { String.valueOf(itemID) });

        // 3. close
        db.close();

        Log.d("deleteItem", "ID = "+itemID);

    }

     /**
     * List of items within some category
     * @return
     */
    public List<Seznam> showCategory()
    {
        return null;
    }
}