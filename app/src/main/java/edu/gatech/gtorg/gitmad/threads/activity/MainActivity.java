package edu.gatech.gtorg.gitmad.threads.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import edu.gatech.gtorg.gitmad.threads.R;
import edu.gatech.gtorg.gitmad.threads.ThreadInfo;
import edu.gatech.gtorg.gitmad.threads.fragment.ThreadDetailsFragment;
import edu.gatech.gtorg.gitmad.threads.fragment.ThreadsListFragment;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends AppCompatActivity implements ThreadsListFragment.OnThreadClickedListener {

    private static final String TAG_THREADS_LIST_FRAGMENT = "threads list fragment in main activity tag";


    private List<String> threadDetailsStrings;

    private ThreadDetailsFragment threadDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeToolbar();

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
        ThreadInfo.loadThreadInformation(this, new ThreadInfo.OnThreadLoadListener() {
            @Override
            public void onThreadInfoLoaded() {
                threadDetailsStrings = ThreadInfo.getThreadDetails();
            }
        });
    }

    @Override
    public void threadClicked(String threadName, int threadIndex) {
        Toast.makeText(this, threadName + " was clicked", Toast.LENGTH_SHORT).show();

        String threadDetails = threadDetailsStrings.get(threadIndex);
        if (isScreenLandscape()) {
            threadDetailsFragment.setThread(threadName, threadDetails);

        } else {
            startThreadDetailsActivity(threadName, threadDetails);
        }
    }

    private void startThreadDetailsActivity(String threadName, String threadDescription) {
        Intent threadDetailsActivityIntent = new Intent(this, ThreadDetailsActivity.class);

        threadDetailsActivityIntent.putExtra(ThreadDetailsActivity.KEY_THREAD_NAME, threadName);
        threadDetailsActivityIntent.putExtra(ThreadDetailsActivity.KEY_THREAD_DESCRIPTION, threadDescription);

        startActivity(threadDetailsActivityIntent);
    }

    private boolean isScreenLandscape() {
        return getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE;
    }
}
