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
    private Button edit_sub,add_sub,cont_next,delete_sub;
    ArrayList<String> Course_parts = new ArrayList<String>();
    ArrayAdapter myAdapter;
    Integer indexVal;
    String item;
    private BottomNavigationView myBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course_sub_parts);

        back = findViewById(R.id.go_back);
        edit_sub = findViewById(R.id.edit_sub);
        delete_sub = findViewById(R.id.delete_sub);
        add_sub = findViewById(R.id.add_sub);
        cont_next = findViewById(R.id.cont_next);
        lv = findViewById(R.id.lv);
        search_sub = findViewById(R.id.serach_sub);
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_course_sub_parts.this, delete_course.class);
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
                this, android.R.layout.simple_list_item_1,Course_parts);
        lv.setAdapter(myAdapter);


        //add
        add_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringval = search_sub.getText().toString();
                Course_parts.add(stringval);
                myAdapter.notifyDataSetChanged();
                search_sub.setText("");
                Toast.makeText(admin_course_sub_parts.this, "Added Succesfully ",  Toast.LENGTH_SHORT).show();
            }
        });
        //select item

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString() + "has been selected";
                indexVal = i;
                Toast.makeText(admin_course_sub_parts.this,item,  Toast.LENGTH_SHORT).show();
            }
        });
        //update

        edit_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringval = search_sub.getText().toString();
                Course_parts.set(indexVal,stringval);
                myAdapter.notifyDataSetChanged();
                search_sub.setText("");
                Toast.makeText(admin_course_sub_parts.this, "Added Succesfully ",  Toast.LENGTH_SHORT).show();
            }
        });

        //delete
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i).toString() + "has been deleted";
                Toast.makeText(admin_course_sub_parts.this, "Deleted Succesfully ",  Toast.LENGTH_SHORT).show();

                Course_parts.remove(i);
                myAdapter.notifyDataSetChanged();
                return true;
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