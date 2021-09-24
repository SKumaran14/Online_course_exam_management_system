package com.example.courseexamapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

    public class assignment_submit extends AppCompatActivity {
        private Button next;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_assignment_submit);

            // calling the action bar
            ActionBar actionBar = getSupportActionBar();
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
            next = (Button)findViewById(R.id.btn_edit);

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), upload_file.class);
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
    }