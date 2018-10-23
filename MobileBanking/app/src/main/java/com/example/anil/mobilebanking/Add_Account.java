package com.example.anil.mobilebanking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Add_Account extends AppCompatActivity {

    DataBase1 obj;
    EditText nm, nom, ac, ui, bn, ifs, aa, ph, em, ad, dob, pass, cpass;
    Button sv;
    RadioGroup rg;
    RadioButton gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__account);

        obj = new DataBase1(this);
        nm = (EditText)findViewById(R.id.name);
        nom = (EditText)findViewById(R.id.nominee);
        ac = (EditText)findViewById(R.id.account_no);
        ui = (EditText)findViewById(R.id.user_id);
        bn = (EditText)findViewById(R.id.bank);
        ifs = (EditText)findViewById(R.id.ifsc);
        aa = (EditText)findViewById(R.id.aadhar);
        ph = (EditText)findViewById(R.id.phone);
        em = (EditText)findViewById(R.id.email);
        ad = (EditText)findViewById(R.id.address);
        dob = (EditText)findViewById(R.id.dob);
        pass = (EditText)findViewById(R.id.password);
        cpass = (EditText)findViewById(R.id.c_password);
        rg = (RadioGroup)findViewById(R.id.gender);
        int x = rg.getCheckedRadioButtonId();
        gen = (RadioButton)findViewById(x);
        sv = (Button)findViewById(R.id.save);

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nm.getText().toString();
                String nominee = nom.getText().toString();
                String acn = ac.getText().toString();
                String uid = ui.getText().toString();
                String bnk = bn.getText().toString();
                String ifc = ifs.getText().toString();
                String adh = aa.getText().toString();
                String pho = ph.getText().toString();
                String eml = em.getText().toString();
                String addr = ad.getText().toString();
                String gnd = gen.getText().toString();
                String db = dob.getText().toString();
                String ps = pass.getText().toString();
                String cps = cpass.getText().toString();

                boolean r = false;

                if(name.length()==0 || nominee.length()==0 || acn.length()==0 || uid.length()==0 || bnk.length()==0 || ifc.length()==0 || adh.length()==0 || pho.length()==0 || eml.length()==0 || addr.length()==0 || gnd.length()==0 || db.length()==0 || ps.length()==0 || cps.length()==0) {
                    Toast.makeText(getApplicationContext(), "All fields are not filled", Toast.LENGTH_SHORT).show();
                }
                else if(pho.length()!=10){
                    Toast.makeText(getApplicationContext(), "Phone No. should be of 10 Digit", Toast.LENGTH_SHORT).show();
                }
                else if(adh.length()!=12){
                    Toast.makeText(getApplicationContext(), "Aadhar No. should be of 12 Digit", Toast.LENGTH_SHORT).show();
                }
                else if(!cps.equals(ps)){
                    Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_SHORT).show();
                }
                else {
                    r = obj.saveData(name, nominee, acn, uid, bnk, ifc, adh, pho, eml, addr, gnd, db, ps, "2000");
                    if(r) {
                        nm.setText("");
                        nom.setText("");
                        ac.setText("");
                        ui.setText("");
                        bn.setText("");
                        ifs.setText("");
                        aa.setText("");
                        ph.setText("");
                        em.setText("");
                        ad.setText("");
                        dob.setText("");
                        pass.setText("");
                        cpass.setText("");
                        Toast.makeText(getApplicationContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Adding failed(this Account may be already added or userid is used earlier)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
