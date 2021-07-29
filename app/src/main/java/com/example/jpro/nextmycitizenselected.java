package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class nextmycitizenselected extends AppCompatActivity {
    TextView tvuser;
    Button btngeninfo;
    Button btneduinfo;
    Button btnhealthinfo;
    ImageView imageView;

    //variables for general information.
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

    //variables for education information
    String readAndWrite="";
    String everAttendedSch="";
    String attendschlyear= "";
    String highestgrade ="";
    String currentlyinsch="";
    String currentgradeatt="";
    String whoruns="";
    String problemswithsch = "";

    //variables for health information
    String livebirth="";
    String prenatalcare="";
    String postnatalcare = "";
    String sicklast4wks = "";
    String sortof="";
    String kindof="";
    String sickness="";
    String consultedlt4wks="";
    String times="";
    String medic="";
    String visit="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextmycitizenselected);
        tvuser = (TextView)findViewById(R.id.selectedCitizen);
        btngeninfo = (Button)findViewById(R.id.btngeneralinfo);
        btneduinfo = (Button)findViewById(R.id.btneducationinfo);
        btnhealthinfo = (Button)findViewById(R.id.btnhealthinfo);
        imageView = (ImageView)findViewById(R.id.shapeableImageView);

        Intent intent = getIntent();
        if (intent.getExtras() !=null){
            CitizenDataClass citizenDataClass = (CitizenDataClass) intent.getSerializableExtra("data");
            tvuser.setText(citizenDataClass.getNames());
            Glide.with(this).load(citizenDataClass.getCitizenimage()).into(imageView);


            //general information starts
            names = citizenDataClass.getNames();
            gender = citizenDataClass.getGender();
            marital_status = citizenDataClass.getMarital_status();
            contributeTohouseholdIncome = citizenDataClass.getContributeTohouseholdIncome();
            beenaway = citizenDataClass.getBeenaway();
            relatedTohousehold = citizenDataClass.getRelatedTohousehold();
            ages = citizenDataClass.getAges();
            refnum= citizenDataClass.getRefnum();
            enufullname = citizenDataClass.getEnufullname();
            houseowner = citizenDataClass.getHouseowner();

            //education information starts
            readAndWrite = citizenDataClass.getReadAndWrite();
            everAttendedSch = citizenDataClass.getEverAttendedSch();
            attendschlyear = citizenDataClass.getAttendschlyear();
            highestgrade = citizenDataClass.getHighestgrade();
            currentlyinsch = citizenDataClass.getCurrentlyinsch();
            currentgradeatt = citizenDataClass.getCurrentgradeatt();
            whoruns = citizenDataClass.getWhoruns();
            problemswithsch = citizenDataClass.getProblemswithsch();



            //health information starts
            livebirth = citizenDataClass.getLivebirth();
            postnatalcare = citizenDataClass.getPostnatalcare();
            prenatalcare = citizenDataClass.getPrenatalcare();
            sicklast4wks = citizenDataClass.getSicklast4wks();
            sortof = citizenDataClass.getSortof();
            kindof = citizenDataClass.getKindof();
            sickness = citizenDataClass.getSickness();
            consultedlt4wks = citizenDataClass.getConsultedlt4wks();
            times = citizenDataClass.getTimes();
            medic = citizenDataClass.getMedic();
            visit = citizenDataClass.getVisit();


        }

        btngeninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("names",names);
                bundle.putString("gender",gender);
                bundle.putString("marital_status",marital_status);
                bundle.putString("contributeTohouseholdIncome",contributeTohouseholdIncome);
                bundle.putString("beenaway",beenaway);
                bundle.putString("relatedTohousehold",relatedTohousehold);
                bundle.putString("ages",ages);
                bundle.putString("houseowner",houseowner);

                Intent intent1 = new Intent(nextmycitizenselected.this,citizen_general_info.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

        btneduinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("readAndWrite",readAndWrite);
                bundle.putString("everAttendedSch",everAttendedSch);
                bundle.putString("attendschlyear",attendschlyear);
                bundle.putString("highestgrade",highestgrade);
                bundle.putString("currentgradeatt",currentgradeatt);
                bundle.putString("currentlyinsch",currentlyinsch);
                bundle.putString("whoruns",whoruns);
                bundle.putString("problemswithsch",problemswithsch);

                Intent intent1 = new Intent(nextmycitizenselected.this,citizen_edu_info.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });

        btnhealthinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("livebirth",livebirth);
                bundle.putString("prenatalcare",prenatalcare);
                bundle.putString("postnatalcare",postnatalcare);
                bundle.putString("sicklast4wks",sicklast4wks);
                bundle.putString("sortof",sortof);
                bundle.putString("kindof",kindof);
                bundle.putString("sickness",sickness);
                bundle.putString("consultedlt4wks",consultedlt4wks);
                bundle.putString("times",times);
                bundle.putString("medic",medic);
                bundle.putString("visit",visit);

                Intent intent1 = new Intent(nextmycitizenselected.this,citizen_health_info.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
    }
}