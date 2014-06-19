package com.brnokavarna.melounovycukr.app.View;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.Exceptions.PrintException;
import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.CelkovaTrzba;
import com.brnokavarna.melounovycukr.app.Print.PrintActivity;
import com.brnokavarna.melounovycukr.app.R;
import com.starmicronics.stario.StarIOPortException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by mpx on 29.4.2014.
 */
public class TakingDialog extends DialogFragment{

    private Context context;
    private PrintActivity print;
    private Handler mHandler;
    private ArrayList<HashMap<String, String>> listStul;
    private SimpleAdapter adapter;
    private ListView listview;
    private List<CelkovaTrzba> itemsList;
    private HashMap<String, String> map;
    private Controller.TagKavy tagKavy;
    private boolean printProblemFlag = false;

    public TakingDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // instance for print recipe
        this.context = this.getActivity();
        mHandler = new Handler();
        print = new PrintActivity(context, mHandler);


        // get items from db
        listview = (ListView) view.findViewById(R.id.listview);
        listview.setDivider(null);

        listStul = new ArrayList<HashMap<String, String>>();
        listStul.clear();
        itemsList = ((MainActivity)getActivity()).cont.ZobrazTrzbu();
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

        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        TextView doneText = (TextView) view.findViewById(R.id.doneText);
        doneText.setTypeface(gothamLight);

        ImageView done = (ImageView) view.findViewById(R.id.done);
        done.setOnClickListener(doneListener);

        ImageView print = (ImageView) view.findViewById(R.id.print);
        print.setOnClickListener(printListener);

        TextView printText = (TextView) view.findViewById(R.id.printText);
        printText.setTypeface(gothamLight);

        TextView titleText = (TextView) view.findViewById(R.id.titleText);
        titleText.setText("Prodej - 15.4.2014");
        titleText.setTypeface(gothamLight);

        return view;




    }


    View.OnClickListener doneListener = new View.OnClickListener() {

        public void onClick(View v) {

            Toast.makeText(getActivity(), "Done", Toast.LENGTH_LONG).show();

            ((MainActivity) getActivity()).cont.VymazTrzbu();
            dismiss();
        }
    };

    View.OnClickListener printListener = new View.OnClickListener() {
        public void onClick(View v) {

            Toast.makeText(getActivity(), "Print", Toast.LENGTH_LONG).show();

            Thread  temp = new Thread( new Runnable() {
                public void run() {

                    try {
                        // print recipe
                        print.printRecipePerDay(listStul);

                    } catch(PrintException e) {
                        System.out.println(e+"eeeeeeeeeeeeee\n");
                        printProblemFlag = true;
                    }
                }
            });
            temp.start();
            try {
                temp.join();
            }catch (InterruptedException e)
            {}

            if(printProblemFlag == false) {
                // remove items
                ((MainActivity) getActivity()).cont.VymazTrzbu();
                dismiss();
                ((MainActivity) getActivity()).ShowMainHideOthers();
            }
            printProblemFlag = false;
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
