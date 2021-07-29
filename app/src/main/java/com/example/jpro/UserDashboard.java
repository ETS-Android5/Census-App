package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDashboard extends AppCompatActivity {
    String superlname="";
    String superfname="";
    String enumfullname="";
    private TextView tvfname, tvnamed;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USER="user";
    String email;
    private LinearLayout linearLayout;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        //getting email from login page
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            enumfullname= bundle.getString("enufullname");
        }

//        tvname = (TextView)findViewById(R.id.textviewuser);
        tvfname = (TextView)findViewById(R.id.textviewfname);
        linearLayout =(LinearLayout)findViewById(R.id.linearlayout);
        tvnamed = (TextView)findViewById(R.id.textviewfnamed);

        tvnamed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),visualizations.class);
                startActivity(intent1);
            }
        });

        //logout
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(UserDashboard.this,login.class));
            }
        });

        //instance of the database reference
        database =FirebaseDatabase.getInstance();
        userRef = database.getReference(USER);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    if (ds.child("email").getValue().equals(email)){
//                       tvname.setText(ds.child("lastname").getValue(String.class));
                       superlname= ds.child("lastname").getValue(String.class);
//                       tvfname.setText(ds.child("firstname").getValue(String.class));
                       superfname = ds.child("firstname").getValue(String.class);
                       tvfname.setText(superfname+" "+superlname);
                       enumfullname = superfname+" "+superlname;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void addcitizen(View view) {
        //getting supervisors first and lastname
        String enufullname = superfname +" "+ superlname;

        Intent intent = new Intent(getApplicationContext(), DetectorActivity.class);
        intent.putExtra("enufullname",enufullname);
        startActivity(intent);


    }


    public void mycitizen(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("enumfullname",enumfullname);
        Intent intent = new Intent(getApplicationContext(), mycitizen.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}