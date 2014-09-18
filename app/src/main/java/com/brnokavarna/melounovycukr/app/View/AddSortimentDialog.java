package com.brnokavarna.melounovycukr.app.View;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mpx on 29.4.2014.
 */
public class AddSortimentDialog extends DialogFragment{

    private EditText sortimentNameEditText;
    private EditText sortimentCostEditText;
    private Controller.CategoryID chosenCategory;
    private Handler handler;


    public AddSortimentDialog() {
        // Empty constructor required for DialogFragment
    }

    //cons
    public AddSortimentDialog(Controller.CategoryID cat, Handler h)
    {
        chosenCategory = cat;
        handler = h;
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

        final ImageView add = (ImageView) view.findViewById(R.id.add);
        add.setOnClickListener(doneListener);

        add.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    add.setBackground(getResources().getDrawable(R.drawable.btn_green_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    add.setBackground(getResources().getDrawable(R.drawable.btn_green_normal));
                }
                return false;
            }
        });

        final ImageView back = (ImageView) view.findViewById(R.id.back);
        back.setOnClickListener(backListener);

        back.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    back.setBackground(getResources().getDrawable(R.drawable.btn_grey_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    back.setBackground(getResources().getDrawable(R.drawable.btn_grey_normal));
                }
                return false;
            }
        });

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



        return view;
    }



    View.OnClickListener doneListener = new View.OnClickListener() {
        public void onClick(View v) {
            try{


                String pom = "";
                pom = sortimentNameEditText.getText().toString();
                float temp = Float.parseFloat(sortimentCostEditText.getText().toString());
                ((MainActivity)getActivity()).cont.PridejPolozkuSeznam(new Seznam(chosenCategory.ordinal(),
                        temp,
                        sortimentNameEditText.getText().toString(),true));
                Toast.makeText(getActivity(), "Položka přídána", Toast.LENGTH_LONG).show();
                getDialog().dismiss();
            }
            catch(NumberFormatException e){
                Toast.makeText(getActivity(), "Špatně zadaná cena!", Toast.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener backListener = new View.OnClickListener() {
        public void onClick(View v) {
            //close
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

    /**
     * Dismiss handler
     * @param dialog
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        handler.sendEmptyMessage(0);
    }

}
