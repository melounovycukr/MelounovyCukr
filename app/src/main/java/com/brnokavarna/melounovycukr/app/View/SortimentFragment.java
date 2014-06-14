package com.brnokavarna.melounovycukr.app.View;

/**
 * Created by mpx on 5.5.2014.
 */
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class SortimentFragment extends Fragment {

    private GridView gridView;
    private List<Seznam> ItemsGrid;
    private List<String> stringList;
    private TextView tv1, tv2, tv3, tv4, tv5;
    private Controller.CategoryID  chosenCategory = Controller.CategoryID.Kava;


    public SortimentFragment() {
        }

    @Override
    public void onResume() {
        ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazPopularni();
        stringList.clear();
        for(int i=0; i < ItemsGrid.size();i++)
            stringList.add(ItemsGrid.get(i).getNazev_zbozi() +";"+ ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
        gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
        super.onResume();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sortiment,
                container, false);

        //Nastaveni fontu
        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Medium.otf");
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

       // ArrayList<ClipData.Item> gridArray = new ArrayList<ClipData.Item>();
        gridView = (GridView) view.findViewById(R.id.gridViewSortiment);
        stringList = new ArrayList<String>();

        //volba kategorie

        tv1 = (TextView) view.findViewById(R.id.textPopular);
        tv2 = (TextView) view.findViewById(R.id.textKava);
        tv3 = (TextView) view.findViewById(R.id.textDobroty);
        tv4 = (TextView) view.findViewById(R.id.textAlkohol);
        tv5 = (TextView) view.findViewById(R.id.textOstatni);

        tv1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazPopularni();
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++) {
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi() +";"+ ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
                }
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
                //set all other classes gray color
                tv1.setTextColor(Color.parseColor("#9c2320"));
                tv2.setTextColor(Color.parseColor("#808080"));
                tv3.setTextColor(Color.parseColor("#808080"));
                tv4.setTextColor(Color.parseColor("#808080"));
                tv5.setTextColor(Color.parseColor("#808080"));
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Kava);
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++)
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi() +";"+ ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
                //set all other classes gray color
                tv1.setTextColor(Color.parseColor("#808080"));
                tv2.setTextColor(Color.parseColor("#9c2320"));
                tv3.setTextColor(Color.parseColor("#808080"));
                tv4.setTextColor(Color.parseColor("#808080"));
                tv5.setTextColor(Color.parseColor("#808080"));
                chosenCategory = Controller.CategoryID.Kava;
              }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Dobroty);
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++)
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi() +";"+ ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
                //set all other classes gray color
                tv1.setTextColor(Color.parseColor("#808080"));
                tv2.setTextColor(Color.parseColor("#808080"));
                tv3.setTextColor(Color.parseColor("#9c2320"));
                tv4.setTextColor(Color.parseColor("#808080"));
                tv5.setTextColor(Color.parseColor("#808080"));
                chosenCategory = Controller.CategoryID.Dobroty;
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Alkohol);
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++)
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi() +";"+ ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
                //set all other classes gray color
                tv1.setTextColor(Color.parseColor("#808080"));
                tv2.setTextColor(Color.parseColor("#808080"));
                tv3.setTextColor(Color.parseColor("#808080"));
                tv4.setTextColor(Color.parseColor("#9c2320"));
                tv5.setTextColor(Color.parseColor("#808080"));
                chosenCategory = Controller.CategoryID.Alkohol;
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Ostatni);
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++)
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi() +";"+ ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
                //set all other classes gray color
                tv1.setTextColor(Color.parseColor("#808080"));
                tv2.setTextColor(Color.parseColor("#808080"));
                tv3.setTextColor(Color.parseColor("#808080"));
                tv4.setTextColor(Color.parseColor("#808080"));
                tv5.setTextColor(Color.parseColor("#9c2320"));
                chosenCategory = Controller.CategoryID.Ostatni;
            }
        });

        //item click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();

                //EditDialog
                EditSortimentDialog alert = new EditSortimentDialog(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString(), new DialogFragmentDismissHandler());
                alert.show(fm, "Edit sortiment dialog");

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


        //pridani sortimentu
        ImageView addSortiment;
        addSortiment = (ImageView) view.findViewById(R.id.addSortiment);
        addSortiment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                AddSortimentDialog alert = new AddSortimentDialog(chosenCategory, new DialogFragmentDismissHandler());
                alert.show(fm, "Add sortiment dialog");
            }
        });



        return view;
    }

    /**
     * Dismiss handler
     */
    private class DialogFragmentDismissHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(chosenCategory);
            stringList.clear();
            for(int i=0; i < ItemsGrid.size();i++)
                stringList.add(ItemsGrid.get(i).getNazev_zbozi() +";"+ ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
            gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
            Log.d("aa","aa");

        }
    }

}