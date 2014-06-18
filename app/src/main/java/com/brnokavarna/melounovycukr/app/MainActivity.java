package com.brnokavarna.melounovycukr.app;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    private int tableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lock landscape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        MySQLiteHelper db = new MySQLiteHelper(this);
        cont = new Controller(this);

        mainScreenFragment = new MainScreen();
        layoutMainScreen = (RelativeLayout) findViewById(R.id.mainScreenFragment);

        sortimentFragment = new SortimentFragment();
        layoutSortiment = (RelativeLayout) findViewById(R.id.sortimentFragment);

        stulFragment = new StulFragment();
        layoutStul = (RelativeLayout) findViewById(R.id.stultFragment);

        ShowMainHideOthers();

        Typeface gothamBook = Typeface.createFromAsset(getAssets(), "Gotham-Book.otf");
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView yourTextView = (TextView) findViewById(titleId);
        yourTextView.setTypeface(gothamBook);

        // add the custom view to the action bar
        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_item);
        TextView odhlasit = (TextView) actionBar.getCustomView().findViewById(R.id.odhlasit);
        TextView title = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        odhlasit.setTypeface(gothamBook);
        title.setTypeface(gothamBook);

        odhlasit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Nooooooooooooooooooooooooo!!!!!!!!!! Don't do this!!! Don't leave meeee!!!!!!",
                        Toast.LENGTH_LONG).show();
            }
        });

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);




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

    public int getTableId(){
        System.out.println(tableId+"ccccccccccccccc");
        return tableId;
    }

    public void printMethod(View view){
        Toast.makeText(MainActivity.this, "Az to hanz dodela, ta mozna neco vytisku :D", Toast.LENGTH_LONG).show();
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

    /**
     * Zobrazi hlavni fragment a schova ostatni
     */
    public void ShowTableHideOthers()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(sortimentFragment);
        layoutSortiment.setVisibility(View.GONE);
        ft.hide(mainScreenFragment);
        layoutMainScreen.setVisibility(View.GONE);
        ft.show(stulFragment);
        layoutStul.setVisibility(View.VISIBLE);
        ft.commit();

    }

    /**
     * Zobrazi hlavni fragment a schova ostatni
     */
    public void ShowSortimentHideOthers()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(stulFragment);
        layoutStul.setVisibility(View.GONE);
        ft.hide(mainScreenFragment);
        layoutMainScreen.setVisibility(View.GONE);
        ft.show(sortimentFragment);
        layoutSortiment.setVisibility(View.VISIBLE);
        ft.commit();

    }

    public void SetTableNumber(int id){

        this.tableId = id;
        StulFragment fragment = (StulFragment) getFragmentManager().findFragmentById(R.id.stultFragment);
        fragment.zobrazStul(id);
     }

}
