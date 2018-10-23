package com.example.anil.mobilebanking;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Account extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DataBase1 obj;
    TextView nm, nom, ac, ui, bnk, ifs, adh, ph, em, adr, gen, ob;
    TextView title, subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        obj = new DataBase1(this);
        nm = (TextView)findViewById(R.id.name);
        nom = (TextView)findViewById(R.id.nominee);
        ac = (TextView)findViewById(R.id.act);
        ui = (TextView)findViewById(R.id.uid);
        bnk = (TextView)findViewById(R.id.bb);
        ifs = (TextView)findViewById(R.id.ifsc);
        adh = (TextView)findViewById(R.id.aadhar);
        ph = (TextView)findViewById(R.id.phone);
        em = (TextView)findViewById(R.id.email);
        adr = (TextView)findViewById(R.id.adrs);
        gen = (TextView)findViewById(R.id.gender);
        ob = (TextView)findViewById(R.id.dob);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        String s = b.getString("user");

        String n=null, nomi=null, aco=null, uid=null, bank=null, isc= null, aad=null, p=null, e=null, addr=null, g=null, D=null;
        Cursor cs = obj.getData(s);
        while(cs.moveToNext()){
            n = String.valueOf(cs.getString(cs.getColumnIndex("APPLICANT_NAME")));
            nomi = String.valueOf(cs.getString(cs.getColumnIndex("NOMINEE_NAME")));
            aco = String .valueOf(cs.getString(cs.getColumnIndex("ACCOUNT_NO")));
            uid = String.valueOf(cs.getString(cs.getColumnIndex("USER_ID")));
            bank = String.valueOf(cs.getString(cs.getColumnIndex("BANK_NAME")));
            isc = String .valueOf(cs.getString(cs.getColumnIndex("IFSC_CODE")));
            aad = String.valueOf(cs.getString(cs.getColumnIndex("AADHAR_NO")));
            p = String.valueOf(cs.getString(cs.getColumnIndex("PHONE_NO")));
            e = String.valueOf(cs.getString(cs.getColumnIndex("EMAIL_ID")));
            addr = String.valueOf(cs.getString(cs.getColumnIndex("ADDRESS")));
            g = String.valueOf(cs.getString(cs.getColumnIndex("GENDER")));
            D = String.valueOf(cs.getString(cs.getColumnIndex("DOB")));
        }
        nm.setText(n);
        nom.setText(nomi);
        ac.setText(aco);
        ui.setText(uid);
        bnk.setText(bank);
        ifs.setText(isc);
        adh.setText(aad);
        ph.setText(p);
        em.setText(e);
        adr.setText(addr);
        gen.setText(g);
        ob.setText(D);

        //title = (TextView)findViewById(R.id.tit);
        //subtitle = (TextView)findViewById(R.id.subtit);
        //title.setText(nm.getText());
        //subtitle.setText(ac.getText());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.help) {
            Intent in = new Intent(Account.this, Help_Account.class);
            startActivity(in);
        } else if(id == R.id.lgout){
            Intent in = new Intent(Account.this, Login_Home.class);
            startActivity(in);
            Toast.makeText(getApplicationContext(), "Log out successfully from Bank Account", Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edit){
            Intent in = new Intent(Account.this, Edit_Details.class);
            in.putExtra("user", ui.getText().toString());
            startActivity(in);

        } else if(id == R.id.nav_chgpassword){
            Intent in = new Intent(Account.this, Change_Password.class);
            in.putExtra("user", ui.getText().toString());
            startActivity(in);

        } else if(id == R.id.nav_transfer){
            Intent in = new Intent(Account.this, Transfer_Amount.class);
            in.putExtra("user", ui.getText().toString());
            startActivity(in);

        } else if(id == R.id.nav_receive){
            Intent in = new Intent(Account.this, Receive_Amount.class);
            in.putExtra("user", ui.getText().toString());
            startActivity(in);

        }else if(id == R.id.nav_bal){
            Intent in = new Intent(Account.this, Check_Balance.class);
            in.putExtra("user", ui.getText().toString());
            startActivity(in);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
