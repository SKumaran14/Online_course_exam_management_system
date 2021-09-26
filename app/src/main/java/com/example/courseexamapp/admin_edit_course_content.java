package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class admin_edit_course_content extends AppCompatActivity {
    private Button save;
    private Button cou_next,admin_home;
    private ImageView back;
    private EditText admin_course_content,admin_course_content1;
    private TextView Edited_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_course_content);

        admin_home= findViewById(R.id.admin_home);
        save = findViewById(R.id.admin_course_content_save);
        admin_course_content= findViewById(R.id.admin_course_content);
        admin_course_content1= findViewById(R.id.admin_course_content1);
        Edited_text= findViewById(R.id.Edited_text);
        cou_next = findViewById(R.id.admin_course_content_next);
        back = findViewById(R.id.go_back);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(admin_edit_course_content.this, "Saved Succesfully",  Toast.LENGTH_SHORT).show();
                String stradmin_course_content = admin_course_content.getText().toString();
                String stradmin_course_content1 = admin_course_content1.getText().toString();
                Edited_text.setText(stradmin_course_content + "\n" + stradmin_course_content1);
            }
        });

        cou_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_edit_course_content.this, admin_courses.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_edit_course_content.this, admin_courses.class);
                startActivity(intent);
            }
        });


        admin_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),admin_courses.class));
                finish();
            }
        });
    }


}