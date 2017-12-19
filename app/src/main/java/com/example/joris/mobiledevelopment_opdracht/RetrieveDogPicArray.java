package com.example.joris.mobiledevelopment_opdracht;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gener on 19/12/2017.
 */

public class RetrieveDogPicArray extends AsyncTask<String,Void ,List<Bitmap>> {
    List<Bitmap> ImageList = new ArrayList<Bitmap>();


    @Override
    protected List<Bitmap> doInBackground(String... strings) {
        return null;
    }

}
