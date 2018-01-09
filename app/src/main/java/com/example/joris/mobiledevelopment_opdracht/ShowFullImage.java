package com.example.joris.mobiledevelopment_opdracht;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gener on 9/01/2018.
 */

public class ShowFullImage extends AppCompatActivity {
    ImageView dogimage;
    Bitmap image;
    String breed;
    TextView breedText;
    byte[] byteArray;
    String API;
    TextView APIText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailimage);

        dogimage = (ImageView)findViewById(R.id.dogImage);
        Intent intent=getIntent();
        byteArray=intent.getByteArrayExtra("IMAGE");
        image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        dogimage.setImageBitmap(image);
        breed=intent.getStringExtra("DOGBREED");
        breedText = (TextView)findViewById(R.id.BreedText);
        breedText.setText(breed);
        API=intent.getStringExtra("API");
        APIText = (TextView)findViewById(R.id.APIText);
        APIText.setText(API);




    }
}
