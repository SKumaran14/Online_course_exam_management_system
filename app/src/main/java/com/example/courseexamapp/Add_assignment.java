package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Add_assignment extends AppCompatActivity {
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    String id, type;
    TextView dueDate,title,reminingTime;

    private BottomNavigationView myBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();

        dueDate = findViewById(R.id.textview_date2);
        title = findViewById(R.id.textView_file_picker);
        reminingTime = findViewById(R.id.textView_textView_time_remaining_value);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            type = extras.getString("type");

        }

        title.setText(type);
        FirebaseDatabase.getInstance().getReference("FileUpload").child(type).child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(type.equals("Java")){
                            Java java = snapshot.getValue(Java.class);
                            dueDate.setText(java.getDeadline());

                            String result = null;
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            try {
                                Date d1 = new Date();
                                Date d2 = sdf.parse(java.getDeadline());

                                long millis = d2.getTime() - d1.getTime();
                                int hours = (int) (millis / (1000 * 60 * 60));
                                int mins = (int) ((millis / (1000 * 60)) % 60);
                                int days = (int) (millis / (1000*60*60*24));

                                result =days + "d : " +hours + "h : " + mins + "m";

                            }catch (ParseException e) {
                                e.printStackTrace();
                            }

                            reminingTime.setText(result);
                        }else {
                            Html html = snapshot.getValue(Html.class);
                            dueDate.setText(html.getDeadline());

                            String result = null;
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            try {
                                Date d1 = new Date();
                                Date d2 = sdf.parse(html.getDeadline());

                                long millis = d2.getTime() - d1.getTime();
                                int hours = (int) (millis / (1000 * 60 * 60));
                                int mins = (int) ((millis / (1000 * 60)) % 60);
                                int days = (int) (millis / (1000*60*60*24));

                                result =days + "d : " +hours + "h : " + mins + "m";

                            }catch (ParseException e) {
                                e.printStackTrace();
                            }

                            reminingTime.setText(result);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        Button button = findViewById(R.id.btn_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), upload_file.class);
                intent.putExtra("id",id);
                intent.putExtra("type", type);
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