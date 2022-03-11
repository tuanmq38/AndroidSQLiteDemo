package com.example.androidsqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// DBHandler class to communicate with the database
// import SQLiteOpenHelper abstract class and implement its methods
public class DBHandler extends SQLiteOpenHelper {
    // create fields for tables
    private static final String DB_NAME = "coursedb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "courses";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String DURATION_COL = "duration";
    private static final String DESCRIPTION_COL = "description";
    private static final String TRACKS_COL = "tracks";

    // a constructor for database handler
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // a method to create tables using sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + TRACKS_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT)";

        db.execSQL(query); // execute the query string
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // method to create new course and
    // add new course to sqlite database
    public void addNewCourse(String courseName, String courseDuration,
                             String courseTracks, String courseDescription) {
        // create a connection to the database and
        // get the method that we can write data to the table
        SQLiteDatabase db = this.getWritableDatabase();

        // create new object of ContentValues to put values into table
        ContentValues values = new ContentValues();

        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(TRACKS_COL, courseTracks);
        values.put(DESCRIPTION_COL, courseDescription);

        // adding values to the table
        db.insert(TABLE_NAME, null, values);

        // close the connection of database.
        // !!!It does not work if using Database Inspector from Android Studio to view database
        db.close();
    }

    // method to read all courses that added to the database
    public ArrayList<CourseModel> readCourses() {
        // create an object for reading the database
        // get a read method from SQLite database
        SQLiteDatabase db = this.getReadableDatabase();

        // create a cursor to read all data from table
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // create an array list of courses
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<>();

        // adding data to the array list
        if (cursor.moveToFirst()) {
            do {
                courseModelArrayList.add(new CourseModel(cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),
                        cursor.getString(4)));
            }
            while (cursor.moveToNext());
        }

        // close when done and return array list
        cursor.close();
        return courseModelArrayList;
    }

    // method to update the course from database
    public void updateCourse(String originalCourseName, String courseName, String courseDuration,
                             String courseTracks, String courseDescription)
    {
        // calling a method to get writable database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // passing values to the database table
        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(TRACKS_COL, courseTracks);
        values.put(DESCRIPTION_COL, courseDescription);

        // calling an update method to update the database and passing the values
        // comparing it with name of course which is stored in original name variable
        db.update(TABLE_NAME, values, "name=?", new String[]{originalCourseName});
        db.close();
    }

    // method to delete the course from database
    public void deleteCourse(String courseName) {
        // calling a method to get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        // calling an delete method to delete data
        // comparing with the course name
        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }
}
