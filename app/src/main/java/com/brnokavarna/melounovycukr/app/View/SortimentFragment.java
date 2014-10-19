package com.brnokavarna.melounovycukr.app.View;

/**
 * Created by mpx on 5.5.2014.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
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
import android.widget.BaseAdapter;
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

    private GridView gridView1;
    private GridView gridView2;
    private GridView gridView3;
    private GridView gridView4;
    private GridView gridView5;
    private List<Seznam> ItemsGridPopularni;
    private List<Seznam> ItemsGridKava;
    private List<Seznam> ItemsGridDobroty;
    private List<Seznam> ItemsGridAlkohol;
    private List<Seznam> ItemsGridOstatni;
    private List<String> stringListPopularni = new ArrayList<String>();
    private List<String> stringListKava = new ArrayList<String>();
    private List<String> stringListDobroty = new ArrayList<String>();
    private List<String> stringListAlkohol = new ArrayList<String>();
    private List<String> stringListOstatni = new ArrayList<String>();
    private TextView tv1, tv2, tv3, tv4, tv5;
    private Controller.CategoryID  chosenCategory = Controller.CategoryID.Kava;
    private ImageView addSortiment;


    public SortimentFragment() {
        }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("2");
        ItemsGridPopularni = ((MainActivity) getActivity()).cont.ZobrazPopularni();
        ItemsGridKava = ((MainActivity) getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Kava);
        ItemsGridDobroty = ((MainActivity) getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Dobroty);
        ItemsGridAlkohol = ((MainActivity) getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Alkohol);
        ItemsGridOstatni = ((MainActivity) getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Ostatni);
        stringListPopularni.clear();
        stringListKava.clear();
        stringListDobroty.clear();
        stringListAlkohol.clear();
        stringListOstatni.clear();

        for (int i = 0; i < ItemsGridPopularni.size(); i++) {
            stringListPopularni.add(ItemsGridPopularni.get(i).getNazev_zbozi() + ";" + ItemsGridPopularni.get(i).getKategorie_id() + "|" + ItemsGridPopularni.get(i).getId());
        }
        for (int i = 0; i < ItemsGridKava.size(); i++){
            stringListKava.add(ItemsGridKava.get(i).getNazev_zbozi() + ";" + ItemsGridKava.get(i).getKategorie_id() + "|" + ItemsGridKava.get(i).getId());
        }
        for(int i=0; i < ItemsGridDobroty.size();i++){
            stringListDobroty.add(ItemsGridDobroty.get(i).getNazev_zbozi() +";"+ ItemsGridDobroty.get(i).getKategorie_id() + "|" + ItemsGridDobroty.get(i).getId());
        }
        for(int i=0; i < ItemsGridAlkohol.size();i++){
            stringListAlkohol.add(ItemsGridAlkohol.get(i).getNazev_zbozi() +";"+ ItemsGridAlkohol.get(i).getKategorie_id() + "|" + ItemsGridAlkohol.get(i).getId());
        }
        for(int i=0; i < ItemsGridOstatni.size();i++){
            stringListOstatni.add(ItemsGridOstatni.get(i).getNazev_zbozi() +";"+ ItemsGridOstatni.get(i).getKategorie_id() + "|" + ItemsGridOstatni.get(i).getId());
        }

        gridView1.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListPopularni));
        gridView2.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListKava));
        gridView3.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListDobroty));
        gridView4.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListAlkohol));
        gridView5.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListOstatni));

        gridView1.setVisibility(View.VISIBLE);
        gridView2.setVisibility(View.GONE);
        gridView3.setVisibility(View.GONE);
        gridView4.setVisibility(View.GONE);
        gridView5.setVisibility(View.GONE);

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
        gridView1 = (GridView) view.findViewById(R.id.gridViewSortiment5);
        gridView2 = (GridView) view.findViewById(R.id.gridViewSortiment4);
        gridView3 = (GridView) view.findViewById(R.id.gridViewSortiment3);
        gridView4 = (GridView) view.findViewById(R.id.gridViewSortiment2);
        gridView5 = (GridView) view.findViewById(R.id.gridViewSortiment);

        //vraceni zpet do mainu
        final ImageView zpetImage;
        zpetImage = (ImageView) view.findViewById(R.id.backSortiment);
        zpetImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().onBackPressed();
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
                gridView1.setVisibility(View.VISIBLE);
                gridView2.setVisibility(View.GONE);
                gridView3.setVisibility(View.GONE);
                gridView4.setVisibility(View.GONE);
                gridView5.setVisibility(View.GONE);
                //set all other classes gray color
                tv1.setTextColor(Color.parseColor("#9c2320"));
                tv2.setTextColor(Color.parseColor("#808080"));
                tv3.setTextColor(Color.parseColor("#808080"));
                tv4.setTextColor(Color.parseColor("#808080"));
                tv5.setTextColor(Color.parseColor("#808080"));
                chosenCategory = Controller.CategoryID.Popularni;

                addSortiment.setVisibility(View.INVISIBLE);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gridView1.setVisibility(View.GONE);
                gridView2.setVisibility(View.VISIBLE);
                gridView3.setVisibility(View.GONE);
                gridView4.setVisibility(View.GONE);
                gridView5.setVisibility(View.GONE);
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
                gridView1.setVisibility(View.GONE);
                gridView2.setVisibility(View.GONE);
                gridView3.setVisibility(View.VISIBLE);
                gridView4.setVisibility(View.GONE);
                gridView5.setVisibility(View.GONE);
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
                gridView1.setVisibility(View.GONE);
                gridView2.setVisibility(View.GONE);
                gridView3.setVisibility(View.GONE);
                gridView4.setVisibility(View.VISIBLE);
                gridView5.setVisibility(View.GONE);
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
                gridView1.setVisibility(View.GONE);
                gridView2.setVisibility(View.GONE);
                gridView3.setVisibility(View.GONE);
                gridView4.setVisibility(View.GONE);
                gridView5.setVisibility(View.VISIBLE);
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
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();

                //EditDialog
                EditSortimentDialog alert = new EditSortimentDialog(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString(), new DialogFragmentDismissHandler());
                alert.show(fm, "Edit sortiment dialog");

            }
        });

        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();

                //EditDialog
                EditSortimentDialog alert = new EditSortimentDialog(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString(), new DialogFragmentDismissHandler());
                alert.show(fm, "Edit sortiment dialog");

            }
        });

        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();

                //EditDialog
                EditSortimentDialog alert = new EditSortimentDialog(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString(), new DialogFragmentDismissHandler());
                alert.show(fm, "Edit sortiment dialog");

            }
        });

        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();

                //EditDialog
                EditSortimentDialog alert = new EditSortimentDialog(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString(), new DialogFragmentDismissHandler());
                alert.show(fm, "Edit sortiment dialog");

            }
        });

        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();

                //EditDialog
                EditSortimentDialog alert = new EditSortimentDialog(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString(), new DialogFragmentDismissHandler());
                alert.show(fm, "Edit sortiment dialog");

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

            System.out.println("sutu1");
            System.out.println("sutu2" + chosenCategory);

                //ItemsGrid = ((MainActivity) getActivity()).cont.ZobrazPopularni();

            ItemsGridPopularni = ((MainActivity) getActivity()).cont.ZobrazPopularni();
            ItemsGridKava = ((MainActivity) getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Kava);
            ItemsGridDobroty = ((MainActivity) getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Dobroty);
            ItemsGridAlkohol = ((MainActivity) getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Alkohol);
            ItemsGridOstatni = ((MainActivity) getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Ostatni);

            stringListPopularni.clear();
                for (int i = 0; i < ItemsGridPopularni.size(); i++) {
                    stringListPopularni.add(ItemsGridPopularni.get(i).getNazev_zbozi() + ";" + ItemsGridPopularni.get(i).getKategorie_id() + "|" + ItemsGridPopularni.get(i).getId());
                }
                gridView1.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListPopularni));

                stringListKava.clear();
                for (int i = 0; i < ItemsGridKava.size(); i++){
                    stringListKava.add(ItemsGridKava.get(i).getNazev_zbozi() + ";" + ItemsGridKava.get(i).getKategorie_id() + "|" + ItemsGridKava.get(i).getId());
                }
                gridView2.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListKava));

                stringListDobroty.clear();
                for(int i=0; i < ItemsGridDobroty.size();i++){
                    stringListDobroty.add(ItemsGridDobroty.get(i).getNazev_zbozi() +";"+ ItemsGridDobroty.get(i).getKategorie_id() + "|" + ItemsGridDobroty.get(i).getId());
                }
                gridView3.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListDobroty));

                stringListAlkohol.clear();
                for(int i=0; i < ItemsGridAlkohol.size();i++){
                    stringListAlkohol.add(ItemsGridAlkohol.get(i).getNazev_zbozi() +";"+ ItemsGridAlkohol.get(i).getKategorie_id() + "|" + ItemsGridAlkohol.get(i).getId());
                }
                gridView4.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListAlkohol));

                stringListOstatni.clear();
                for(int i=0; i < ItemsGridOstatni.size();i++){
                    stringListOstatni.add(ItemsGridOstatni.get(i).getNazev_zbozi() +";"+ ItemsGridOstatni.get(i).getKategorie_id() + "|" + ItemsGridOstatni.get(i).getId());
                }
                gridView5.setAdapter(new CustomGridViewAdapterr(getActivity(),stringListOstatni));

//            else {
//                ItemsGrid = ((MainActivity) getActivity()).cont.ZobrazKategoriiSeznam(chosenCategory);
//                stringList.clear();
//                for (int i = 0; i < ItemsGrid.size(); i++)
//                    stringList.add(ItemsGrid.get(i).getNazev_zbozi() + ";" + ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
//                gridView.setAdapter(new CustomGridViewAdapter(getActivity(), stringList));
//            }

        }
    }

    public class CustomGridViewAdapterr extends BaseAdapter {
        private  List<String> itemsValues;
        private LayoutInflater inflater;

        public CustomGridViewAdapterr(Context context, List<String> itemsValues) {
            this.itemsValues = itemsValues;
            inflater = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            if (convertView == null) {

                convertView = inflater.inflate(R.layout.row_grid, null);
                holder = new ViewHolder();
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //setting fonts
            Typeface gothamMedium = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Medium.otf");
            holder.label = (TextView) convertView.findViewById(R.id.grid_item_label);
            holder.label.setTypeface(gothamMedium);
            System.out.println("position: " + position);

            // set value into textview
            if(itemsValues.get(position).contains(";"))
                holder.label.setText(itemsValues.get(position).substring(0, itemsValues.get(position).indexOf(";")));

            //store id grid_item_hidden_id
            holder.hidden = (TextView) convertView.findViewById(R.id.grid_item_hidden_id);
            if(itemsValues.get(position).contains("|")) {
                holder.hidden.setText(itemsValues.get(position).substring(itemsValues.get(position).indexOf("|")+1, itemsValues.get(position).length()));
            }

            // set image based on selected text
            holder.image = (ImageView) convertView.findViewById(R.id.grid_item_image);


            String items = itemsValues.get(position);
            //setting item background within category
            if(items.contains(";0"))
                holder.image.setImageResource(R.drawable.item_red);
            else if(items.contains(";1"))
                holder.image.setImageResource(R.drawable.item_pink);
            else if(items.contains(";2"))
                holder.image.setImageResource(R.drawable.item_green);
            else if(items.contains(";3"))
                holder.image.setImageResource(R.drawable.item_blue);

            return convertView;
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

    class ViewHolder {
        ImageView image;
        TextView label;
        TextView hidden;
    }

}