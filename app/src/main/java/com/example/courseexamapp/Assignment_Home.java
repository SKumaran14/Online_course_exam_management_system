package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Assignment_Home extends AppCompatActivity {
    private Button Java, Html;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    private BottomNavigationView myBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_home);

        FirebaseApp.initializeApp(this);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference("FileUpload");

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Java = (Button)findViewById(R.id.button_Java);

        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();


        Java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseReference.child("Java").child("MkMz49kAE92pTf_LeE4").addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        if(dataSnapshot.exists()){
                            Java java = dataSnapshot.getValue(Java.class);
                            if(java.getIsSubmitted().equals("true")){
                                Intent intent = new Intent(getApplicationContext(), assignment_submit.class);
                                intent.putExtra("id","MkMz49kAE92pTf_LeE4");
                                intent.putExtra("type", "Java");
                                startActivity(intent);
                            }else {
                                Intent intent = new Intent(getApplicationContext(), Add_assignment.class);
                                intent.putExtra("id","MkMz49kAE92pTf_LeE4");
                                intent.putExtra("type", "Java");
                                startActivity(intent);
                            }
                        }else {
                            Java javaModel = new Java("false", "30/09/2021 12:00:00");
                            FirebaseDatabase.getInstance().getReference("FileUpload").child("Java").child("MkMz49kAE92pTf_LeE4").setValue(javaModel);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError){
                        // TODO: Implement this method
                    }

                });

            }
        });

        Html = (Button)findViewById(R.id.button_html);
        Html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mDatabaseReference.child("Html").child("MkMz-FUztj5snXkhcoT").addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        if(dataSnapshot.exists()){
                            Html html = dataSnapshot.getValue(Html.class);
                            if(html.getIsSubmitted().equals("true")){
                                Intent intent = new Intent(getApplicationContext(), assignment_submit.class);
                                intent.putExtra("id","MkMz-FUztj5snXkhcoT");
                                intent.putExtra("type", "Html");
                                startActivity(intent);
                            }else {
                                Intent intent = new Intent(getApplicationContext(), Add_assignment.class);
                                intent.putExtra("id","MkMz-FUztj5snXkhcoT");
                                intent.putExtra("type", "Html");
                                startActivity(intent);
                            }
                        }else {
                            Html htmlModel = new Html("false" , "30/09/2021 12:00:00");
                            FirebaseDatabase.getInstance().getReference("FileUpload").child("Html").child("MkMz-FUztj5snXkhcoT").setValue(htmlModel);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError){
                        // TODO: Implement this method
                    }

                });

            }
        });



    }
    // this event will enable the back
    // function to the button on press

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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