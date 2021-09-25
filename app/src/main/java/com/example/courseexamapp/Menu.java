package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    private TextView menu_courses,menu_exam,menu_tasks,menu_forum;
    private ImageView logoutImage;
    private BottomNavigationView myBottomNavigation;
//    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu_courses = findViewById(R.id.menu_courses);
        menu_exam = findViewById(R.id.menu_exam);
        menu_tasks = findViewById(R.id.menu_tasks);
        menu_forum = findViewById(R.id.menu_forum);
        logoutImage = findViewById(R.id.logoutImage);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();
//        firebaseAuth = FirebaseAuth.getInstance();

        menu_courses.setOnClickListener(this);
        menu_exam.setOnClickListener(this);
        menu_tasks.setOnClickListener(this);
        menu_forum.setOnClickListener(this);

//        if(firebaseAuth.getCurrentUser() == null){
//            Intent intent = new Intent(Menu.this,Login.class);
//            startActivity(intent);
//            finish();
//        }
//
        logoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Login.class);
                startActivity(intent);
                finish();
            }

        });
    }



//                if(firebaseAuth != null) {
//                    firebaseAuth.signOut();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_courses:
                startActivity(new Intent(Menu.this, your_courses.class));
                break;
            case R.id.menu_exam:
                startActivity(new Intent(Menu.this, ExamPhotoCapture.class));
                break;
            case R.id.menu_tasks:
                startActivity(new Intent(Menu.this, Assignment_Home.class));
                break;
            case R.id.menu_forum:
                startActivity(new Intent(Menu.this, Forum_Main.class));
                break;
        }
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