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

public class Change_Password extends AppCompatActivity {

    DataBase1 obj;
    LinearLayout l1, l2;
    TextView usrid;
    EditText op, np, cnp;
    Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__password);

        obj = new DataBase1(this);
        op = (EditText)findViewById(R.id.ps);
        l1 = (LinearLayout)findViewById(R.id.np);
        l2 = (LinearLayout) findViewById(R.id.np1);
        usrid = (TextView)findViewById(R.id.uid);
        np = (EditText)findViewById(R.id.newps);
        cnp = (EditText)findViewById(R.id.newcps);
        bt1 = (Button)findViewById(R.id.sbmt);
        bt2 = (Button)findViewById(R.id.chg);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String s = b.getString("user");
        usrid.setText(s);

        String pass=null;
        Cursor cs = obj.getData(s);
        while(cs.moveToNext()){
            pass = String.valueOf(cs.getString(cs.getColumnIndex("PASSWORD")));
        }
        final String ps=pass;
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ps.equals(op.getText().toString())){
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.VISIBLE);
                    bt1.setVisibility(View.GONE);
                } else{
                    op.setHint("Incorrect Password");
                    op.setText("");
                    Toast.makeText(getApplicationContext(), "Entered Old Password is Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(np.getText().toString().equals(cnp.getText().toString())){

                    String p = np.getText().toString();

                    obj.update(usrid.getText().toString(), p);
                    Toast.makeText(getApplicationContext(), "Password Changed successfully", Toast.LENGTH_SHORT).show();
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                    bt1.setVisibility(View.VISIBLE);
                    bt2.setVisibility(View.GONE);
                    op.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Confirmation Password not matched", Toast.LENGTH_SHORT).show();
                    cnp.setHint("Re-Enter Password not matched");
                    cnp.setText("");
                }

            }
        });

    }
}
