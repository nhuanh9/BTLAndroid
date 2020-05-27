package com.example.btl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName = "mydbBTL1.db";
    private static final String TableName = "Player";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String SCORE = "score";
    private static final int VERSION = 1;
    private static SQLiteDatabase myDB;

    public DBHelper(@Nullable Context context) {

        super(context, DBName, null, VERSION);
    }

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getScore() {
        return SCORE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTable = "CREATE TABLE " + TableName + "( " + ID + " INTEGER PRIMARY KEY, " + NAME + " TEXT NOT NULL, " + SCORE + " INTEGER NOT NULL)";
        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDB() {
        myDB = getWritableDatabase();
    }

    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }
    }

    public static long Insert(int id, String name, int score) {

        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(NAME, name);
        values.put(SCORE, score);
        return myDB.insert(TableName, null, values);
    }

    public static long InsertPlayer(int id, String name, int score) {
        if (checkIfExistName(name) == 0) {
            ContentValues values = new ContentValues();
            values.put(ID, id);
            values.put(NAME, name);
            values.put(SCORE, score);
            return myDB.insert(TableName, null, values);
        }
        return -100;
    }

    public long Update(int id, String name, int score) {
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(NAME, name);
        values.put(SCORE, score);
        String where = ID + " = " + id;
        return myDB.update(TableName, values, where, null);
    }

    public long Delete(int id) {
        String where = ID + " = " + id;
        return myDB.delete(TableName, where, null);
    }

    public static Cursor getAllRecord() {
        String query = "SELECT * FROM " + TableName;
        return myDB.rawQuery(query, null);
    }

    public static Cursor getAllRecordOrderByScore() {
        String query = "SELECT * FROM " + TableName + " ORDER BY " + SCORE + " DESC  LIMIT 5";
        return myDB.rawQuery(query, null);
    }

    public static int checkIfExistName(String name) {
        String query = "SELECT * FROM " + TableName + " WHERE NAME = '" + name + "'";
        Cursor cursor = myDB.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


}
