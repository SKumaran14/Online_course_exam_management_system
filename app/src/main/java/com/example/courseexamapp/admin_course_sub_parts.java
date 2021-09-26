package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import java.util.ArrayList;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class admin_course_sub_parts extends AppCompatActivity {
    private ImageView back;
    private ListView lv;
    private EditText search_sub;
    private Button edit_sub, add_sub, cont_next, admin_home;
    ArrayList<String> Course_parts = new ArrayList<String>();
    ArrayAdapter myAdapter;
    Integer indexVal;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course_sub_parts);
        admin_home = findViewById(R.id.admin_home);
        back = findViewById(R.id.go_back);
        edit_sub = findViewById(R.id.edit_sub);
        add_sub = findViewById(R.id.add_sub);
        cont_next = findViewById(R.id.cont_next);
        lv = findViewById(R.id.lv);
        search_sub = findViewById(R.id.serach_sub);
        admin_home = findViewById(R.id.admin_home);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_course_sub_parts.this, admin_courses.class);
                startActivity(intent);
            }
        });

        //list view
        Course_parts.add("Tags For Headers");
        Course_parts.add("Links");
        Course_parts.add("Tables");
        Course_parts.add("Lists");
        Course_parts.add("Formatting");

        myAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, Course_parts);
        lv.setAdapter(myAdapter);


        //add
        add_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringval = search_sub.getText().toString();
                Course_parts.add(stringval);
                myAdapter.notifyDataSetChanged();
                search_sub.setText("");
                Toast.makeText(admin_course_sub_parts.this, "Added Succesfully ", Toast.LENGTH_SHORT).show();
            }
        });
        //select item

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString() + "has been selected";
                indexVal = i;
                Toast.makeText(admin_course_sub_parts.this, item, Toast.LENGTH_SHORT).show();
            }
        });
        //update

        edit_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringval = search_sub.getText().toString();
                Course_parts.set(indexVal, stringval);
                myAdapter.notifyDataSetChanged();
                search_sub.setText("");
                Toast.makeText(admin_course_sub_parts.this, "Added Succesfully ", Toast.LENGTH_SHORT).show();
            }
        });

        //delete
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString() + "has been deleted";
                Toast.makeText(admin_course_sub_parts.this, "Deleted Succesfully ", Toast.LENGTH_SHORT).show();

                Course_parts.remove(i);
                myAdapter.notifyDataSetChanged();
                return true;
            }
        });


        admin_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), admin_courses.class));
                finish();
            }
        });

    }


}
