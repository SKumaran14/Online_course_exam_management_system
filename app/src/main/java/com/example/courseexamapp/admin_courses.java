package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class admin_courses extends AppCompatActivity {
    private Button btn_edit;
    private Button btn_delete;
    private Button admin_html;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_courses);
        btn_edit = findViewById(R.id.admin_edit);
        btn_delete = findViewById(R.id.admin_delete);
        admin_html = findViewById(R.id.admin_menu_btn_html);
        back = findViewById(R.id.go_back);

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
                Intent intent = new Intent(admin_courses.this, admin_courses.class);
                startActivity(intent);
            }
        });
    }
}

