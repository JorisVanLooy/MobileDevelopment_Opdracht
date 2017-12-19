package com.example.joris.mobiledevelopment_opdracht;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//RETURNS JSON CALL AND SETS IN TEXTVIEW

public class RetrieveDog extends AsyncTask<String, Void, String> {
    private  Exception exception;
    private ProgressBar progressBar;
    private TextView textView;
    private EditText editText;
    private String API_URL;
    public RetrieveDog(EditText edittext,TextView textview,ProgressBar progressbar)
    {
        this.progressBar = progressbar;
        this.textView=textview;
        this.editText = edittext;
    }


    @Override
    protected void onPreExecute(){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("");
    }

    @Override
    protected String doInBackground(String... args) {
        //String DogBreed = args[0];
        API_URL=String.format("https://dog.ceo/api/breed/%s/images/random", args[0]);
        try{
            URL url = new URL(API_URL);
            HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine())!=null){
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally {
                urlConnection.disconnect();
            }
        }
        catch(Exception e)
        {
            Log.e("Error", e.getMessage(),e);
            return null;
        }
    }

    @Override
    protected  void onPostExecute(String result){
        if(result == null){
            result = "ER WAS EEN ERROR";

        }
        progressBar.setVisibility(View.GONE);
        Log.i("INFO",result);
        textView.setText(result);


    }
}
