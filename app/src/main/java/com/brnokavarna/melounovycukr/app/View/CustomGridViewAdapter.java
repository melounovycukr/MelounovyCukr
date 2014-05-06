package com.brnokavarna.melounovycukr.app.View;

/**
 * Created by Seky on 6. 5. 2014.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brnokavarna.melounovycukr.app.R;


public class CustomGridViewAdapter extends BaseAdapter {
    private Context context;
    private final String[] itemsValues;

    public CustomGridViewAdapter(Context context, String[] itemsValues) {
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

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(itemsValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String items = itemsValues[position];

            imageView.setImageResource(R.drawable.item_pink);


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return itemsValues.length;
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

