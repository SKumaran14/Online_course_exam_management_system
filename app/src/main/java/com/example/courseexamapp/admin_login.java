package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class admin_login extends AppCompatActivity {
    private EditText admin_username, admin_pw;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        admin_username=findViewById(R.id.admin_username);
        admin_pw=findViewById(R.id.admin_pw);
        Button btn_login = findViewById(R.id.btn_login);

        mAuth=FirebaseAuth.getInstance();
        btn_login.setOnClickListener(v -> {
            String email= admin_username.getText().toString().trim();
            String password=admin_pw.getText().toString().trim();
            if(email.isEmpty())
            {
                admin_username.setError("Email is empty");
                admin_pw.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                admin_username.setError("Enter the valid email");
                admin_pw.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                admin_username.setError("Password is empty");
                admin_pw.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                admin_username.setError("Length of password is more than 6");
                admin_pw.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(admin_login.this, admin_courses.class));
                }
                else
                {
                    Toast.makeText(admin_login.this,
                            "Please Check Your login Credentials",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });

    }

}