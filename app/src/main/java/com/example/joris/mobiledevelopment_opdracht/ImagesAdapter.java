package com.example.joris.mobiledevelopment_opdracht;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by joris on 1/9/2018.
 */

public class ImagesAdapter extends ArrayAdapter<ImageListItem> {
    private final Context context;
    private final ImageListItem[] values;
    public ImagesAdapter(Context context,ImageListItem[] values){
        super(context,-1,values);
        this.context= context;
        this.values= values;
    }

    @Override
    public View getView(int postion, View convertview, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.image_item, parent, false);

        TextView textViewBreed = (TextView)rowView.findViewById(R.id.item_breed);
        ImageView imageViewImage = (ImageView)rowView.findViewById(R.id.item_image);
        TextView textViewAPI = (TextView)rowView.findViewById(R.id.item_api);

        if(values[postion] != null){
            textViewBreed.setText(values[postion].Breed);
            imageViewImage.setImageBitmap(values[postion].Image);
            textViewAPI.setText(values[postion].API);
        }


        return rowView;
    }
}
