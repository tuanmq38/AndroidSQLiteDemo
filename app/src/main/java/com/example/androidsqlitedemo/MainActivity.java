package com.example.androidsqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Student Name: Quintus Mai
// Student Id: 300332979

public class MainActivity extends AppCompatActivity {
    // get the form app attributes and create DBHandler object
    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private Button addCourseBtn, readCourseBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);
        readCourseBtn = findViewById(R.id.idBtnReadCourse);

        // passing the database context to the main activity
        dbHandler = new DBHandler(MainActivity.this);

        // event handling method when addCourse button is clicked
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get the input text from EditText view
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();

                // validating if any of the input text fields is empty
                if (courseName.isEmpty() && courseTracks.isEmpty()
                        && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // calling DBHandler method to add new course with input values to the database
                dbHandler.addNewCourse(courseName, courseDuration, courseTracks,  courseDescription);

                // display the message when added course successfully
                Toast.makeText(MainActivity.this,
                        "Course has been added to the database",Toast.LENGTH_SHORT).show();

                // set all input text fields empty
                courseNameEdt.setText("");
                courseDurationEdt.setText("");
                courseTracksEdt.setText("");
                courseDescriptionEdt.setText("");
            }
        });

        // event handling method for readCourse button when clicked
        readCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open a new activity when click to the button
                Intent intent = new Intent(MainActivity.this, ViewCourses.class);
                startActivity(intent);
            }
        });
    }
}