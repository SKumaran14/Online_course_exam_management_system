package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class Forum_Main extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_main);


        Button mButton = findViewById(R.id.Add_post_btn);
        mButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(Forum_Main.this,CreateActivity.class));

            }
        });

        Button sButton = findViewById(R.id.edit_post_btn);
        sButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(Forum_Main.this,CreateActivity.class));

            }
        });

        Button dButton = findViewById(R.id.delete_post_btn);
        dButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(Forum_Main.this,CreateActivity.class));

            }
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Forum_Main.this,UsersList.class);
                startActivity(intent);


            }
        },15000);

    }
}
