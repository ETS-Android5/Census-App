package com.example.jpro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SupervisorLogin extends AppCompatActivity {

    EditText email,password;
    Button blogin;
    private FirebaseAuth mAuth;
    private static String TAG="SupervisorLogin";

//    @Override
//    protected void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_login);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        blogin = (Button)findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();

        //onclick listerner for the login button
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                //call loginAdmin method.
                loginAdmin(mEmail,mPass);

            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    //loginAdmin method
    private void loginAdmin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SupervisorLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    //updateUI method
    private void updateUI(FirebaseUser currentUser){
        Intent adminDash = new Intent(this, SupervisorDashboard.class);
        adminDash.putExtra("email",currentUser.getEmail());//sends the email to the adminddash activity
        startActivity(adminDash);
        finish();
    }
}