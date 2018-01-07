package com.example.joris.mobiledevelopment_opdracht;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joris.mobiledevelopment_opdracht.GetImgurImages.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Screen3 extends AppCompatActivity  {
    public String JSON;
    public String Term;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        //final CustomImageSearch ImageSearch = new CustomImageSearch();
        final ListView listView = (ListView)findViewById(R.id.ListView1);
        final TextView textView =(TextView)findViewById(R.id.textView3);
        final List<String> Links = new ArrayList<>();
        final Button Search = (Button)findViewById(R.id.SearchBtn);
        final EditText text = (EditText)findViewById(R.id.SearchTerm);

        final GetImgurImages ImageSearch = new GetImgurImages(new AsyncResponse() {
            @Override
            public void processFinish(JSONObject output) {
                try{
                    JSONArray Data = output.getJSONArray("data");
                    for(int i =0; i < Data.length();i++){
                        JSONObject DataItem = Data.getJSONObject(i);

                        if(DataItem.has("images")){
                            JSONArray Images = DataItem.getJSONArray("images");
                            for(int j =0; j < Images.length(); j++){
                                JSONObject ImageItem = Images.getJSONObject(j);
                                String ImageLink = ImageItem.getString("link");
                                Links.add(ImageLink);
                            }
                        }
                    }
                    //textView.setText(Data.getJSONObject(0).toString());
                    textView.setText(Links.get(0));
                }
                catch (JSONException e){
                    Log.e("Error", e.getMessage(),e);
                }
            }
        });


        //listView.setAdapter(new LinkArrayAdapter(this,LinksArray));


        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Term = text.getText().toString();
                    ImageSearch.searchTerm = Term;
                    ImageSearch.execute();
                }
                catch (IllegalStateException e){

                }
            }
        });
    }

}
