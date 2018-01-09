package com.example.joris.mobiledevelopment_opdracht;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class Screen1 extends AppCompatActivity implements OnTaskCompleted{
    AutoCompleteTextView input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);


        input = (AutoCompleteTextView) findViewById(R.id.input);

        RetrieveBreeds retrieveBreeds = new RetrieveBreeds(this);
        retrieveBreeds.execute();
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        
        Button search =(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dogbreed = input.getText().toString();
                RetrieveDogPicArray retrieveDogPicArray =new RetrieveDogPicArray(progressBar);
                retrieveDogPicArray.execute(dogbreed);
            }
        });


        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen1.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void OnTaskComplete(List<String> output) {
        //input = (AutoCompleteTextView) findViewById(R.id.input);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,output);
        input.setAdapter(adapter);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.showDropDown();
            }
        });
    }
}
