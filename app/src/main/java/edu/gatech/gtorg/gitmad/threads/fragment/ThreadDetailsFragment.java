package edu.gatech.gtorg.gitmad.threads.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import edu.gatech.gtorg.gitmad.threads.R;

public class ThreadDetailsFragment extends Fragment {

    private static final String KEY_THREAD_NAME = "thread name instance state key";
    private static final String KEY_THREAD_DETAILS = "thread details instance state key";
    private static final String EMPTY_STRING = "";
    private static final int SEND_REQUEST_CODE = 1;
    private static final String URL = "http://grfx.cstv.com/schools/psu/graphics/auto/GT_Logo.jpg";

    private String threadName;
    private String threadDetails;

    private TextView threadDescriptionTextView;
    private Button sendButton;
    private Button downloadButton;
    private ImageView imageView;


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
        sendButton = (Button) rootView.findViewById(R.id.button_send);
        downloadButton = (Button) rootView.findViewById(R.id.button_download);
        imageView = (ImageView) rootView.findViewById(R.id.download_view);

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

            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* TODO 12 (Advanced)
                 * Create a new instance of your async task and execute it here using the static URL
                 * at the top of the file. If everything goes right, you'll have a familiar looking
                 * logo in your image view.
                 */

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

    public Drawable getImage(String string_url) throws IOException {
        URL url = new URL(string_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            int responseCode = connection.getResponseCode();

            if (responseCode != HttpsURLConnection.HTTP_OK) {
                Log.i("error", "HTTP error code: " + responseCode);
            }
            InputStream input = connection.getInputStream();
            BufferedInputStream buff = new BufferedInputStream(input);
            Bitmap bitmap = BitmapFactory.decodeStream(buff);

            if (input != null) {
                input.close();
            }

            if (buff != null) {
                buff.close();
            }
            return new BitmapDrawable(getResources(), bitmap);

        } catch (MalformedURLException mue) {
            Log.e("mue", mue.getMessage());

        }  finally {
            connection.disconnect();
        }

        return null;
    }

    /* TODO 11 (Advanced)
     * The method getImage(<url>) is defined above to download an image at a given URL and return
     * a drawable image that may be displayed for the user. We can't do the download on the main UI
     * thread, so create an AsyncTask that runs getImage in the background and updates the ImageView
     * upon completion. You'll need to override doInBackground and onPostExecute for this.
     *
     * Hint: When defining the AsyncTask, it has 3 type parameters - AsyncTask<1,2,3>
     * 1. Type for the doInBackground var args
     * 2. Type for onProgressUpdate var args (not necessary for this so use Void)
     * 3. Type for onPostExecute which is also the return type from doInBackground
     */


    private void updateImageView(Drawable image) {
        imageView.setImageDrawable(image);
    }

}
