package com.example.jpro;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class health_information_form3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   public static final int CAMERA_REQUEST_CODE = 1000;
//    private ProgressDialog dialog;
    private FirebaseDatabase rootnode;
    private DatabaseReference reference;
    private DatabaseReference ref;
    Location location;

    private IdData idData;

    Button btn;
    //variables from this activity
    String times="";
    String medic="";
    String visit="";
    String enufullname="";

    //variables from previous activities
    String notinschool="";

    //variables from previous activity
    String readAndWrite="";
    String everAttendedSch="";
    String attendschlyear= "";
    String highestgrade ="";

    //variables for data from previous activity
    String gender="";
    String marital_status="";
    String contributeTohouseholdIncome = "";
    String beenaway = "";
    String relatedTohousehold = "";
    String ages="";
    String names="";
    String refnum="";

    //variables from previous activity
    String currentlyinsch="";
    String currentgradeatt="";
    String whoruns="";
    String problemswithsch = "";


    //variables from previous activity
    String livebirth="";
    String prenatalcare="";
    String postnatalcare = "";
    String sicklast4wks = "";

    //variables from previous activity
    String sortof;
    String kindof="";
    String sickness="";
    String consultedlt4wks="";

    String houseowner="";
    Double lat, lon;

    String lati, loni;

    boolean bool=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_information_form3);

        btn = (Button)findViewById(R.id.save);
        //getting data from previous activities
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            sortof = bundle.getString("sortof");
            kindof = bundle.getString("kindof");
            sickness = bundle.getString("sickness");
            consultedlt4wks = bundle.getString("consultedlt4wks");
            livebirth = bundle.getString("livebirth");
            prenatalcare = bundle.getString("prenatalcare");
            postnatalcare = bundle.getString("postnatalcare");
            sicklast4wks = bundle.getString("sicklast4wks");
            gender = bundle.getString("gender");
            marital_status = bundle.getString("maritalstatus");
            contributeTohouseholdIncome = bundle.getString("contribute");
            beenaway = bundle.getString("beenaway");
            relatedTohousehold = bundle.getString("relatedto");
            ages = bundle.getString("age");
            names = bundle.getString("name");
            refnum = bundle.getString("refnum");
            readAndWrite = bundle.getString("readandwrite");
            everAttendedSch = bundle.getString("everattended");
            attendschlyear = bundle.getString("attendschlyear");
            highestgrade = bundle.getString("highestgrade");
            currentlyinsch = bundle.getString("currentlyinsch");
            currentgradeatt = bundle.getString("currentgradeatt");
            whoruns = bundle.getString("whoruns");
            problemswithsch = bundle.getString("problemwithsch");
            notinschool = bundle.getString("notinschool");
            enufullname = bundle.getString("enufullname");
            houseowner = bundle.getString("houseowner");

            lat = bundle.getDouble("lat");
            lon = bundle.getDouble("lon");

            lati = String.valueOf(lat);
            loni = String.valueOf(lon);

            rootnode = FirebaseDatabase.getInstance();
            ref = rootnode.getReference("Ref_Numbers");




//            ref = rootnode.getReference("Ref_Numbers");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        if (ds.child("refnum").getValue().equals(refnum)){
//                            mEmail.setError("Email already registered");
//                            mEmail.requestFocus();
//                            return;
                            bool = false;
                            Log.d("meow", "onDataChange: Data already in database");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            //check permission
//            if (ActivityCompat.checkSelfPermission(health_information_form3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
//                //call location
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 44);
//
////                btn.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                       operncamera();
////                    }
////                });
//
//            }else {
//
//            }

            Log.d("billson", "onCreate: "+houseowner);
//            if(location != null){
//                lat = location.getLatitude();
//                lon = location.getLatitude();
//            }
            Log.d("billson", "onCreate: "+lati+" "+loni);

            if (consultedlt4wks.equals("No")){
                Spinner visit = (Spinner)findViewById(R.id.spinnervisit);
                Spinner times = (Spinner)findViewById(R.id.spinnertimes);

                visit.setEnabled(false);
                times.setEnabled(false);
            }
        }

        //get data collected from this activity also and save in database
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askpermission();
            }
        });



        Spinner spinner = (Spinner)findViewById(R.id.spinnertimes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        Spinner spinner1 = (Spinner)findViewById(R.id.spinnervisit);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.visit, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner)findViewById(R.id.spinnermedic);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.medic, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }

    public void operncamera(){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    public void askpermission(){
        if (ActivityCompat.checkSelfPermission(health_information_form3.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            //call location
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 44);

//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                       operncamera();
//                    }
//                });

        }else {
            operncamera();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinnertimes){
            times = parent.getSelectedItem().toString();

        }else if (parent.getId() == R.id.spinnervisit){
            visit = parent.getSelectedItem().toString();

        }else if (parent.getId() == R.id.spinnermedic){
            medic = parent.getSelectedItem().toString();

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("shoe", "onActivityResult: Have entered onactivityresult");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE){
            Log.d("shoe", "onActivityResult: Have entered loop");
            if (resultCode == RESULT_OK){
                Log.d("shoe", "onActivityResult: Have second entered loop");
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                File file = new File(String.valueOf(bitmap));
                Uri uri = Uri.fromFile(file);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (bitmap !=null ){
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                }

                byte[] bytes = byteArrayOutputStream.toByteArray();
                String imageString = bytes.toString();

                //set dialog to tell user u sending picture
//                dialog.setMessage("Processing");
//                dialog.show();

                final StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/"+file);
                Log.d("shoe", "onActivityResult: Have entered storage");
                UploadTask uploadTask = storageReference.putBytes(bytes);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @SuppressLint("MissingPermission")
                            @Override
                            public void onSuccess(Uri uri) {
                                rootnode = FirebaseDatabase.getInstance();
                                reference = rootnode.getReference("Citizens");

                                idData = new IdData(refnum,enufullname);

                                if (bool==true){
                                String keyID = ref.push().getKey();
                                ref.child(keyID).setValue(idData);
                                }
//                                ref.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        for (DataSnapshot ds: dataSnapshot.getChildren()){
//                                            if (ds.child("refnum").getValue().equals(refnum)){
//                                                Log.d("meow", "ref number already exist ");
//                                            }
//                                            else {
//                                                String keyID = ref.push().getKey();
//                                                ref.child(keyID).setValue(idData);
//                                            }
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//                                String keyID = ref.push().getKey();
//                                ref.child(keyID).setValue(refnum);

                                CitizenDataClass citizen = new CitizenDataClass( times,  medic,  visit,  enufullname,  notinschool,  readAndWrite, everAttendedSch, attendschlyear, highestgrade, gender, marital_status, contributeTohouseholdIncome, beenaway, relatedTohousehold, ages, names, refnum, currentlyinsch, currentgradeatt, whoruns, problemswithsch, livebirth, prenatalcare,  postnatalcare,  sicklast4wks,  sortof, kindof, sickness, consultedlt4wks, houseowner, lati, loni, String.valueOf(uri));
                                reference.child(refnum).child(names).setValue(citizen);

                                //check if id already exist
//                                ref.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        for (DataSnapshot ds: dataSnapshot.getChildren()){
//                                            if (ds.child("refnum").getValue().equals(refnum)){
//                                                Log.d("meio", "onDataChange: ");
//                                            }
//                                            else {
//                                                String keyID = ref.push().getKey();
//                                                ref.child(keyID).setValue(refnum);
//                                            }
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });

                                Bundle bundle = new Bundle();
                                bundle.putString("refnum",refnum);
                                bundle.putString("enufullname",enufullname);
                                bundle.putString("houseowner",houseowner);
                                bundle.putDouble("lat",lat);
                                bundle.putDouble("lon",lon);

                                Intent intent = new Intent(health_information_form3.this, Citizen_list.class);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            }
                        });
//                        dialog.hide();
                        Toast.makeText(health_information_form3.this, "All done", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                operncamera();
            }else {
                Toast.makeText(this, "Permission is needed", Toast.LENGTH_SHORT).show();
            }
        }
    }

}



//get all data and save in database
//                rootnode = FirebaseDatabase.getInstance();
//                reference = rootnode.getReference("Citizens");
//
//                //getting all the values
//                //CitizenDataClass citizen = new CitizenDataClass(times, medic, visit, enufullname, notinschool, readAndWrite, everAttendedSch, attendschlyear, highestgrade, gender, marital_status, contributeTohouseholdIncome, beenaway, relatedTohousehold, ages, names, refnum, currentlyinsch, currentgradeatt, whoruns, problemswithsch, livebirth, prenatalcare, postnatalcare, sicklast4wks, sortof, kindof, sickness, consultedlt4wks, houseowner);
//                CitizenDataClass citizen = new CitizenDataClass(times, medic, visit, enufullname, notinschool, readAndWrite, everAttendedSch, attendschlyear, highestgrade, gender, marital_status, contributeTohouseholdIncome, beenaway, relatedTohousehold, ages, names, refnum, currentlyinsch, currentgradeatt, whoruns, problemswithsch, livebirth, prenatalcare, postnatalcare, sicklast4wks, sortof, kindof, sickness, consultedlt4wks, houseowner, lati, loni);
//                reference.child(refnum).child(names).setValue(citizen);
//                Toast.makeText(health_information_form3.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
