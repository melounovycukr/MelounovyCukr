package com.brnokavarna.melounovycukr.app.View;

import android.app.Activity;
import android.app.Fragment;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private GridView gridView;
    private ArrayList<HashMap<String, String>> listStul;
    private SimpleAdapter adapter;
    private ListView listview;
    private List<Seznam> ItemsGrid;
    private List<Stul> itemsList;
    private HashMap<String, String> map;
    private List<String> stringList;
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
        System.out.println("muuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazPopularni();
        stringList.clear();
        listStul.clear();
        for(int i=0; i < ItemsGrid.size();i++)
            stringList.add(ItemsGrid.get(i).getNazev_zbozi() +";"+ ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
        gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
        adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
        listview.setAdapter(adapter);
        super.onResume();
    }

    @Override
    public void onPause() {
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
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
        TextView backText = (TextView) view.findViewById(R.id.backText);
        backText.setTypeface(gothamLight);

        TextView zapText = (TextView) view.findViewById(R.id.zaplatitText);
        zapText.setTypeface(gothamLight);
        TextView zapVseText = (TextView) view.findViewById(R.id.zaplatitVseText);
        zapVseText.setTypeface(gothamLight);
        tableNumberText = (TextView) view.findViewById(R.id.textTableNumber);
        tableNumberText.setTypeface(gothamLight);

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


        listview = (ListView) view.findViewById(R.id.viewStul);
        listStul = new ArrayList<HashMap<String, String>>();
        listStul.clear();
        listview.setDivider(null);


        //klikani v sortimentu vlevo
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Seznam pomPolozka= ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).
                        getText().toString()));
                int pomPolozkaID= Integer.parseInt(((TextView) v.findViewById(R.id.grid_item_hidden_id)).getText().toString());
                idAktPolozky = pomPolozkaID;

                if(pomPolozka.getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    FragmentManager fm = ((MainActivity) getActivity()).getSupportFragmentManager();
                    coffeeDialog = new CoffeeDialog(pomPolozkaID, new DialogFragmentDismissHandler());
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
                String[] parts = item.get("item").split(" ");
                int idPol = ((MainActivity) getActivity()).cont.ZobrazIDPolozkySeznamPodleNazvu(parts[0]);
                if(((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(idPol).getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    ((MainActivity)getActivity()).cont.OdstranPolozkuStul(stulID,idPol ,
                            Controller.TagKavy.valueOf(parts[2]));
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


        ImageView zpetImage;
        zpetImage = (ImageView) view.findViewById(R.id.backStul);
        zpetImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //empty list not red
                if(itemsList.size() < 1)
                    ((MainActivity) getActivity()).SetTableDefault (stulID);
                ((MainActivity)getActivity()).ShowMainHideOthers();
            }
        });

        ImageView zaplatit;
        zaplatit = (ImageView) view.findViewById(R.id.zaplatit);
        zaplatit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                payOneDialog = new PayOneDialog();
                payOneDialog.show(fm, "Pay one dialog");
            }
        });

        ImageView zaplatitVse;
        zaplatitVse = (ImageView) view.findViewById(R.id.zaplatitVse);
        zaplatitVse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                payAllDialog = new PayAllDialog();
                payAllDialog.show(fm, "Pay all dialog");
            }
        });

        //gridArray.add(new ClipData.Item(pinkIcon,"test"));



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
        this.stulID = id;
        tableNumberText.setText("Stůl č. " + id);

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
        //TADy pak udelas to naplneni z DB..novym adapterem

        listStul.clear();
        itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(id);
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

        if (kava == Controller.TagKavy.Keňa.ordinal())
            return " - Keňa";
        else if (kava == Controller.TagKavy.Ethyopia.ordinal())
            return " - Ethyopia";

        return "";
    }
}
