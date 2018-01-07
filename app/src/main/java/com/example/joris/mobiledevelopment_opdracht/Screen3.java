package com.example.joris.mobiledevelopment_opdracht;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joris.mobiledevelopment_opdracht.GetImgurImages.AsyncResponse;

import java.util.ArrayList;
import java.util.List;

public class Screen3 extends AppCompatActivity  {
    public String JSON;
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
        final String Term = text.getText().toString();
        final GetImgurImages ImageSearch = new GetImgurImages(new AsyncResponse() {
            @Override
            public void processFinish(String output) {
              /*  try{
                    JSONArray Data = output.getJSONArray("data");

                    for(int i =0; i < Data.length(); i++){
                        JSONObject DataItem = Data.getJSONObject(i);
                        JSONArray Images = DataItem.getJSONArray("images");

                        for ( int j =0; j < Images.length(); j++){
                            JSONObject Image = Images.getJSONObject(j);
                            String link = Image.getString("link");
                            if(link != null){
                                Links.add(link);
                            }
                        }
                    }

                }
                catch (JSONException e){
                    Log.e("Error", e.getMessage(),e);
                } */



          /* try{
                JSONArray Data = output.getJSONArray("data");
                int j =0;
                for(int i =0; i < Data.length(); i++){
                     JSONObject DataItem = Data.getJSONObject(i);
                     int id =  DataItem.getInt("id");
                     j += id;
                }
                textView.setText(Integer.toString(j));


            } catch (JSONException e){

            } */
          textView.setText(output);
            }
        });

        //String[] LinksArray = new String[Links.size()];
        //LinksArray = Links.toArray(LinksArray);
        //listView.setAdapter(new LinkArrayAdapter(this,LinksArray));

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ImageSearch.execute(Term);
                }
                catch (IllegalStateException e){

                }
            }
        });
    }

}
