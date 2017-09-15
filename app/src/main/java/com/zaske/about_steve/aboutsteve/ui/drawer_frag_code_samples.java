package com.zaske.about_steve.aboutsteve.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.ui.code_samples.BoggleActivity;
import com.zaske.about_steve.aboutsteve.ui.code_samples.PassDataActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by steve on 9/8/2017.
 */

public class drawer_frag_code_samples extends Fragment {
    // View view;
//    @BindView(R.id.clickEventButton) Button mClickEventButton;
//    @BindView(R.id.playBoggleButton) Button mPlayBoggleButton;
//    @BindView(R.id.passDataButton) Button mPassDataButton;

    @OnClick(R.id.clickEventButton) void onEventButton() {
        Snackbar.make(getView(), "You clicked the button, and I caught it", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @OnClick(R.id.playBoggleButton) void OnPlayBoggleButtonClick() {
        Intent playBoggle = new Intent(getActivity(), BoggleActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(playBoggle);
    }

    @OnClick(R.id.passDataButton) void OnPassButtonClick() {
        Intent jumpToFormsSample = new Intent(getActivity(), PassDataActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(jumpToFormsSample);
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
