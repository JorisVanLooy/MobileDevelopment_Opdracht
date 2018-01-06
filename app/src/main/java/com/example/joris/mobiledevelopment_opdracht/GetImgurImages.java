package com.example.joris.mobiledevelopment_opdracht;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by joris on 1/6/2018.
 */

public class GetImgurImages extends AsyncTask<Void,Void,String>{
    HttpURLConnection myURLConnection;

    public interface AsyncResponse{
        void processFinish(String output);
    }
    public AsyncResponse delegate = null;

    public GetImgurImages(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected  String doInBackground(Void... v){
        String API_URL = "https://api.imgur.com/3/gallery/search/?q=dog";

        try{
            URL myURL = new URL(API_URL);
            myURLConnection = (HttpURLConnection)myURL.openConnection();
            String APIkey = "6fc95f2956c6a63";
            myURLConnection.setRequestProperty("Authorization", "Client-ID" + APIkey);
            myURLConnection.setRequestMethod("GET");
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()) );
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine())!= null){
                stringBuilder.append(line).append("\n");
            }
            in.close();
            return stringBuilder.toString();
        }
        catch (Exception e){
            Log.e("Error", e.getMessage(),e);
            return null;
        }
        finally {
            myURLConnection.disconnect();
        }
    }
    @Override
    protected void onPostExecute(String result){
        if(result == null){
            result = "ERROR";
        }
        delegate.processFinish(result);
    }
}