package com.brnokavarna.melounovycukr.app.View;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.brnokavarna.melounovycukr.app.Controller.Controller;
import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.R;
/**
 * Created by mpx on 29.4.2014.
 */
public class CoffeeDialog extends DialogFragment{
    private EditText sortimentNameEditText;
    private EditText sortimentCostEditText;
    private Controller.CategoryID chosenCategory;
    private Handler handler;
    int polozkaID;
    public CoffeeDialog() {
        // Empty constructor required for DialogFragment
    }
    //cons
    public CoffeeDialog(int id, Handler h)
    {
        this.polozkaID = id;
        this.handler = h;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coffee, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Book.otf");
        ImageView back = (ImageView) view.findViewById(R.id.back);
        back.setOnClickListener(printListener);
        TextView backText = (TextView) view.findViewById(R.id.backText);
        backText.setTypeface(gothamLight);
        TextView titleText = (TextView) view.findViewById(R.id.titleText);
        titleText.setText("Vyberte kávu");
        titleText.setTypeface(gothamLight);
        ImageView ethyopie = (ImageView) view.findViewById(R.id.Ethyopie);
        ethyopie.setOnClickListener(ethyopieListener);
        TextView ethyopieText = (TextView) view.findViewById(R.id.EthyopieText);
        ethyopieText.setTypeface(gothamBook);
        ImageView kena = (ImageView) view.findViewById(R.id.Kena);
        kena.setOnClickListener(kenaListener);
        TextView kenaText = (TextView) view.findViewById(R.id.KenaText);
        kenaText.setTypeface(gothamBook);
        return view;
    }
    View.OnClickListener printListener = new View.OnClickListener() {
        public void onClick(View v) {
            //close
            getDialog().dismiss();
        }
    };
    View.OnClickListener ethyopieListener = new View.OnClickListener() {
        public void onClick(View v) {
            ((MainActivity)getActivity()).cont.PridejPolozkuStul(((MainActivity)getActivity()).getTableId(),
                    polozkaID,Controller.TagKavy.Ethyopia);
            Log.d("tralal", "id_polozky(ethyopie):" + polozkaID+", "+Controller.TagKavy.Ethyopia.ordinal());
            getDialog().dismiss();
        }
    };
    View.OnClickListener kenaListener = new View.OnClickListener() {
        public void onClick(View v) {
            ((MainActivity)getActivity()).cont.PridejPolozkuStul(((MainActivity)getActivity()).getTableId(),
                    polozkaID,Controller.TagKavy.Keňa);
            Log.d("tralal", "id_polozky(kena):" + polozkaID+", "+Controller.TagKavy.Keňa.ordinal());
            getDialog().dismiss();
        }
    };
    @Override
    public void onStart() {
        super.onStart();
        // safety check
        if (getDialog() == null)
            return;
        int dialogWidth = 700; // specify a value here
        int dialogHeight = 320; // specify a value here
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }
    /**
     * Dismiss handler
     * @param dialog
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        handler.sendEmptyMessage(0);
    }
}