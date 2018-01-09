package com.example.joris.mobiledevelopment_opdracht;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

public class Screen3 extends AppCompatActivity   {
    public String Term;
    private GetImgurImages ImageTask;
    public ImageView imageView;
    public ProgressBar progressBar;
    public int id =0;
    public Bitmap[] ImagesArray;
    ListView ImagesListview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        final Button Search = (Button)findViewById(R.id.SearchBtn);
        final EditText text = (EditText)findViewById(R.id.SearchTerm);
        //imageView = (ImageView)findViewById(R.id.imageView);

        final Button Scroll =(Button)findViewById(R.id.scrollbtn);
        ImagesListview = (ListView)findViewById(R.id.listViewImages);
        Scroll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id < ImageTask.NumberOfImages -1){
                    id++;
                }
                else{
                    id =0;
                }
                imageView.setImageBitmap(ImagesArray[id]);
            }
        });

        Search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                Term = text.getText().toString();
                if(ImageTask.getStatus() == AsyncTask.Status.RUNNING){
                    Log.d("Task", "onClick: ImageTask already running");
                }
                else{


                }
            }
        });
    }



}
