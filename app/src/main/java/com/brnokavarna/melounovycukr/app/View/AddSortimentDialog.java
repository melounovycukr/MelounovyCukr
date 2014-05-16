package com.brnokavarna.melounovycukr.app.View;

import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mpx on 29.4.2014.
 */
public class AddSortimentDialog extends DialogFragment{

    public Controller cont;
    private EditText sortimentNameEditText;
    private EditText sortimentCostEditText;

    public AddSortimentDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_sortiment, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Book.otf");
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

        TextView sortimentNameText = (TextView) view.findViewById(R.id.sortimentNameText);
        sortimentNameText.setTypeface(gothamBook);

        sortimentNameEditText = (EditText) view.findViewById(R.id.sortimentNameEditText);
        sortimentNameEditText.setTypeface(gothamBook);

        TextView sortimentCostText = (TextView) view.findViewById(R.id.sortimentCostText);
        sortimentCostText.setTypeface(gothamBook);

        sortimentCostEditText = (EditText) view.findViewById(R.id.sortimentCostEditText);
        sortimentCostEditText.setTypeface(gothamBook);

        cont = new Controller(getActivity());


        return view;
    }

    View.OnClickListener doneListener = new View.OnClickListener() {
        public void onClick(View v) {
            cont.PridejPolozkuSeznam(new Seznam(Controller.CategoryID.Alkohol.ordinal(),
                    Float.parseFloat(sortimentCostEditText.getText().toString()),
                    sortimentNameEditText.getText().toString(),true));
            Toast.makeText(getActivity(), "Položka přídána", Toast.LENGTH_LONG).show();
            getDialog().dismiss();
        }
    };

    View.OnClickListener printListener = new View.OnClickListener() {
        public void onClick(View v) {
            getDialog().dismiss();
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        // safety check
        if (getDialog() == null)
            return;

        int dialogWidth = 700; // specify a value here
        int dialogHeight = 320; // specify a value here

        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

        // ... other stuff you want to do in your onStart() method
    }

}
