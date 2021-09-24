package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class upload_file extends AppCompatActivity {
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);

        // calling the action bar
//        ActionBar actionBar = getSupportActionBar();
//         showing the back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);
//

        next = (Button)findViewById(R.id.btn_upload);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Assignment_Success.class);
                startActivity(intent);
            }
        });
    }

    // this event will enable the back
    // function to the button on press

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