package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;


public class Forum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
//        getSupportActionBar().setTitle("Code World");
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Button mButton = findViewById(R.id.circle_btn);
        mButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(Forum.this, CreateActivity.class));

            }
        });


        Button bButton = findViewById(R.id.circle_btn2);
        bButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(Forum.this, Posts.class));

            }
        });

        Button vButton = findViewById(R.id.Rated_post_btn);
        vButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(Forum.this, Forum_Main.class));

            }
        });



    }
}

