package com.example.shoes;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


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

    public List<Shoe> readAll(){

        List<Shoe> shoeArrayList = new ArrayList<>();

        String query = "SELECT * FROM shoes ORDER by DateAdded";

        Cursor cursor = _db.rawQuery(query, null);

        while(cursor.moveToNext()){
            @SuppressLint("Range") Shoe shoe = new Shoe(
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID"))),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getString(cursor.getColumnIndex("ImageSrc")),
                    Float.parseFloat(cursor.getString(cursor.getColumnIndex("Price"))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("Quantity"))),
                    cursor.getString(cursor.getColumnIndex("DateAdded"))
            );

            shoeArrayList.add(shoe);
        }

        return shoeArrayList;

    }

    public Shoe getById(Integer shoeId){

        String query = "SELECT * FROM shoes WHERE id = " + shoeId.toString();

        Cursor cursor = _db.rawQuery(query, null);

        cursor.moveToFirst();

        @SuppressLint("Range") Shoe shoe = new Shoe(
            Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID"))),
            cursor.getString(cursor.getColumnIndex("Name")),
            cursor.getString(cursor.getColumnIndex("ImageSrc")),
            Float.parseFloat(cursor.getString(cursor.getColumnIndex("Price"))),
            Integer.parseInt(cursor.getString(cursor.getColumnIndex("Quantity"))),
            cursor.getString(cursor.getColumnIndex("DateAdded"))
        );

        return shoe;

    }

    public void insert(String name, String imageSrc, float price, Integer quantity, String dateAdded){
        String query = "INSERT INTO shoes(" +
                "Name, ImageSrc, Price, Quantity, DateAdded" +
                ") VALUES(?, ?, ?, ?, ?)";

        _db.execSQL(query, new Object[]{
                name, imageSrc, price, quantity, dateAdded
        });
    }

    public void update(Integer id, String name, String imageSrc, float price, Integer quantity){
        String query = "UPDATE shoes SET " +
                "Name = ?, ImageSrc = ?, Price = ?, Quantity = ? WHERE id = ?";

        _db.execSQL(query, new Object[]{
                name, imageSrc, price, quantity, id
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