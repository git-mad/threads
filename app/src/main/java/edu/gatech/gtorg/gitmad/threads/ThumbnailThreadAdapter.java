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
        LayoutInflater inflater = LayoutInflater.from(parentView.getContext());
        View listItemView = inflater.inflate(R.layout.thread_list_item, parentView, false);

        ThreadViewHolder viewHolder = new ThreadViewHolder(listItemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ThreadViewHolder threadViewHolder, int i) {
        threadViewHolder.threadNameTextView.setText(threadNames[i]);
        threadViewHolder.threadImageView.setImageBitmap(thumbnails[i]);
    }

    @Override
    public int getItemCount() {
        return threadNames.length;
    }

    class ThreadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView threadImageView;
        TextView threadNameTextView;

        ThreadViewHolder(View itemView) {
            super(itemView);
            threadImageView = (ImageView) itemView.findViewById(R.id.threadImageView);
            threadNameTextView = (TextView) itemView.findViewById(R.id.threadNameTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            listener.threadClicked(threadNames[position], position);
        }
    }
}
