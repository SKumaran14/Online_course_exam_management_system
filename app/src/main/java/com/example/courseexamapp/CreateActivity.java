package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateActivity extends AppCompatActivity {
    EditText titleName, content;
    Button share_btn, update_btn, delete_btn;
    Students stuobj;
    DatabaseReference dbRef;
    private BottomNavigationView myBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();

        //  getSupportActionBar().setTitle("Code World");
        //  getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);



        titleName = findViewById(R.id.titleName);
        content = findViewById(R.id.content);
        share_btn = findViewById(R.id.share_btn);
        update_btn = findViewById(R.id.update_btn);

        stuobj = new Students();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Students");
        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateData();
            }
        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpdateData();
            }
        });


    }

    public void ClearControls() {
        titleName.setText("");
        content.setText("");
    }

    public void CreateData() {

        try {
            if (TextUtils.isEmpty(titleName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Add title", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(content.getText().toString()))
                Toast.makeText(getApplicationContext(), "Type your doubts", Toast.LENGTH_SHORT).show();
            else {
                stuobj.setTitle(titleName.getText().toString().trim());
                stuobj.setDoubts(content.getText().toString().trim());

                dbRef.child("std1").setValue(stuobj);

                Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                ClearControls();

            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Add title", Toast.LENGTH_SHORT).show();
        }
    }


    public void UpdateData() {
        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Student");
        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {

                    if (dataSnapshot.hasChild("std1")) {
                        stuobj.setTitle(titleName.getText().toString().trim());
                        stuobj.setDoubts(content.getText().toString().trim());

                        dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("std1");
                        dbRef.setValue(stuobj);
                        ClearControls();

                        Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Add title", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }







    //For Bottom Navigationbar Function
    public void bottomNavClick(){
        myBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

                switch (menuItem.getItemId()) {
                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(), your_courses.class));
                        break;
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), Menu.class));
                        break;
                    case R.id.forum:
                        startActivity(new Intent(getApplicationContext(), Forum_Main.class));
                        break;
                }
                return true;
            }
        });
    }
}

