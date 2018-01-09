package com.example.joris.mobiledevelopment_opdracht;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.joris.mobiledevelopment_opdracht.BooVariable.ChangeListener;
import com.example.joris.mobiledevelopment_opdracht.GetImgurImages.AsyncResponseImgur;
import com.example.joris.mobiledevelopment_opdracht.RetrieveDogPicArray.AsyncResponseDog;

import java.io.ByteArrayOutputStream;

public class Screen2 extends AppCompatActivity implements AsyncResponseImgur,AsyncResponseDog {

    public ListView imagesList;
    public ProgressBar progressBarImgur;
    public ProgressBar progressBarDog;
    private GetImgurImages ImgurTask;
    private RetrieveDogPicArray DogTask;
    public ImageListItem[] Images;
    public String Breed;
    public BooVariable ImgurIsFinished = new BooVariable();
    public BooVariable DogIsFinished = new BooVariable();
    public BooVariable TasksFinished = new BooVariable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        //get dog breed
        Intent intent = getIntent();
        String dogBreed = intent.getStringExtra(Screen1.EXTRA_MESSAGE);
        Breed = dogBreed;

        imagesList = (ListView) findViewById(R.id.images_list);
        imagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Screen2.this,ShowFullImage.class);

                Bitmap bmp= Images[i].Image;
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("IMAGE", byteArray);
                intent.putExtra("DOGBREED", Breed);
                intent.putExtra("API",Images[i].API);
                try{
                    startActivity(intent);
                }catch (Exception e){
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byteArray = stream.toByteArray();
                    intent.putExtra("IMAGE", byteArray);
                    intent.putExtra("DOGBREED", Breed);
                    intent.putExtra("API",Images[i].API);
                    startActivity(intent);
                }

            }
        });
        progressBarImgur =(ProgressBar)findViewById(R.id.progressBar2);
        progressBarDog =(ProgressBar)findViewById(R.id.progressBar3);
        Images = new ImageListItem[20];


        //start dog api task
        DogTask = new RetrieveDogPicArray(Screen2.this,progressBarDog);
        DogTask.execute(dogBreed);
        //start imgur api task
        ImgurTask = new GetImgurImages(Screen2.this,progressBarImgur);
        ImgurTask.execute(dogBreed);


        //set imageview when tasks complete
        DogIsFinished.setListener(new ChangeListener() {
            @Override
            public void onChange() {
                if(ImgurIsFinished.isBoo()){
                    TasksFinished.setBoo(true);
                }
            }
        });
        ImgurIsFinished.setListener(new ChangeListener() {
            @Override
            public void onChange() {
                if(DogIsFinished.isBoo()){
                    TasksFinished.setBoo(true);
                }
            }
        });
        TasksFinished.setListener(new ChangeListener() {
            @Override
            public void onChange() {
                imagesList.setAdapter(new ImagesAdapter(Screen2.this,Images));
            }
        });
        }

    @Override
    public void processFinishImgur(Bitmap[] output) {
        for(int i =0; i < output.length;i++){
            int id = i +10;
            Images[i+10]= new ImageListItem(Breed + " "+id,"Imgur API",output[i]);
        }
        ImgurIsFinished.setBoo(true);
    }
    @Override
    public void processFinishDog(Bitmap[] output) {
        for(int i =0; i < output.length;i++){

            Images[i]= new ImageListItem(Breed + " " +i,"Dog API",output[i]);
        }
        DogIsFinished.setBoo(true);
    }
}

