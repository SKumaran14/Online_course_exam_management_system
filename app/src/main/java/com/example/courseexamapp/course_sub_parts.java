package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class course_sub_parts extends AppCompatActivity {
    private Button tit;
    private ImageView back;
    private Button nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_sub_parts);
        tit = findViewById(R.id.btn_ur_python);
        nxt = findViewById(R.id.cont_next);
        back = findViewById(R.id.go_back);

        tit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_sub_parts.this, course_content.class);
                startActivity(intent);
            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_sub_parts.this, course_content.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(course_sub_parts.this, your_courses.class);
                startActivity(intent);
            }
        });
    }
}