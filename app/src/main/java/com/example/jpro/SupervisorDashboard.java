package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SupervisorDashboard extends AppCompatActivity {
    TextView tvnamed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_dashboard);

        tvnamed = (TextView)findViewById(R.id.textviewnamed);
        tvnamed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),visualizations.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {

    }

    public void add_new(View view) {
        Intent intent = new Intent(getApplicationContext(),register_user.class);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this,login.class));
    }

    public void view_members(View view) {
        Intent intent = new Intent(getApplicationContext(),users_list.class);
        startActivity(intent);
    }

    public void facerec(View view) {
        Intent intent = new Intent(getApplicationContext(),all_citizen_list.class);
        startActivity(intent);
    }

}