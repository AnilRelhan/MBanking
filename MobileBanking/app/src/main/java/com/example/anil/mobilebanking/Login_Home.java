package com.example.anil.mobilebanking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login_Home extends AppCompatActivity {

    Button add, lgin, remove;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__home);

        add = (Button)findViewById(R.id.add_account);
        lgin = (Button)findViewById(R.id.login_account);
        remove = (Button)findViewById(R.id.remove_account);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login_Home.this, Add_Account.class);
                startActivity(in);
            }
        });

        lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login_Home.this, Login_Account.class);
                startActivity(in);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login_Home.this, Remove_Account.class);
                startActivity(in);
            }
        });

        tb = (Toolbar)findViewById(R.id.t);
        tb.inflateMenu(R.menu.home);
        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.help : {
                        Intent in = new Intent(Login_Home.this, Help_Home.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.rate : {
                        Intent in = new Intent(Login_Home.this, Rating.class);
                        startActivity(in);
                        break;
                    }
                    case R.id.lgout : {
                        Intent in = new Intent(Login_Home.this, MainActivity.class);
                        startActivity(in);
                        Toast.makeText(getApplicationContext(), "Log out successfully from Mobile Banking App", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                return true;
            }
        });
    }
}
