package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExamPhotoCapture extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    ImageView imageExam;
    private Button photoButton;
    private TextView tlevel, txtPhoto;
    private BottomNavigationView myBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_photo_capture);

        imageExam = (ImageView) this.findViewById(R.id.imageExam);
        imageExam.setImageResource(R.drawable.photo);
        photoButton = this.findViewById(R.id.photoButton);
        tlevel = findViewById(R.id.tlevel);
        txtPhoto = findViewById(R.id.txtphoto);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();

        //Same Alert Box created for both buttons to get consent about photo taking
        AlertDialog alertDialog = new AlertDialog.Builder(ExamPhotoCapture.this).create();
        alertDialog.setTitle("Photo Verification");
        alertDialog.setMessage("Please Take a decent Photograph to confirm Identity");

        //for photo taking button pressed
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For agreeing to camera photo taking
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Agree", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ExamPhotoCapture.this, "Agreed to Take Photo", Toast.LENGTH_SHORT).show();
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                });

                //When not agreeing to camera photo taking
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ExamPhotoCapture.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExamPhotoCapture.this, StudyNotesStudent.class));
                Toast.makeText(ExamPhotoCapture.this, "Take Notes and Study Well", Toast.LENGTH_SHORT).show();
            }
        });

        ///Battery percentage finder
        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    //Displaying the photo taken by student
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if student has taken a photo
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageExam.setImageBitmap(photo);
            txtPhoto.setText("Identity Verified");
            photoButton.setText("Attend Exam");

            Toast.makeText(getApplicationContext(), "Identity Verified", Toast.LENGTH_SHORT).show(); //works
            //attend button enables for exam navigation
            photoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ExamPhotoCapture.this, ExamMenu.class);
                    startActivity(intent);
                }
            });
        }// When photo has not taken successfully , return back and display a toast
        else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "Please Confirm Identity", Toast.LENGTH_SHORT).show(); //works}
        }
    }
    //End of Photo taking


    // For Getting the battery percentage and displaying in text view
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {


        @SuppressLint("SetTeasy18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            tlevel = findViewById(R.id.tlevel);
            tlevel.setText("Battery Level " + Integer.toString(level) + "%");
            // For alerting low level battery conditions
            if (level <= 30) {
                Toast.makeText(getApplicationContext(), "Low Battery Level", Toast.LENGTH_SHORT).show();
            }
        }
    };
// End of battery percentage coding
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