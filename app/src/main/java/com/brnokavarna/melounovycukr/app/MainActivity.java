package com.brnokavarna.melounovycukr.app;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.Model.MySQLiteHelper;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Stul;
import com.brnokavarna.melounovycukr.app.View.MainScreen;
import com.brnokavarna.melounovycukr.app.View.SortimentFragment;
import com.brnokavarna.melounovycukr.app.View.StulFragment;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private MainScreen mainScreenFragment;
    private SortimentFragment sortimentFragment;
    private StulFragment stulFragment;
    private RelativeLayout layoutMainScreen;
    private RelativeLayout layoutSortiment;
    private RelativeLayout layoutStul;
    public Controller cont;
    private int tableId;
    private List<Stul> listOnePay;
    private boolean flagOnePay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lock landscape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        cont = new Controller(this);
        System.out.println("1");
        //mainScreenFragment = new MainScreen();
        //layoutMainScreen = (RelativeLayout) findViewById(R.id.mainScreenFragment);



//        sortimentFragment = new SortimentFragment();
//        layoutSortiment = (RelativeLayout) findViewById(R.id.sortimentFragment);
//
//        stulFragment = new StulFragment();
//        layoutStul = (RelativeLayout) findViewById(R.id.stultFragment);

        //ShowMainHideOthers();

        Typeface gothamBook = Typeface.createFromAsset(getAssets(), "Gotham-Book.otf");
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView yourTextView = (TextView) findViewById(titleId);
        yourTextView.setTypeface(gothamBook);

        // add the custom view to the action bar
        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_item);
        TextView title = (TextView) actionBar.getCustomView().findViewById(R.id.title);
        title.setTypeface(gothamBook);


        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

        // Kava ------------------------------------------------------------------------------------
//        this.cont.PridejPolozkuSeznam(new Seznam(0,39,"Espresso",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(0,55,"Espresso Doppio",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(0,42,"Espresso Macchiato",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(0,45,"Cappuccino",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(0,60,"Flat white",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(0,55,"Caffé latte",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(0,50,"Latte Macchiato",false));
//
//        // Nealko ----------------------------------------------------------------------------------
//        this.cont.PridejPolozkuSeznam(new Seznam(3,39,"Sonnentor Darjeeling",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,39,"Sonnentor Earl Grey",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,39,"Sonnentor Sencha",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,39,"Sonnentor Sencha s citronovu trávou",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,39,"Sonnentor Jasmínový čaj",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,39,"Sonnentor Citrónová šťáva",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,39,"Sonnentor Květ Levandule",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,39,"Sonnentor Poupata růže",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,39,"Sonnentor Čokoládová máta",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,45,"Zabíječ",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,45,"Vanilkové nebe, chci tebe!",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,45,"Čaj od pouštních nomádů",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,45,"Pečené ovoce s kořením",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,49,"Hořká čokoláda",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,55,"Čokoláda z kozího mléka",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,38,"Malinová limonáda s tymiánem - 0,4l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,38,"Okurková limonáda s limetkou a mátou - 0,4l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,35,"Mošt jablko a rakytník - 0,2l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,35,"Mošt jablko a červený rybíz - 0,2l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,35,"Mošt jablko a angrešt - 0,2l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,35,"Mošt jablko a kdoule - 0,2l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,35,"Mošt jablko a zázvor - 0,2l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,36,"Coca cola",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,45,"Vanilková Coca Cola",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,36,"Tonic Kinley klasik",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,36,"Tonic Kinley zázvor",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,35,"Vincentka",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,15,"Neperlivá voda - 0,5l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,15,"Perlivá voda - 0,5l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,30,"Neperlivá voda - 1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,30,"Perlivá voda - 1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,59,"Lemur se závazky",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,59,"Zavřete oči, i když neodcházím",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(3,59,"Babiččina paruka",false));
//
//        // Vino ------------------------------------------------------------------------------------
//        this.cont.PridejPolozkuSeznam(new Seznam(2,40,"RF - Ryzlink vlašský - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,40,"RF - Veltlínské zelené - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,40,"RF - Chardonnay - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,280,"RF - Ryzlink vlašský - láhev",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,280,"RF - Veltlínské zelené - láhev",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,280,"RF - Chardonnay - láhev",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,50,"RF - Sauvignon - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,320,"RF - Sauvignon - láhev",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,50,"RF - Cabernet Moravia - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,320,"RF - Cabernet Moravia - láhev",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,46,"ZP - Ryzlink rýnský - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,320,"ZP - Ryzlink rýnský - láhev",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,46,"ZP - Müller Thurgau - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,320,"ZP - Müller Thurgau - láhev",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,46,"ZP - Frankovka rosé - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,320,"ZP - Frankovka rosé - láhev",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,46,"ZP - Frankovka klaret - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,320,"ZP - Frankovka klaret - láhev",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,38,"Ricossa Cortese Piemonte - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,38,"Ricossa Rosato Piemonte - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,38,"Ricossa Barbera Piemonte - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,19,"Veltlín zelený - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,24,"Muškát moravský - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,24,"Tramín červený - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,24,"Rulandské šedé - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,24,"Pálava - 0,1l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,21,"Frankovka rosé - 0,1l",false));
//
//        this.cont.PridejPolozkuSeznam(new Seznam(2,75,"Guilty lime - 0,3l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,75,"Bezinkový Spritz - 0,3l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,75,"Zahradní párty - 0,3l",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,60,"Třešňový likér z grappy",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,60,"Likér z citrónové kůry",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,28,"Svijany máz 11°",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,32,"Poutník 12°",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,55,"Svařené víno s levandulí",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,55,"Svařené víno s bílým pepřem",false));
//
//        this.cont.PridejPolozkuSeznam(new Seznam(2,58,"Jameson",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,58,"Tullamore Dew",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,105,"Jack Daniels (lim ed. 2011)",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,140,"Nikka All Malt",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,195,"The Glenlivet 18y",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,130,"Hennessy Fine Cognac",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,180,"Godet Poire au Cognac",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,48,"Cpt. Morgan spiced",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,48,"Havana white",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,50,"Havana especial",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,65,"Diplomatico Anejo",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,85,"Matusalem clasico",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,75,"Ti Rhum Mauricius",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,75,"Ti Rhum Vanille Mauricius",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,75,"Capitan Bucanero Elixir 7 viejo",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,95,"Legendario Elixir de Cuba",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,110,"Matusalem Gran Riserva 15",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,110,"Vanilla Cane",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,140,"Zacapa 23",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,50,"Finlandia",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,55,"Absolut Vanilla",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,55,"Absolut Raspberry",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,48,"Beefeater",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,84,"Beefeater 24",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,120,"Hendrick's gin",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,165,"Bitter Truth Sloe Berry",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,47,"Jagermeister",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,45,"Campari",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,50,"Metaxa 5*",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,65,"Metaxa 7*",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,60,"Bulas Porto tawny reserva",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,85,"Bulas Porto L.B.V. 2007",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,75,"Midori Melounový likér",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,65,"Absinth Bainsfather",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(2,45,"Domácí slivovička z Jevíčka",false));
//
//        // Dobroty ---------------------------------------------------------------------------------
//        this.cont.PridejPolozkuSeznam(new Seznam(1,45,"Řecké olivy naložené v oleji",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(1,45,"Domácí pražené mandle",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(1,50,"Domácí pražené para ořechy",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(1,58,"Pomazánka z česneku",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(1,63,"Pomazánka z šunky",false));
//        this.cont.PridejPolozkuSeznam(new Seznam(1,58,"Tapenáda z oliv",false));
//
//        cont.db.close();


        Log.d("lala", "lolo");
        Fragment mainScreenFragmentt = new MainScreen();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ff = fragmentManager.beginTransaction();
        Log.d("lala", "lulu");
        ff.replace(R.id.container, mainScreenFragmentt);
        ff.addToBackStack(null);
        ff.commit();
        Log.d("lala", "lele");

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

    /**
     * Zobrazi hlavni fragment a schova ostatni
     */
//    public void ShowMainHideOthers()
//    {
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.hide(sortimentFragment);
//        layoutSortiment.setVisibility(View.GONE);
//        ft.hide(stulFragment);
//        layoutStul.setVisibility(View.GONE);
//        //ft.detach(mainScreenFragment);
//       // ft.attach(mainScreenFragment);
//        ft.show(mainScreenFragment);
//        layoutMainScreen.setVisibility(View.VISIBLE);
//        ft.commit();
//
//    }
//
//    /**
//     * Zobrazi hlavni fragment a schova ostatni
//     */
//    public void ShowTableHideOthers()
//    {
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.hide(sortimentFragment);
//        layoutSortiment.setVisibility(View.GONE);
//        ft.hide(mainScreenFragment);
//        layoutMainScreen.setVisibility(View.GONE);
//        ft.show(stulFragment);
//        layoutStul.setVisibility(View.VISIBLE);
//        ft.commit();
//
//    }
//
//    /**
//     * Zobrazi hlavni fragment a schova ostatni
//     */
//    public void ShowSortimentHideOthers()
//    {
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.hide(stulFragment);
//        layoutStul.setVisibility(View.GONE);
//        ft.hide(mainScreenFragment);
//        layoutMainScreen.setVisibility(View.GONE);
//        ft.show(sortimentFragment);
//        layoutSortiment.setVisibility(View.VISIBLE);
//        ft.commit();
//
//    }

//    public void SetTableNumber(int id){
//        this.tableId = id;
//        StulFragment fragment = (StulFragment) getFragmentManager().findFragmentById(R.id.stultFragment);
//        fragment.zobrazStul(id);
//     }
//
//    public void SetTableDefault(int id)
//    {
//        MainScreen fragment = (MainScreen) getFragmentManager().findFragmentById(R.id.mainScreenFragment);
//        fragment.setTableNormalColor(id);
//    }

    public void setListOnePay(List<Stul> listOnePay) {
        this.listOnePay = listOnePay;
    }

    public List<Stul> getListOnePay() {
        return this.listOnePay;
    }

    public void setOnePayFlag(boolean flagOnePay) {
        this.flagOnePay = flagOnePay;
    }

    public boolean getOnePayFlag() {
        return this.flagOnePay;
    }

}
