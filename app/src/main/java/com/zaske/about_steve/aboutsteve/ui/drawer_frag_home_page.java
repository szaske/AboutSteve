package com.zaske.about_steve.aboutsteve.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zaske.about_steve.aboutsteve.R;

/**
 * Created by steve on 9/8/2017.
 */

public class drawer_frag_home_page extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.drawer_home_frag, container, false);
    }
}
