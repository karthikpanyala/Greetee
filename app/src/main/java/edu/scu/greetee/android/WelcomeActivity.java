package edu.scu.greetee.android;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent i = new Intent(WelcomeActivity.this, AppControllerActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}
