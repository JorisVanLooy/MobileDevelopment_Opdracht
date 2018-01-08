package com.example.joris.mobiledevelopment_opdracht;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joris on 1/6/2018.
 */

public class GetImgurImages extends AsyncTask<String,Integer,Bitmap[]>{
    HttpURLConnection myURLConnection;
    String API_URL;
    public List<String> Links = new ArrayList<>();
    public List<Bitmap>BitmapImages = new ArrayList<>();
    public String searchTerm;
    public int NumberOfImages;
    private ProgressBar progressBar;

    public interface AsyncResponse{
        void processFinish(Bitmap[] output);
    }
    public AsyncResponse delegate = null;

    public GetImgurImages(AsyncResponse delegate, ProgressBar barr){
        this.delegate = delegate;
        progressBar = barr;
    }

    @Override
    protected  Bitmap[]  doInBackground(String... term){
        API_URL = String.format("https://api.imgur.com/3/gallery/search/?q="+term[0]);
        try{
            URL myURL = new URL(API_URL);
            myURLConnection = (HttpURLConnection)myURL.openConnection();
            String APIkey = "6fc95f2956c6a63";
            myURLConnection.setRequestMethod("GET");
            myURLConnection.setRequestProperty("Authorization", "Client-ID " + APIkey);
            myURLConnection.setDoInput(true);
            myURLConnection.setDoOutput(false);
            BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream(), Charset.forName("UTF-8")) );
            StringBuilder stringBuilder = new StringBuilder();
            int pos;
            while ((pos = in.read())!= -1){
                stringBuilder.append((char) pos);
            }
            String jsonText = stringBuilder.toString();
            JSONObject json = new JSONObject(jsonText);
            in.close();

            try {
                JSONArray Data = json.getJSONArray("data");
                for (int i = 0; i < Data.length(); i++) {
                    JSONObject DataItem = Data.getJSONObject(i);

                    if (DataItem.has("images")) {
                        JSONArray Images = DataItem.getJSONArray("images");
                        for (int j = 0; j < Images.length(); j++) {
                            JSONObject ImageItem = Images.getJSONObject(j);
                            String ImageLink = ImageItem.getString("link");
                            Links.add(ImageLink);
                        }
                    }
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage(), e);
            }

            if(Links.size() >= 10){
                NumberOfImages =10;
            }
            else{
                NumberOfImages = Links.size();
            }

            for(int i =0; i< NumberOfImages;i++){
                BitmapImages.add(loadBitmap(Links.get(i)));
                publishProgress(i);
            }
            Bitmap[] arr = new Bitmap[BitmapImages.size()];
            arr = BitmapImages.toArray(arr);
            return arr;
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
    protected void onPostExecute(Bitmap[] result) {
        if (result == null) {
            Log.d("Error", "Failed to retrieve JSON");
        }
        delegate.processFinish(result);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        progressBar.setProgress(values[0]);
    }

    public static Bitmap loadBitmap(String src){
        try{
            URL url = new URL(src);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Config.RGB_565;
            options.inSampleSize =2;
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream(),null,options);
            return image;
        }catch (IOException e){
            Log.e("ERROR", e.getMessage(),e);
            return null;
        }
    }
}