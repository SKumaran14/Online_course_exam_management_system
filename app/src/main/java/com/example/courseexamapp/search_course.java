package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class search_course extends AppCompatActivity {
    private Button btn_html;
    private ImageButton back;
    private Button btn_search_html;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_course);
        btn_html = findViewById(R.id.course_html);
        btn_search_html = findViewById(R.id.result_html);
        back = findViewById(R.id.go_back);

        btn_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_course.this, enrollment_confirmation.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_course.this, your_courses.class);
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
}