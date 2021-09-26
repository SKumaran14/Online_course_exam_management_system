package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class search_course extends AppCompatActivity {
    private Button btn_html;
    private ImageButton back;
    private Button btn_search_html;
    private BottomNavigationView myBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_course);
        btn_html = findViewById(R.id.course_html);
        btn_search_html = findViewById(R.id.result_html);
        back = findViewById(R.id.go_back);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();

        btn_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_course.this, course_sub_parts.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_course.this, Menu.class);
                startActivity(intent);
            }
        });

        btn_search_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_course.this, course_sub_parts.class);
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