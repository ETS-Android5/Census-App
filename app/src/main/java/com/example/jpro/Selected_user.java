package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Selected_user extends AppCompatActivity {

    TextView tvuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_user);

        tvuser = (TextView)findViewById(R.id.selectedUser);

        Intent intent = getIntent();
        if (intent.getExtras() !=null){
            User user = (User) intent.getSerializableExtra("data");
            tvuser.setText(user.getFirstname());
        }
    }
}