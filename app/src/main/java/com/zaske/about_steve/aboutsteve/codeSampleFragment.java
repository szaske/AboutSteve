package com.zaske.about_steve.aboutsteve;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by steve on 9/8/2017.
 */

public class codeSampleFragment extends Fragment {
    // View view;
    @BindView(R.id.clickEventButton) Button mClickEventButton;
    @BindView(R.id.playBoggleButton) Button mPlayBoggleButton;

    @OnClick(R.id.clickEventButton) void onEventButton() {
        Snackbar.make(getView(), "You clicked the button, and I aught it", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @OnClick(R.id.playBoggleButton) void OnPlayBoggle() {
        Snackbar.make(getView(), "You clicked the Boggle button", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.drawer_code_samples_frag, container, false);

        //bind variables to view objects
        ButterKnife.bind(this,view);

        return view;
    }
}
