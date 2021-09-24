package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    private TextView menu_courses,menu_exam,menu_tasks,menu_forum;
    private ImageView logoutImage;
//    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu_courses = findViewById(R.id.menu_courses);
        menu_exam = findViewById(R.id.menu_exam);
        menu_tasks = findViewById(R.id.menu_tasks);
        menu_forum = findViewById(R.id.menu_forum);
        logoutImage = findViewById(R.id.logoutImage);
//        firebaseAuth = FirebaseAuth.getInstance();

        menu_courses.setOnClickListener(this);
        menu_exam.setOnClickListener(this);
        menu_tasks.setOnClickListener(this);
        menu_forum.setOnClickListener(this);

//        if(firebaseAuth.getCurrentUser() == null){
//            Intent intent = new Intent(Menu.this,Login.class);
//            startActivity(intent);
//            finish();
//        }
//
//        logoutImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(firebaseAuth != null) {
//                    firebaseAuth.signOut();
//                    Intent intent = new Intent(Menu.this,Login.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_courses:
                startActivity(new Intent(Menu.this, your_courses.class));
                break;
            case R.id.menu_exam:
                startActivity(new Intent(Menu.this, ExamPhotoCapture.class));
                break;
            case R.id.menu_tasks:
                startActivity(new Intent(Menu.this, Assignment_Home.class));
                break;
            case R.id.menu_forum:
                startActivity(new Intent(Menu.this, Forum_Main.class));
                break;
        }
    }
}