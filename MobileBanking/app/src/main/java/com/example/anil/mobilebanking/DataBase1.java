package com.example.anil.mobilebanking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBase1 extends SQLiteOpenHelper {

    static final String Database_name = "Bank.db";
    static final String Table_Name = "Account";

    public DataBase1(Context context) {
        super(context, Table_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + Table_Name + "(APPLICANT_NAME TEXT, NOMINEE_NAME TEXT, ACCOUNT_NO TEXT, USER_ID TEXT PRIMARY KEY, BANK_NAME TEXT, IFSC_CODE TEXT, AADHAR_NO TEXT, PHONE_NO TEXT, EMAIL_ID TEXT, ADDRESS TEXT, GENDER TEXT, DOB TEXT, PASSWORD TEXT, BALANCE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL((" DROP TABLE IF EXISTS " + Table_Name));
        onCreate(db);
    }

    public boolean saveData(String name, String nominee, String account, String usrid, String bank, String ifsc, String aadhar, String phone, String email, String address, String gender, String dob, String password, String amt) {

        SQLiteDatabase sq = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put("APPLICANT_NAME", name);
        c.put("NOMINEE_NAME", nominee);
        c.put("ACCOUNT_NO", account);
        c.put("USER_ID", usrid);
        c.put("BANK_NAME", bank);
        c.put("IFSC_CODE", ifsc);
        c.put("AADHAR_NO", aadhar);
        c.put("PHONE_NO", phone);
        c.put("EMAIL_ID", email);
        c.put("ADDRESS", address);
        c.put("GENDER", gender);
        c.put("DOB", dob);
        c.put("PASSWORD", password);
        c.put("BALANCE", amt);

        long res = sq.insert(Table_Name, null, c);

        if (res == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(String usrid){

        SQLiteDatabase sd = this.getWritableDatabase();
        String query = " SELECT *FROM " + Table_Name + " WHERE USER_ID = '" + usrid +"' ";

        Cursor cs = sd.rawQuery(query, null);

        return cs;
    }

    public void delete(String usrid){

        SQLiteDatabase sq = this.getWritableDatabase();
        sq.delete(Table_Name, " USER_ID = '" + usrid + "'", null);
    }

    public void update(String usrid, String name, String nominee, String aadhar, String phone, String email, String address, String gender, String dob){

        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put("APPLICANT_NAME", name);
        c.put("NOMINEE_NAME", nominee);
        c.put("AADHAR_NO", aadhar);
        c.put("PHONE_NO", phone);
        c.put("EMAIL_ID", email);
        c.put("ADDRESS", address);
        c.put("GENDER", gender);
        c.put("DOB", dob);

        sd.update(Table_Name, c, " USER_ID = '" + usrid + "'", null);
    }

    public void update(String usrid, String p){

        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put("PASSWORD", p);

        sd.update(Table_Name, c, " USER_ID = '" + usrid + "'", null);
    }

    public Cursor search_acc(String acc){

        SQLiteDatabase sd = this.getWritableDatabase();
        String query = " SELECT *FROM " + Table_Name + " WHERE ACCOUNT_NO = '" + acc +"' ";

        Cursor cs = sd.rawQuery(query, null);

        return cs;
    }
    public void add_money(String acc, String bal){
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put("BALANCE", bal);

        sd.update(Table_Name, c, " ACCOUNT_NO = '" + acc + "'", null);
    }
    public void remove_money(String usrid, String bal){
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put("BALANCE", bal);

        sd.update(Table_Name, c, " ACCOUNT_NO = '" + usrid + "'", null);
    }
}
