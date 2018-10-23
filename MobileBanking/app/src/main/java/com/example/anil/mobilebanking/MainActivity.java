package com.example.anil.mobilebanking;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar tb;
    DataBase obj;
    EditText un, pw;
    Button li, su;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        un = (EditText)findViewById(R.id.username);
        pw = (EditText)findViewById(R.id.password);
        li = (Button)findViewById(R.id.login);
        su = (Button)findViewById(R.id.signup);

        tb = (Toolbar)findViewById(R.id.t);
        tb.inflateMenu(R.menu.menu);
        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                    case R.id.help : {
                        Intent in = new Intent(MainActivity.this, Help.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.about : {
                        Intent in = new Intent(MainActivity.this, About.class);
                        startActivity(in);
                        break;
                    }
                }
                return true;
            }
        });

        obj = new DataBase(this);

        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, Signup.class);
                startActivity(in);
            }
        });

        li.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cs = obj.getData(un.getText().toString());

                boolean ch = false;
                while(cs.moveToNext()){
                    ch = true;
                    String pass = String.valueOf(cs.getString(cs.getColumnIndex("PASSWORD")));
                    String st = pw.getText().toString();

                    if(st.length()==0)
                        Toast.makeText(getApplicationContext(),"Enter Password", Toast.LENGTH_SHORT).show();
                    else if(pass.equals(st)) {
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(MainActivity.this, Login_Home.class);
                        in.putExtra("username", un.getText().toString());
                        startActivity(in);
                        pw.setText("");
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
                if(un.getText().toString().length()==0)
                    Toast.makeText(getApplicationContext(),"Enter Username and Password", Toast.LENGTH_SHORT).show();
                else if(ch==false)
                    Toast.makeText(getApplicationContext(),"Username not Exist", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog al = builder.create();
        al.setTitle("Alert...");
        al.show();
    }
}