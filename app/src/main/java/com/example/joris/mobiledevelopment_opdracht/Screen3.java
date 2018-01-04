package com.example.joris.mobiledevelopment_opdracht;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Screen3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        final CustomImageSearch ImageSearch = new CustomImageSearch();
        final TextView InputText = (TextView)findViewById(R.id.SearchText);
        final Button Search = (Button)findViewById(R.id.SearchBtn);
        final EditText text = (EditText)findViewById(R.id.JsonText);
        final CustomImageSearch myAsyncSearch = new CustomImageSearch();
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAsyncSearch.SearchTerm = "dog";
                myAsyncSearch.execute();
               // text.setText(myAsyncSearch.GetJsonAsString());
            }
        });
    }
}
