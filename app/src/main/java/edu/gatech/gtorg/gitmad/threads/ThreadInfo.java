package edu.gatech.gtorg.gitmad.threads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Stores information about the Georiga Tech Thread program
 */
public class ThreadInfo {
    private static String[] orderedThreadNames;
    private static String[] threadDetails;

    public static List<String> getThreadNames() {
        // Defensive copy
        // We don't let return a single List because anyone could edit our list of threads
        // Another approach would be to return a Collections.unmodifiableList()
        if (orderedThreadNames == null) {
            throw new IllegalStateException("ThreadInfo.loadThreadInformation must be called before accessing thread names");
        }
        return Arrays.asList(orderedThreadNames);
    }

    public static List<String> getThreadDetails() {
        if (threadDetails == null) {
            throw new IllegalStateException("ThreadInfo.loadThreadInformation must be called before accessing thread details");
        }
        return Arrays.asList(threadDetails);
    }

    public interface OnThreadLoadListener {
        void onThreadInfoLoaded();
    }

    public static void loadThreadInformation(@NonNull final Context context, @Nullable final OnThreadLoadListener threadLoadListener) {
        boolean requiresLoad = (orderedThreadNames == null || threadDetails == null);
        if (!requiresLoad) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                orderedThreadNames = context.getResources().getStringArray(R.array.thread_names);
                threadDetails = context.getResources().getStringArray(R.array.thread_descriptions);

                if (threadLoadListener != null) {
                    threadLoadListener.onThreadInfoLoaded();
                }
            }
        }, 5000); // 5 second delay (delay in milliseconds)
    }
}
