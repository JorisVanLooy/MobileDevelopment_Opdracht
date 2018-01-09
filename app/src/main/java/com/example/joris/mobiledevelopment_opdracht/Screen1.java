package com.example.joris.mobiledevelopment_opdracht;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.List;

public class Screen1 extends AppCompatActivity implements OnTaskCompleted{
    AutoCompleteTextView input;
    public static final String EXTRA_MESSAGE = "com.example.MobileDevelopment.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        input = (AutoCompleteTextView) findViewById(R.id.input);

        RetrieveBreeds retrieveBreeds = new RetrieveBreeds(this);
        retrieveBreeds.execute();
        
        Button search =(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dogbreed = input.getText().toString();

                // start new intent
                Intent intent = new Intent(Screen1.this,Screen2.class);
                intent.putExtra(EXTRA_MESSAGE,dogbreed);
                startActivity(intent);

            }
        });

    }

    @Override
    public void OnTaskComplete(List<String> output) {

        for(int i=0; i < output.size();i++){
            if(output.get(i).length() > 0)
            output.set(i,output.get(i).substring(0,1).toUpperCase() + output.get(i).substring(1));
        }
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
