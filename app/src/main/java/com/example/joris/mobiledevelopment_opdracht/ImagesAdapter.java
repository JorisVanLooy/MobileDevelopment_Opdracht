package com.example.joris.mobiledevelopment_opdracht;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by joris on 1/8/2018.
 */

public class ImagesAdapter extends ArrayAdapter<Bitmap> {
    private final Context context;
    private final Bitmap[] values;
    public ImagesAdapter(Context context, Bitmap[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.afspraken_list_item, parent, false);

        TextView textViewDatum = (TextView) rowView.findViewById(R.id.afspraak_datum);
        String datum = values[position].getDay() + "/" + values[position].getMonth() + "/" +values[position].getYear();
        textViewDatum.setText(datum);

        TextView textViewTijd = (TextView) rowView.findViewById(R.id.afspraak_tijd);
        textViewTijd.setText(String.valueOf(values[position].getTime()));
        String tijd = values[position].getHour() +":"+ values[position].getMinute();
        textViewTijd.setText(tijd);

        TextView textViewPersoon =(TextView) rowView.findViewById(R.id.afspraak_persoon);
        textViewPersoon.setText(values[position].getContactName());

        TextView textViewLocatie = (TextView) rowView.findViewById(R.id.afspraak_locatie);
        textViewLocatie.setText(values[position].getLocation());

        ImageView img =(ImageView) rowView.findViewById(R.id.img);
        BitmapDrawable ob = new BitmapDrawable(context.getResources(), values[position].getBitmap());
        img.setBackground(ob);
        return rowView;
    }
}
