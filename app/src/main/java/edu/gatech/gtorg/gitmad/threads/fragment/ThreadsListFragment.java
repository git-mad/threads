package edu.gatech.gtorg.gitmad.threads.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.gatech.gtorg.gitmad.threads.R;

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

        //TODO check if the activity implements the correct interface, and keep a reference to it
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        threadNames = getActivity().getResources().getStringArray(R.array.thread_names);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        ListView listView = null;

        //TODO replace the above with code that inflates the layout R.layout.fragment_threads_list
        // and gets a reference to the ListView R.id.threadsListView

        setUpList(listView);

        return rootView;
    }

    private void setUpList(ListView listView) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, threadNames);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO notify the activity through a call to our clickListener
            }
        });
    }

    public interface OnThreadClickedListener {
        public void threadClicked(String threadName, int threadIndex);
    }
}
