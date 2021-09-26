package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class alarm extends AppCompatActivity {
    Button st_alarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);



        st_alarm= findViewById(R.id.st_alarm);

        st_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAlert();
            }
        });
    }

    public void startAlert(){
        EditText text = findViewById(R.id.input_time);
        EditText text1 = findViewById(R.id.input_time);

        int i = Integer.parseInt(text.getText().toString());
        int j = Integer.parseInt(text.getText().toString());
        int k;
        k = i +j;
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + ((k) * 1000), pendingIntent);
        Toast.makeText(this, "Alarm set in " + k + " seconds",Toast.LENGTH_LONG).show();
    }
}
