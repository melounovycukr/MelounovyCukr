package com.brnokavarna.melounovycukr.app.View;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mpx on 29.4.2014.
 */
public class PrintTableDialog extends DialogFragment{

    public PrintTableDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_print_table, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //getDialog().getWindow().setLayout(200, 300);
        //getDialog().setTitle("Prodej - 15.4.2014");
        //getDialog().getWindow().setBackgroundDrawableResource(R.drawable.btn_blue_normal);

        final ListView listview = (ListView) view.findViewById(R.id.listview);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
        //listview.setBackgroundColor(Color.BLACK);
        listview.setDivider(null);

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
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(100).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Je libo " + item.toString() + "?", Toast.LENGTH_LONG).show();
                                //list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            };
                        });
            }


        });

        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Book.otf");

        TextView overallText = (TextView) view.findViewById(R.id.overallText);
        overallText.setTypeface(gothamBook);

        TextView overallCostText = (TextView) view.findViewById(R.id.overallCostText);
        overallCostText.setTypeface(gothamBook);

        TextView doneText = (TextView) view.findViewById(R.id.doneText);
        doneText.setTypeface(gothamLight);

        ImageView done = (ImageView) view.findViewById(R.id.done);
        done.setOnClickListener(doneListener);

        ImageView print = (ImageView) view.findViewById(R.id.print);
        print.setOnClickListener(printListener);

        TextView printText = (TextView) view.findViewById(R.id.printText);
        printText.setTypeface(gothamLight);

        TextView titleText = (TextView) view.findViewById(R.id.titleText);
        titleText.setText("Platba - stůl č.8");
        titleText.setTypeface(gothamLight);

        return view;
    }

    View.OnClickListener doneListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Done", Toast.LENGTH_LONG).show();

        }
    };

    View.OnClickListener printListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Az to hanz dodela, tak mozna neco vytisku :D", Toast.LENGTH_LONG).show();
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
