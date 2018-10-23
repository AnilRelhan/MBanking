package com.example.anil.mobilebanking;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class forgetpassword extends AppCompatActivity {

    DataBase1 obj;
    EditText usid, acc, pho, aadh, ifsc, password, cpassword;
    Button bt1, bt2;
    LinearLayout ll1, ll2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        obj = new DataBase1(this);
        usid = (EditText)findViewById(R.id.ui);
        acc = (EditText)findViewById(R.id.ac);
        pho = (EditText)findViewById(R.id.ph);
        aadh = (EditText)findViewById(R.id.adh);
        ifsc = (EditText)findViewById(R.id.isc);
        bt1 = (Button)findViewById(R.id.sbmt);
        bt2 = (Button)findViewById(R.id.chg);
        ll1 = (LinearLayout)findViewById(R.id.l1);
        ll2 = (LinearLayout)findViewById(R.id.l2);

        password = (EditText)findViewById(R.id.newps);
        cpassword = (EditText)findViewById(R.id.newcps);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean ch=false;
                String account=null, phone=null, icode=null, aadhar=null;
                Cursor cs = obj.getData(usid.getText().toString());
                while(cs.moveToNext()){
                    ch = true;
                    account = String.valueOf(cs.getString(cs.getColumnIndex("ACCOUNT_NO")));
                    phone = String.valueOf(cs.getString(cs.getColumnIndex("PHONE_NO")));
                    icode = String.valueOf(cs.getString(cs.getColumnIndex("IFSC_CODE")));
                    aadhar = String.valueOf(cs.getString(cs.getColumnIndex("AADHAR_NO")));

                    if(account.equals(acc.getText().toString()) && phone.equals(pho.getText().toString()) && icode.equals(ifsc.getText().toString()) && aadhar.equals(aadh.getText().toString())){
                        ll1.setVisibility(View.VISIBLE);
                        ll2.setVisibility(View.VISIBLE);
                        bt2.setVisibility(View.VISIBLE);
                        bt1.setVisibility(View.GONE);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Details not matched", Toast.LENGTH_SHORT).show();
                    }
                }
                if(ch==false){
                    Toast.makeText(getApplicationContext(), "User ID not Exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(password.getText().toString().length()==0 || cpassword.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(), "Enter new Password", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().equals(cpassword.getText().toString())){

                    obj.update(usid.getText().toString(), password.getText().toString());
                    Toast.makeText(getApplicationContext(), "Password changed Successfully", Toast.LENGTH_SHORT).show();
                    usid.setText("");
                    acc.setText("");
                    pho.setText("");
                    aadh.setText("");
                    ifsc.setText("");
                    ll1.setVisibility(View.GONE);
                    ll2.setVisibility(View.GONE);
                    bt2.setVisibility(View.GONE);
                    bt1.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Password & Confirm Password not matched", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
