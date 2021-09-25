package com.example.courseexamapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class admin_courses extends AppCompatActivity {
    private Button btn_edit;
    private Button btn_delete;
    private Button admin_html;
    private ImageView back;
    private Button admin_logout;
    FirebaseAuth mAuth;
//admin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_courses);

        btn_edit = findViewById(R.id.admin_edit);
        btn_delete = findViewById(R.id.admin_delete);
        admin_html = findViewById(R.id.admin_menu_btn_html);
        back = findViewById(R.id.go_back);
        admin_logout = findViewById(R.id.admin_logout);

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
                Intent intent = new Intent(admin_courses.this, course_sub_parts.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_courses.this,admin_login.class);
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
}