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
import com.brnokavarna.melounovycukr.app.Model.Tabulky.CelkovaTrzba;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;
import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mpx on 29.4.2014.
 */
public class PayAllDialog extends DialogFragment{

    private ArrayList<HashMap<String, String>> listStul;
    private SimpleAdapter adapter;
    private ListView listview;
    private List<Stul> itemsList;
    private HashMap<String, String> map;
    private Controller.TagKavy tagKavy;
    private int totalCost;

    public PayAllDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_all, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //getDialog().getWindow().setLayout(200, 300);
        //getDialog().setTitle("Prodej - 15.4.2014");
        //getDialog().getWindow().setBackgroundDrawableResource(R.drawable.btn_blue_normal);

        listview = (ListView) view.findViewById(R.id.listview);
        listview.setDivider(null);

        listStul = new ArrayList<HashMap<String, String>>();
        listStul.clear();
        itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(((MainActivity)getActivity()).getTableId());
        for(int i=0; i < itemsList.size();i++) {
            map = new HashMap<String, String>();
            map.put("item", ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy()));
            map.put("amount", String.valueOf(itemsList.get(i).getMnozstvi()));
            int pomCost = (int)((MainActivity)getActivity()).cont.
                    ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getCena()*itemsList.get(i).getMnozstvi();
            totalCost += pomCost;
            map.put("price", String.valueOf(pomCost) + " Kč");
            listStul.add(map);
            //stringList.add(ItemsGrid.get(i).getNazev_zbozi() + ";" + ItemsGrid.get(i).getKategorie_id() + "|" + ItemsGrid.get(i).getId());
        }
        adapter = new SimpleAdapter(getActivity(), listStul, R.layout.listview_row_stul, new String[] {"item", "amount", "price"},new int[]{R.id.listViewItemStulFirstText, R.id.listViewItemStulSecondText, R.id.listViewItemStulThirdText});
        listview.setAdapter(adapter);

        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Book.otf");

        TextView overallText = (TextView) view.findViewById(R.id.overallText);
        overallText.setTypeface(gothamBook);

        TextView overallCostText = (TextView) view.findViewById(R.id.overallCostText);
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
        titleText.setText("Platba - stůl č." + ((MainActivity)getActivity()).getTableId());
        titleText.setTypeface(gothamLight);

        return view;
    }

    View.OnClickListener backListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Done", Toast.LENGTH_LONG).show();
            dismiss();

        }
    };

    View.OnClickListener payListener = new View.OnClickListener() {
        public void onClick(View v) {
            ((MainActivity)getActivity()).setListOnePay(itemsList);
            ((MainActivity)getActivity()).setOnePayFlag(false);
            FragmentManager fm = (getActivity()).getSupportFragmentManager();
            PrintTableDialog alert = new PrintTableDialog();
            alert.show(fm, "Print table dialog");
            dismiss();
        }
    };

    @Override
    public void onStart()
    {
        super.onStart();

        // safety check
        if (getDialog() == null)
            return;

        int dialogWidth = 430; // specify a value here
        int dialogHeight = 650; // specify a value here

        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);

        // ... other stuff you want to do in your onStart() method
    }

    private String vypisDruhKavy(int kava)
    {

        if (kava == Controller.TagKavy.Keňa.ordinal())
            return "_Keňa";
        else if (kava == Controller.TagKavy.Ethyopia.ordinal())
            return "_Ethyopia";

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
