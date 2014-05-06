package com.brnokavarna.melounovycukr.app.View;

/**
 * Created by mpx on 5.5.2014.
 */
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;

public class SortimentFragment extends Fragment {

    private GridView gridView;

    public SortimentFragment() {
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sortiment,
                container, false);

        //Nastaveni fontu
        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Book.otf");
        TextView backText = (TextView) view.findViewById(R.id.backText);
        backText.setTypeface(gothamLight);
        TextView tempText = (TextView) view.findViewById(R.id.textPopular);
        tempText.setTypeface(gothamBook);
        tempText = (TextView) view.findViewById(R.id.textKava);
        tempText.setTypeface(gothamBook);
        tempText = (TextView) view.findViewById(R.id.textAlkohol);
        tempText.setTypeface(gothamBook);
        tempText = (TextView) view.findViewById(R.id.textDobroty);
        tempText.setTypeface(gothamBook);
        tempText = (TextView) view.findViewById(R.id.textOstatni);
        tempText.setTypeface(gothamBook);
        TextView addText = (TextView) view.findViewById(R.id.addText);
        addText.setTypeface(gothamLight);

        //grid

        ArrayList<ClipData.Item> gridArray = new ArrayList<ClipData.Item>();
        gridView = (GridView) view.findViewById(R.id.gridViewSortiment);

        //bude z databaze
         final String[] MOBILE_OS = new String[] {
                "Android", "iOS","Windows", "Blackberry" , "Blackberry" ,"Blackberry" ,"Blackberry" ,"Blackberry" ,"Blackberry" ,};

        final String[] MOBILE_OS2 = new String[] {
                "Seky", "Pepaaaa","Windows", "Blackberry" , "Blackberry" ,"Blackberry" ,"Blackberry" ,"Blackberry" ,"Blackberry" ,};


        //volba kategorie
        TextView tv1, tv2, tv3, tv4, tv5;
        tv1 = (TextView) view.findViewById(R.id.textPopular);
        tv2 = (TextView) view.findViewById(R.id.textKava);
        tv3 = (TextView) view.findViewById(R.id.textAlkohol);
        tv4 = (TextView) view.findViewById(R.id.textDobroty);
        tv5 = (TextView) view.findViewById(R.id.textOstatni);


        //reakce na kliknuti
        tv1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(), MOBILE_OS));
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(), MOBILE_OS2));
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(), MOBILE_OS2));
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(), MOBILE_OS2));
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(), MOBILE_OS2));
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(
                        getActivity(),
                        ((TextView) v.findViewById(R.id.grid_item_label))
                                .getText(), Toast.LENGTH_SHORT).show();

            }
        });

        //vraceni zpet do mainu
        ImageView zpetImage;
        zpetImage = (ImageView) view.findViewById(R.id.backSortiment);
        zpetImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).ShowMainHideOthers();
            }
        });

        //ruzova polozka
       // ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
       // imageView.setImageResource(R.drawable.item_pink);


        //gridArray.add(new ClipData.Item(pinkIcon,"test"));


        return view;
    }

    public void BackSortiment(View view)
    {
        Toast.makeText(
                getActivity(),
                "zkouska", Toast.LENGTH_SHORT).show();
    }

}