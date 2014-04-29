package com.brnokavarna.melounovycukr.app;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Controller test = new Controller(this);
/*
        test.PridejPolozkuSeznam(new Seznam(Controller.CategoryID.Alkohol.ordinal(),250,"Fernet", false ));
        test.PridejPolozkuSeznam(new Seznam(Controller.CategoryID.Alkohol.ordinal(),50,"Pivo", true ));
        test.PridejPolozkuSeznam(new Seznam(Controller.CategoryID.Kava.ordinal(),50,"Preso", true ));

        test.PridejPolozkuStul(1,1, Controller.TagKavy.Zadna);
        test.PridejPolozkuStul(1,1, Controller.TagKavy.Zadna);
        test.PridejPolozkuStul(2,3, Controller.TagKavy.Ethyopie);
        test.PridejPolozkuStul(1,3, Controller.TagKavy.Kena);


        test.ZaplatPolozkuStul(1,1, Controller.TagKavy.Zadna);

        */

        TextView txtView;
        txtView = (TextView)findViewById(R.id.txtView);
        txtView.setText(test.ZobrazPopularni().toString()+ "\n" +"IIIII"+
                test.ZobrazKategoriiSeznam(Controller.CategoryID.Alkohol).toString()+ "\n"+"IIIII"+
                test.ZobrazVsechnyPolozkyStul(1).toString()+ "\n"+"IIIII"+
                test.ZobrazTrzbu().toString() );



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //testicecek

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

}

