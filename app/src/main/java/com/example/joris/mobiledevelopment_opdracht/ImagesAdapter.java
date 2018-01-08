package com.example.joris.mobiledevelopment_opdracht;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by joris on 1/8/2018.
 */

public class ImagesAdapter extends ArrayAdapter<ImagePair> {
    private final Context context;
    private final ImagePair[] values;
    public ImagesAdapter(Context context, ImagePair[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.itemimages, parent, false);

        ImageView img1 =(ImageView) rowView.findViewById(R.id.img1);
        img1.setImageBitmap(values[position].Image1);

        ImageView img2 =(ImageView) rowView.findViewById(R.id.img2);
        img2.setImageBitmap(values[position].Image2);

        return rowView;
    }
}
