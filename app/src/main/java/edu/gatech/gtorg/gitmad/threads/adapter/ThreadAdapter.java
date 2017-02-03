package edu.gatech.gtorg.gitmad.threads.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.gatech.gtorg.gitmad.threads.R;

/**
 * Created by shannortrotty on 2/2/17.
 * Simple adapter to load thread names and ratings.
 * Would use actual Classes here and not pass in multiple arrays
 */

public class ThreadAdapter extends ArrayAdapter<String> {

    private String[] threads;
    private String[] ratings;

    public ThreadAdapter(Context context, String [] threads, String[] ratings) {
        super(context,R.layout.listview_layout);
        this.threads = threads;
        this.ratings = ratings;

    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        String thread = this.threads[position];
        String rating = this.ratings[position];
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout, parent, false);
        }
        // Lookup view for data population
//        TODO:(14) Add the Id's of both of your textViews to show the information
//        TextView threadRating = (TextView) convertView.findViewById();
//        TextView threadName = (TextView) convertView.findViewById();
//        // Populate the data into the template view using the data object
//        threadRating.setText(rating);
//        threadName.setText(thread);
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return threads.length;
    }
}
