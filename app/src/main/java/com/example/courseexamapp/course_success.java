package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class course_success extends AppCompatActivity {
    private Button  btn_att_exam;
    private ImageView back;
    private BottomNavigationView myBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_success);
        btn_att_exam = findViewById(R.id.btn_exam);
        back = findViewById(R.id.go_back);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();

        btn_att_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_success.this, Menu.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_success.this, your_courses.class);
                startActivity(intent);
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
