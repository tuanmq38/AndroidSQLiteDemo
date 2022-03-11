package com.example.androidsqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCourse extends AppCompatActivity {
    private EditText editCourseName, editCourseDuration, editCourseTracks, editCourseDescription;
    private Button updateCourseBtn, deleteCourseBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        // get the fields
        editCourseName = findViewById(R.id.idEdtCourseName);
        editCourseDuration = findViewById(R.id.idEdtCourseDuration);
        editCourseTracks = findViewById(R.id.idEdtCourseTracks);
        editCourseDescription = findViewById(R.id.idEdtCourseDescription);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);

        dbHandler = new DBHandler(UpdateCourse.this);

        // get the original data
        String courseName = getIntent().getStringExtra("name");
        String courseDuration = getIntent().getStringExtra("duration");
        String courseTracks = getIntent().getStringExtra("tracks");
        String courseDescription = getIntent().getStringExtra("description");

        // set the original data to the edit text
        editCourseName.setText(courseName);
        editCourseDuration.setText(courseDuration);
        editCourseTracks.setText(courseTracks);
        editCourseDescription.setText(courseDescription);

        // add event handler for update button
        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling update method
                // and passing text values
                dbHandler.updateCourse(courseName, editCourseName.getText().toString(),
                        editCourseDuration.getText().toString(),
                        editCourseTracks.getText().toString(), editCourseDescription.getText().toString());

                // display message when course has been updated
                Toast.makeText(UpdateCourse.this, "Course has been updated",
                        Toast.LENGTH_SHORT).show();

                // get back to Main activity when done
                Intent intent = new Intent(UpdateCourse.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // add event handler for delete button
        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete course
                dbHandler.deleteCourse(courseName);

                // display message when course has been deleted
                Toast.makeText(UpdateCourse.this, "Course has been deleted",
                        Toast.LENGTH_SHORT).show();

                // get back to Main activity when done
                Intent intent = new Intent(UpdateCourse.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}