package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class mycitizen extends AppCompatActivity implements all_citizen_adapter.allselectedCitizen{

    String enumfullname="";
    RecyclerView recyclerView;
    List<IdData> allcitizenList;

    //adapter here
    all_citizen_adapter all_citizen_adapter;
    // FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycitizen);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            enumfullname= bundle.getString("enumfullname");
        }

        recyclerView = (RecyclerView)findViewById(R.id.newrecyclerviewmycitizen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        allcitizenList = new ArrayList<>();

        all_citizen_adapter = new all_citizen_adapter(allcitizenList,this);
        recyclerView.setAdapter(all_citizen_adapter);


        Query query = FirebaseDatabase.getInstance().getReference("Ref_Numbers").orderByChild("enumna").equalTo(enumfullname);
        query.addValueEventListener(new ValueEventListener() {
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
        for (DataSnapshot ds:dataSnapshot.getChildren()){
            IdData data = ds.getValue(IdData.class);
            allcitizenList.add(data);
        }

        all_citizen_adapter = new all_citizen_adapter(allcitizenList,this);
        recyclerView.setAdapter(all_citizen_adapter);
    }

    @Override
    public void allselectedcitizen(IdData idData) {
        startActivity(new Intent(mycitizen.this, mycitizenselected.class).putExtra("data",idData));
    }
}