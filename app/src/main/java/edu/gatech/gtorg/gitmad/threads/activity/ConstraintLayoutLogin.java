package edu.gatech.gtorg.gitmad.threads.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.gtorg.gitmad.threads.R;

public class ConstraintLayoutLogin extends AppCompatActivity {

    private static boolean loggedIn = false;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout_login);
        //TODO:(11) Add the reference for button in constraint layout same as before
//        signIn = ((Button) findViewById("**Reference to your button id**"));

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
                setLoggedIn(true);
                finish();
            }
        });

    }

    //Code to start the Main Activity after clicking signing in
    public void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static boolean isLoggedIn(){
        return loggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        ConstraintLayoutLogin.loggedIn = loggedIn;
    }

    //Static Factory method to start this activity by another activity
    public static void startConstraintActivity(Context context) {
        Intent starter = new Intent(context, ConstraintLayoutLogin.class);
        context.startActivity(starter);
    }
}
