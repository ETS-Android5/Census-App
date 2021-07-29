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

public class health_information_form2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btn;
    //variables from previous activity
    String notinschool="";
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

    //variables from previous activity
    String currentlyinsch="";
    String currentgradeatt="";
    String whoruns="";
    String problemswithsch = "";


    //variables from previous activity
    String livebirth="";
    String prenatalcare="";
    String postnatalcare = "";
    String sicklast4wks = "";

    //variables from this activity
    String sortof="";
    String kindof="";
    String sickness="";
    String consultedlt4wks="";

    String houseowner="";

    Double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_information_form2);

        btn = (Button)findViewById(R.id.btnhealthinforform2);

        //getting
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            livebirth = bundle.getString("livebirth");
            prenatalcare = bundle.getString("prenatalcare");
            postnatalcare = bundle.getString("postnatalcare");
            sicklast4wks = bundle.getString("sicklast4wks");
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
            notinschool = bundle.getString("notinschool");
            enufullname = bundle.getString("enufullname");
            houseowner = bundle.getString("houseowner");

            lat = bundle.getDouble("lat");
            lon = bundle.getDouble("lon");

            Log.d("news", "onCreate: "+houseowner);
            if (sicklast4wks.equals("No")){
                Spinner sick = (Spinner)findViewById(R.id.spinnersickness);
                Spinner sortof = (Spinner)findViewById(R.id.spinnersortof);
                sick.setEnabled(false);
                sortof.setEnabled(false);
            }
        }

        Spinner spinner = (Spinner)findViewById(R.id.spinnersickness);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sickness, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner1 =(Spinner)findViewById(R.id.spinnersortof);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.many, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner)findViewById(R.id.spinnerkindtof);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.kindof, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

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

                //added from previous activity
                bundle.putString("notinschool",notinschool);

                //added from previous activity
                bundle.putString("livebirth",livebirth);
                bundle.putString("prenatalcare",prenatalcare);
                bundle.putString("postnatalcare",postnatalcare);
                bundle.putString("sicklast4wks",sicklast4wks);
                bundle.putString("enufullname",enufullname);

                //added from this activity
                bundle.putString("sortof",sortof);
                bundle.putString("kindof",kindof);
                bundle.putString("sickness",sickness);
                bundle.putString("consultedlt4wks",consultedlt4wks);
                bundle.putString("houseowner",houseowner);

                bundle.putDouble("lat",lat);
                bundle.putDouble("lon",lon);

                Intent intent = new Intent(health_information_form2.this, health_information_form3.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinnersickness){
            sickness = parent.getSelectedItem().toString();

        }else if (parent.getId() == R.id.spinnersortof){
            sortof = parent.getSelectedItem().toString();

        }else if (parent.getId() == R.id.spinnerkindtof){
            kindof = parent.getSelectedItem().toString();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radiohbtn_yes:
                if (checked){
                    consultedlt4wks = "Yes";
                }
                break;
            case R.id.radiohbtn_no:
                if (checked){
                    consultedlt4wks = "No";

                    //disable here
                    Spinner kindof = (Spinner)findViewById(R.id.spinnerkindtof);
                    kindof.setEnabled(false);
                }
                break;
        }
    }
}