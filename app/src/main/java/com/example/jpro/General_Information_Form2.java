package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class General_Information_Form2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText ccname,ccage;
    Button btn;

    //global variables
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

    Double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general__information__form2);

        ccname = (EditText)findViewById(R.id.cname);
        ccage = (EditText)findViewById(R.id.cage);
        btn = (Button)findViewById(R.id.btngeninforform2);




        //getting data from previous intent
//        Intent intent = getIntent();
//        refnum = intent.getStringExtra("refNum");//the reference number

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
        refnum = bundle.getString("refNum");
        enufullname = bundle.getString("enufullname");
        houseowner = bundle.getString("houseowner");
        lat = bundle.getDouble("lat");
        lon = bundle.getDouble("lon");

        Log.d("wele", "onCreate: "+lat+" "+lon);
        }




        //getting data from this activity




        Spinner spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.away, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        //spinner for household
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.household, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names = ccname.getText().toString();
                ages = ccage.getText().toString();
//                Log.d("news", "onCreate: "+names);
                //Log.d("news", "onCreate: "+ages);

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
                bundle.putString("houseowner",houseowner);
                bundle.putDouble("lat",lat);
                bundle.putDouble("lon",lon);

                Intent intent1 = new Intent(General_Information_Form2.this, education_information_form1.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
    }

    //getting radio button selected value
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_male:
                if (checked){
                    gender = "Male";
                }
                break;

            case R.id.radio_female:
                if (checked){
                    gender = "Female";
                }
                break;
        }
    }

    public void RadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_single:
                if (checked){
                    marital_status = "Single";
                }
                break;

            case R.id.radio_married:
                if (checked){
                    marital_status = "Married";
                }
                break;
        }
    }

    public void onRadioButtonClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radio_yes:
                if (checked){
                    contributeTohouseholdIncome = "Yes";
                }
                break;

            case R.id.radio_no:
                if (checked){
                    contributeTohouseholdIncome = "No";
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner1){
            beenaway = parent.getSelectedItem().toString();
//            Toast.makeText(this, beenaway, Toast.LENGTH_SHORT).show();
        }else if (parent.getId() == R.id.spinner2){
            relatedTohousehold = parent.getSelectedItem().toString();
//            Toast.makeText(this, relatedTohousehold, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}