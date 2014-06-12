package com.brnokavarna.melounovycukr.app.View;

/**
 * Created by Seky on 6. 5. 2014.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brnokavarna.melounovycukr.app.R;

import java.util.List;


public class CustomGridViewAdapter extends BaseAdapter {
    private Context context;
    private  List<String> itemsValues;

    public CustomGridViewAdapter(Context context, List<String> itemsValues) {
        this.context = context;
        this.itemsValues = itemsValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from items.xml
            gridView = inflater.inflate(R.layout.row_grid, null);

            //setting fonts
            Typeface gothamMedium = Typeface.createFromAsset(context.getAssets(), "Gotham-Medium.otf");
            TextView backText = (TextView) gridView.findViewById(R.id.grid_item_label);
            backText.setTypeface(gothamMedium);
            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(itemsValues.get(position).substring(0, itemsValues.get(position).indexOf(";")));

            //store id grid_item_hidden_id
            TextView idText = (TextView) gridView
                    .findViewById(R.id.grid_item_hidden_id);
            if(itemsValues.get(position).contains("|")) {
                idText.setText(itemsValues.get(position).substring(itemsValues.get(position).indexOf("|")+1, itemsValues.get(position).length()));
            }

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);


            String items = itemsValues.get(position);
            //setting item background within category
            if(items.contains(";0"))
                imageView.setImageResource(R.drawable.item_red);
            else if(items.contains(";1"))
                imageView.setImageResource(R.drawable.item_pink);
            else if(items.contains(";2"))
                imageView.setImageResource(R.drawable.item_green);
            else if(items.contains(";3"))
                imageView.setImageResource(R.drawable.item_blue);

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return itemsValues.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}

