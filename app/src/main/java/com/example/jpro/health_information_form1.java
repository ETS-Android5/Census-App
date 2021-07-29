package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class health_information_form1 extends AppCompatActivity {

    Button btn;

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
    String enufullname ="";


    //variables from this activity
    String livebirth="";
    String prenatalcare="";
    String postnatalcare = "";
    String sicklast4wks = "";

    String houseowner="";

    Double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_information_form1);
        btn = (Button)findViewById(R.id.btnhealthinforform1);

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
            notinschool = bundle.getString("notinschool");
            enufullname = bundle.getString("enufullname");
            houseowner = bundle.getString("houseowner");

            lat = bundle.getDouble("lat");
            lon = bundle.getDouble("lon");

            Log.d("news", "onCreate: "+houseowner);
        }

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
                bundle.putString("enufullname",enufullname);

                //added from this activity
                bundle.putString("livebirth",livebirth);
                bundle.putString("prenatalcare",prenatalcare);
                bundle.putString("postnatalcare",postnatalcare);
                bundle.putString("sicklast4wks",sicklast4wks);

                bundle.putString("houseowner",houseowner);

                bundle.putDouble("lat",lat);
                bundle.putDouble("lon",lon);

                Intent intent = new Intent(health_information_form1.this, health_information_form2.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radiohealth_yes:
                if (checked){
                    livebirth = "Yes";

                    //enabling buttons
                    RadioButton rad = (RadioButton)findViewById(R.id.radiohealthyes);
                    RadioButton rads = (RadioButton)findViewById(R.id.radiohealthno);
                    RadioButton radss = (RadioButton)findViewById(R.id.radihealth_no);
                    RadioButton radsss = (RadioButton)findViewById(R.id.radihealth_yes);

                    rad.setClickable(true);
                    rads.setClickable(true);
                    radss.setClickable(true);
                    radsss.setClickable(true);
                }
                break;

            case R.id.radiohealth_no:
                if (checked){
                    livebirth = "No";

                    //disable here
                    RadioButton rad = (RadioButton)findViewById(R.id.radiohealthyes);
                    RadioButton rads = (RadioButton)findViewById(R.id.radiohealthno);
                    RadioButton radss = (RadioButton)findViewById(R.id.radihealth_no);
                    RadioButton radsss = (RadioButton)findViewById(R.id.radihealth_yes);

                    rad.setClickable(false);
                    rads.setClickable(false);
                    radss.setClickable(false);
                    radsss.setClickable(false);
                }
                break;
        }
    }

    public void onRadioButnClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radiohealthyes:
                if (checked){
                    prenatalcare = "Yes";
                }
                break;
            case R.id.radiohealthno:
                if (checked){
                    prenatalcare = "No";
                }
                break;
        }
    }

    public void dioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radihealth_yes:
                if (checked){
                    postnatalcare = "Yes";
                }
                break;

            case R.id.radihealth_no:
                if (checked){
                    postnatalcare = "No";
                }
                break;
        }
    }

    public void onRadioClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rai_yes:
                if (checked){
                    sicklast4wks = "Yes";
                }
                break;

            case R.id.rai_no:
                if (checked){
                    sicklast4wks = "No";
                }
                break;

        }
    }
}