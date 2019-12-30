package com.example.hpuser.stockmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

/**
 * Created by HP USER on 05/06/2018.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    private static final int Version = 2;
    private static final String DATABASE_NAME = "stockmanager.db";
    private static final String TABLE_NAME = "Stockitem";
    private static final String COLUMN_ITEM_NAME = "itemName";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_QRT = "qrt";
    private static final String COLUMN_IMG = "img";

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Stockitem(itemName TEXT,id TEXT PRIMARY KEY,date STRING, price TEXT,qrt TEXT,img BLOB)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS"+ TABLE_NAME);
        onCreate(db);
    }


    public long AddItem(String item_name, String id, String date, String price, String qrt, byte[] imgData) {
        SQLiteDatabase db = getWritableDatabase();
        long result;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, item_name);
        values.put(COLUMN_ID, id);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_QRT, qrt);
        values.put(COLUMN_IMG, imgData);

        result = db.insert(TABLE_NAME, null, values);
        return result;
    }

    public ArrayList<Model> viewDatabase() {
        Model myModel =null;
        ArrayList<Model> ListModel = new  ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String Query = "SELECT * FROM  Stockitem";
        //pointing to the first record in the database
        Cursor cursorObj = db.rawQuery(Query,null);
        cursorObj.moveToFirst();
        while(!cursorObj.isAfterLast()){
        myModel = new Model(cursorObj.getString(0),cursorObj.getString(1),cursorObj.getString(2),cursorObj.getString(3),cursorObj.getString(4),cursorObj.getBlob(5));
        ListModel.add(myModel);
            cursorObj.moveToNext();
        }
        return ListModel;
    }

public void delete(String id, SQLiteDatabase SQLiteDatabase){
    //accessing database
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_NAME, COLUMN_ID+"=?",new String[]{String.valueOf(id)});
}


}