package com.example.anil.mobilebanking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    static final String Database_name = "Bank.db";
    static final String Table_name = "User";

    public DataBase(Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + Table_name + "(NAME TEXT, PHONE_NO TEXT, EMAIL_ID TEXT PRIMARY KEY, PASSWORD TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + Table_name);
        onCreate(db);
    }

    public boolean saveData(String name, String phone, String email, String password){

        SQLiteDatabase sq = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("NAME", name);
        c.put("PHONE_NO", phone);
        c.put("EMAIL_ID", email);
        c.put("PASSWORD", password);

        long res = sq.insert(Table_name, null, c);

        if(res == -1)
            return  false;
        else
            return  true;
    }

    public Cursor getData(String email){

        SQLiteDatabase sd = this.getWritableDatabase();
        String query = " SELECT *FROM " + Table_name + " WHERE EMAIL_ID = '" + email +"' ";

        Cursor cs = sd.rawQuery(query, null);

        return cs;
    }
}
