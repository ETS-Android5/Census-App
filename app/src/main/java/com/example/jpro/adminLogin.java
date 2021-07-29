package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class adminLogin extends AppCompatActivity {
    EditText email,password;
    Button login;

    String AdminEmail="admin@gmail.com";
    String AdminPass="adminpassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        email = (EditText)findViewById(R.id.emails);
        password = (EditText)findViewById(R.id.passwords);
        login = (Button)findViewById(R.id.btn_logins);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (mEmail.isEmpty()){
                    email.setError("Enter email");
                    email.requestFocus();
                    return;
                }
                if (!isEmailValid(mEmail)){
                    email.setError("invalid email");
                    email.requestFocus();
                    return;
                }
                if (mPass.isEmpty()){
                    password.setError("Enter password");
                    password.requestFocus();
                    return;
                }

                adlogin(mEmail,mPass);
            }
        });
    }

    private void adlogin(String mEmail, String mPass) {
        if (AdminEmail.equals(mEmail) && AdminPass.equals(mPass)){
            Intent intent = new Intent(getApplicationContext(),adminDashboard.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Invalid credentials, Login as supervisor or enumerator!", Toast.LENGTH_LONG).show();
        }
    }

    //method
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}