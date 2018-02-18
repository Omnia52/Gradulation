package com.example.mohamedfouad.gradulation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StudentLoginActivity extends AppCompatActivity {
    TextView signup_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        signup_text = (TextView)findViewById(R.id.regbtn);
        signup_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentLoginActivity.this,RegActivity.class);
                startActivity(i);
            }
        });
    }
    public void studentLogin(View view)
    {

    }
    public void reg(View view)
    {
       /* Intent i = new Intent(this,RegActivity.class);
        startActivity(i);*/
    }
}
