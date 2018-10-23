package com.example.anil.mobilebanking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class Signup extends AppCompatActivity {

    DataBase obj;
    EditText fn, ph, em, ps, cps;
    Button rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fn = (EditText) findViewById(R.id.name);
        ph = (EditText) findViewById(R.id.phone);
        em = (EditText) findViewById(R.id.email);
        ps = (EditText) findViewById(R.id.password);
        cps = (EditText) findViewById(R.id.cpassword);
        rg = (Button)findViewById(R.id.register);

        obj = new DataBase(this);

        rg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nam = fn.getText().toString();
                String pho = ph.getText().toString();
                String ema = em.getText().toString();
                String pas = ps.getText().toString();
                String cpas = cps.getText().toString();

                boolean r = false;

                if (nam.length()==0 || pho.length()==0 || ema.length()==0 || pas.length()==0 || cpas.length()==0){
                    Toast.makeText(getApplicationContext(), "All fieds are not filled", Toast.LENGTH_SHORT).show();
                }
                else if(pho.length()<10){
                    Toast.makeText(getApplicationContext(), "Phone No. should be of 10 digits", Toast.LENGTH_SHORT).show();
                }
                else if(!cpas.equals(pas)) {
                    Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_SHORT).show();
                }
                else {
                    r = obj.saveData(nam, pho, ema, pas);
                    if(r) {
                        fn.setText("");
                        ph.setText("");
                        em.setText("");
                        ps.setText("");
                        cps.setText("");
                        Toast.makeText(getApplicationContext(), "Registred successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
