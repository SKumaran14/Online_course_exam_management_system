package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {
    private Button chng, cancel;
    private final String Ch_ID = "Code World";
    private final int Noti_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        chng = findViewById(R.id.btn_chgpass);
        cancel = findViewById(R.id.btn_cancel);
        //Intents ok

        //when click change button goes to login page
        chng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, Login.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Password Changed ", Toast.LENGTH_SHORT).show();
                showNotification();
            }
        });

        //when click cancel button goes to login page
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, Login.class);
                startActivity(intent);
            }
        });
//End of Intents
    }

    private void showNotification() {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), Ch_ID);
        builder.setSmallIcon(R.drawable.chat);
        builder.setContentTitle("Change Password");
        builder.setContentText("Password Changed");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(Noti_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String description = "Simple Notification";
            String name = "Simple Noti";
            int imp = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(Ch_ID, name, imp);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}