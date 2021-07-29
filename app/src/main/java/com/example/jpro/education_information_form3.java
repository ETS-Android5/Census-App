package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class education_information_form3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn;
    String enufullname="";


    //variables from this activity
    String notinschool="";

    //variables from previous activity
    String readAndWrite="";
    String everAttendedSch="";
    String attendschlyear= "";
    String highestgrade ="";

    //variables for data from previous activity
    String gender="";
    String marital_status="";
    String contributeTohouseholdIncome = "";
    String beenaway = "";
    String relatedTohousehold = "";
    String ages="";
    String names="";
    String refnum="";

    //variables from previous activity
    String currentlyinsch="";
    String currentgradeatt="";
    String whoruns="";
    String problemswithsch = "";
    String houseowner = "";

    Double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_information_form3);

        btn = (Button)findViewById(R.id.btneduinforform3);

        //getting data from previous acitivity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            gender = bundle.getString("gender");
            marital_status = bundle.getString("maritalstatus");
            contributeTohouseholdIncome = bundle.getString("contribute");
            beenaway = bundle.getString("beenaway");
            relatedTohousehold = bundle.getString("relatedto");
            ages = bundle.getString("age");
            names = bundle.getString("name");
            refnum = bundle.getString("refnum");
            readAndWrite = bundle.getString("readandwrite");
            everAttendedSch = bundle.getString("everattended");
            attendschlyear = bundle.getString("attendschlyear");
            highestgrade = bundle.getString("highestgrade");
            currentlyinsch = bundle.getString("currentlyinsch");
            currentgradeatt = bundle.getString("currentgradeatt");
            whoruns = bundle.getString("whoruns");
            problemswithsch = bundle.getString("problemwithsch");
            enufullname = bundle.getString("enufullname");
            houseowner = bundle.getString("houseowner");
            lat = bundle.getDouble("lat");
            lon = bundle.getDouble("lon");

//            Log.d("news", "onCreate: "+houseowner);
            Log.d("welesss", "onCreate: "+houseowner);

            if (everAttendedSch.equals("No")){
                Spinner spins = (Spinner)findViewById(R.id.spinnernotinschool);
                spins.setEnabled(false);
            }
        }



        Spinner spinner = (Spinner)findViewById(R.id.spinnernotinschool);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.notinschool, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("gender",gender);
                bundle.putString("maritalstatus",marital_status);
                bundle.putString("contribute",contributeTohouseholdIncome);
                bundle.putString("beenaway",beenaway);
                bundle.putString("relatedto",relatedTohousehold);
                bundle.putString("age",ages);
                bundle.putString("name",names);
                bundle.putString("refnum",refnum);

                //added from previous activity
                bundle.putString("readandwrite",readAndWrite);
                bundle.putString("everattended", everAttendedSch);
                bundle.putString("attendschlyear", attendschlyear);
                bundle.putString("highestgrade", highestgrade);

                //added from previous activity
                bundle.putString("currentlyinsch", currentlyinsch);
                bundle.putString("currentgradeatt", currentgradeatt);
                bundle.putString("whoruns", whoruns);
                bundle.putString("problemswithsch", problemswithsch);
                bundle.putString("enufullname",enufullname);

                //added from this activity
                bundle.putString("notinschool",notinschool);

                bundle.putString("houseowner",houseowner);

                bundle.putDouble("lat",lat);
                bundle.putDouble("lon",lon);


                Intent intent = new Intent(education_information_form3.this, health_information_form1.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        notinschool = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}