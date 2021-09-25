package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button btnlogin;
    private Button btnsignup;
    private Button btnchangepassword;
    private BottomNavigationView myBottomNavigation;
//    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.admin_login);
        btnsignup = findViewById(R.id.btn_signup);
        btnchangepassword = findViewById(R.id.btn_chgpas);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();

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