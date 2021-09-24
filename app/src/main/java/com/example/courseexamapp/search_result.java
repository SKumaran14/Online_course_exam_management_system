package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class search_result extends AppCompatActivity {
    private Button  btn_result_html;
    private Button  no_more;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        btn_result_html = findViewById(R.id.result_html);
        no_more = findViewById(R.id.btn_no_more);
        back = findViewById(R.id.go_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_result.this, search_course.class);
                startActivity(intent);
            }
        });

        btn_result_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_result.this, enrollment_confirmation.class);
                startActivity(intent);
            }
        });

        no_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_result.this, search_course.class);
                startActivity(intent);
            }
        });
    }
}