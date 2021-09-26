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
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Signup extends AppCompatActivity {
    private Button login;
    private Button signup;
    private final String Ch_ID = "Code World";
    private final int Noti_ID = 1;
    private EditText username,password,repassword;
    private BottomNavigationView myBottomNavigation;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);
        username = findViewById(R.id.semail);
        password = findViewById(R.id.spassword);
        repassword = findViewById(R.id.srepassword);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        DB = new DBHelper(this); // Database helper instance


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
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")) { //If any of fields are empty
                    Toast.makeText(Signup.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(user.toString()).matches()) {
                    Toast.makeText(Signup.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(repass)) {
                    Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (pass.toString().length() < 6) {
                    Toast.makeText(Signup.this, "Password should be atleast 6 digits", Toast.LENGTH_SHORT).show();
                } else {
                    // checking both passwords are same
                    Boolean checkuser = DB.checkUsername(user); // email address already existed or not
                    if (checkuser == false) { // email is not exists
                        Boolean insert = DB.insertData(user, pass); // creating a user with email
                        if (insert == true) {
                            Toast.makeText(Signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signup.this, Menu.class));
                            showNotifications(); //Showing a welcome notification
                        } else {
                            Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Signup.this, "Email address already exists, Please Login", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }


            //Regarding Notifications
    private void showNotifications(){
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

}


