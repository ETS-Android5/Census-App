package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Citizen_list extends AppCompatActivity implements citizen_adapter.selectedCitizen{

    String refNum="";
    String houseowner="";
    String enufullname="";
    Double lat, lon;
//    Toolbar toolbar;
    RecyclerView recyclerView;

    List<CitizenDataClass> citizenList;
    String[] names={"Rich","Alice","Rose"};
    TextView tvref;
    Button btnaddanother;
    Button btndone;

    //adapter here
    citizen_adapter Citizen_adapter;
   // FirebaseDatabase rootnode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citizen_list);

        tvref = (TextView)findViewById(R.id.tvrefnum);
        btnaddanother = (Button)findViewById(R.id.btnaddanother);
        btndone = (Button)findViewById(R.id.btnadone);

        //accerpting bundle from health information form 3
        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            refNum = bundle.getString("refnum");
            houseowner = bundle.getString("houseowner");
            enufullname = bundle.getString("enufullname");
            lat = bundle.getDouble("lat");
            lon = bundle.getDouble("lon");

            //display refnumber on top of the list form
            tvref.setText(refNum);
        }

        recyclerView = (RecyclerView)findViewById(R.id.citizenrecyclerview);
//        toolbar = (Toolbar) findViewById(R.id.citizentoolbar);

//        this.setSupportActionBar(toolbar);
        //this.getSupportActionBar().setTitle(" ");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        citizenList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Citizens");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                run(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnaddanother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to begining of form with refnum and house hold owner
                Bundle bundle1 = new Bundle();
                bundle1.putString("refNum",refNum);
                bundle1.putString("enufullname",enufullname);
                bundle1.putString("houseowner",houseowner);
                bundle1.putDouble("lat",lat);
                bundle1.putDouble("lon",lon);

                Intent intent = new Intent(Citizen_list.this, General_Information_Form2.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Citizen_list.this, UserDashboard.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void run(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds:dataSnapshot.child(refNum).getChildren()){
            CitizenDataClass data = ds.getValue(CitizenDataClass.class);
            citizenList.add(data);
        }
        Citizen_adapter = new citizen_adapter(citizenList,this);
        recyclerView.setAdapter(Citizen_adapter);
    }


    @Override
    public void selectedcitizen(CitizenDataClass citizenDataClass) {
        startActivity(new Intent(Citizen_list.this, nextmycitizenselected.class).putExtra("data",citizenDataClass));
    }
}