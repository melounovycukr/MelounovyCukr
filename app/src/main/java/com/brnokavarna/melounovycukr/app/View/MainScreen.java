package com.brnokavarna.melounovycukr.app.View;

/**
 * Created by mpx on 5.5.2014.
 */
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;
import com.brnokavarna.melounovycukr.app.R;

import java.util.List;

public class MainScreen extends Fragment {

    private int tableId;
    private View globa;
    private Fragment mFragment;

    View.OnClickListener listener;
    /*OnHeadlineSelectedListener mCallback;

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }*/

    @Override
    public void onResume()
    {
        //setting occupied tables to red
        System.out.println("3");
        List<Stul> itemsList;
        for(int i = 1; i <= 17; i++)
        {
            itemsList = ((MainActivity)getActivity()).cont.ZobrazVsechnyPolozkyStul(i);
            if(itemsList.size() > 0)
                setTableRedColor(i);
        }
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_screen,
                container, false);

        System.out.println("lalalala");
        Log.d("su","tu");
        mFragment = this;

        Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Book.otf");

        // Set font to tables and buttons
        TextView table1Text = (TextView) view.findViewById(R.id.table1Text);
        table1Text.setTypeface(gothamBook);
        TextView table2Text = (TextView) view.findViewById(R.id.table2Text);
        table2Text.setTypeface(gothamBook);
        TextView table3Text = (TextView) view.findViewById(R.id.table3Text);
        table3Text.setTypeface(gothamBook);
        TextView table4Text = (TextView) view.findViewById(R.id.table4Text);
        table4Text.setTypeface(gothamBook);
        TextView table5Text = (TextView) view.findViewById(R.id.table5Text);
        table5Text.setTypeface(gothamBook);
        TextView table6Text = (TextView) view.findViewById(R.id.table6Text);
        table6Text.setTypeface(gothamBook);
        TextView table7Text = (TextView) view.findViewById(R.id.table7Text);
        table7Text.setTypeface(gothamBook);
        TextView table8Text = (TextView) view.findViewById(R.id.table8Text);
        table8Text.setTypeface(gothamBook);
        TextView table9Text = (TextView) view.findViewById(R.id.table9Text);
        table9Text.setTypeface(gothamBook);
        TextView table10Text = (TextView) view.findViewById(R.id.table10Text);
        table10Text.setTypeface(gothamBook);
        TextView table11Text = (TextView) view.findViewById(R.id.table11Text);
        table11Text.setTypeface(gothamBook);
        TextView table12Text = (TextView) view.findViewById(R.id.table12Text);
        table12Text.setTypeface(gothamBook);
        TextView table13Text = (TextView) view.findViewById(R.id.table13Text);
        table13Text.setTypeface(gothamBook);
        TextView bar4Text1 = (TextView) view.findViewById(R.id.bar4Text1);
        bar4Text1.setTypeface(gothamBook);
        TextView bar4Text2 = (TextView) view.findViewById(R.id.bar4Text2);
        bar4Text2.setTypeface(gothamBook);
        TextView bar3Text1 = (TextView) view.findViewById(R.id.bar3Text1);
        bar3Text1.setTypeface(gothamBook);
        TextView bar3Text2 = (TextView) view.findViewById(R.id.bar3Text2);
        bar3Text2.setTypeface(gothamBook);
        TextView bar2Text1 = (TextView) view.findViewById(R.id.bar2Text1);
        bar2Text1.setTypeface(gothamBook);
        TextView bar2Text2 = (TextView) view.findViewById(R.id.bar2Text2);
        bar2Text2.setTypeface(gothamBook);
        TextView bar1Text1 = (TextView) view.findViewById(R.id.bar1Text1);
        bar1Text1.setTypeface(gothamBook);
        TextView bar1Text2 = (TextView) view.findViewById(R.id.bar1Text2);
        bar1Text2.setTypeface(gothamBook);
        TextView goodsText = (TextView) view.findViewById(R.id.goodsText);
        goodsText.setTypeface(gothamBook);
        TextView takingText = (TextView) view.findViewById(R.id.takingText);
        takingText.setTypeface(gothamBook);

        final ImageView table1 = (ImageView) view.findViewById(R.id.table1);
        table1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 1;
                table1.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",1);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table2 = (ImageView) view.findViewById(R.id.table2);
        table2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 2;
                table2.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",2);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table3 = (ImageView) view.findViewById(R.id.table3);
        table3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 3;
                table3.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",3);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table4 = (ImageView) view.findViewById(R.id.table4);
        table4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 4;
                table4.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",4);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table5 = (ImageView) view.findViewById(R.id.table5);
        table5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 5;
                table5.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",5);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table6 = (ImageView) view.findViewById(R.id.table6);
        table6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 6;
                table6.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",6);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table7 = (ImageView) view.findViewById(R.id.table7);
        table7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 7;
                table7.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",7);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table8 = (ImageView) view.findViewById(R.id.table8);
        table8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 8;
                table8.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",8);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table9 = (ImageView) view.findViewById(R.id.table9);
        table9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 9;
                table9.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",9);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table10 = (ImageView) view.findViewById(R.id.table10);
        table10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 10;
                table10.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",10);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table11 = (ImageView) view.findViewById(R.id.table11);
        table11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 11;
                table11.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",11);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table12 = (ImageView) view.findViewById(R.id.table12);
        table12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 12;
                table12.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",12);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView table13 = (ImageView) view.findViewById(R.id.table13);
        table13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 13;
                table13.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",13);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView bar1 = (ImageView) view.findViewById(R.id.bar1);
        bar1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 14;
                bar1.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",14);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView bar2 = (ImageView) view.findViewById(R.id.bar2);
        bar2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 15;
                bar2.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",15);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView bar3 = (ImageView) view.findViewById(R.id.bar3);
        bar3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 16;
                bar3.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",16);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView bar4 = (ImageView) view.findViewById(R.id.bar4);
        bar4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tableId = 17;
                bar4.setImageResource(R.drawable.table_active);

                Bundle bundle = new Bundle();
                bundle.putInt("table",17);
                StulFragment myFragment = new StulFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                myFragment.setArguments(bundle);
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        final ImageView sortiment = (ImageView) view.findViewById(R.id.goods);
        sortiment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SortimentFragment myFragment = new SortimentFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(mFragment.getId(),myFragment,"myFragment");
                fragmentTransaction.addToBackStack("myFragment");
                fragmentTransaction.commit();
            }
        });

        sortiment.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    sortiment.setBackground(getResources().getDrawable(R.drawable.btn_blue_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    sortiment.setBackground(getResources().getDrawable(R.drawable.btn_blue_normal));
                }
                return false;
            }
        });

        final ImageView taking = (ImageView) view.findViewById(R.id.taking);
        taking.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = ((MainActivity)getActivity()).getSupportFragmentManager();
                TakingDialog takingDialog = new TakingDialog();
                takingDialog.show(fm, "fragment_edit_name");
            }
        });

        taking.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    taking.setBackground(getResources().getDrawable(R.drawable.btn_blue_hover));
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    taking.setBackground(getResources().getDrawable(R.drawable.btn_blue_normal));
                }
                return false;
            }
        });

        return view;
    }

    /**
     * Set table as occupied
     * @param id
     */
    public void setTableRedColor(int id)
    {
        ImageView table;
        switch(id){
            case 1:
                table = (ImageView) getView().findViewById(R.id.table1);
                table.setImageResource(R.drawable.table_active);
                break;
            case 2:
                table = (ImageView) getView().findViewById(R.id.table2);
                table.setImageResource(R.drawable.table_active);
                break;
            case 3:
                table = (ImageView) getView().findViewById(R.id.table3);
                table.setImageResource(R.drawable.table_active);
                break;
            case 4:
                table = (ImageView) getView().findViewById(R.id.table4);
                table.setImageResource(R.drawable.table_active);
                break;
            case 5:
                table = (ImageView) getView().findViewById(R.id.table5);
                table.setImageResource(R.drawable.table_active);
                break;
            case 6:
                table = (ImageView) getView().findViewById(R.id.table6);
                table.setImageResource(R.drawable.table_active);
                break;
            case 7:
                table = (ImageView) getView().findViewById(R.id.table7);
                table.setImageResource(R.drawable.table_active);
                break;
            case 8:
                table = (ImageView) getView().findViewById(R.id.table8);
                table.setImageResource(R.drawable.table_active);
                break;
            case 9:
                table = (ImageView) getView().findViewById(R.id.table9);
                table.setImageResource(R.drawable.table_active);
                break;
            case 10:
                table = (ImageView) getView().findViewById(R.id.table10);
                table.setImageResource(R.drawable.table_active);
                break;
            case 11:
                table = (ImageView) getView().findViewById(R.id.table11);
                table.setImageResource(R.drawable.table_active);
                break;
            case 12:
                table = (ImageView) getView().findViewById(R.id.table12);
                table.setImageResource(R.drawable.table_active);
                break;
            case 13:
                table = (ImageView) getView().findViewById(R.id.table13);
                table.setImageResource(R.drawable.table_active);
                break;
            case 14:
                table = (ImageView) getView().findViewById(R.id.bar1);
                table.setImageResource(R.drawable.table_active);
                break;
            case 15:
                table = (ImageView) getView().findViewById(R.id.bar2);
                table.setImageResource(R.drawable.table_active);
                break;
            case 16:
                table = (ImageView) getView().findViewById(R.id.bar3);
                table.setImageResource(R.drawable.table_active);
                break;
            case 17:
                table = (ImageView) getView().findViewById(R.id.bar4);
                table.setImageResource(R.drawable.table_active);
                break;
        }
    }



    /**
     * Setting table to normal color
     * @param id
     */
    public void setTableNormalColor(int id)
    {
        ImageView table;
        switch(id){
            case 1:
                table = (ImageView) getView().findViewById(R.id.table1);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 2:
                table = (ImageView) getView().findViewById(R.id.table2);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 3:
                table = (ImageView) getView().findViewById(R.id.table3);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 4:
                table = (ImageView) getView().findViewById(R.id.table4);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 5:
                table = (ImageView) getView().findViewById(R.id.table5);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 6:
                table = (ImageView) getView().findViewById(R.id.table6);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 7:
                table = (ImageView) getView().findViewById(R.id.table7);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 8:
                table = (ImageView) getView().findViewById(R.id.table8);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 9:
                table = (ImageView) getView().findViewById(R.id.table9);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 10:
                table = (ImageView) getView().findViewById(R.id.table10);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 11:
                table = (ImageView) getView().findViewById(R.id.table11);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 12:
                table = (ImageView) getView().findViewById(R.id.table12);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 13:
                table = (ImageView) getView().findViewById(R.id.table13);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 14:
                table = (ImageView) getView().findViewById(R.id.bar1);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 15:
                table = (ImageView) getView().findViewById(R.id.bar2);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 16:
                table = (ImageView) getView().findViewById(R.id.bar3);
                table.setImageResource(R.drawable.table_normal);
                break;
            case 17:
                table = (ImageView) getView().findViewById(R.id.bar4);
                table.setImageResource(R.drawable.table_normal);
                break;
        }
    }
}
