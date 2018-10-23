package com.example.anil.mobilebanking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Remove_Account extends AppCompatActivity {

    DataBase1 obj;
    EditText ui, pw;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove__account);

        obj = new DataBase1(this);
        ui = (EditText)findViewById(R.id.uid);
        pw = (EditText)findViewById(R.id.pswd);
        bt = (Button)findViewById(R.id.remove);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cs = obj.getData(ui.getText().toString());

                boolean ch = false;
                while(cs.moveToNext()){
                    ch = true;
                    String pass = String.valueOf(cs.getString(cs.getColumnIndex("PASSWORD")));
                    String st = pw.getText().toString();

                    if(st.length()==0)
                        Toast.makeText(getApplicationContext(),"Enter Password", Toast.LENGTH_SHORT).show();
                    else if(pass.equals(st)) {
                        obj.delete(ui.getText().toString());
                        Toast.makeText(getApplicationContext(), "Account Removed Successfully", Toast.LENGTH_SHORT).show();
                        ui.setText("");
                        pw.setText("");
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
                if(ui.getText().toString().length()==0)
                    Toast.makeText(getApplicationContext(),"Enter User ID and Password", Toast.LENGTH_SHORT).show();
                else if(ch==false)
                    Toast.makeText(getApplicationContext(),"Account not Exist", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
