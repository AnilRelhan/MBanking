package com.example.anil.mobilebanking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Check_Balance extends AppCompatActivity {

    DataBase1 obj;
    TextView acc, ui, ifs, bnk, bal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check__balance);

        obj = new DataBase1(this);
        acc = (TextView)findViewById(R.id.acno);
        ui = (TextView)findViewById(R.id.uid);
        bnk = (TextView)findViewById(R.id.bb);
        ifs = (TextView)findViewById(R.id.ifsc);
        bal = (TextView)findViewById(R.id.balance);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String s = b.getString("user");

        String ac=null, usrid=null, bank=null, isc=null, bln=null;
        Cursor cs = obj.getData(s);
        while(cs.moveToNext()){
            ac = String.valueOf(cs.getString(cs.getColumnIndex("ACCOUNT_NO")));
            usrid = String.valueOf(cs.getString(cs.getColumnIndex("USER_ID")));
            bank = String.valueOf(cs.getString(cs.getColumnIndex("BANK_NAME")));
            isc = String.valueOf(cs.getString(cs.getColumnIndex("IFSC_CODE")));
            bln = String.valueOf(cs.getString(cs.getColumnIndex("BALANCE")));
        }
        acc.setText(ac);
        ui.setText(usrid);
        bnk.setText(bank);
        ifs.setText(isc);
        bal.setText(bln);
    }
}
