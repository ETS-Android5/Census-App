package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class education_information_form2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btns;

    String enufullname="";

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

    //variables from this activity
    String currentlyinsch="";
    String currentgradeatt="";
    String whoruns="";
    String problemswithsch = "";
    String houseowner="";

    Double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_information_form2);

        btns = (Button)findViewById(R.id.btneduinforform2);

        //getting data from previous activity
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
            enufullname = bundle.getString("enufullname");
            houseowner = bundle.getString("houseowner");
            lat = bundle.getDouble("lat");
            lon = bundle.getDouble("lon");

            if (everAttendedSch.equals("No")){
                RadioButton rad = (RadioButton)findViewById(R.id.radioedu_yes);
                RadioButton rads = (RadioButton)findViewById(R.id.radioedu_no);
                rad.setClickable(false);
                rads.setClickable(false);

                Spinner spin = (Spinner)findViewById(R.id.spinnercurrentgrade);
                Spinner spins = (Spinner)findViewById(R.id.spinnerwho);
                Spinner spinss = (Spinner)findViewById(R.id.spinnerproblem);
                spin.setEnabled(false);
                spins.setEnabled(false);
                spinss.setEnabled(false);
            }


        }
        //disabling if ever attended school is no


        Spinner spinner = (Spinner)findViewById(R.id.spinnercurrentgrade);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);





        Spinner spinner2 = (Spinner)findViewById(R.id.spinnerwho);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.whoruns, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);




        Spinner spinner3 = (Spinner)findViewById(R.id.spinnerproblem);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.schoolpro, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);


        btns.setOnClickListener(new View.OnClickListener() {
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

                //added from this activity
                bundle.putString("currentlyinsch", currentlyinsch);
                bundle.putString("currentgradeatt", currentgradeatt);
                bundle.putString("whoruns", whoruns);
                bundle.putString("problemswithsch", problemswithsch);
                bundle.putString("enufullname",enufullname);
                bundle.putString("houseowner",houseowner);

                bundle.putDouble("lat",lat);
                bundle.putDouble("lon",lon);

                Intent intent = new Intent(education_information_form2.this, education_information_form3.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked= ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioedu_yes:
                if (checked){
                    currentlyinsch = "Yes";

                    //enable rest of form input
                    Spinner spin = (Spinner)findViewById(R.id.spinnercurrentgrade);
                    Spinner spins = (Spinner)findViewById(R.id.spinnerwho);
                    Spinner spinss = (Spinner)findViewById(R.id.spinnerproblem);
                    spin.setEnabled(true);
                    spins.setEnabled(true);
                    spinss.setEnabled(true);
                }
                break;

            case R.id.radioedu_no:
                if (checked){
                    currentlyinsch = "No";

                    //disable rest of form input
                    Spinner spin = (Spinner)findViewById(R.id.spinnercurrentgrade);
                    Spinner spins = (Spinner)findViewById(R.id.spinnerwho);
                    Spinner spinss = (Spinner)findViewById(R.id.spinnerproblem);
                    spin.setEnabled(false);
                    spins.setEnabled(false);
                    spinss.setEnabled(false);
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinnercurrentgrade){
            currentgradeatt = parent.getSelectedItem().toString();

        }else if (parent.getId() == R.id.spinnerwho){
            whoruns = parent.getSelectedItem().toString();

        }else if (parent.getId() == R.id.spinnerproblem){
            problemswithsch = parent.getSelectedItem().toString();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}