package com.example.noahcrieslol;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "swag.db";
    public static final String TABLE_NAME = "Emotion";
    public static final String col_1 = "ID";
    public static final String col_2 = "emotion";
    public static final String col_3 = "color";
    public static final String col_4 = "date";
    public static final String col_5 = "time";
    public static final String col_6 = "reason";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "emotion TEXT,"
                + "color TEXT," + "date TEXT," + "time TEXT," + "reason TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    public boolean addEmotion(String emotion, Integer color, String date, String time, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, emotion);
        contentValues.put(col_3, color);
        contentValues.put(col_4, date);
        contentValues.put(col_5, time);
        contentValues.put(col_6, reason);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }



    public boolean updateEmotion (Integer id, String emotion, Integer color, String date, String time,String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2, emotion);
        contentValues.put(col_3, color);
        contentValues.put(col_4, date);
        contentValues.put(col_5, time);
        contentValues.put(col_6, reason);
        long result = db.update("Emotion", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }


    /*public Integer deleteEmotion(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Emotion",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }*/

    public void deleteEmotion(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME +
                " WHERE " + col_1 + " = " + id);
    }

    public Cursor getEmotion() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }



    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Emotion where id="+id+"", null);
        return res;
    }



    public ArrayList<String> getAllEmotions() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from Emotion", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(col_2)));
            res.moveToNext();
        }

        return array_list;
    }

    /*public Integer getColor(String emotion) {
        int color;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =
        db.query(
                "Emotion",               // name of the table to query
                new String[]{"color"},     // String array of columns to extract
                "emotion = ? ",                    // WHERE clause (? indicates an arg)
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
    }*/

}

