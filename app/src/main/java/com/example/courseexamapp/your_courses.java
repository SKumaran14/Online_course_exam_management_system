package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class your_courses extends AppCompatActivity {
    private Button enroll_more;
    private Button ur_html;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_course);

        ur_html = findViewById(R.id.btn_ur_python);
        enroll_more = findViewById(R.id.btn_ur_enroll);
        back = findViewById(R.id.go_back);

        ur_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(your_courses.this, course_sub_parts.class);
                startActivity(intent);
            }
        });

        enroll_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(your_courses.this, search_course.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(your_courses.this, search_course.class);
                startActivity(intent);
            }
        });
    }
}