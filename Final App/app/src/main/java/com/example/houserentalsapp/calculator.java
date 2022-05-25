package com.example.houserentalsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;



public class calculator extends AppCompatActivity {



    EditText e1, e2,e3,e4;
    TextView t1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


    }


    public void calculate(View view) {


        e1 = (EditText) findViewById(R.id.roommates);
        e2 = (EditText) findViewById(R.id.rent);
        e3= (EditText) findViewById(R.id.utility);
        e4 = (EditText) findViewById(R.id.maintenance);
// defining the text view to t1
        t1 = (TextView) findViewById(R.id.result);


        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        String s3 = e3.getText().toString();
        String s4 = e4.getText().toString();

// condition to check if box is not empty
        if ((s1.equals(null) && s2.equals(null)&&(s3.equals(null) && s4.equals(null))
                || (s1.equals("") && s2.equals("")) || (s3.equals("") && s4.equals(""))))
        {

            String result = "Please enter a value";
            t1.setText(result);

        } else {
// converting string to int.
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            double d3 = Double.parseDouble(s3);
            double d4 = Double.parseDouble(s4);

            double res = (d2+d3+d4)/d1;
            String r = Double.toString(res);
            t1.setText(r);

        }

    }

}