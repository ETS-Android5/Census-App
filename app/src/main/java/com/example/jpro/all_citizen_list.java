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

public class all_citizen_list extends AppCompatActivity implements all_citizen_adapter.allselectedCitizen {

    RecyclerView recyclerView;
    List<IdData> allcitizenList;
    String[] names={"Rich","Alice","Rose"};
    TextView tvref;

    //adapter here
    all_citizen_adapter all_citizen_adapter;
    // FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_citizen_list);

        recyclerView = (RecyclerView)findViewById(R.id.newrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        allcitizenList = new ArrayList<>();

//        allcitizenList.add(new IdData("mango"));
//        allcitizenList.add(new IdData("shoelace"));
//        allcitizenList.add(new IdData("Lucy"));

        all_citizen_adapter = new all_citizen_adapter(allcitizenList,this);
        recyclerView.setAdapter(all_citizen_adapter);

        reference = FirebaseDatabase.getInstance().getReference("Ref_Numbers");
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
        for (DataSnapshot ds:dataSnapshot.getChildren()){
            IdData data = ds.getValue(IdData.class);
            allcitizenList.add(data);


            //to do
            //create another list and push new list into it removing dupliceates
            //if new list already contatins value coming from old list, do not add it else add it.


        }

        all_citizen_adapter = new all_citizen_adapter(allcitizenList,this);
        recyclerView.setAdapter(all_citizen_adapter);
    }

    @Override
    public void allselectedcitizen(IdData idData) {
        startActivity(new Intent(all_citizen_list.this, allselectedCitizen.class).putExtra("data",idData));
    }
}