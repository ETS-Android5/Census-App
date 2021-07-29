package com.example.jpro;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class register_supervisor extends AppCompatActivity {
    EditText fName, lName, mEmail, Password;
    Button BtnReg,alertBt;

    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private static final String USER="supervisor";
    private static final String TAG="register_supervisor";
    private User user;
    String sEmail,sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_supervisor);

        fName = (EditText)findViewById(R.id.fnames);
        lName = (EditText)findViewById(R.id.lnames);
        mEmail = (EditText)findViewById(R.id.emailss);
        Password = (EditText)findViewById(R.id.passwordss);
        BtnReg = (Button)findViewById(R.id.btn_register);

        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USER);
        mAuth = FirebaseAuth.getInstance();

        BtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {

        String Fname = fName.getText().toString().trim();
        String Lname = lName.getText().toString().trim();
        final String memail = mEmail.getText().toString().trim();
        final String mPassword = Password.getText().toString().trim();

        if (Fname.isEmpty()){
            fName.setError("First Name is required!");
            fName.requestFocus();
            return;
        }
        if (Lname.isEmpty()){
            lName.setError("Last Name is required!");
            lName.requestFocus();
            return;
        }
        if (memail.isEmpty()){
            mEmail.setError("Email is required!");
            mEmail.requestFocus();
            return;
        }
        if(!isEmailValid(memail)){
            mEmail.setError("Invalid Email");
            mEmail.requestFocus();
            return;
        }

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    if (ds.child("email").getValue().equals(memail)){
                        mEmail.setError("Email already registered");
                        mEmail.requestFocus();
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (mPassword.isEmpty()){
            Password.setError("Password is required!");
            Password.requestFocus();
            return;
        }
        if (mPassword.length()<6){
            Password.setError("Minimum number of characters is 6!");
            Password.requestFocus();
            return;
        }

        user = new User(memail,mPassword,Fname,Lname);


        mAuth.createUserWithEmailAndPassword(memail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

//                            alertDialog();

                            sendMail();//send mail

                            updateUI(user);//update

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(register_supervisor.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser){
        String keyID = mDatabase.push().getKey();
        mDatabase.child(keyID).setValue(user);

//        alertDialog();
       Intent i = new Intent(this, adminDashboard.class);
        startActivity(i);
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public void sendMail(){
        //sender email credentials
        sEmail="finalyearprojectmail1@gmail.com";
        sPassword="Myf1nalyearpr0ject";

        //initialize properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        //initialize session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sEmail,sPassword);
            }
        });


        try {
            //initialize email content
            Message message = new MimeMessage(session);

            //sender email
            message.setFrom(new InternetAddress(sEmail));

            //recipient email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mEmail.getText().toString().trim()));

            //email subject
            message.setSubject("New account created for you!");

            //email message
            message.setText("A new account is created for you with login credentials:\n\n EMAIL: "+mEmail.getText().toString().trim()+"\n PASSWORD: "+Password.getText().toString().trim()+"\n\n Thank You!");

            //send email
            new SendMail().execute(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private class SendMail extends AsyncTask<Message,String,String> {
        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Fail";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("Success")){
                Toast.makeText(register_supervisor.this, "Email Sent",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(register_supervisor.this, "Email sending failed.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}