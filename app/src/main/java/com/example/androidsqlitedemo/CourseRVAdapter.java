package com.example.androidsqlitedemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {
   // class fields
    private ArrayList<CourseModel> courseModelArrayList;
    private Context context;

    // constructor
    public CourseRVAdapter(ArrayList<CourseModel> courseModelArrayList, Context context) {
        this.courseModelArrayList = courseModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_readview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHolder holder, int position) {
        // get the course info and set to the recycler view
        CourseModel model = courseModelArrayList.get(position);
        holder.textViewCourseName.setText(model.getCourseName());
        holder.textViewCourseDuration.setText(model.getCourseDuration());
        holder.textViewCourseTracks.setText(model.getCourseTracks());
        holder.textViewCourseDescription.setText(model.getCourseDescription());

        // add an event handler when clicking on recycler view item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateCourse.class);

                // passing all the values
                intent.putExtra("name", model.getCourseName());
                intent.putExtra("duration", model.getCourseDuration());
                intent.putExtra("tracks", model.getCourseTracks());
                intent.putExtra("description", model.getCourseDescription());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // create variables for the text views
        private TextView textViewCourseName, textViewCourseDuration,
                textViewCourseTracks, textViewCourseDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // get the text views
            textViewCourseName = itemView.findViewById(R.id.idTVCourseName);
            textViewCourseDuration = itemView.findViewById(R.id.idTVCourseDuration);
            textViewCourseTracks = itemView.findViewById(R.id.idTVCourseTracks);
            textViewCourseDescription = itemView.findViewById(R.id.idTVCourseDescription);
        }
    }
}
