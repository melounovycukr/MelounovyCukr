package com.brnokavarna.melounovycukr.app.View;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import com.brnokavarna.melounovycukr.app.Exceptions.PrintException;
import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;
import com.brnokavarna.melounovycukr.app.Print.PrintActivity;
import com.brnokavarna.melounovycukr.app.R;
import com.starmicronics.stario.StarIOPortException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mpx on 29.4.2014.
 */
public class PrintTableDialog extends DialogFragment{

    private ArrayList<HashMap<String, String>> listStul;
    private SimpleAdapter adapter;
    private ListView listview;
    private List<Stul> itemsList;
    private HashMap<String, String> map;
    private Controller.TagKavy tagKavy;
    private int totalCost;
    private Context context;
    private Handler mHandler;
    private PrintActivity print;
    private boolean printProblemFlag = false;
    int stulID;

    public PrintTableDialog() {

    }


    /**
     * Odznaceni stolu po zaplaceni
     * @param id
     */
    public void OdznacStul(int id)
    {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.main_screen, null, true);

        ImageView table;

        switch(id){
            case 1:
                table = (ImageView) view.findViewById(R.id.table1);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 2:
                table = (ImageView) view.findViewById(R.id.table2);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 3:
                table = (ImageView) view.findViewById(R.id.table3);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 4:
                table = (ImageView) view.findViewById(R.id.table4);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 5:
                table = (ImageView) view.findViewById(R.id.table5);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 6:
                table = (ImageView) view.findViewById(R.id.table6);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 7:
                table = (ImageView) view.findViewById(R.id.table7);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 8:
                table = (ImageView) view.findViewById(R.id.table8);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 9:
                table = (ImageView) view.findViewById(R.id.table9);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 10:
                table = (ImageView) view.findViewById(R.id.table10);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 11:
                table = (ImageView) view.findViewById(R.id.table11);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 12:
                table = (ImageView) view.findViewById(R.id.table12);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 13:
                table = (ImageView) view.findViewById(R.id.table13);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 14:
                table = (ImageView) view.findViewById(R.id.bar1);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 15:
                table = (ImageView) view.findViewById(R.id.bar2);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 16:
                table = (ImageView) view.findViewById(R.id.bar3);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 17:
                table = (ImageView) view.findViewById(R.id.bar4);
                table.setImageResource(R.drawable.table_normal);
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_print_table, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);


        Bundle arguments = this.getArguments();
        this.stulID = arguments.getInt("table");
        //getDialog().getWindow().setLayout(200, 300);
        //getDialog().setTitle("Prodej - 15.4.2014");
        //getDialog().getWindow().setBackgroundDrawableResource(R.drawable.btn_blue_normal);

        listview = (ListView) view.findViewById(R.id.listview);
        listview.setDivider(null);

        // instance for print recipe
        this.context = this.getActivity();
        mHandler = new Handler();
        print = new PrintActivity(context, mHandler);

        listStul = new ArrayList<HashMap<String, String>>();
        listStul.clear();
        itemsList = ((MainActivity)getActivity()).getListOnePay();
        //itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(((MainActivity)getActivity()).getTableId());
        for(int i=0; i < itemsList.size();i++) {
            map = new HashMap<String, String>();
            String pom = ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(itemsList.get(i).getId_polozky()).getNazev_zbozi() + vypisDruhKavy(itemsList.get(i).getDruh_kavy());
            if(pom.length() >= 25){
                pom = pom.substring(0,24) + new String(Character.toChars(8230));

            }
            map.put("item", pom);
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

        TextView doneText = (TextView) view.findViewById(R.id.doneText);
        doneText.setTypeface(gothamLight);

        final ImageView done = (ImageView) view.findViewById(R.id.done);
        done.setOnClickListener(doneListener);

        done.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    done.setBackground(getResources().getDrawable(R.drawable.btn_grey_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    done.setBackground(getResources().getDrawable(R.drawable.btn_grey_normal));
                }
                return false;
            }
        });

        final ImageView print = (ImageView) view.findViewById(R.id.print);
        print.setOnClickListener(printListener);

        print.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    print.setBackground(getResources().getDrawable(R.drawable.btn_green_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    print.setBackground(getResources().getDrawable(R.drawable.btn_green_normal));
                }
                return false;
            }
        });

        TextView printText = (TextView) view.findViewById(R.id.printText);
        printText.setTypeface(gothamLight);

        TextView titleText = (TextView) view.findViewById(R.id.titleText);
        titleText.setText("Platba - stůl č." + stulID);
        titleText.setTypeface(gothamLight);

        return view;
    }

    View.OnClickListener doneListener = new View.OnClickListener() {
        public void onClick(View v) {

            for(int i=0; i < itemsList.size();i++) {
                for(int j=itemsList.get(i).getMnozstvi(); j>0;j--) {
                    if(itemsList.get(i).getDruh_kavy() == Controller.TagKavy.Keňa.ordinal()) {
                        if (((MainActivity) getActivity()).getOnePayFlag()) {
                            ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(33,
                                    itemsList.get(i).getId_polozky(), tagKavy.Keňa);
                        } else {
                            ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(stulID,
                                    itemsList.get(i).getId_polozky(), tagKavy.Keňa);
                        }
                    } else if(itemsList.get(i).getDruh_kavy() == Controller.TagKavy.Ethyopia.ordinal()) {
                        if (((MainActivity) getActivity()).getOnePayFlag()) {
                            ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(33,
                                    itemsList.get(i).getId_polozky(), tagKavy.Ethyopia);
                        } else {
                            ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(stulID,
                                    itemsList.get(i).getId_polozky(), tagKavy.Ethyopia);
                        }
                    } else {
                        if (((MainActivity) getActivity()).getOnePayFlag()) {
                            ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(33,
                                    itemsList.get(i).getId_polozky(), tagKavy.Zadna);
                        } else {
                            ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(stulID,
                                    itemsList.get(i).getId_polozky(), tagKavy.Zadna);
                        }
                    }

                }
            }

//            List<Stul> itemsList;
//            itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(((MainActivity)getActivity()).getTableId());
//            if(itemsList.size() < 1)
//                ((MainActivity) getActivity()).SetTableDefault (((MainActivity)getActivity()).getTableId());
            //((MainActivity) getActivity()).SetTableDefault (((MainActivity) getActivity()).getTableId());
            dismiss();
            Fragment mainScreenFragmentt = new MainScreen();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction ff = fragmentManager.beginTransaction();
            ff.replace(R.id.container, mainScreenFragmentt);
            ff.addToBackStack(null);
            ff.commit();
            //((MainActivity)getActivity()).ShowMainHideOthers();
        }
    };

    View.OnClickListener printListener = new View.OnClickListener() {
        public void onClick(View v) {

            Thread  temp = new Thread( new Runnable() {
                public void run() {

                    try {
                        // print recipe
                        print.printRecipePerTable(listStul);

                    } catch(PrintException e) {
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
                for(int i=0; i < itemsList.size();i++) {
                    for(int j=itemsList.get(i).getMnozstvi(); j>0;j--) {
                        if(itemsList.get(i).getDruh_kavy() == Controller.TagKavy.Keňa.ordinal()) {
                            if (((MainActivity) getActivity()).getOnePayFlag()) {
                                ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(33,
                                        itemsList.get(i).getId_polozky(), tagKavy.Keňa);
                            } else {
                                ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(stulID,
                                        itemsList.get(i).getId_polozky(), tagKavy.Keňa);
                            }
                        } else if(itemsList.get(i).getDruh_kavy() == Controller.TagKavy.Ethyopia.ordinal()) {
                            if (((MainActivity) getActivity()).getOnePayFlag()) {
                                ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(33,
                                        itemsList.get(i).getId_polozky(), tagKavy.Ethyopia);
                            } else {
                                ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(stulID,
                                        itemsList.get(i).getId_polozky(), tagKavy.Ethyopia);
                            }
                        } else {
                            if (((MainActivity) getActivity()).getOnePayFlag()) {
                                ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(33,
                                        itemsList.get(i).getId_polozky(), tagKavy.Zadna);
                            } else {
                                ((MainActivity) getActivity()).cont.ZaplatPolozkuStul(stulID,
                                        itemsList.get(i).getId_polozky(), tagKavy.Zadna);
                            }
                        }

                    }
                }
                //((MainActivity) getActivity()).SetTableDefault (((MainActivity) getActivity()).getTableId());
//                List<Stul> itemsList;
//                itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(((MainActivity)getActivity()).getTableId());
//                if(itemsList.size() < 1)
//                    ((MainActivity) getActivity()).SetTableDefault (((MainActivity)getActivity()).getTableId());
                dismiss();
                //((MainActivity) getActivity()).ShowMainHideOthers();
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
