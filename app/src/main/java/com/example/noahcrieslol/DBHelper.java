package com.example.noahcrieslol;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "swag.db";
    public static final String TABLE_NAME = "Emotion";
    public static final String col_1 = "emotion";
    public static final String col_2 = "color";
    public static final String col_3 = "date";
    public static final String col_4 = "time";
    public static final String col_5 = "reason";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, emotion TEXT, color TEXT, date TEXT, time TEXT, reason TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addEmotion(String emotion, Integer color, String date, String time, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, emotion);
        contentValues.put(col_2, color);
        contentValues.put(col_3, date);
        contentValues.put(col_4, time);
        contentValues.put(col_5, reason);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public int getEmotion(String emotion) {
        int color;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =
        db.query(
                "Emotion",               // name of the table to query
                new String[]{"color"},     // String array of columns to extract
                "emotion=?",                    // WHERE clause (? indicates an arg)
                new String[]{emotion},     // The list of args to replace the ? (or ?'s on a sequential basis)
                null,                         // GROUP BY clause
                null,                         // HAVING clause
                "t_id DESC",                  // ORDER clause
                "1"                           // LIMIT value as a String
        );
        if (cursor.moveToFirst()) {
            color = cursor.getInt(cursor.getColumnIndex("balance"));
        } else {
            color = 0;
        }
        return color;
    }

    public ArrayList<String> getAllEmotions() {
        ArrayList<String> array_list = new ArrayList<String>();;

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(col_1)));
            res.moveToNext();
        }
        return array_list;
    }

}

