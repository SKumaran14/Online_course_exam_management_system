package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private Button login;
    private Button signup;
    private final String Ch_ID = "Code World";
    private final int Noti_ID = 1;
    private EditText email;
    private EditText password;
    private BottomNavigationView myBottomNavigation;
//    private FirebaseAuth firebaseAuth;
//    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);
        email = findViewById(R.id.input_semail);
        password = findViewById(R.id.input_spass);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();
//        firebaseAuth = FirebaseAuth.getInstance();
//        progressDialog = new ProgressDialog(this);

        //Login button inside the signup page
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);

            }
        });
        //Signup button inside the signup page
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String strEmail = email.getText().toString();
//                String strPassword = password.getText().toString();
//
//                if(!TextUtils.isEmpty(strEmail) && TextUtils.isEmpty(strPassword)) {
//                    progressDialog.setMessage("Registering Please Wait");
//                    progressDialog.show();
//                    //For creating a signup with firebase
//                    firebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword)
//                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(Signup.this, "Successfully Created Account", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//                                    progressDialog.dismiss();
//                                }
//                            });
//
//                }
//                else{
//                    Toast.makeText(Signup.this,"Email Or Password is Empty",Toast.LENGTH_SHORT).show();
//                }

                Intent intent = new Intent(Signup.this, Menu.class);
                startActivity(intent);
                showNotification();
            }
        });
    }

//Regarding Notifications
    private void showNotification(){
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), Ch_ID);
        builder.setSmallIcon(R.drawable.chat);
        builder.setContentTitle("Your Profile Created");
        builder.setContentText("Amazing Courses, Exams are on the way");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(Noti_ID,builder.build());
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String description = "Simple Notification";
            String name = "Simple Noti";
            int imp = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(Ch_ID,name,imp);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
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


