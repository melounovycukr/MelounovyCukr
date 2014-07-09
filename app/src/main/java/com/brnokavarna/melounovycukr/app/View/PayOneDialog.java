package com.brnokavarna.melounovycukr.app.View;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;
import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mpx on 29.4.2014.
 */
public class PayOneDialog extends DialogFragment{

    private ArrayList<HashMap<String, String>> listStul;
    private ArrayList<HashMap<String, String>> listStul2;
    private SimpleAdapter adapter;
    private SimpleAdapter adapter2;
    private ListView listview;
    private ListView listview2;
    private List<Stul> itemsList;
    private List<Stul> itemsList2;
    private HashMap<String, String> map;
    private HashMap<String, String> map2;
    private Controller.TagKavy tagKavy;
    private int totalCost;
    TextView overallCostText;

    public PayOneDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_one, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        //getDialog().getWindow().setLayout(200, 300);
        //getDialog().setTitle("Prodej - 15.4.2014");
        //getDialog().getWindow().setBackgroundDrawableResource(R.drawable.btn_blue_normal);
        itemsList2 = new ArrayList<Stul>();

        listview = (ListView) view.findViewById(R.id.listview);
        listview2 = (ListView) view.findViewById(R.id.listview2);
        listview.setDivider(null);
        listview2.setDivider(null);

        listStul = new ArrayList<HashMap<String, String>>();
        listStul2 = new ArrayList<HashMap<String, String>>();
        listStul.clear();
        listStul2.clear();
        itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(((MainActivity)getActivity()).getTableId());
        for(int i=0; i < itemsList.size();i++) {
            map = new HashMap<String, String>();
            map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
            map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
            map.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                    ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");
            listStul.add(map);
            //listStul2.add(map);
            //stringList.add(ItemsGrid.get(i).getNazev_zbozi() + ";" + ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
        }
        adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
        listview.setAdapter(adapter);
        //listview2.setAdapter(adapter);

        //list nalevo
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                final HashMap<String, String> item = (HashMap<String, String>) parent.getItemAtPosition(position);
                String[] parts = item.get("item").split(" ");
                int idPol = ((MainActivity) getActivity()).cont.ZobrazIDPolozkySeznamPodleNazvu(parts[0]);
                if(((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(idPol).getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    ((MainActivity)getActivity()).cont.OdstranPolozkuStul(((MainActivity)getActivity()).getTableId(),idPol ,
                            Controller.TagKavy.valueOf(parts[2]));

                    ((MainActivity)getActivity()).cont.PridejPolozkuStul(33,idPol,Controller.TagKavy.valueOf(parts[2]));
                } else {
                    ((MainActivity)getActivity()).cont.OdstranPolozkuStul(((MainActivity)getActivity()).getTableId(), idPol,
                            Controller.TagKavy.Zadna);

                    ((MainActivity)getActivity()).cont.PridejPolozkuStul(33,idPol,Controller.TagKavy.Zadna);
                }


                /*itemsList2.add(new Stul(((MainActivity) getActivity()).cont.ZobrazIDPolozkySeznamPodleNazvu(item.get("item")),((MainActivity)getActivity()).getTableId(),
                        Controller.TagKavy.Zadna.ordinal(),1));
                map2 = new HashMap<String, String>();
                map2.put("item", item.get("item"));
                //map2.put("amount", item.get("amount"));
                map2.put("price", item.get("price"));
                int flag = 0;
                Iterator<HashMap<String, String>> itr = listStul2.iterator();
                while(itr.hasNext()) {
                    HashMap<String, String> map = itr.next();
                    if(map.get("item").equals(item.get("item"))) {
                        map.put("amount", String.valueOf(Integer.parseInt(map.get("amount"))+1));
                        String[] parts = map.get("price").split(" "); // to get rid of " Kč"
                        String price = parts[0];
                        map.put("price", String.valueOf(Integer.parseInt(price)*Integer.parseInt(map.get("amount"))));
                        flag = 1;
                    }
                }
                if(flag == 0) {
                    map2.put("item", item.get("item"));
                    map2.put("amount", "1");
                    map2.put("price", item.get("price"));
                }*/
                /*map2.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
                map2.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                        ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");*/
                /*listStul2.add(map2);
                adapter2 = new SimpleAdapter(getActivity(), listStul2, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview2.setAdapter(adapter2);*/

                listStul.clear();
                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(((MainActivity)getActivity()).getTableId());
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

                listStul2.clear();
                itemsList2 = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(33);
                int pomCost2 = (int)((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(idPol).getCena();
                totalCost += pomCost2;
                for(int i=0; i < itemsList2.size();i++) {
                    map2 = new HashMap<String, String>();
                    map2.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList2.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList2.get(i).getDruh_kavy()));
                    map2.put("amount", String.valueOf(itemsList2.get(i).getMnozstvi()));
                    int pomCost = (int)((MainActivity)getActivity()).cont.
                            ZobrazPolozkuSeznam(itemsList2.get(i).getId_polozky()).getCena();

                    map2.put("price", String.valueOf(pomCost*itemsList2.get(i).getMnozstvi()) + " Kč");
                    listStul2.add(map2);
                }
                adapter2 = new SimpleAdapter(getActivity(), listStul2, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview2.setAdapter(adapter2);
                overallCostText.setText(totalCost + " Kč");

            }
        });

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                final HashMap<String, String> item = (HashMap<String, String>) parent.getItemAtPosition(position);
                String[] parts = item.get("item").split(" ");
                int idPol = ((MainActivity) getActivity()).cont.ZobrazIDPolozkySeznamPodleNazvu(parts[0]);
                if(((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(idPol).getKategorie_id() == Controller.CategoryID.Kava.ordinal()) {
                    ((MainActivity)getActivity()).cont.OdstranPolozkuStul(33,idPol ,Controller.TagKavy.valueOf(parts[2]));

                    ((MainActivity)getActivity()).cont.PridejPolozkuStul(((MainActivity)getActivity()).getTableId(),idPol,Controller.TagKavy.valueOf(parts[2]));
                } else {
                    ((MainActivity)getActivity()).cont.OdstranPolozkuStul(33,idPol,Controller.TagKavy.Zadna);

                    ((MainActivity)getActivity()).cont.PridejPolozkuStul(((MainActivity)getActivity()).getTableId(),idPol,Controller.TagKavy.Zadna);
                }

                /*itemsList2.add(new Stul(((MainActivity) getActivity()).cont.ZobrazIDPolozkySeznamPodleNazvu(item.get("item")),((MainActivity)getActivity()).getTableId(),
                        Controller.TagKavy.Zadna.ordinal(),1));
                map2 = new HashMap<String, String>();
                map2.put("item", item.get("item"));
                //map2.put("amount", item.get("amount"));
                map2.put("price", item.get("price"));
                int flag = 0;
                Iterator<HashMap<String, String>> itr = listStul2.iterator();
                while(itr.hasNext()) {
                    HashMap<String, String> map = itr.next();
                    if(map.get("item").equals(item.get("item"))) {
                        map.put("amount", String.valueOf(Integer.parseInt(map.get("amount"))+1));
                        String[] parts = map.get("price").split(" "); // to get rid of " Kč"
                        String price = parts[0];
                        map.put("price", String.valueOf(Integer.parseInt(price)*Integer.parseInt(map.get("amount"))));
                        flag = 1;
                    }
                }
                if(flag == 0) {
                    map2.put("item", item.get("item"));
                    map2.put("amount", "1");
                    map2.put("price", item.get("price"));
                }*/
                /*map2.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
                map2.put("price", String.valueOf((int)((MainActivity)getActivity()).cont.
                        ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi()) + " Kč");*/
                /*listStul2.add(map2);
                adapter2 = new SimpleAdapter(getActivity(), listStul2, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview2.setAdapter(adapter2);*/

                listStul.clear();
                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(((MainActivity)getActivity()).getTableId());
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

                int pomCost2 = (int)((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(idPol).getCena();
                totalCost -= pomCost2;

                listStul2.clear();
                itemsList2 = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(33);
                for(int i=0; i < itemsList2.size();i++) {
                    map2 = new HashMap<String, String>();
                    map2.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList2.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList2.get(i).getDruh_kavy()));
                    map2.put("amount", String.valueOf(itemsList2.get(i).getMnozstvi()));
                    int pomCost = (int)((MainActivity)getActivity()).cont.
                            ZobrazPolozkuSeznam(itemsList2.get(i).getId_polozky()).getCena();
                    map2.put("price", String.valueOf(pomCost*itemsList2.get(i).getMnozstvi()) + " Kč");
                    listStul2.add(map2);
                }
                adapter2 = new SimpleAdapter(getActivity(), listStul2, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
                listview2.setAdapter(adapter2);
                overallCostText.setText(totalCost + " Kč");

            }
        });

        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Book.otf");

        TextView overallText = (TextView) view.findViewById(R.id.overallText);
        overallText.setTypeface(gothamBook);

        overallCostText = (TextView) view.findViewById(R.id.overallCostText);
        overallCostText.setText(totalCost + " Kč");
        overallCostText.setTypeface(gothamBook);

        TextView backText = (TextView) view.findViewById(R.id.backText);
        backText.setTypeface(gothamLight);

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

        final ImageView pay = (ImageView) view.findViewById(R.id.pay);
        pay.setOnClickListener(payListener);

        pay.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    pay.setBackground(getResources().getDrawable(R.drawable.btn_green_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    pay.setBackground(getResources().getDrawable(R.drawable.btn_green_normal));
                }
                return false;
            }
        });

        TextView payText = (TextView) view.findViewById(R.id.payText);
        payText.setTypeface(gothamLight);

        TextView titleText = (TextView) view.findViewById(R.id.titleText);
        titleText.setText("Platba - stůl č.8");
        titleText.setTypeface(gothamLight);

        return view;
    }

    View.OnClickListener backListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Back", Toast.LENGTH_LONG).show();
            for (int i = 0; i < itemsList2.size(); i++) {
                for (int j = itemsList2.get(i).getMnozstvi(); j > 0; j--) {
                    System.out.println("bbbb " + i + " " + j);
                    if(itemsList2.get(i).getDruh_kavy() == Controller.TagKavy.Keňa.ordinal()) {
                        ((MainActivity) getActivity()).cont.OdstranPolozkuStul(33, itemsList2.get(i).getId_polozky(), tagKavy.Keňa);
                        ((MainActivity) getActivity()).cont.PridejPolozkuStul(((MainActivity) getActivity()).getTableId(), itemsList2.get(i).getId_polozky(), tagKavy.Keňa);
                    } else if(itemsList2.get(i).getDruh_kavy() == Controller.TagKavy.Ethyopia.ordinal()) {
                        ((MainActivity) getActivity()).cont.OdstranPolozkuStul(33, itemsList2.get(i).getId_polozky(), tagKavy.Ethyopia);
                        ((MainActivity) getActivity()).cont.PridejPolozkuStul(((MainActivity) getActivity()).getTableId(), itemsList2.get(i).getId_polozky(), tagKavy.Ethyopia);
                    } else {
                        ((MainActivity) getActivity()).cont.OdstranPolozkuStul(33, itemsList2.get(i).getId_polozky(), tagKavy.Zadna);
                        ((MainActivity) getActivity()).cont.PridejPolozkuStul(((MainActivity) getActivity()).getTableId(), itemsList2.get(i).getId_polozky(), tagKavy.Zadna);
                    }

                }
            }
            dismiss();
        }
    };

    View.OnClickListener payListener = new View.OnClickListener() {
        public void onClick(View v) {
            ((MainActivity)getActivity()).setListOnePay(itemsList2);
            ((MainActivity)getActivity()).setOnePayFlag(true);
            FragmentManager fm = (getActivity()).getSupportFragmentManager();
            PrintTableDialog alert = new PrintTableDialog();
            alert.show(fm, "Print table dialog");
            dismiss();
        }
    };


    public boolean onTouchEvent(MotionEvent event)
    {

        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            System.out.println("TOuch outside the dialog ******************** ");
            for (int i = 0; i < itemsList2.size(); i++) {
                for (int j = itemsList2.get(i).getMnozstvi(); j > 0; j--) {
                    System.out.println("bbbb " + i + " " + j);
                    ((MainActivity) getActivity()).cont.OdstranPolozkuStul(33, itemsList2.get(i).getId_polozky(), tagKavy.Zadna);
                    ((MainActivity) getActivity()).cont.PridejPolozkuStul(((MainActivity) getActivity()).getTableId(), itemsList2.get(i).getId_polozky(), tagKavy.Zadna);
                }
            }
            this.dismiss();
        }
        return false;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // safety check
        if (getDialog() == null)
            return;

        int dialogWidth = 860; // specify a value here
        int dialogHeight = 650; // specify a value here

        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

        // ... other stuff you want to do in your onStart() method
    }


    private String vypisDruhKavy(int kava)
    {

        if (kava == Controller.TagKavy.Keňa.ordinal())
            return " - Keňa";
        else if (kava == Controller.TagKavy.Ethyopia.ordinal())
            return " - Ethyopia";

        return "";
    }

    /*public void onResume()
    {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(800, 500);
        window.setGravity(Gravity.CENTER);
        //TODO:
    }*/

   /* public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNameDialogListener activity = (EditNameDialogListener) getActivity();
            activity.onFinishEditDialog(mEditText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }*/

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
