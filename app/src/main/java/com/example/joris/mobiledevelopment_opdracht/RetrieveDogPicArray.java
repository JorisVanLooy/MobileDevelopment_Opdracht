package com.example.joris.mobiledevelopment_opdracht;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.joris.mobiledevelopment_opdracht.GetImgurImages.loadBitmap;

//RETURNS JSON CALL AND SETS IN TEXTVIEW

public class RetrieveDogPicArray extends AsyncTask<String, Void, Bitmap[]> {
    private  Exception exception;
    private ProgressBar progressBar;
    private String API_URL;
    public List<String> Links = new ArrayList<>();
    public List<Bitmap>BitmapImages = new ArrayList<>();
    public int NumberOfImages;

    public RetrieveDogPicArray(ProgressBar progressbar)
    {
        this.progressBar = progressbar;
    }


    @Override
    protected void onPreExecute(){
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Bitmap[] doInBackground(String... args) {
        //String DogBreed = args[0];
        API_URL=String.format("https://dog.ceo/api/breed/%s/images", args[0]);
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
                JSONObject jObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jObject.getJSONArray("message");
                String[] arr = new String[jsonArray.length()];
                for(int i=0; i <jsonArray.length();i++)
                {
                    arr[i] = jsonArray.getString(i);
                    Links.add(i,arr[i]);
                }
                if(Links.size()>10){
                    NumberOfImages=10;
                }
                else {
                    NumberOfImages=Links.size();
                }
                for(int i =0; i< NumberOfImages;i++){
                    BitmapImages.add(loadBitmap(Links.get(i)));
                }
                Bitmap[] bitmaparr = new Bitmap[BitmapImages.size()];
                bitmaparr = BitmapImages.toArray(bitmaparr);


                return bitmaparr;

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
    protected  void onPostExecute(Bitmap[] result){
        String errormsg;
        if(result == null){
            errormsg = "ER WAS EEN ERROR";
            Log.i("INFO",errormsg);

        }
        progressBar.setVisibility(View.GONE);



    }
}
