package edu.gatech.gtorg.gitmad.threads.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.gtorg.gitmad.threads.R;

public class LinearLayoutLogin extends AppCompatActivity {

    private static boolean loggedIn = false;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout_login);
        signInBtn = ((Button) findViewById(R.id.btn_lin_sign_in));

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
                setLoggedIn(true);
                finish();
            }
        });
    }

    public void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void setLoggedIn(boolean val){
        loggedIn = val;
    }

    public static boolean isLoggedIn(){
        return loggedIn;
    }
    public static void startLinearLayoutActivity(Context context){
        Intent intent = new Intent(context, LinearLayoutLogin.class);
        context.startActivity(intent);
    }
}
