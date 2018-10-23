package com.example.anil.mobilebanking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Receive_Amount extends AppCompatActivity {

    DataBase1 obj;
    TextView ui, ac;
    EditText yps, rui, rac, rps, ram;
    Button bt1, bt2;
    LinearLayout l1, l2, l3, l4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive__amount);

        obj = new DataBase1(this);
        ui = (TextView) findViewById(R.id.uid);
        ac = (TextView) findViewById(R.id.acno);
        yps = (EditText)findViewById(R.id.ps);
        rui = (EditText)findViewById(R.id.ruid);
        rac = (EditText)findViewById(R.id.racc);
        rps = (EditText)findViewById(R.id.rpa);
        ram = (EditText)findViewById(R.id.ramo);
        bt1 = (Button)findViewById(R.id.sbmt);
        bt2 = (Button)findViewById(R.id.rcv);
        l1 = (LinearLayout)findViewById(R.id.ll1);
        l2 = (LinearLayout)findViewById(R.id.ll2);
        l3 = (LinearLayout)findViewById(R.id.ll3);
        l4 = (LinearLayout)findViewById(R.id.ll4);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String s = b.getString("user");

        String pass=null, acc=null, bala=null;
        Cursor cs = obj.getData(s);
        while(cs.moveToNext()){
            acc = String.valueOf(cs.getString(cs.getColumnIndex("ACCOUNT_NO")));
            pass = String.valueOf(cs.getString(cs.getColumnIndex("PASSWORD")));
            bala = String.valueOf(cs.getString(cs.getColumnIndex("BALANCE")));
        }
        final String account = acc;
        final String pss = pass;
        final String bl = bala;
        ui.setText(s);
        ac.setText(acc);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pss.equals(yps.getText().toString())){
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                    l3.setVisibility(View.VISIBLE);
                    l4.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.VISIBLE);
                    bt1.setVisibility(View.GONE);
                } else{
                    yps.setHint("Incorrect Password");
                    yps.setText("");
                    Toast.makeText(getApplicationContext(), "Entered Password is Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aac = rac.getText().toString();
                String aui = rui.getText().toString();
                String aps = rps.getText().toString();
                String aam = ram.getText().toString();

                boolean ch=false;
                String reui=null, reac=null, reps=null, rebal=null;
                Cursor cs = obj.search_acc(aac);
                while (cs.moveToNext()){

                    ch = true;
                    reui = String.valueOf(cs.getString(cs.getColumnIndex("USER_ID")));
                    reac = String.valueOf(cs.getString(cs.getColumnIndex("ACCOUNT_NO")));
                    reps = String.valueOf(cs.getString(cs.getColumnIndex("PASSWORD")));
                    rebal = String.valueOf(cs.getString(cs.getColumnIndex("BALANCE")));

                    final String recui=reui;
                    if(reui.equals(rui.getText().toString()) && reps.equals(rps.getText().toString())){

                        if(reac.equals(ac.getText().toString())){
                            Toast.makeText(getApplicationContext(), "Received Amount from self Account is not possible", Toast.LENGTH_SHORT).show();
                        }

                        Float r_amt = Float.parseFloat(ram.getText().toString());
                        Float a_amt = Float.parseFloat(rebal);

                        if(r_amt <= a_amt) {
                            //here received

                            Float amt_add = Float.parseFloat(bl) + r_amt;
                            obj.add_money(account, amt_add.toString());

                            Float amt_cut = a_amt - r_amt;
                            obj.remove_money(recui.toString(), amt_cut.toString());

                            Toast.makeText(getApplicationContext(), "Amount Received Successfully", Toast.LENGTH_SHORT).show();
                            rui.setText("");
                            rac.setText("");
                            rps.setText("");
                            ram.setText("");
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Balance not available", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(!reui.equals(rui.getText().toString())){
                        Toast.makeText(getApplicationContext(), "User ID not matched", Toast.LENGTH_SHORT).show();
                    }
                    else if(!reps.equals(rps)){
                        Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "User ID & Password not matched", Toast.LENGTH_SHORT).show();
                    }

                }
                if(ch==false){
                    Toast.makeText(getApplicationContext(), "Account Number not exist", Toast.LENGTH_SHORT).show();
                    rac.setHint("Account No. not found");
                }
            }
        });
    }
}
