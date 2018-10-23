package com.example.anil.mobilebanking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Edit_Details extends AppCompatActivity {

    DataBase1 obj;
    EditText nm, nom, aa, ph, em, ad, dob;
    Button sv;
    RadioGroup rg;
    RadioButton gen, m, f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__details);

        obj = new DataBase1(this);
        nm = (EditText)findViewById(R.id.name);
        nom = (EditText)findViewById(R.id.nominee);
        aa = (EditText)findViewById(R.id.aadhar);
        ph = (EditText)findViewById(R.id.phone);
        em = (EditText)findViewById(R.id.email);
        ad = (EditText)findViewById(R.id.address);
        dob = (EditText)findViewById(R.id.dob);
        rg = (RadioGroup)findViewById(R.id.gender);
        int x = rg.getCheckedRadioButtonId();
        gen = (RadioButton)findViewById(x);
        sv = (Button)findViewById(R.id.save);
        f = (RadioButton)findViewById(R.id.fmale);
        m = (RadioButton)findViewById(R.id.male);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String s = b.getString("user");
        final String usrid = s;

        String n=null, nomi=null, aad=null, p=null, e=null, addr=null, g=null, D=null;
        Cursor cs = obj.getData(s);
        while(cs.moveToNext()){
            n = String.valueOf(cs.getString(cs.getColumnIndex("APPLICANT_NAME")));
            nomi = String.valueOf(cs.getString(cs.getColumnIndex("NOMINEE_NAME")));
            aad = String.valueOf(cs.getString(cs.getColumnIndex("AADHAR_NO")));
            p = String.valueOf(cs.getString(cs.getColumnIndex("PHONE_NO")));
            e = String.valueOf(cs.getString(cs.getColumnIndex("EMAIL_ID")));
            addr = String.valueOf(cs.getString(cs.getColumnIndex("ADDRESS")));
            g = String.valueOf(cs.getString(cs.getColumnIndex("GENDER")));
            D = String.valueOf(cs.getString(cs.getColumnIndex("DOB")));
        }
        nm.setText(n);
        nom.setText(nomi);
        aa.setText(aad);
        ph.setText(p);
        em.setText(e);
        ad.setText(addr);
        dob.setText(D);

        if(g.equals("Male")) {
            m.setChecked(true);
        } else {
            f.setChecked(true);
        }

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = nm.getText().toString();
                String nomi = nom.getText().toString();
                String adhar = aa.getText().toString();
                String pho = ph.getText().toString();
                String eml = em.getText().toString();
                String add = ad.getText().toString();
                String db = dob.getText().toString();
                String gn = gen.getText().toString();

                obj.update(usrid, n, nomi, adhar, pho, eml, add, gn, db);
                Toast.makeText(getApplicationContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
