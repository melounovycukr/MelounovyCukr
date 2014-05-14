package com.brnokavarna.melounovycukr.app.View;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mpx on 29.4.2014.
 */
public class AddSortimentDialog extends DialogFragment{

    public AddSortimentDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_sortiment, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        TextView addText = (TextView) view.findViewById(R.id.addText);
        addText.setTypeface(gothamLight);

        ImageView add = (ImageView) view.findViewById(R.id.add);
        add.setOnClickListener(doneListener);

        ImageView back = (ImageView) view.findViewById(R.id.back);
        back.setOnClickListener(printListener);

        TextView backText = (TextView) view.findViewById(R.id.backText);
        backText.setTypeface(gothamLight);

        TextView titleText = (TextView) view.findViewById(R.id.titleText);
        titleText.setText("Přidat sortiment");
        titleText.setTypeface(gothamLight);

        return view;
    }

    View.OnClickListener doneListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Done", Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener printListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Az to hanz dodela, tak mozna neco vytisku :D", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        // safety check
        if (getDialog() == null)
            return;

        int dialogWidth = 700; // specify a value here
        int dialogHeight = 330; // specify a value here

        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

        // ... other stuff you want to do in your onStart() method
    }

}
