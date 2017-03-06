package edu.gatech.gtorg.gitmad.threads.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.gatech.gtorg.gitmad.threads.R;
import edu.gatech.gtorg.gitmad.threads.ThumbnailThreadAdapter;
import edu.gatech.gtorg.gitmad.threads.Utils;

public class ThreadsListFragment extends Fragment {

    private static final String KEY_THREAD_NAMES = "thread names key";


    private OnThreadClickedListener clickListener;
    private String[] threadNames;

    /*
        public default constructor necessary for the Android OS to manage
        the application's state. See
        http://stackoverflow.com/questions/25984054/android-fragments-is-empty-constructor-really-required
     */
    public ThreadsListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof OnThreadClickedListener) {
            clickListener = (OnThreadClickedListener) getActivity();

        } else {
            throw new ClassCastException("Activity that contains Fragment ThreadsListFragment must implement OnThreadClickedListener in order to receive click events");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        threadNames = getActivity().getResources().getStringArray(R.array.thread_names);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_threads_list, container, false);

        // get RecyclerView, create Adapter, set Adapter --> RecyclerView

        return rootView;
    }

    private Bitmap[] getThreadImages() {
        TypedArray threadImageIds = getResources().obtainTypedArray(R.array.thread_image_ids);

        Bitmap[] threadImages = new Bitmap[threadImageIds.length()];

        int threadThumbnailDimensions = (int) getResources().getDimension(R.dimen.thread_thumbnail_dims);

        for (int i = 0; i < threadImageIds.length(); i++) {
            int resourceId = threadImageIds.getResourceId(i, Utils.NULL_RESOURCE);
            threadImages[i] = Utils.sampleBitmapForDimensions(getResources(), resourceId, threadThumbnailDimensions, threadThumbnailDimensions);
        }

        threadImageIds.recycle();

        return threadImages;
    }

    public interface OnThreadClickedListener {
        void threadClicked(String threadName, int threadIndex);
    }
}
