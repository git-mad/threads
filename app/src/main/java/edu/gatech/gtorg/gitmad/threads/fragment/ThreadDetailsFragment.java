package edu.gatech.gtorg.gitmad.threads.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.gtorg.gitmad.threads.R;

public class ThreadDetailsFragment extends Fragment {

    private static final String KEY_THREAD_NAME = "thread name instance state key";
    private static final String KEY_THREAD_DETAILS = "thread details instance state key";
    private static final String EMPTY_STRING = "";
    private static final int SEND_REQUEST_CODE = 1;

    private String threadName;
    private String threadDetails;

    private TextView threadDescriptionTextView;


    public ThreadDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            threadName = savedInstanceState.getString(KEY_THREAD_NAME);
            threadDetails = savedInstanceState.getString(KEY_THREAD_DETAILS);

        } else {
            threadName = getString(R.string.choose_a_thread);
            threadDetails = EMPTY_STRING;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        setThreadUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_thread_details, container, false);

        threadDescriptionTextView = (TextView) rootView.findViewById(R.id.detailsTextView);
        Button sendButton = (Button) rootView.findViewById(R.id.button_send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendMessage = "About " + threadName + ": \n\n" + threadDetails;
                /* TODO 10
                 * Tell your friends about the threads you're interested in! You don't know how the
                 * user wants to send their message, so use an implicit intent for sending messages.
                 *
                 * 1. Create an intent with a specified action
                 * 2. Put sendMessage in the intent as extra data
                 * 3. Set the type of intent (hint: plain text would be good here)
                 * 4. Force the app chooser to appear every time (Android Intent docs will help with this)
                 * 5. Start the activity!
                 */
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,sendMessage );
                sendIntent.setType("text/plain");
                Intent chooser = Intent.createChooser(sendIntent, "Send Thread Details");


                if (sendIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(chooser, SEND_REQUEST_CODE);
                }
            }
        });

        return rootView;
    }

    public void setThread(String threadName, String threadDetails) {
        this.threadName = threadName;
        this.threadDetails = threadDetails;

        setThreadUI();
    }

    private void setThreadUI() {
        getActivity().setTitle(threadName);

        threadDescriptionTextView.setText(threadDetails);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_THREAD_NAME, threadName);
        outState.putString(KEY_THREAD_DETAILS, threadDetails);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(getContext(),"Successful onActivityResult!", Toast.LENGTH_SHORT).show();
    }
}
