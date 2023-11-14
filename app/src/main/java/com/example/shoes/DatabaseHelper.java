package com.example.shoes;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "shoes.db";
    public static final int DBVERSION = 1;

    public static String DBCREATE = "" +
            "CREATE TABLE shoes(" +
            "ID integer PRIMARY KEY AUTOINCREMENT, " +
            "Name text not null, " +
            "ImageSrc text not null, " +
            "Price real not null, " +
            "Quantity integer DEFAULT 0, " +
            "DateAdded text not null" +
            ")";

    public static String DBDROP = "DROP TABLE shoes; ";

    private SQLiteDatabase _db = null;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
        _db = this.getWritableDatabase();
    }

    public ArrayList<Shoe> readAll(){
        String query = "SELECT * FROM shoes";

        Cursor cursor = _db.rawQuery(query, null);

        ArrayList<Shoe> shoeArrayList
                = new ArrayList<>();

        return shoeArrayList;

    }

    public void insert(String name, String imageSrc, float price, Integer quantity, String dateAdded){
        String query = "INSERT INTO shoes(" +
                "Name, ImageSrc, Price, Quantity, DateAdded" +
                ") VALUES(?, ?, ?, ?, ?)";

        _db.execSQL(query, new Object[]{
                name, imageSrc, price, quantity, dateAdded
        });

    }

    @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBCREATE);
    }

    @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i1 > i){
            sqLiteDatabase.execSQL(DBDROP);
            sqLiteDatabase.execSQL(DBCREATE);
        }
    }
}