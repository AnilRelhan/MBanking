package com.example.anil.mobilebanking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Rating extends AppCompatActivity {

    RatingBar rb;
    Button bt;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        rb = (RatingBar)findViewById(R.id.rate);
        bt = (Button)findViewById(R.id.sbmt);
        et = (EditText)findViewById(R.id.feed);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb.getRating()>0.0 || et.getText().length()>0)
                    Toast.makeText(getApplicationContext(), "Thankyou for your feedback", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Enter Rating or Feedback", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
