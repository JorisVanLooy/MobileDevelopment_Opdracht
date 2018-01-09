package com.example.joris.mobiledevelopment_opdracht;

import android.graphics.Bitmap;

/**
 * Created by joris on 1/9/2018.
 */

public class ImageListItem {
    public String Breed;
    public String API;
    public Bitmap Image;

    public ImageListItem(String breed,String api,Bitmap image){
        this.Breed = breed;
        this.API = api;
        this.Image = image;
    }
}
