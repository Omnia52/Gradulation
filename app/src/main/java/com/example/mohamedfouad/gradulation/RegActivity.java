package com.example.mohamedfouad.gradulation;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegActivity extends AppCompatActivity {
    EditText Name, Email, Password;
    Button Reg_button;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        Name = (EditText) findViewById(R.id.name);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        Reg_button = (Button) findViewById(R.id.submitBtn);
        Reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Name.getText().toString().equals("") || Email.getText().toString().equals("") || Password.getText().toString().equals("")) {
                    builder = new AlertDialog.Builder(RegActivity.this);
                    builder.setTitle("Something Wrong....");
                    builder.setMessage("Please fill all fields");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    /*builder = new AlertDialog.Builder(RegActivity.this);
                    builder.setTitle("Success....");
                    builder.setMessage("Hello ^_^");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();*/
                    BackgroungTask backgroundTask = new BackgroungTask(RegActivity.this);
                    backgroundTask.execute("register", Name.getText().toString(), Email.getText().toString(), Password.getText().toString());

                }


            }
        });
    }

    public void register(View view) {

    }
}
