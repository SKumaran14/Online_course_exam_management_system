package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Login extends AppCompatActivity {
    private Button btnlogin;
    private Button btnsignup;
    private Button btnchangepassword;
    private EditText username,password;
    DBHelper DB ;
    private BottomNavigationView myBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btn_llogin);
        btnsignup = findViewById(R.id.btn_lsignup);
        btnchangepassword = findViewById(R.id.btn_chgpas);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        username = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
        DB = new DBHelper(this);


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString(); // To get values from user
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){ // If any fields are empty
                    Toast.makeText(Login.this,"Please Enter All the fields",Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(user.toString()).matches()){
                    Toast.makeText(Login.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
                else if(pass.toString().length()<6){
                    Toast.makeText(Login.this, "Password should be atleast 6 digits", Toast.LENGTH_SHORT).show();
                }
                //Validations got passed
                else
                {
                    Boolean checkuserPass = DB.checkUsernamePassword(user,pass); // To verify email and password from the database
                    if(checkuserPass == true){ // email,password correct, then login success
                        Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Menu.class);
                        startActivity(intent);
                    }
                    else{ //If username or password incorrect
                        Toast.makeText(Login.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
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