package com.example.anil.mobilebanking;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Account extends AppCompatActivity {

    DataBase1 obj;
    EditText ui, pw;
    Button lg;
    TextView frgt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__account);

        obj = new DataBase1(this);
        ui = (EditText)findViewById(R.id.uid);
        pw = (EditText)findViewById(R.id.pswd);
        lg = (Button)findViewById(R.id.lgin);
        frgt = (TextView) findViewById(R.id.forget);

        frgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login_Account.this, forgetpassword.class);
                startActivity(in);
            }
        });
        lg.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(Login_Account.this, Account.class);
                        in.putExtra("user", ui.getText().toString());
                        startActivity(in);
                        pw.setText("");
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
                if(ui.getText().toString().length()==0)
                    Toast.makeText(getApplicationContext(),"Enter Username and Password", Toast.LENGTH_SHORT).show();
                else if(ch==false)
                    Toast.makeText(getApplicationContext(),"User ID not Exist", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
