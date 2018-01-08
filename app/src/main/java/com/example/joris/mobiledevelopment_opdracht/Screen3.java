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
import android.widget.TextView;

import com.example.joris.mobiledevelopment_opdracht.GetImgurImages.AsyncResponse;

public class Screen3 extends AppCompatActivity implements AsyncResponse  {
    public String Term;
    private GetImgurImages ImageTask;
    public ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        //final CustomImageSearch ImageSearch = new CustomImageSearch();
        final ListView listView = (ListView)findViewById(R.id.ListView1);
        final TextView textView =(TextView)findViewById(R.id.textView3);
        final Button Search = (Button)findViewById(R.id.SearchBtn);
        final EditText text = (EditText)findViewById(R.id.SearchTerm);
        imageView = (ImageView)findViewById(R.id.imageView);
        ImageTask = new GetImgurImages(this);

        Search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Term = text.getText().toString();
                if(ImageTask.getStatus() == AsyncTask.Status.RUNNING){
                    Log.d("Task", "onClick: ImageTask already running");
                }
                else{
                    ImageTask.execute();
                }
            }
        });
    }

    @Override
    public void processFinish(Bitmap[] output) {
        imageView.setImageBitmap(output[0]);
    }
}
