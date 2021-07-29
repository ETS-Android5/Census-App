package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class education_information_form1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn;

    String enufullname="";

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

    String houseowner="";

    Double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_information_form1);

        btn = (Button)findViewById(R.id.btneduinforform1);

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
            enufullname= bundle.getString("enufullname");
            houseowner = bundle.getString("houseowner");
            lat = bundle.getDouble("lat");
            lon = bundle.getDouble("lon");

//            Log.d("news", "onCreate: "+houseowner);
            Log.d("weles", "onCreate: "+houseowner);
//            Log.d("def", "onCreate: "+names);
        }

        //getting value from txt spinner
        Spinner spinner = (Spinner)findViewById(R.id.spinnergrade);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grade, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //onclick function for button to next activity
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
                bundle.putString("enufullname",enufullname);

                //added from this activity
                bundle.putString("readandwrite",readAndWrite);
                bundle.putString("everattended", everAttendedSch);
                bundle.putString("attendschlyear", attendschlyear);
                bundle.putString("highestgrade", highestgrade);
                bundle.putString("houseowner",houseowner);

                bundle.putDouble("lat",lat);
                bundle.putDouble("lon",lon);

                Intent intent = new Intent(education_information_form1.this, education_information_form2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radio_yesedu:
                if (checked){
                    readAndWrite = "Yes";
                }
                break;

            case R.id.radio_noedu:
                if (checked){
                    readAndWrite = "No";
                }
                break;
        }
    }

    public void RadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radio_yessedu:
                if (checked){
                    everAttendedSch = "Yes";

                    //enabling the other radio buttons
                    RadioButton rad = (RadioButton)findViewById(R.id.radiobtn_no);
                    RadioButton rads = (RadioButton)findViewById(R.id.radiobtn_yes);
                    rad.setClickable(true);
                    rads.setClickable(true);

                    Spinner spin = (Spinner)findViewById(R.id.spinnergrade);
                    spin.setEnabled(true);
                }
                break;

            case R.id.radio_nooedu:
                if (checked){
                    everAttendedSch = "No";

                    //disabling the other radio buttons
                    RadioButton rad = (RadioButton)findViewById(R.id.radiobtn_no);
                    RadioButton rads = (RadioButton)findViewById(R.id.radiobtn_yes);
                    rad.setClickable(false);
                    rads.setClickable(false);

                    Spinner spin = (Spinner)findViewById(R.id.spinnergrade);
                    spin.setEnabled(false);
                }
                break;
        }
    }

    public void onadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radiobtn_yes:
                if (checked){
                    attendschlyear = "Yes";
                }
                break;

            case R.id.radiobtn_no:
                if (checked){
                    attendschlyear = "No";
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        highestgrade = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}