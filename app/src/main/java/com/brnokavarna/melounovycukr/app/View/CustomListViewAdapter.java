package com.brnokavarna.melounovycukr.app.View;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ArrayAdapter;

/**
 * Created by mpx on 13.6.2014.
 */
public class CustomListViewAdapter extends ArrayAdapter<CharSequence> {

    Context context;
    int layoutResourceId;
    CharSequence data[] = null;
    Typeface tf;

    public CustomListViewAdapter(Context context, int layoutResourceId, CharSequence[] data, String FONT) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        tf = Typeface.createFromAsset(context.getAssets(), FONT);
    }
}