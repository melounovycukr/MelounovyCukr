package com.brnokavarna.melounovycukr.app.View;

/**
 * Created by mpx on 5.5.2014.
 */
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.brnokavarna.melounovycukr.app.R;

public class SortimentFragment extends Fragment {

    public SortimentFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sortiment,
                container, false);

        Typeface gothamLight = Typeface.createFromAsset(getActivity().getAssets(), "Gotham-Light.otf");
        TextView backText = (TextView) view.findViewById(R.id.backText);
        backText.setTypeface(gothamLight);

        TextView addText = (TextView) view.findViewById(R.id.addText);
        addText.setTypeface(gothamLight);

        return view;
    }
}