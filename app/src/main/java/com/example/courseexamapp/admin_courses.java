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
import com.google.firebase.auth.FirebaseAuth;

public class admin_courses extends AppCompatActivity {
    private Button btn_edit,btn_delete,admin_html,admin_logout;
    private ImageView back;
    private BottomNavigationView myBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_courses);
        admin_logout = findViewById(R.id.admin_logout);
        btn_edit = findViewById(R.id.admin_edit);
        btn_delete = findViewById(R.id.admin_delete);
        admin_html = findViewById(R.id.admin_menu_btn_html);
        back = findViewById(R.id.go_back);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_courses.this, admin_edit_course_content.class);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_courses.this, delete_course.class);
                startActivity(intent);
            }
        });

        admin_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_courses.this, admin_course_sub_parts.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_courses.this, admin_courses.class);
                startActivity(intent);
            }
        });
        admin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == admin_logout) {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(admin_courses.this, admin_login.class));

                }
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

