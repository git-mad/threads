package edu.gatech.gtorg.gitmad.threads.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.gatech.gtorg.gitmad.threads.R;
import edu.gatech.gtorg.gitmad.threads.fragment.ThreadDetailsFragment;

public class ThreadDetailsActivity extends AppCompatActivity {

    public static final String KEY_THREAD_NAME = "thread name parameter key";
    public static final String KEY_THREAD_DESCRIPTION = "thread description parameter key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }

        setContentView(R.layout.activity_thread_details);

        setFragmentParams();
    }

    private void setFragmentParams() {
        /*
         * We need to set the correct name and description for the thread that was selected.
         * 1. Get the Intent that brought us to this activity
         * 2. Get the extra data that the intent is carrying
         * 3. Get the string value for the thread name using the extra's key
         * 4. Repeat the 1-3 for the thread description
         */
        String threadName = getIntent().getExtras().getString(KEY_THREAD_NAME);
        String threadDescription = getIntent().getExtras().getString(KEY_THREAD_DESCRIPTION);

        ThreadDetailsFragment detailsFragment = (ThreadDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.threadDetailsFragment);

        detailsFragment.setThread(threadName, threadDescription);
    }

}
