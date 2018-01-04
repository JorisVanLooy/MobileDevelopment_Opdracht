package com.example.joris.mobiledevelopment_opdracht;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
/**
 * Created by joris on 1/4/2018.
 */

public class CustomImageSearch extends AsyncTask<Void, Void, Void> {
    private String APIkey = "AIzaSyC4opQbiisIq3c6T7QKKWthb1bCKQbANt0";
    private  String SearchKey ="003664250181174700788:w9efkaipqoe";
    public String SearchTerm;
    public JSONObject json;
    private String URL = "https://www.googleapis.com/customsearch/v1?q="+SearchTerm+"&key="+APIkey+"&cx="+SearchKey+"&alt=json";

    public CustomImageSearch(){
            super();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{
            Search();
            Log.d("test", "Search: testttsetsestststsetsetsetstsetst");
        }
        catch (IOException | JSONException e){
            Log.e("Error", e.getMessage(),e);
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void v){

    }
    public static JSONObject GetJsonFromUrl(String url)throws IOException, JSONException{
        InputStream stream = new URL(url).openStream();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
            StringBuilder builder = new StringBuilder();
            int in;
            while ((in = reader.read()) != -1){
                builder.append((char) in);
            }
            String jsonText = builder.toString();
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            stream.close();
        }
    }
    public void Search() throws IOException, JSONException{
         json = GetJsonFromUrl(this.URL);
         Log.d("JSON", json.toString());
    }
    public JSONObject GetJson(){
        return json;
    }
    public String GetJsonAsString() {return json.toString();}
}

/*credits: Roland Illig user stackoverflow.com
url:https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java*/