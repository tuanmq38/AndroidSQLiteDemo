package com.example.androidsqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ViewCourses extends AppCompatActivity {
    private ArrayList<CourseModel> courseModelArrayList;
    private DBHandler dbHandler;
    private CourseRVAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        courseModelArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewCourses.this);

        // getting course array list from handler class
        courseModelArrayList = dbHandler.readCourses();

        // passing array list courses to the adapter class
        courseRVAdapter = new CourseRVAdapter(courseModelArrayList, ViewCourses.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(ViewCourses.this, RecyclerView.VERTICAL, false);

        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view
        coursesRV.setAdapter(courseRVAdapter);
    }
}