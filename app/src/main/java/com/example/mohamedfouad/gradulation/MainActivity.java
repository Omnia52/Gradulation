package com.example.mohamedfouad.gradulation;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private TextView mTextView;
    private Handler mHandler=new Handler();
    private int mProgressStatus=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar=(ProgressBar) findViewById(R.id.LoadingProgress);
        mTextView=(TextView) findViewById(R.id.LoadingText);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mProgressStatus<100){
                    mProgressStatus++;
                   // android.os.SystemClock.sleep(50);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(mProgressStatus);
                        }
                    });
                    Intent intent=new Intent(MainActivity.this,ChooseActivity.class);
                    startActivity(intent);
                }
            }
        }).start();
    }
}
