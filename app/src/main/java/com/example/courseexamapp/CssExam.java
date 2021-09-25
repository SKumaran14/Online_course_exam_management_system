package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//actual question page which returns questions on the same page
public class CssExam extends AppCompatActivity implements View.OnClickListener {
    //Initial variable declaration
    private Button btnYes,btnNo;
    private TextView txtQu2,time2,remtime2;
    private ImageView img;
    private int index = 0;
    private BottomNavigationView myBottomNavigation;
    private final String Ch_ID = "Code World";
    private final int Noti_ID = 1;


    //Question array for CSS Exam by checking option 1 correct or not
    private Question[] questionBank2 =new Question[]{
            new Question(R.string.q1c,false),
            new Question(R.string.q2c,false),
            new Question(R.string.q3c,true),
            new Question(R.string.q4c,true),
            new Question(R.string.q5c,true)};

    //Replacing images whie moving next questions
    private Integer images[]={R.mipmap.n1,R.mipmap.n2,R.mipmap.n3,R.mipmap.n4,R.mipmap.n5};
    private int option1[] = {R.string.a1c,R.string.a2c,R.string.a3c,R.string.a4c,R.string.a5c};
    private int option2[] = {R.string.i1c,R.string.i2c,R.string.i3c,R.string.i4c,R.string.i5c};
    private CountDownTimer myTimer;
    private static int correctCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_css_exam);

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        txtQu2 = findViewById(R.id.txtQu2);
        img=findViewById(R.id.img);
        img.setImageResource(images[index]);
        timer2();
        int correctCount = 0;
        btnYes.setText(option1[index]);
        btnNo.setText(option2[index]);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        time2 = findViewById(R.id.txt_time2);
        remtime2 = findViewById(R.id.txt_remtime);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick(); //Calling Bottom navigation functions declared
    }
    public void timer2(){ //Timer with 8 seconds
        myTimer = new CountDownTimer(8000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                time2.setText("00:" + String.format("%02d",millisUntilFinished/1000));
            }

            //On CountDown timer finished
            @Override
            public void onFinish() {
                time2.setVisibility(View.INVISIBLE);
                remtime2.setVisibility(View.INVISIBLE);
                showNotification(); // to show notification regarding exam completed
                //When Time is up but exam was not completed
                if(index<questionBank2.length) {
                    img.setImageResource(R.drawable.timeup);
                    int marks = correctCount * 100 / (questionBank2.length);
                    if (marks >= 80) {
                        txtQu2.setText("Congratulations\n Distinction\n Marks = " + marks);
                    } else if (marks < 80 && marks >= 60) {
                        txtQu2.setText("Congratulations\n Credit Pass\n Marks = " + marks);
                    } else {
                        txtQu2.setText("Study Well Next time\n Marks = " + marks);
                    }
                }
                //When option 1 clicked and checking whether that is correct
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAns(true);
                        index++;
                        updateQue();
                    }
                });
                //When option 2 clicked
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAns(false);
                        index++;
                        updateQue();
                    }
                });

                //at exam  finished button names get changed
                btnYes.setText("Try Again");
                Toast.makeText(getApplicationContext(), "Thank you for Attending Exam", Toast.LENGTH_SHORT).show();
                btnNo.setText("Home");
                correctCount = 0; //resetting the marks at the end of exam

                //When exam finished try again button goes  again main screen
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CssExam.this, ExamPhotoCapture.class);
                        startActivity(intent);
                    }
                });

                //When exam finished home button goes again main screen
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CssExam.this,Menu.class);
                        startActivity(intent);
                    }
                });
            }
            //Calculating correct answers with the answer declared in Question class
            //If Option one is correct it is declared as true in the Question Bank
            private void checkAns(boolean uans) {
                boolean isTrue = questionBank2[index].isAns();
                if (uans == isTrue) {
                    correctCount++;
                }
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNo:
                checkAns(false);
                index++;
                updateQue();
                break;

            case R.id.btnYes:
                checkAns(true);
                index++;
                updateQue();
                break;
        }
    }

    //a function for updating questions and calcualting marks
    public void updateQue() {
        //Display Questions Count
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(index);
        progressBar.setMax(5);
        //Display Correct Answers Count
        ProgressBar progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setProgress(correctCount);
        progressBar2.setMax(5);
        //until question bank goes to last element
        if(index<questionBank2.length){
            txtQu2.setText(questionBank2[index].getAnsId());
            img.setImageResource(images[index]);
            btnYes.setText(option1[index]);
            btnNo.setText(option2[index]);
        }else{ //at exam, finished
            int marks = correctCount * 100/(questionBank2.length);
            if(marks >= 80){
                time2.setVisibility(View.INVISIBLE);
                remtime2.setVisibility(View.INVISIBLE);
                img.setImageResource(R.drawable.success);
                txtQu2.setText("Congratulations\n Distinction\n Marks = " + marks);
            }else if(marks <80 && marks >=60){
                time2.setVisibility(View.INVISIBLE);
                remtime2.setVisibility(View.INVISIBLE);
                img.setImageResource(R.drawable.success);
                txtQu2.setText("Congratulations\n Credit Pass\n Marks = " + marks);
            }
            else{
                time2.setVisibility(View.INVISIBLE);
                remtime2.setVisibility(View.INVISIBLE);
                img.setImageResource(R.drawable.welcme);
                txtQu2.setText("Study Well Next time\n Marks = " + marks);
            }
            //at exam  finished button names get changed
            btnYes.setText("Try Again");
            Toast.makeText(getApplicationContext(),"Thank you for Attending Exam",Toast.LENGTH_SHORT).show();
            btnNo.setText("Home");
            showNotification();

            //When exam finished try again button goes  again main screen
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CssExam.this, ExamPhotoCapture.class);
                    startActivity(intent);
                }
            });

            //When exam finished home button goes again main screen
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CssExam.this, Menu.class);
                    startActivity(intent);
                }
            });
        }
    }

    //Calculating correct answers with the answer declared in Question class
    private void checkAns(boolean uans){
        boolean isTrue =questionBank2[index].isAns();
        if(uans==isTrue){
            correctCount++;
        }
    }

    @Override // on Back button clicked
    public void onBackPressed() {
        if(index < questionBank2.length) { //At the Exam Time
            cancelExam();
        }
        else{
            startActivity(new Intent(CssExam.this,ExamPhotoCapture.class));
        }
    }
//When multitasking button clicked
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            if (index < questionBank2.length) { //At the Exam Time
                cancelExam();
            } else {
                startActivity(new Intent(CssExam.this, ExamPhotoCapture.class));
            }
        }
    }
//A method for call when back pressed or multitasking
    private void cancelExam(){
        if(index < questionBank2.length) { //At the Exam Time Multi tasking stopped
            Toast.makeText(getApplicationContext(), "Focus Missed", Toast.LENGTH_SHORT).show();
            time2.setVisibility(View.INVISIBLE);
            remtime2.setVisibility(View.INVISIBLE);
            img.setImageResource(R.drawable.welcme);
            txtQu2.setText("Focus Missed.\n Exam Cancelled");
            btnYes.setText("Try Again");
            btnNo.setText("Home");
            myTimer.cancel();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(CssExam.this, ExamPhotoCapture.class);
                    startActivity(intent);
                } // When back clicked , an error message displayed then returned to exam main page after 2 seconds
            }, 2000);
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

    //Regarding Notifications
    private void showNotification(){
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), Ch_ID);
        builder.setSmallIcon(R.drawable.chat);
        builder.setContentTitle("Exam Completed");
        builder.setContentText("Thank you for the efforts, Keep Studying");
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

