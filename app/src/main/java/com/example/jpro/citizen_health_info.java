package com.example.jpro;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class citizen_health_info extends AppCompatActivity {
    TextView tvlivebirth, tvprenatal,tvpostnatal,tvsicklast,tvsortof,tvkindof,tvsickness,tvconsulted,tvtimes,tvmedic;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_health_info);

        tvlivebirth = (TextView)findViewById(R.id.txtlivebirth);
        tvprenatal = (TextView)findViewById(R.id.txtprenatal);
        tvpostnatal = (TextView)findViewById(R.id.txtpostnatal);
        tvsicklast = (TextView)findViewById(R.id.txtsickls4wks);
        tvsortof = (TextView)findViewById(R.id.txtinjury);
        tvkindof = (TextView)findViewById(R.id.txtkindof);
        tvsickness = (TextView)findViewById(R.id.txtmisswork);
        tvconsulted = (TextView)findViewById(R.id.txtconsulted);
        tvmedic = (TextView)findViewById(R.id.txtmedic);

        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            livebirth = bundle.getString("livebirth");
            prenatalcare = bundle.getString("prenatalcare");
            postnatalcare = bundle.getString("postnatalcare");
            sicklast4wks = bundle.getString("sicklast4wks");
            sortof = bundle.getString("sortof");
            kindof = bundle.getString("kindof");
            sickness = bundle.getString("sickness");
            consultedlt4wks = bundle.getString("consultedlt4wks");
            times = bundle.getString("times");
            medic = bundle.getString("medic");

            tvlivebirth.setText(livebirth);
            tvprenatal.setText(prenatalcare);
            tvpostnatal.setText(postnatalcare);
            tvsicklast.setText(sicklast4wks);
            tvsortof.setText(sortof);
            tvkindof.setText(kindof);
            tvsickness.setText(times);
            tvconsulted.setText(consultedlt4wks);
            tvmedic.setText(medic);


        }
    }
}