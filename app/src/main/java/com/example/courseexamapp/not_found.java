package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class not_found extends AppCompatActivity {
    private Button nt_found;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_found);
        nt_found = findViewById(R.id.not_found);
        back = findViewById(R.id.go_back);

        nt_found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(not_found.this, search_course.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(not_found.this, search_course.class);
                startActivity(intent);
            }
        });
    }
}



