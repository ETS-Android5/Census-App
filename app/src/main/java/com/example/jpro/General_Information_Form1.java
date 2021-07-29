package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class General_Information_Form1 extends AppCompatActivity {
    String refnum ="";
    EditText ref, houseown;
    TextView refbtn;
    String enufullname="";
    String houseowner="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general__information__form1);

        ref = (EditText)findViewById(R.id.referenceNum);
        refbtn = (TextView) findViewById(R.id.refbtn);
        houseown =(EditText)findViewById(R.id.houseowner);

        //getting enumerator name from previous activity
        Bundle bundle = getIntent().getExtras();
        enufullname = bundle.getString("enufullname");


        refbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refnum = ref.getText().toString();
                houseowner = houseown.getText().toString();
                Log.d("new", "onClick: "+refnum);
                Log.d("new", "onClick: "+houseowner);

                Bundle bundle1 = new Bundle();
                bundle1.putString("enufullname",enufullname);
                bundle1.putString("houseowner",houseowner);
                bundle1.putString("refNum",refnum);

                Intent intent = new Intent(General_Information_Form1.this, UserGoogleMaps.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
    }
}