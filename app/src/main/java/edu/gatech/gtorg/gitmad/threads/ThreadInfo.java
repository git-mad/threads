package edu.gatech.gtorg.gitmad.threads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

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
        final boolean requiresLoad = (orderedThreadNames == null || threadDetails == null);
        if (!requiresLoad) {
            return;
        }
        /*
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
        */


        service.getThreads().enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    Map<String, String> results = response.body();
                    // Get the thread names and sort them
                    orderedThreadNames = results.keySet().toArray(new String[0]);
                    Arrays.sort(orderedThreadNames);
                    // Then create thread details in the correct order
                    threadDetails = new String[orderedThreadNames.length];
                    for (int i = 0; i < orderedThreadNames.length; i += 1) {
                        threadDetails[i] = results.get(orderedThreadNames[i]);
                    }

                    if (threadLoadListener != null) {
                        threadLoadListener.onThreadInfoLoaded();
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Log.e("ThreadInfo", "Request " + call.request().toString() + " failed with error " + t.getMessage());
            }
        });
    }

    private static ThreadDataService service = new Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/git-mad/threads/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ThreadDataService.class);


    private interface ThreadDataService {
        @GET("retrofit/ThreadData.json")
        Call<Map<String, String>> getThreads();
    }

}
