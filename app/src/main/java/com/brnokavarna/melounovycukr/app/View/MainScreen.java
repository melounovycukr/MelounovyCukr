package com.brnokavarna.melounovycukr.app.View;

/**
 * Created by mpx on 5.5.2014.
 */
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brnokavarna.melounovycukr.app.R;

public class MainScreen extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_screen,
                container, false);
        return view;
    }
}
