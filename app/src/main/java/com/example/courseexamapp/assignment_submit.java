package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class assignment_submit extends AppCompatActivity {
    private Button next;
    String id, type;
    TextView dueDate, lastModified, title, reminingTime;

    private BottomNavigationView myBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_submit);

        title = findViewById(R.id.textView_file_picker);
        dueDate = findViewById(R.id.textview_date2);
        lastModified = findViewById(R.id.textView_modi_value);
        reminingTime = findViewById(R.id.textView_textView_time_remaining_value);



        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();

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
                        if(snapshot.exists()){
                            if(type.equals("Java")){
                                Java java = snapshot.getValue(Java.class);
                                dueDate.setText(java.getDeadline());
                                lastModified.setText(java.getSubmittedDate());

                                String result = null;
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                try {
                                    Date d1 = sdf.parse(java.getSubmittedDate());
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
                                lastModified.setText(html.getSubmittedDate());

                                String result = null;
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                try {
                                    Date d1 = sdf.parse(html.getSubmittedDate());
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

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        next = (Button)findViewById(R.id.btn_edit);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), assignment_update_delete.class);
                intent.putExtra("id",id);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String findDifference(String start_date,
                                 String end_date)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String result = null;
        try {
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            long millis = d2.getTime() - d1.getTime();
            int hours = (int) (millis / (1000 * 60 * 60));
            int mins = (int) ((millis / (1000 * 60)) % 60);

            result = hours + ":" + mins;
        }

        catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
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