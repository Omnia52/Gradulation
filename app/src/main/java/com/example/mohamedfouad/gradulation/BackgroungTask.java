package com.example.mohamedfouad.gradulation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Mohamed Fouad on 2/17/2018.
 */

public class BackgroungTask extends  AsyncTask<String,Void,String>{

    String register_url = "http//192.168.1.2/Android/signup.php";

    Context ctx;
    Activity activity;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    public  BackgroungTask(Context ctx){
        this.ctx = ctx;
        activity = (Activity) ctx;
    }
        @Override
        protected void onPreExecute() {
        builder = new AlertDialog.Builder(activity);
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("please wait");
        progressDialog.setMessage("Connecting To server");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {

        String method = params[0];
        if(method.equals("register"))
        {
            try {
                progressDialog.dismiss();
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("post");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));

                String name = params[1];
                String email = params[2];
                String password = params[3];
                String data = URLEncoder.encode("name","utf-8")+"="+URLEncoder.encode(name,"utf-8")+"&"+
                        URLEncoder.encode("email","utf-8")+"="+URLEncoder.encode(email,"utf-8")+"&"+
                        URLEncoder.encode("password","utf-8")+"="+URLEncoder.encode(password,"utf-8")+"&";
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(line+"\n");

                }
                httpURLConnection.disconnect();
                Thread.sleep(5000);
                return stringBuilder.toString().trim();
            }catch (MalformedURLException e ) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String json) {

            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                JSONObject JO = jsonArray.getJSONObject(0);
                String code = JO.getString("code");
                String message = JO.getString("message");
                if(code.equals("reg_true")){
                    showDialog("Registeration Success",message,code);
                }
                else if(code.equals("reg_false")){

                    showDialog("Registeration Failed",message,code);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        public void showDialog(String title,String message,String code){

            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    activity.finish();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }
