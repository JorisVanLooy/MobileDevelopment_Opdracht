package com.example.joris.mobiledevelopment_opdracht;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Screen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        final TextView Text = (TextView)findViewById(R.id.textView);
        final EditText input = (EditText)findViewById(R.id.input);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        Button search =(Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dogbreed = input.getText().toString();
                RetrieveDog retrieveDog=new RetrieveDog(input,Text,progressBar);
                retrieveDog.execute(dogbreed);
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
}
