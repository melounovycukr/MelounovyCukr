package com.brnokavarna.melounovycukr.app.View;

import android.app.Activity;
import android.app.Fragment;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Seky on 6. 5. 2014.
 */
public class StulFragment extends Fragment {

    private GridView gridView;
    private ArrayList<HashMap<String, String>> listStul;
    private SimpleAdapter adapter;
    private ListView listview;
    private List<Seznam> ItemsGrid;
    private HashMap<String, String> map;
    private List<String> stringList;

    public StulFragment() {

    }

    @Override
    public void onResume() {
        ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazPopularni();
        stringList.clear();
        for(int i=0; i < ItemsGrid.size();i++)
            stringList.add(ItemsGrid.get(i).getNazev_zbozi());
        gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
        super.onResume();
    }

    /**
     * Function for deafult screen, showing popular items
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stul,
                container, false);

        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        TextView backText = (TextView) view.findViewById(R.id.backText);
        backText.setTypeface(gothamLight);

        TextView zapText = (TextView) view.findViewById(R.id.zaplatitText);
        zapText.setTypeface(gothamLight);
        TextView zapVseText = (TextView) view.findViewById(R.id.zaplatitVseText);
        zapVseText.setTypeface(gothamLight);

        //grid

        ArrayList<ClipData.Item> gridArray = new ArrayList<ClipData.Item>();
        gridView = (GridView) view.findViewById(R.id.gridViewSortiment);
        stringList = new ArrayList<String>();



        //volba kategorie
        TextView tv1, tv2, tv3, tv4, tv5;
        tv1 = (TextView) view.findViewById(R.id.textView);
        tv2 = (TextView) view.findViewById(R.id.textView2);
        tv3 = (TextView) view.findViewById(R.id.textView3);
        tv4 = (TextView) view.findViewById(R.id.textView4);
        tv5 = (TextView) view.findViewById(R.id.textView5);


        //reakce na kliknuti
        tv1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazPopularni();
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++)
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi());
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Kava);
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++)
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi());
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Dobroty);
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++)
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi());
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Alkohol);
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++)
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi());
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsGrid = ((MainActivity)getActivity()).cont.ZobrazKategoriiSeznam(Controller.CategoryID.Ostatni);
                stringList.clear();
                for(int i=0; i < ItemsGrid.size();i++)
                    stringList.add(ItemsGrid.get(i).getNazev_zbozi());
                gridView.setAdapter(new CustomGridViewAdapter(getActivity(),stringList));
            }
        });


        listview = (ListView) view.findViewById(R.id.viewStul);
        listStul = new ArrayList<HashMap<String, String>>();


        //klikani v sortimentu vlevo
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(
                        getActivity(),
                        ((TextView) v.findViewById(R.id.grid_item_label))
                                .getText().toString(), Toast.LENGTH_SHORT
                ).show();

                map = new HashMap<String, String>();
                map.put("item", ((TextView) v.findViewById(R.id.grid_item_label)).getText().toString());
                map.put("amount", "2");
                map.put("price", "250 Kc");
                listStul.add(map);

                //listStul.add(((TextView) v.findViewById(R.id.grid_item_label)).getText().toString() + "   5     50kč");
                adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview.setAdapter(adapter);

            }
        });

        /**LISTVIEW**/
        //listView


        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };


        //for (int i = 0; i < values.length; ++i) {
          //  list.add(values[i]);
       // }
        //adapter = new StableArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, listStul);
        adapter = new SimpleAdapter(this.getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
        listview.setAdapter(adapter);

        //list napravo
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /*@Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                                Toast.makeText(mCtx, "Je libo " + item.toString() + "?", Toast.LENGTH_LONG).show();
                                list.remove(item);

            }*/

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final HashMap<String, String> item = (HashMap<String, String>) parent.getItemAtPosition(position);
                view.animate().setDuration(100).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Je libo " + item.toString() + "?", Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            };
                        });
            }


        });

        //ruzova polozka
        // ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
        // imageView.setImageResource(R.drawable.item_pink);


        ImageView zpetImage;
        zpetImage = (ImageView) view.findViewById(R.id.backStul);
        zpetImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).ShowMainHideOthers();
            }
        });

        ImageView zaplatit;
        zaplatit = (ImageView) view.findViewById(R.id.zaplatit);
        zaplatit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        ImageView zaplatitVse;
        zaplatitVse = (ImageView) view.findViewById(R.id.zaplatitVse);
        zaplatitVse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                PayAllDialog alert = new PayAllDialog();
                alert.show(fm, "Pay all dialog");
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
}
