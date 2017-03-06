package edu.gatech.gtorg.gitmad.threads;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gatech.gtorg.gitmad.threads.fragment.ThreadsListFragment;

/**
 * Created by Brian on 3/5/2017.
 */

public class ThumbnailThreadAdapter extends RecyclerView.Adapter<ThumbnailThreadAdapter.ThreadViewHolder> {

    private String[] threadNames;
    private Bitmap[] thumbnails;
    private ThreadsListFragment.OnThreadClickedListener listener;

    public ThumbnailThreadAdapter(String[] threadNames, Bitmap[] thumbnails,
                                  ThreadsListFragment.OnThreadClickedListener listener) {

        this.threadNames = threadNames;
        this.thumbnails = thumbnails;
        this.listener = listener;
    }

    @Override
    public ThreadViewHolder onCreateViewHolder(ViewGroup parentView, int viewType) {
        // Inflate list item, create ThreadViewHolder
    }

    @Override
    public void onBindViewHolder(ThreadViewHolder threadViewHolder, int i) {
        // set text of list item TextView,
        // set image of list item ImageView
    }

    @Override
    public int getItemCount() {
        return threadNames.length;
    }

    class ThreadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //

        ThreadViewHolder(View itemView) {
            super(itemView);

            // find and store the views we need


            // This captures click events on the list items.
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            listener.threadClicked(threadNames[position], position);
        }
    }
}
