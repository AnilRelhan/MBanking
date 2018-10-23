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

public class Transfer_Amount extends AppCompatActivity {

    DataBase1 obj;
    TextView ui, ac;
    EditText ps, tam, tacc;
    Button bt1, bt2;
    LinearLayout l1, l2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer__amount);

        obj = new DataBase1(this);
        ui = (TextView)findViewById(R.id.uid);
        ac = (TextView)findViewById(R.id.acno);
        ps = (EditText)findViewById(R.id.ps);
        bt1 = (Button)findViewById(R.id.sbmt);
        bt2 = (Button)findViewById(R.id.trans);
        l1 = (LinearLayout)findViewById(R.id.np);
        l2 = (LinearLayout)findViewById(R.id.np1);
        tacc = (EditText)findViewById(R.id.tac);
        tam = (EditText)findViewById(R.id.bala);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String s = b.getString("user");

        String acc=null, pass=null, bal=null;
        Cursor cs = obj.getData(s);
        while(cs.moveToNext()){
            acc = String.valueOf(cs.getString(cs.getColumnIndex("ACCOUNT_NO")));
            pass = String.valueOf(cs.getString(cs.getColumnIndex("PASSWORD")));
            bal = String.valueOf(cs.getString(cs.getColumnIndex("BALANCE")));
        }
        final String account_1 = acc;
        final String pss = pass;
        final String blnc_1 = bal;
        ui.setText(s);
        ac.setText(acc);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pss.equals(ps.getText().toString())){
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.VISIBLE);
                    bt1.setVisibility(View.GONE);
                } else{
                    ps.setHint("Incorrect Password");
                    ps.setText("");
                    Toast.makeText(getApplicationContext(), "Entered Old Password is Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = tacc.getText().toString();

                boolean ch = false;
                String bal=null, acn=null;
                Cursor cs = obj.search_acc(s);

                Float t_amt = Float.parseFloat(tam.getText().toString());
                Float a_amt = Float.parseFloat(blnc_1);

                while(cs.moveToNext()){
                    ch = true;
                    bal = String.valueOf(cs.getString(cs.getColumnIndex("BALANCE")));
                    acn = String.valueOf(cs.getString(cs.getColumnIndex("ACCOUNT_NO")));

                    if(acn.equals(account_1)){
                        Toast.makeText(getApplicationContext(), "Transfer Amount to self Account is not possible", Toast.LENGTH_SHORT).show();
                    }

                    if(t_amt <= a_amt) {
                        Float amt_add = Float.parseFloat(bal) + t_amt;
                        obj.add_money(s, amt_add.toString());

                        Float amt_cut = a_amt - t_amt;
                        obj.remove_money(account_1.toString(), amt_cut.toString());

                        Toast.makeText(getApplicationContext(), "Amount Transfered Successfully", Toast.LENGTH_SHORT).show();
                        tacc.setText("");
                        tam.setText("");
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Balance not available", Toast.LENGTH_SHORT).show();
                        //tam.setHint("Amount not available");
                    }
                }
                if(ch==false){
                    Toast.makeText(getApplicationContext(), "Account Number not exist", Toast.LENGTH_SHORT).show();
                    tacc.setHint("Account No. not found");
                }
            }
        });
    }
}
