package edu.gatech.gtorg.gitmad.threads.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import edu.gatech.gtorg.gitmad.threads.R;
import edu.gatech.gtorg.gitmad.threads.fragment.ThreadDetailsFragment;

public class ThreadDetailsActivity extends AppCompatActivity {

    public static final String KEY_THREAD_INDEX = "thread index parameter key";

    private int threadIndex;
    private String[] threadNames;
    private String[] threadDescriptions;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }

        setContentView(R.layout.activity_thread_details);

        threadIndex = getIntent().getExtras().getInt(KEY_THREAD_INDEX);
        threadNames = getResources().getStringArray(R.array.thread_names);
        threadDescriptions = getResources().getStringArray(R.array.thread_descriptions);

        setFragmentParams();

        setUpSpinner();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (spinner.getSelectedItemPosition() != threadIndex) {
            spinner.setSelection(threadIndex, false);
        }
    }

    private void setFragmentParams() {
        String threadName = threadNames[threadIndex];
        String threadDescription = threadDescriptions[threadIndex];

        ThreadDetailsFragment detailsFragment = (ThreadDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.threadDetailsFragment);

        detailsFragment.setThread(threadName, threadDescription);
    }

    private void setUpSpinner() {
        spinner = (Spinner) findViewById(R.id.threadsSpinner);

        SpinnerAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, threadNames);
        spinner.setAdapter(adapter);

        spinner.setSelection(threadIndex, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != threadIndex) {
                    Intent intent = new Intent(ThreadDetailsActivity.this, ThreadDetailsActivity.class);
                    intent.putExtra(KEY_THREAD_INDEX, position);

                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
    }

}
