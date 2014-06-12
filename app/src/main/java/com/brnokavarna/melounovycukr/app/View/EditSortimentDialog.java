package com.brnokavarna.melounovycukr.app.View;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



import com.brnokavarna.melounovycukr.app.MainActivity;
import com.brnokavarna.melounovycukr.app.Model.Tabulky.Seznam;
import com.brnokavarna.melounovycukr.app.R;

/**
 * Created by Seky on 12.6.2014.
 */
public class EditSortimentDialog  extends DialogFragment {

    private EditText sortimentNameEditText;
    private EditText sortimentCostEditText;
    private int idItem;

    public EditSortimentDialog() {
        // Empty constructor required for DialogFragment
    }

    public EditSortimentDialog(String v)
    {
        idItem = Integer.parseInt(v);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_sortiment, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        Typeface gothamBook = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Book.otf");
        TextView addText = (TextView) view.findViewById(R.id.doneTextEditSortiment);
        addText.setTypeface(gothamLight);

        ImageView add = (ImageView) view.findViewById(R.id.doneEditSortiment);
        add.setOnClickListener(doneListener);

        ImageView back = (ImageView) view.findViewById(R.id.backEditSortiment);
        back.setOnClickListener(printListener);

        TextView backText = (TextView) view.findViewById(R.id.backTextEditSortiment);
        backText.setTypeface(gothamLight);
        TextView deleteText = (TextView) view.findViewById(R.id.deleteTextEditSortiment);
        deleteText.setTypeface(gothamLight);

        TextView titleText = (TextView) view.findViewById(R.id.titleTextEditSortiment);
        titleText.setText("Sortiment");
        titleText.setTypeface(gothamLight);

        TextView sortimentNameText = (TextView) view.findViewById(R.id.sortimentNameTextEditSortiment);
        sortimentNameText.setTypeface(gothamBook);

        sortimentNameEditText = (EditText) view.findViewById(R.id.sortimentNameEditTextEditSortiment);
        sortimentNameEditText.setTypeface(gothamBook);

        TextView sortimentCostText = (TextView) view.findViewById(R.id.sortimentCostTextEditSortiment);
        sortimentCostText.setTypeface(gothamBook);

        sortimentCostEditText = (EditText) view.findViewById(R.id.sortimentCostEditTextEditSortiment);
        sortimentCostEditText.setTypeface(gothamBook);

        //load from DB
         Seznam item = ((MainActivity)getActivity()).cont.ZobrazPolozkuSeznam(idItem);
        //set texts of item
        ((EditText) view.findViewById(R.id.sortimentNameEditTextEditSortiment)).setText(item.getNazev_zbozi(), TextView.BufferType.EDITABLE);
        ((EditText) view.findViewById(R.id.sortimentCostEditTextEditSortiment)).setText(String.valueOf(item.getCena()), TextView.BufferType.EDITABLE);

       // cont = new Controller(getActivity());


        return view;
    }

    View.OnClickListener doneListener = new View.OnClickListener() {
        public void onClick(View v) {

        }
    };

    View.OnClickListener printListener = new View.OnClickListener() {
        public void onClick(View v) {
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

        // ... other stuff you want to do in your onStart() method
    }

}
