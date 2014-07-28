package com.brnokavarna.melounovycukr.app.View;

/**
 * Created by mpx on 5.5.2014.
 */
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.List;

public class SortimentFragment extends Fragment {

    private GridView gridView;
    private List<Seznam> ItemsGrid;
    private List<String> stringList;
    private TextView tv1, tv2, tv3, tv4, tv5;
    private Controller.CategoryID  chosenCategory = Controller.CategoryID.Kava;
    private ImageView addSortiment;


    public SortimentFragment() {
        }

    @Override
    public void onResume() {
        super.onResume();

        ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazPopularni();
        stringList.clear();
        for(int i=0; i < ItemsGrid.size();i++)
            stringList.add(ItemsGrid.get(i).getNazev_zbozi() +";"+ ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
        gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));

        tv1.setTextColor(Color.parseColor("#9c2320"));
        tv2.setTextColor(Color.parseColor("#808080"));
        tv3.setTextColor(Color.parseColor("#808080"));
        tv4.setTextColor(Color.parseColor("#808080"));
        tv5.setTextColor(Color.parseColor("#808080"));

        addSortiment.setVisibility(View.INVISIBLE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sortiment,
                container, false);

        //Nastaveni fontu
        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        Typeface gothamMedium = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Medium.otf");
        TextView backText = (TextView) view.findViewById(R.id.backText);
        backText.setTypeface(gothamLight);
        TextView tempText = (TextView) view.findViewById(R.id.textPopular);
        tempText.setTypeface(gothamMedium);
        tempText = (TextView) view.findViewById(R.id.textKava);
        tempText.setTypeface(gothamMedium);
        tempText = (TextView) view.findViewById(R.id.textAlkohol);
        tempText.setTypeface(gothamMedium);
        tempText = (TextView) view.findViewById(R.id.textDobroty);
        tempText.setTypeface(gothamMedium);
        tempText = (TextView) view.findViewById(R.id.textOstatni);
        tempText.setTypeface(gothamMedium);
        TextView addText = (TextView) view.findViewById(R.id.addText);
        addText.setTypeface(gothamLight);

        //grid

       // ArrayList<ClipData.Item> gridArray = new ArrayList<ClipData.Item>();
        gridView = (GridView) view.findViewById(R.id.gridViewSortiment);
        stringList = new ArrayList<String>();

        //vraceni zpet do mainu
        final ImageView zpetImage;
        zpetImage = (ImageView) view.findViewById(R.id.backSortiment);
        zpetImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).ShowMainHideOthers();
            }
        });

        zpetImage.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    zpetImage.setBackground(getResources().getDrawable(R.drawable.btn_grey_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    zpetImage.setBackground(getResources().getDrawable(R.drawable.btn_grey_normal));
                }
                return false;
            }
        });

        //pridani sortimentu
        addSortiment = (ImageView) view.findViewById(R.id.addSortiment);
        addSortiment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                AddSortimentDialog alert = new AddSortimentDialog(chosenCategory, new DialogFragmentDismissHandler());
                alert.show(fm, "Add sortiment dialog");
            }
        });

        addSortiment.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    addSortiment.setBackground(getResources().getDrawable(R.drawable.btn_green_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    addSortiment.setBackground(getResources().getDrawable(R.drawable.btn_green_normal));
                }
                return false;
            }
        });


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

                addSortiment.setVisibility(View.INVISIBLE);
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

                addSortiment.setVisibility(View.VISIBLE);
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

                addSortiment.setVisibility(View.VISIBLE);
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

                addSortiment.setVisibility(View.VISIBLE);
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

                addSortiment.setVisibility(View.VISIBLE);
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

        }
    }

}