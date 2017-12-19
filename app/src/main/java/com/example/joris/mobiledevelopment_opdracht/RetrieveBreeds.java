package com.example.joris.mobiledevelopment_opdracht;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RetrieveBreeds extends AsyncTask<Void,Void,List<String>> {

    //INTERFACE MAGIC

    public OnTaskCompleted listener = null;

    public RetrieveBreeds(OnTaskCompleted listener)
    {
        this.listener = listener;
    }



    private String API_URL="https://dog.ceo//api/breeds/list";
    private List<String> dogBreeds;
    @Override
        protected List<String> doInBackground(Void... voids) {
        try{
            URL url = new URL(API_URL);
            HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringbuilder= new StringBuilder();
                String line;
                while((line = bufferedReader.readLine()) != null)
                {
                    stringbuilder.append(line).append("\n");
                }
                bufferedReader.close();
                JSONObject jObject = new JSONObject(stringbuilder.toString());
                JSONArray jArray = jObject.getJSONArray("message");
                for(int i=0; i <jArray.length();i++)
                {
                    try
                    {
                        dogBreeds.add(i,jArray.getJSONObject(i).toString());
                    }
                    catch (JSONException e){
                    }
                }


            }
            finally {
                urlConnection.disconnect();
            }
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage(),e);
            return null;
        }


        return dogBreeds;
    }
    @Override
    protected void onPostExecute(List<String> result){
        listener.OnTaskComplete(result);
    }

}
