package com.example.courseexamapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class adddata extends AppCompatActivity
{
    EditText coursename,duration;
    Button admin_add,admin_home;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    model model;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);

        coursename= findViewById(R.id.ad_course);
        duration= findViewById(R.id.add_duration);

        admin_home= findViewById(R.id.admin_home);

        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("courses");

        // initializing our object
        // class variable.
        model = new model();
        admin_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),admin_courses.class));
                finish();
            }
        });

        admin_add= findViewById(R.id.admin_add);
        admin_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("c",coursename.getText().toString());
        map.put("coursename",coursename.getText().toString());
        map.put("duration",duration.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("course").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void avoid) {
                        coursename.setText("");
                        duration.setText("");

                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
}

//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class adddata extends AppCompatActivity {
//
//    // creating variables for
//    // EditText and buttons.
//
//    private EditText coursename,duration;
//    private Button admin_add;
//    private Button admin_home;
//
//    // creating a variable for our
//    // Firebase Database.
//    FirebaseDatabase firebaseDatabase;
//
//    // creating a variable for our Database
//    // Reference for Firebase.
//    DatabaseReference databaseReference;
//
//    // creating a variable for
//    // our object class
//    model model;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_adddata);
//
//        //        admin home navigation
//        admin_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(adddata.this,admin_courses.class));
//                finish();
//            }
//        });
//
//        // initializing our edittext and button
//        coursename = findViewById(R.id.ad_course);
//        duration = findViewById(R.id.add_duration);
//
//
//        // below line is used to get the
//        // instance of our Firebase database.
//        firebaseDatabase = FirebaseDatabase.getInstance();
//
//        // below line is used to get reference for our database.
//        databaseReference = firebaseDatabase.getReference("course");
//
//        // initializing our object
//        // class variable.
//        model = new model();
//
//        admin_add = findViewById(R.id.admin_add);
//        admin_home = findViewById(R.id.admin_home);
//
//
//
//        // adding on click listener for our button.
//        admin_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // getting text from our edittext fields.
//                String cname = coursename.getText().toString();
//                String dura = duration.getText().toString();
//
//
//                // below line is for checking weather the
//                // edittext fields are empty or not.
//                if (TextUtils.isEmpty(cname) || TextUtils.isEmpty(dura)) {
//                    // if the text fields are empty
//                    // then show the below message.
//                    Toast.makeText(adddata.this, "Please add some data.", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    // else call the method to add
//                    // data to our database.
//                    addDatatoFirebase(cname,dura);
//                }
//            }
//        });
//    }
//
//    private void addDatatoFirebase(String cname, String dura) {
//        // below 3 lines of code is used to set
//        // data in our object class.
//        model.setcoursename(cname);
//        model.setduration(dura);
//
//
//        // we are use add value event listener method
//        // which is called with database reference.
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // inside the method of on Data change we are setting
//                // our object class to our database reference.
//                // data base reference will sends data to firebase.
//                databaseReference.setValue(model);
//
//                // after adding this data we are showing toast message.
//                Toast.makeText(adddata.this, "data added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // if the data is not added or it is cancelled then
//                // we are displaying a failure toast message.
//                Toast.makeText(adddata.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//
//
//    }
//}
