package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class selectedCitizen extends AppCompatActivity {

    TextView tvuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_citizen);

        tvuser = (TextView)findViewById(R.id.selectedCitizen);

        Intent intent = getIntent();
        if (intent.getExtras() !=null){
            CitizenDataClass citizenDataClass = (CitizenDataClass) intent.getSerializableExtra("data");
            tvuser.setText(citizenDataClass.getNames());
        }
    }
}