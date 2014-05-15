package com.brnokavarna.melounovycukr.app;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.Model.MySQLiteHelper;
import com.brnokavarna.melounovycukr.app.View.EditNameDialog;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.View.MainScreen;
import com.brnokavarna.melounovycukr.app.View.SortimentFragment;
import com.brnokavarna.melounovycukr.app.View.StulFragment;


public class MainActivity extends ActionBarActivity {

    private MainScreen mainScreenFragment;
    private SortimentFragment sortimentFragment;
    private StulFragment stulFragment;
    private RelativeLayout layoutMainScreen;
    private RelativeLayout layoutSortiment;
    private RelativeLayout layoutStul;
    public Controller cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lock landscape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        mainScreenFragment = new MainScreen();
        layoutMainScreen = (RelativeLayout) findViewById(R.id.mainScreenFragment);

        sortimentFragment = new SortimentFragment();
        layoutSortiment = (RelativeLayout) findViewById(R.id.sortimentFragment);

        stulFragment = new StulFragment();
        layoutStul = (RelativeLayout) findViewById(R.id.stultFragment);



        //hide other fragments
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(stulFragment);
        layoutStul.setVisibility(View.GONE);
        ft.show(sortimentFragment);
        layoutSortiment.setVisibility(View.GONE);
        ft.commit();

        MySQLiteHelper db = new MySQLiteHelper(this);
        cont = new Controller(this);
        cont.PridejPolozkuSeznam(new Seznam(Controller.CategoryID.Alkohol.ordinal(),50,"hhh",true));
        cont.PridejPolozkuSeznam(new Seznam(Controller.CategoryID.Alkohol.ordinal(),80,"Fernet",true));

        Typeface tf = Typeface.createFromAsset(getAssets(), "Gotham-Book.otf");
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView yourTextView = (TextView) findViewById(titleId);
        yourTextView.setTypeface(tf);

        // add the custom view to the action bar
        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_item);
        TextView odhlasit = (TextView) actionBar.getCustomView().findViewById(R.id.odhlasit);
        TextView title = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        odhlasit.setTypeface(tf);
        title.setTypeface(tf);

        odhlasit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Nooooooooooooooooooooooooo!!!!!!!!!! Don't do this!!! Don't leave meeee!!!!!!",
                        Toast.LENGTH_LONG).show();
            }
        });

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

        // Set font to tables and buttons
        TextView table1Text = (TextView) findViewById(R.id.table1Text);
        table1Text.setTypeface(tf);
        TextView table2Text = (TextView) findViewById(R.id.table2Text);
        table2Text.setTypeface(tf);
        TextView table3Text = (TextView) findViewById(R.id.table3Text);
        table3Text.setTypeface(tf);
        TextView table4Text = (TextView) findViewById(R.id.table4Text);
        table4Text.setTypeface(tf);
        TextView table5Text = (TextView) findViewById(R.id.table5Text);
        table5Text.setTypeface(tf);
        TextView table6Text = (TextView) findViewById(R.id.table6Text);
        table6Text.setTypeface(tf);
        TextView table7Text = (TextView) findViewById(R.id.table7Text);
        table7Text.setTypeface(tf);
        TextView table8Text = (TextView) findViewById(R.id.table8Text);
        table8Text.setTypeface(tf);
        TextView table9Text = (TextView) findViewById(R.id.table9Text);
        table9Text.setTypeface(tf);
        TextView table10Text = (TextView) findViewById(R.id.table10Text);
        table10Text.setTypeface(tf);
        TextView table11Text = (TextView) findViewById(R.id.table11Text);
        table11Text.setTypeface(tf);
        TextView table12Text = (TextView) findViewById(R.id.table12Text);
        table12Text.setTypeface(tf);
        TextView table13Text = (TextView) findViewById(R.id.table13Text);
        table13Text.setTypeface(tf);
        TextView bar4Text1 = (TextView) findViewById(R.id.bar4Text1);
        bar4Text1.setTypeface(tf);
        TextView bar4Text2 = (TextView) findViewById(R.id.bar4Text2);
        bar4Text2.setTypeface(tf);
        TextView bar3Text1 = (TextView) findViewById(R.id.bar3Text1);
        bar3Text1.setTypeface(tf);
        TextView bar3Text2 = (TextView) findViewById(R.id.bar3Text2);
        bar3Text2.setTypeface(tf);
        TextView bar2Text1 = (TextView) findViewById(R.id.bar2Text1);
        bar2Text1.setTypeface(tf);
        TextView bar2Text2 = (TextView) findViewById(R.id.bar2Text2);
        bar2Text2.setTypeface(tf);
        TextView bar1Text1 = (TextView) findViewById(R.id.bar1Text1);
        bar1Text1.setTypeface(tf);
        TextView bar1Text2 = (TextView) findViewById(R.id.bar1Text2);
        bar1Text2.setTypeface(tf);
        TextView goodsText = (TextView) findViewById(R.id.goodsText);
        goodsText.setTypeface(tf);
        TextView takingText = (TextView) findViewById(R.id.takingText);
        takingText.setTypeface(tf);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_settings);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item= menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return true;
    }
    /*public View onCreateActionView(MenuItem forItem) {
        // Inflate the action view to be shown on the action bar.
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.action_provider, null);
        ImageView button = (ImageView) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something...
            }
        });
        return view;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tableMark(View view){
        int tableId = view.getId();
        ImageView table = (ImageView) findViewById(tableId);
        table.setImageResource(R.drawable.table_active);

        //fragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(mainScreenFragment);
        layoutMainScreen.setVisibility(View.GONE);
        ft.hide(mainScreenFragment);
        layoutMainScreen.setVisibility(View.GONE);
        ft.show(sortimentFragment);
        layoutStul.setVisibility(View.VISIBLE);
        ft.commit();
    }

    public void createTakingDialog(View view) {
        FragmentManager fm = getSupportFragmentManager();
        EditNameDialog editNameDialog = new EditNameDialog();
        editNameDialog.show(fm, "fragment_edit_name");
        /*FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        EditNameDialog newFragment = new EditNameDialog();
        newFragment.show(ft, "dialog");*/
    }

    public void printMethod(View view){
        Toast.makeText(MainActivity.this, "Az to hanz dodela, ta mozna neco vytisku :D", Toast.LENGTH_LONG).show();
    }

    public void sortimentDialog(View view){
        // for Seky nejvetsi borec na svete :D
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.hide(mainScreenFragment);
        layoutMainScreen.setVisibility(View.GONE);
        ft.show(sortimentFragment);
        layoutSortiment.setVisibility(View.VISIBLE);
        System.out.println("hihihi");
        ft.commit();
    }

    /**
     * Zobrazi hlavni fragment a schova ostatni
     */
    public void ShowMainHideOthers()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(sortimentFragment);
        layoutSortiment.setVisibility(View.GONE);
        ft.hide(stulFragment);
        layoutStul.setVisibility(View.GONE);
        ft.show(mainScreenFragment);
        layoutMainScreen.setVisibility(View.VISIBLE);
        ft.commit();

    }



}
