package com.example.jpro;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class citizen_edu_info extends AppCompatActivity {
    TextView tvread,tvattended,tvattendschlyr,tvhighestgrade,tvcurrentlyinsch,tvcurrentgrade,tvwhoruns,tvproblem;

    String readAndWrite="";
    String everAttendedSch="";
    String attendschlyear= "";
    String highestgrade ="";
    String currentlyinsch="";
    String currentgradeatt="";
    String whoruns="";
    String problemswithsch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_edu_info);

        tvread = (TextView)findViewById(R.id.txtreadandwrite);
        tvattended = (TextView)findViewById(R.id.txteverattendedschool);
        tvattendschlyr = (TextView)findViewById(R.id.txtattenshcoollyr);
        tvhighestgrade = (TextView)findViewById(R.id.txthigestgrade);
        tvcurrentlyinsch = (TextView)findViewById(R.id.txtcurrentlyinsch);
        tvcurrentgrade = (TextView)findViewById(R.id.txtcurrentgrade);
        tvwhoruns = (TextView)findViewById(R.id.txtwhoruns);
        tvproblem = (TextView)findViewById(R.id.txtprblemswithsch);

        Bundle bundle = getIntent().getExtras();

        if (bundle !=null){
            readAndWrite = bundle.getString("readAndWrite");
            everAttendedSch = bundle.getString("everAttendedSch");
            attendschlyear = bundle.getString("attendschlyear");
            highestgrade = bundle.getString("highestgrade");
            currentlyinsch = bundle.getString("currentlyinsch");
            currentgradeatt = bundle.getString("currentgradeatt");
            whoruns = bundle.getString("whoruns");
            problemswithsch = bundle.getString("problemswithsch");

            tvread.setText(readAndWrite);
            tvattended.setText(everAttendedSch);
            tvattendschlyr.setText(attendschlyear);
            tvhighestgrade.setText(highestgrade);
            tvcurrentlyinsch.setText(currentlyinsch);
            tvcurrentgrade.setText(currentgradeatt);
            tvwhoruns.setText(whoruns);
            tvproblem.setText(problemswithsch);
        }
    }
}