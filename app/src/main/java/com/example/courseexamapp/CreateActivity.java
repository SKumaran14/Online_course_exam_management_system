package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;

public class CreateActivity extends AppCompatActivity {
//    EditText titleName, content;
//    Button share_btn;
//    Students stuobj;
//    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
//
//        getSupportActionBar().setTitle("Code World");
////        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//
//        titleName = findViewById(R.id.title);
//        content = findViewById(R.id.content);
//        share_btn = findViewById(R.id.share_btn);
//
//        stuobj = new Students();


//    }
//
//    public void ClearControls() {
//        titleName.setText("");
//        content.setText("");
//    }
//    public void CreateData(View view){
//        dbRef  = FirebaseDatabase.getInstance().getReference().child("Student");
//        try{
//            if (TextUtils.isEmpty(titleName.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Add title", Toast.LENGTH_SHORT).show();
//            else if (TextUtils.isEmpty(content.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Type your doubts", Toast.LENGTH_SHORT).show();
//            else {
//                stuobj.setTitle(titleName.getText().toString().trim());
//                stuobj.setDoubts(content.getText().toString().trim());
//
//                dbRef.push().setValue(stuobj);
//
//                Toast.makeText(getApplicationContext(),"Data saved successfully", Toast.LENGTH_SHORT).show();
//                ClearControls();
//
//            }
//        }
//        catch (Exception e){
//            Toast.makeText(getApplicationContext(),"Attach the pdf file",Toast.LENGTH_SHORT).show();
//        }

 }
}

