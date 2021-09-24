package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button btnlogin;
    private Button btnsignup;
    private Button btnchangepassword;
//    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btn_login);
        btnsignup = findViewById(R.id.btn_signup);
        btnchangepassword = findViewById(R.id.btn_chgpas);

//        firebaseAuth = FirebaseAuth.getInstance();
//        //When Login is ok
//        if(firebaseAuth.getCurrentUser() != null){
//            Intent intent = new Intent(Login.this, Menu.class);
//            startActivity(intent);
//            finish();
//        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Menu.class);
                Toast.makeText(getApplicationContext(),"A First Step to Betterment",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        btnchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ChangePassword.class);
                startActivity(intent);
            }
        });
    }
}