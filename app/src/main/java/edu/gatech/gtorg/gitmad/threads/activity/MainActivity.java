package edu.gatech.gtorg.gitmad.threads.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import edu.gatech.gtorg.gitmad.threads.R;
import edu.gatech.gtorg.gitmad.threads.fragment.ThreadDetailsFragment;
import edu.gatech.gtorg.gitmad.threads.fragment.ThreadsListFragment;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity implements ThreadsListFragment.OnThreadClickedListener {

    private static final String TAG_THREADS_LIST_FRAGMENT = "threads list fragment in main activity tag";


    private String[] threadDetailsStrings;

    private ThreadDetailsFragment threadDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeToolbar();

        /* TODO 9
             * When the screen is landscape, we want to adjust the UI so we can see the list of
             * threads AND a description at the same time.Before you continue, implement
             * isScreenLandscape() at the bottom of this file and come back here.
             *
             * Now that you can detect screen rotations, let's do something with that. If the screen
             * is in landscape mode:
             *
             * 1. Get the support fragment manager
             * 2. Use that fragment manager to find a specific fragment by its ID. The ID we want to
             *    load is threadDetailsFragment
             * 3. The fragment we just found is a specific type of fragment, so cast it as
             *    (ThreadDetailsFragment) and save it into the threadDetailsFragment variable.
             */


        if (isScreenLandscape()) {

            threadDetailsFragment = (ThreadDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.threadDetailsFragment);
        }

        loadThreadDetailsStrings();
    }

    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!isScreenLandscape()) {
            setTitle(getString(R.string.choose_a_thread));
        }
    }

    private void loadThreadDetailsStrings() {
        threadDetailsStrings = getResources().getStringArray(R.array.thread_descriptions);
    }

    @Override
    public void threadClicked(String threadName, int threadIndex) {
        /* TODO 6
         * Create a toast that displays which thread was clicked.
         * The message should be something like "<threadName> was clicked!"
         * Don't forget to use show() to actually display the toast.
         */

        Toast.makeText(this, threadName + " was clicked", Toast.LENGTH_SHORT).show();


        /* TODO 7
         * Log the thread name to the Android Monitor. Hint: Log.i() might be helpful
         */
        Log.i("threadName", threadName);

        if (isScreenLandscape()) {

            threadDetailsFragment.setThread(threadName, threadDetailsStrings[threadIndex]);

        } else {
            startThreadDetailsActivity(threadName, threadDetailsStrings[threadIndex]);
        }
    }

    private void startThreadDetailsActivity(String threadName, String threadDescription) {
        /* TODO 1
         * Create an Intent that will take you from this activity to ThreadDetailsActivity.
         */
        Intent intent = new Intent(MainActivity.this, ThreadDetailsActivity.class);

        /* TODO 2
         * When you go to ThreadDetailsActivity, you'll want to take the thread name and description
         * with you. Since Intents can carry data between activities, "put extra" (hint, hint) data
         * into the Intent for the thread name and then do the same for the description.
         *
         * ThreadDetailsActivity has public keys you may use to pass your data or you can use your own string
         */
        intent.putExtra(ThreadDetailsActivity.KEY_THREAD_NAME, threadName);
        intent.putExtra(ThreadDetailsActivity.KEY_THREAD_DESCRIPTION, threadDescription);

        /* TODO 3
         * All that is left to do now is to start the activity based on your newly created intent
         */
        startActivity(intent);
    }

    private boolean isScreenLandscape() {
        /* TODO 8
         * Return true if the phone is in landscape mode
         */
        return getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE;
    }
}
