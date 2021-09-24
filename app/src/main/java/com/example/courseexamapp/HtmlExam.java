package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HtmlExam extends AppCompatActivity implements View.OnClickListener {
    private Button btnYes, btnNo;
    private TextView txtQu, time1, remtime1;
    private ImageView img;
    private int index = 0;
    private CountDownTimer myTimer;
    private static int correctCount = 0;

    private final Question[] questionBank = new Question[]{
            new Question(R.string.q1, true),
            new Question(R.string.q2, true),
            new Question(R.string.q3, true),
            new Question(R.string.q4, false), new Question(R.string.q5, false)};

    private final Integer images[] = {R.mipmap.n1, R.mipmap.n2, R.mipmap.n3, R.mipmap.n4, R.mipmap.n5};
    private int option1[] = {R.string.a1, R.string.a2, R.string.a3, R.string.a4, R.string.a5};
    private int option2[] = {R.string.i1, R.string.i2, R.string.i3, R.string.i4, R.string.i5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_exam);

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        txtQu = findViewById(R.id.txtQu);
        img = findViewById(R.id.img);
        img.setImageResource(images[index]);
        time1 = findViewById(R.id.txt_time1);
        timer1();
        remtime1 = findViewById(R.id.txt_remtime1);
        int correctCount = 0;
        btnYes.setText(option1[index]);
        btnNo.setText(option2[index]);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);
    }

    public void timer1() {
        myTimer = new CountDownTimer(7000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                time1.setText("00:" + String.format("%02d", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                time1.setVisibility(View.INVISIBLE);
                remtime1.setVisibility(View.INVISIBLE);
                if (index < questionBank.length) {
                    img.setImageResource(R.drawable.timeup);
                    int marks = correctCount * 100 / (questionBank.length);
                    if (marks >= 80) {
                        txtQu.setText("Congratulations\n Distinction\n Marks = " + marks);
                    } else if (marks < 80 && marks >= 60) {
                        txtQu.setText("Congratulations\n Credit Pass\n Marks = " + marks);
                    } else {
                        txtQu.setText("Study Well Next time\n Marks = " + marks);
                    }
                }

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAns(false);
                        index++;
                        updateQue();
                    }
                });

                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAns(true);
                        index++;
                        updateQue();
                    }
                });

                //at exam  finished button names get changed
                btnYes.setText("Try Again");
//                Toast.makeText(getApplicationContext(), "Thank you for Attending Exam", Toast.LENGTH_SHORT).show();
                btnNo.setText("Home");
                correctCount = 0; //resetting the marks at the end of exam

                //When exam finished try again button goes  again main screen
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HtmlExam.this, ExamPhotoCapture.class);
                        startActivity(intent);
                    }
                });

                //When exam finished home button goes again main screen
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HtmlExam.this, Menu.class);
                        startActivity(intent);
                    }
                });
            }

            //Calculating correct answers with the answer declared in Question class
            private void checkAns(boolean uans) {
                boolean isTrue = questionBank[index].isAns();
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
        ProgressBar progressBar3 = findViewById(R.id.progressBar3);
        progressBar3.setProgress(index);
        progressBar3.setMax(5);
        //Display Correct Answers Count
        ProgressBar progressBar4 = findViewById(R.id.progressBar4);
        progressBar4.setProgress(correctCount);
        progressBar4.setMax(5);
        //until question bank goes to last element
        if (index < questionBank.length) {
            txtQu.setText(questionBank[index].getAnsId());
            img.setImageResource(images[index]);
            btnYes.setText(option1[index]);
            btnNo.setText(option2[index]);
        } else { //at exam, finished
            int marks = correctCount * 100 / (questionBank.length);

            if (marks >= 80) {
                img.setImageResource(R.drawable.success);
                time1.setVisibility(View.INVISIBLE);
                remtime1.setVisibility(View.INVISIBLE);
                txtQu.setText("Congratulations\n Distinction\n Marks = " + marks);
            } else if (marks < 80 && marks >= 60) {
                time1.setVisibility(View.INVISIBLE);
                remtime1.setVisibility(View.INVISIBLE);
                img.setImageResource(R.drawable.success);
                txtQu.setText("Congratulations\n Credit Pass\n Marks = " + marks);
            } else {
                time1.setVisibility(View.INVISIBLE);
                remtime1.setVisibility(View.INVISIBLE);
                img.setImageResource(R.drawable.welcme);
                txtQu.setText("Study Well Next time\n Marks = " + marks);
            }

            //at exam  finished button names get changed
            btnYes.setText("Try Again");
            Toast.makeText(getApplicationContext(), "Thank you for Attending Exam", Toast.LENGTH_SHORT).show();
            btnNo.setText("Home");


            //When exam finished try again button goes  again main screen
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HtmlExam.this, ExamPhotoCapture.class);
                    startActivity(intent);
                }
            });

            //When exam finished home button goes again main screen
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HtmlExam.this, ExamPhotoCapture.class);
                    startActivity(intent);
                }
            });
        }
    }

    //Calculating correct answers with the answer declared in Question class
    private void checkAns(boolean uans) {
        boolean isTrue = questionBank[index].isAns();
        if (uans == isTrue) {
            correctCount++;
        }
    }

    //---------------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (index < questionBank.length) { //At the Exam Time
            cancelExam();
        } else {
            startActivity(new Intent(HtmlExam.this, ExamPhotoCapture.class));
        }
    }

//----------------------------------------------------------------------------------------------------------

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            if (index < questionBank.length) { //At the Exam Time
                cancelExam();
            } else {
                startActivity(new Intent(HtmlExam.this, ExamPhotoCapture.class));
            }
        }
    }

    private void cancelExam() {
        if (index < questionBank.length) { //At the Exam Time Multi tasking stopped
            Toast.makeText(getApplicationContext(), "Focus Missed", Toast.LENGTH_SHORT).show();
            time1.setVisibility(View.INVISIBLE);
            remtime1.setVisibility(View.INVISIBLE);
            img.setImageResource(R.drawable.welcme);
            txtQu.setText("Focus Missed.\n Exam Cancelled");
            btnYes.setText("Try Again");
            btnNo.setText("Home");
            myTimer.cancel();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(HtmlExam.this, ExamPhotoCapture.class);
                    startActivity(intent);
                }
            }, 2000);
        }
    }
}