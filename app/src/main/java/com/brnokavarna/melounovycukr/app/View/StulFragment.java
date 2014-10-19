package com.brnokavarna.melounovycukr.app.View;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;
import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Seky on 6. 5. 2014.
 */
public class StulFragment extends Fragment {

    PayAllDialog payAllDialog;
    PayOneDialog payOneDialog;
    CoffeeDialog coffeeDialog;
    private GridView gridView1;
    private GridView gridView2;
    private GridView gridView3;
    private GridView gridView4;
    private GridView gridView5;
    private ArrayList<HashMap<String, String>> listStul;
    private SimpleAdapter adapter;
    private ListView listview;
    private List<Seznam> ItemsGridPopularni;
    private List<Seznam> ItemsGridKava;
    private List<Seznam> ItemsGridDobroty;
    private List<Seznam> ItemsGridAlkohol;
    private List<Seznam> ItemsGridOstatni;
    private List<Stul> itemsList;
    private HashMap<String, String> map;
    private List<String> stringListPopularni = new ArrayList<String>();
    private List<String> stringListKava = new ArrayList<String>();
    private List<String> stringListDobroty = new ArrayList<String>();
    private List<String> stringListAlkohol = new ArrayList<String>();
    private List<String> stringListOstatni = new ArrayList<String>();
    private TextView tv1, tv2, tv3, tv4, tv5;
    private Controller.CategoryID  chosenCategory = Controller.CategoryID.Kava;
    TextView tableNumberText;
    private Controller.TagKavy tagKavy;
    private int idAktPolozky;


    TextView firstPartOfList;


    /**
     * stul ID
     */
    private int stulID;

    public StulFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
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

        adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
        listview.setAdapter(adapter);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Function for deafult screen, showing popular items
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stul,
                container, false);

        final Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        final Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Book.otf");
        Typeface gothamMedium = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Medium.otf");
        TextView backText = (TextView) view.findViewById(R.id.backText);
        backText.setTypeface(gothamLight);

        TextView zapText = (TextView) view.findViewById(R.id.zaplatitText);
        zapText.setTypeface(gothamLight);
        TextView zapVseText = (TextView) view.findViewById(R.id.zaplatitVseText);
        zapVseText.setTypeface(gothamLight);
        tableNumberText = (TextView) view.findViewById(R.id.textTableNumber);
        tableNumberText.setTypeface(gothamLight);

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

        TextView listItemFirst;
        TextView listItemSecond;
        TextView listItemThird;

        LayoutInflater inflaterRow = getActivity().getLayoutInflater();
        View rowView;
        rowView = inflaterRow.inflate(R.layout.listview_row_stul, null, true);
        listItemFirst = (TextView) rowView.findViewById(R.id.listViewItemStulFirstText);
        listItemSecond = (TextView) rowView.findViewById(R.id.listViewItemStulSecondText);
        listItemThird = (TextView) rowView.findViewById(R.id.listViewItemStulThirdText);
        listItemFirst.setTypeface(gothamBook);
        listItemSecond.setTypeface(gothamBook);
        listItemThird.setTypeface(gothamBook);

        //grid

        ArrayList<ClipData.Item> gridArray = new ArrayList<ClipData.Item>();
        gridView1 = (GridView) view.findViewById(R.id.gridViewSortiment5);
        gridView2 = (GridView) view.findViewById(R.id.gridViewSortiment4);
        gridView3 = (GridView) view.findViewById(R.id.gridViewSortiment3);
        gridView4 = (GridView) view.findViewById(R.id.gridViewSortiment2);
        gridView5 = (GridView) view.findViewById(R.id.gridViewSortiment);




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
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Kava);

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
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Dobroty);

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
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Alkohol);

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
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(

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
            }
        });


        listview = (ListView) view.findViewById(R.id.viewStul);
        listStul = new ArrayList<HashMap<String, String>>();
        listStul.clear();
        listview.setDivider(null);


        //klikani v sortimentu vlevo
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Seznam pomPolozka= ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                        getText().toString()));
                int pomPolozkaID= Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString());
                idAktPolozky = pomPolozkaID;

                if(pomPolozka.getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    FragmentManager fm = ((MainActivity) getActivity()).getSupportFragmentManager();
                    coffeeDialog = new CoffeeDialog(stulID,pomPolozkaID, new DialogFragmentDismissHandler());
                    coffeeDialog.show(fm, "coffee dialog");
                }else {
                    ((MainActivity)getActivity()).cont.PridejPolozkuStul(stulID,Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                            getText().toString()),Controller.TagKavy.Zadna);
                }

                listStul.clear();
                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(stulID);
                for(int i=0; i < itemsList.size();i++) {
                    map = new HashMap<String, String>();
                    //if()
                    map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
                    map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
                    map.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                            ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");
                    listStul.add(map);
                }

                adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview.setAdapter(adapter);

            }
        });

        //klikani v sortimentu vlevo
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Seznam pomPolozka= ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                        getText().toString()));
                int pomPolozkaID= Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString());
                idAktPolozky = pomPolozkaID;

                if(pomPolozka.getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    FragmentManager fm = ((MainActivity) getActivity()).getSupportFragmentManager();
                    coffeeDialog = new CoffeeDialog(stulID,pomPolozkaID, new DialogFragmentDismissHandler());
                    coffeeDialog.show(fm, "coffee dialog");
                }else {
                    ((MainActivity)getActivity()).cont.PridejPolozkuStul(stulID,Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                            getText().toString()),Controller.TagKavy.Zadna);
                }

                listStul.clear();
                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(stulID);
                for(int i=0; i < itemsList.size();i++) {
                    map = new HashMap<String, String>();
                    //if()
                    map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
                    map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
                    map.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                            ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");
                    listStul.add(map);
                }

                adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview.setAdapter(adapter);

            }
        });

        //klikani v sortimentu vlevo
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Seznam pomPolozka= ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                        getText().toString()));
                int pomPolozkaID= Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString());
                idAktPolozky = pomPolozkaID;

                if(pomPolozka.getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    FragmentManager fm = ((MainActivity) getActivity()).getSupportFragmentManager();
                    coffeeDialog = new CoffeeDialog(stulID,pomPolozkaID, new DialogFragmentDismissHandler());
                    coffeeDialog.show(fm, "coffee dialog");
                }else {
                    ((MainActivity)getActivity()).cont.PridejPolozkuStul(stulID,Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                            getText().toString()),Controller.TagKavy.Zadna);
                }

                listStul.clear();
                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(stulID);
                for(int i=0; i < itemsList.size();i++) {
                    map = new HashMap<String, String>();
                    //if()
                    map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
                    map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
                    map.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                            ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");
                    listStul.add(map);
                }

                adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview.setAdapter(adapter);

            }
        });

        //klikani v sortimentu vlevo
        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Seznam pomPolozka= ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                        getText().toString()));
                int pomPolozkaID= Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString());
                idAktPolozky = pomPolozkaID;

                if(pomPolozka.getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    FragmentManager fm = ((MainActivity) getActivity()).getSupportFragmentManager();
                    coffeeDialog = new CoffeeDialog(stulID,pomPolozkaID, new DialogFragmentDismissHandler());
                    coffeeDialog.show(fm, "coffee dialog");
                }else {
                    ((MainActivity)getActivity()).cont.PridejPolozkuStul(stulID,Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                            getText().toString()),Controller.TagKavy.Zadna);
                }

                listStul.clear();
                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(stulID);
                for(int i=0; i < itemsList.size();i++) {
                    map = new HashMap<String, String>();
                    //if()
                    map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
                    map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
                    map.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                            ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");
                    listStul.add(map);
                }

                adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview.setAdapter(adapter);

            }
        });

        //klikani v sortimentu vlevo
        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Seznam pomPolozka= ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                        getText().toString()));
                int pomPolozkaID= Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString());
                idAktPolozky = pomPolozkaID;

                if(pomPolozka.getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    FragmentManager fm = ((MainActivity) getActivity()).getSupportFragmentManager();
                    coffeeDialog = new CoffeeDialog(stulID,pomPolozkaID, new DialogFragmentDismissHandler());
                    coffeeDialog.show(fm, "coffee dialog");
                }else {
                    ((MainActivity)getActivity()).cont.PridejPolozkuStul(stulID,Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                            getText().toString()),Controller.TagKavy.Zadna);
                }

                listStul.clear();
                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(stulID);
                for(int i=0; i < itemsList.size();i++) {
                    map = new HashMap<String, String>();
                    //if()
                    map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
                    map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
                    map.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                            ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");
                    listStul.add(map);
                }

                adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview.setAdapter(adapter);

            }
        });

        /**LISTVIEW**/
        //listView


        //for (int i = 0; i < values.length; ++i) {
          //  list.add(values[i]);
       // }
        //adapter = new StableArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, listStul);
        adapter = new SimpleAdapter(this.getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
        //TextView tv = (TextView)view.findViewById(R.id.listViewItemStulFirstText);
        //tv.setTypeface(gothamBook);
        listview.setAdapter(adapter);

        //list napravo
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                final HashMap<String, String> item = (HashMap<String, String>) parent.getItemAtPosition(position);
                String[] parts = item.get("item").split("_");
                int idPol = ((MainActivity) getActivity()).cont.ZobrazIDPolozkySeznamPodleNazvu(parts[0]);
                if(((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(idPol).getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    ((MainActivity)getActivity()).cont.OdstranPolozkuStul(stulID,idPol ,
                            Controller.TagKavy.valueOf(parts[1]));
                } else {
                    ((MainActivity)getActivity()).cont.OdstranPolozkuStul(stulID,idPol ,
                            Controller.TagKavy.Zadna);
                }


                listStul.clear();
                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(stulID);
                for(int i=0; i < itemsList.size();i++) {
                    map = new HashMap<String, String>();
                    map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
                    map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
                    map.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                            ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");
                    listStul.add(map);
                }

                adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview.setAdapter(adapter);

            }
        });

        //ruzova polozka
        // ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
        // imageView.setImageResource(R.drawable.item_pink);


        final ImageView zpetImage;
        zpetImage = (ImageView) view.findViewById(R.id.backStul);
        zpetImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //empty list not red
//                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(stulID);
//                if(itemsList.size() < 1)
//                    ((MainActivity) getActivity()).SetTableDefault (stulID);

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

        final ImageView zaplatit;
        zaplatit = (ImageView) view.findViewById(R.id.zaplatit);
        zaplatit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                payOneDialog = new PayOneDialog();
                Bundle bundle = new Bundle();
                bundle.putInt("table",stulID);
                payOneDialog.setArguments(bundle);
                payOneDialog.show(fm, "Pay one dialog");
            }
        });

        zaplatit.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    zaplatit.setBackground(getResources().getDrawable(R.drawable.btn_green_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    zaplatit.setBackground(getResources().getDrawable(R.drawable.btn_green_normal));
                }
                return false;
            }
        });

        final ImageView zaplatitVse;
        zaplatitVse = (ImageView) view.findViewById(R.id.zaplatitVse);
        zaplatitVse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                payAllDialog = new PayAllDialog();
                Bundle bundle = new Bundle();
                bundle.putInt("table",stulID);
                payAllDialog.setArguments(bundle);
                payAllDialog.show(fm, "Pay all dialog");
            }
        });

        zaplatitVse.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    zaplatitVse.setBackground(getResources().getDrawable(R.drawable.btn_green_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    zaplatitVse.setBackground(getResources().getDrawable(R.drawable.btn_green_normal));
                }
                return false;
            }
        });


        Bundle arguments = this.getArguments();
        this.stulID = arguments.getInt("table");
        tableNumberText.setText("Stůl č. " + this.stulID);


        listStul.clear();
        itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(this.stulID);
        for(int i=0; i < itemsList.size();i++) {
            map = new HashMap<String, String>();
            map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
            map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
            map.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                    ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");
            listStul.add(map);
            //stringList.add(ItemsGrid.get(i).getNazev_zbozi() + ";" + ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
        }
        adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
        listview.setAdapter(adapter);



        return view;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        public void doneMethod(View view){
            Toast.makeText(getActivity(), "it's done", Toast.LENGTH_LONG).show();
        }



    }


    /**
     * Nastaveni kliknuteho stolu
     */
    public void zobrazStul(int id){

/*
        map = new HashMap<String, String>();
                map.put("item", "pepa");
                map.put("amount", "2");
                map.put("price", "80kč");
                listStul.add(map);

                //listStul.add(((TextView) v.findViewById(R.id.grid_item_label)).getText().toString() + "   5     50kč");
                adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview.setAdapter(adapter);
*/
    }

    public int getStulID() {
        return stulID;
    }

    /**
     * Dismiss handler
     */
    private class DialogFragmentDismissHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

             listStul.clear();
            itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(stulID);
            for(int i=0; i < itemsList.size();i++) {
                map = new HashMap<String, String>();
                //if()
                map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
                map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
                map.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                        ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");
                listStul.add(map);
            }

            adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
            listview.setAdapter(adapter);



        }
    }

    private String vypisDruhKavy(int kava)
    {
        String pom;
        if (kava == Controller.TagKavy.Keňa.ordinal()){
            return "_Keňa";}
        else if (kava == Controller.TagKavy.Ethyopia.ordinal()){
            return "_Ethyopia";}
        return "";
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
