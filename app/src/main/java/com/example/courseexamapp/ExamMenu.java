package com.example.courseexamapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExamMenu extends AppCompatActivity implements View.OnClickListener {
    private Button btnHtml;
    private Button btnCss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_menu);
        AlertDialog alertDialog = new AlertDialog.Builder(ExamMenu.this).create();
        alertDialog.setTitle("Online Exam Rules");
        alertDialog.setMessage("No Back Navigation\nNo App Switching\nTimed Exam \nAutomated Correction\nPhoto Verification");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show(); // To show

        btnHtml = findViewById(R.id.menuHtml);
        btnCss = findViewById(R.id.menuCss);
        btnHtml.setOnClickListener(this);
        btnCss.setOnClickListener(this);
    }

    //Checking the button clicks of answer buttons
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.menuHtml:
                Intent intent1 = new Intent(ExamMenu.this, HtmlExam.class);
                startActivity(intent1);
                break;

            case R.id.menuCss:
                Intent intent2 = new Intent(ExamMenu.this,CssExam.class);
                startActivity(intent2);
                break;
        }
    }
}