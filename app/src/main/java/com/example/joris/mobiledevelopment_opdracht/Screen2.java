package com.example.joris.mobiledevelopment_opdracht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

public class Screen2 extends AppCompatActivity implements OnTaskCompleted {


    final TextView textView = (TextView)findViewById(R.id.textView2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        //blablabala


       RetrieveBreeds retrieveBreeds = new RetrieveBreeds(this);
        retrieveBreeds.execute();


        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen2.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void OnTaskComplete(List<String> output) {
        for(String s : output){
            textView.append(s);
        }


    }

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };



}

