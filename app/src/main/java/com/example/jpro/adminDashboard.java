package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class adminDashboard extends AppCompatActivity {
    TextView tvnamed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

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
        Intent intent = new Intent(getApplicationContext(),register_supervisor.class);
        startActivity(intent);
    }

    public void view_members(View view) {
        Intent intent = new Intent(getApplicationContext(),supervisors_list.class);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(this,login.class));
    }

    public void facerec(View view) {
        Intent intent = new Intent(getApplicationContext(),all_citizen_list.class);
        startActivity(intent);
    }
}