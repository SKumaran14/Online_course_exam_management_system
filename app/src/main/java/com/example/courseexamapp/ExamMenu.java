package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExamMenu extends AppCompatActivity implements View.OnClickListener {
    private Button btnHtml;
    private Button btnCss;
    private BottomNavigationView myBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_menu);
        //Before attending Exam
        AlertDialog alertDialog = new AlertDialog.Builder(ExamMenu.this).create();
        alertDialog.setTitle("Online Exam Conditions");
        alertDialog.setMessage("No Back Navigation\nNo App Switching\nTimed Exam \nRealtime Automated Marking\nPhoto Verification");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show(); // To show

        btnHtml = findViewById(R.id.menuHtml);
        btnCss = findViewById(R.id.menuCss);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick(); // To get functions from the botton Navigation
        btnHtml.setOnClickListener(this);
        btnCss.setOnClickListener(this);
    }

    //Checking the button clicks of answer buttons
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.menuHtml:
                //If HTML button clicked it prompt to HTML Exam
                Intent intent1 = new Intent(ExamMenu.this, HtmlExam.class);
                startActivity(intent1);
                break;

            case R.id.menuCss:
                // If CSS button clicked it prompt to CSS Exam
                Intent intent2 = new Intent(ExamMenu.this,CssExam.class);
                startActivity(intent2);
                break;
        }
    }
    //For Bottom Navigationbar Function
    public void bottomNavClick(){
        myBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

                switch (menuItem.getItemId()) {
                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(), your_courses.class));
                        break;
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), Menu.class));
                        break;
                    case R.id.forum:
                        startActivity(new Intent(getApplicationContext(), Forum_Main.class));
                        break;
                }
                return true;
            }
        });
    }
}