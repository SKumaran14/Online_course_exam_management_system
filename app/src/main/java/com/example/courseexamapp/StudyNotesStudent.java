package com.example.courseexamapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class StudyNotesStudent extends AppCompatActivity {

    private EditText editTextChapter;
    private EditText editTextNotes;
    private String ID;
    private BottomNavigationView myBottomNavigation;
    private DatabaseReference mDatabase; //Firebase reference


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_notes_student);

        mDatabase = FirebaseDatabase.getInstance().getReference("StudyNotes"); // Firebase instance

        Button buttonSave = findViewById(R.id.button);
        Button buttonUpdate = findViewById(R.id.button3);
        editTextChapter = findViewById(R.id.Name);
        editTextNotes = findViewById(R.id.Notes);
        RecyclerView recyclerView = findViewById(R.id.recyclerView); // To display notes in a recycler view
        myBottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavClick(); //Bottom Navigation functions

        //Insert CRUD
        buttonSave.setOnClickListener(v ->{
            String chapter = editTextChapter.getText().toString().trim(); // trim for removing white spaces
            String notes = editTextNotes.getText().toString().trim();

            if(chapter.isEmpty() || notes.isEmpty()){ // Checking empty fields
                Toast.makeText(StudyNotesStudent.this,"Please Fill the Fields", Toast.LENGTH_SHORT).show();
            } else{
                String Id = mDatabase.push().getKey();
                createData(Id,chapter,notes); // Creating to database

                editTextChapter.getText().clear(); //Clearing fields after insertion
                editTextNotes.getText().clear();
            }
        });

        //Update CRUD
        buttonUpdate.setOnClickListener(v ->{
            String chapter = editTextChapter.getText().toString().trim();
            String notes = editTextNotes.getText().toString().trim();

            if(chapter.isEmpty() || notes.isEmpty()){ // Checking empty fields
                Toast.makeText(StudyNotesStudent.this,"Please Fill the Fields", Toast.LENGTH_SHORT).show();
            } else{
                if(ID!= null) {
                    createData(ID, chapter, notes); //updating to database
                }else{
                    Toast.makeText(StudyNotesStudent.this,"Please Fill the Fields", Toast.LENGTH_SHORT).show();
                }
                editTextChapter.getText().clear();
                editTextNotes.getText().clear();
            }
        });

        ArrayList<String> notes_id = new ArrayList<>(); //Notes are placed inside an arraylist to send to recycler view
        ArrayList<String> chapter = new ArrayList<>();
        ArrayList<String> notes = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(StudyNotesStudent.this)); //Recycler view
        MyAdapter myAdapter = new MyAdapter(this, notes_id,chapter,notes); // Creating an adapter to put the information

        //Receive events about datachanges
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                notes_id.clear();
                chapter.clear();
                notes.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id1 = dataSnapshot.child("id").getValue(String.class);
                    String chapter1 = dataSnapshot.child("chapter").getValue(String.class);
                    String notes1 = dataSnapshot.child("notes").getValue(String.class);

                    notes_id.add(id1);
                    chapter.add(chapter1);
                    notes.add(notes1);
                }

                recyclerView.setAdapter(myAdapter); // Assigning Information to recycler view
                myAdapter.notifyDataSetChanged();
            }

            //If listener failed at server level or due to database rules and getting the exception
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudyNotesStudent.this,(CharSequence) error.toException(),Toast.LENGTH_SHORT).show();
            }
        };
        mDatabase.addValueEventListener(eventListener);

    }

    //Insertion CRUD method
    private void createData(String ID, String chapter,String notes){
        StudyNotes studyNotes= new StudyNotes(ID,chapter,notes); //StudyNotes instance instantiation
        mDatabase.child(ID).setValue(studyNotes);
        Toast.makeText(this,"Notes Successfully Saved",Toast.LENGTH_SHORT).show();
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        Context context;
        ArrayList notes_id,chapter,notes;

        public MyAdapter(Context context, ArrayList notes_id, ArrayList chapter, ArrayList notes){
            this.context = context;
            this.notes_id = notes_id;
            this.chapter = chapter;
            this.notes = notes;
        }
        @Override
        public ViewHolder onCreateViewHolder (@NonNUll ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.list_items,parent,false);
            return new ViewHolder(view); //Assigning list_items to recycler view
        }

        @SuppressLint("RecyclerView")
        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
          holder.ID.setText((CharSequence)chapter.get(position));
            holder.ID.setVisibility(View.GONE); //Not showing the Key ID to the student user

            holder.Chapter.setText( (CharSequence)chapter.get(position)); //Displaying the notes
            holder.Notes.setText( (CharSequence)notes.get(position));

            //Popup  menu facilate CRUD functions
            holder.layout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(StudyNotesStudent.this,holder.layout);
                    popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()){
                                case R.id.item_1: //update CRUD
                                    editTextChapter.setText((CharSequence)chapter.get(position)); //UPDATE CRUD
                                    editTextNotes.setText((CharSequence)notes.get(position)); //UPDATE CRUD
                                    ID = (String) notes_id.get(position);
                                    holder.Chapter.setBackgroundColor(0x8FD0F8); //changing to default the background color
                                    holder.Notes.setBackgroundColor(0x8FD0F8);
                                    break;

                                case R.id.item_2: //delete CRUD , an alert confirmation is displayed
                                    AlertDialog alertDialog = new AlertDialog.Builder(StudyNotesStudent.this).create();
                                    alertDialog.setTitle("Confirm Delete");
                                    alertDialog.setMessage("Do you really want to delete this note?");

                                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            mDatabase.child((String)notes_id.get(position)).removeValue(); // Delete CRUD
                                            Toast.makeText(getApplicationContext(),"Data Deleted Successfully",Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                            //When delete function get cancelled by user
                                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(getApplicationContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            alertDialog.show();
                                            break;


                                case R.id.item_3: //highlight the notes
                                    holder.Chapter.setBackgroundColor(0xFF00FF00); //Hightligting notes to Green Color
                                    holder.Notes.setBackgroundColor(0xFF00FF00);
                                    break;
                            }
                            return true;
                        }
                    });
                    popupMenu.show(); //To show the popup menu
                }
            });
        }

        @Override
        public int getItemCount() { // To get the number of items shown in the recycler view
            return notes_id.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView ID;
            TextView Chapter;
            TextView Notes;
            LinearLayout layout;

            public ViewHolder(View itemView){
                super(itemView);
                ID = itemView.findViewById(R.id.textView_filename);
                Chapter = itemView.findViewById(R.id.textView2);
                Notes = itemView.findViewById(R.id.textView3);
                layout = itemView.findViewById(R.id.list_layout);
            }
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
}


