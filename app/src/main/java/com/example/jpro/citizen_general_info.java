package com.example.jpro;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class citizen_general_info extends AppCompatActivity {
    TextView tvname,genderr,age,maritalstatus,beenawa,related,tvcontribute;

    String gender="";
    String marital_status="";
    String contributeTohouseholdIncome = "";
    String beenaway = "";
    String relatedTohousehold = "";
    String ages="";
    String names="";
    String refnum="";
    String enufullname="";
    String houseowner="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_general_info);
        tvname = (TextView)findViewById(R.id.txtname);
        genderr = (TextView)findViewById(R.id.txtgender);
        age = (TextView)findViewById(R.id.txtage);
        maritalstatus = (TextView)findViewById(R.id.txtmarital_status);
        beenawa = (TextView)findViewById(R.id.txtbeenaway);
        related = (TextView)findViewById(R.id.txtrelation);
        tvcontribute = (TextView)findViewById(R.id.txtcontribute);

        //getting data from bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            gender = bundle.getString("gender");
            marital_status = bundle.getString("marital_status");
            contributeTohouseholdIncome = bundle.getString("contributeTohouseholdIncome");
            beenaway = bundle.getString("beenaway");
            relatedTohousehold = bundle.getString("relatedTohousehold");
            ages = bundle.getString("ages");
            names = bundle.getString("names");
            houseowner = bundle.getString("houseowner");


            tvname.setText(names);
            genderr.setText(gender);
            age.setText(ages);
            maritalstatus.setText(marital_status);
            beenawa.setText(beenaway);
            related.setText(relatedTohousehold);
            tvcontribute.setText(contributeTohouseholdIncome);

        }
    }
}