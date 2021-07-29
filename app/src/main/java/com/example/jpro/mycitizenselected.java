package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
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

public class mycitizenselected extends AppCompatActivity implements citizen_adapter.selectedCitizen{

    TextView tvname;
    String refnum="";

    RecyclerView recyclerView;
    List<CitizenDataClass> citizenList;

    //adapter here
    citizen_adapter Citizen_adapter;
    // FirebaseDatabase rootnode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycitizenselected);

        tvname = (TextView)findViewById(R.id.tvnamemycitizen);

        Intent intent = getIntent();
        if (intent.getExtras() !=null){
            IdData idData = (IdData) intent.getSerializableExtra("data");
            tvname.setText(idData.getRefnum());
            refnum = idData.getRefnum();
        }

        recyclerView = (RecyclerView)findViewById(R.id.recyclerviewallcitizen);

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
    }

    private void run(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds:dataSnapshot.child(refnum).getChildren()){
            CitizenDataClass data = ds.getValue(CitizenDataClass.class);
            citizenList.add(data);
        }
        Citizen_adapter = new citizen_adapter(citizenList,this);
        recyclerView.setAdapter(Citizen_adapter);
    }

    @Override
    public void selectedcitizen(CitizenDataClass citizenDataClass) {
    startActivity(new Intent(mycitizenselected.this,nextmycitizenselected.class).putExtra("data",citizenDataClass));
    }
}