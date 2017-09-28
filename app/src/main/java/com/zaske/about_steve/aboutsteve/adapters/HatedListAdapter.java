package com.zaske.about_steve.aboutsteve.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zaske.about_steve.aboutsteve.models.Stuff;
import com.zaske.about_steve.aboutsteve.ui.hated.HatedStuffPagerFragment;

import java.util.ArrayList;

/**
 * Created by steve on 9/15/2017.
 */

public class HatedListAdapter extends FragmentPagerAdapter {
    private ArrayList<Stuff> mHatedStuff;

    public HatedListAdapter(FragmentManager fm, ArrayList<Stuff> hatedStuff) {
        super(fm);
        mHatedStuff = hatedStuff;
    }

    @Override
    public int getCount() {
        return mHatedStuff.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mHatedStuff.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {

        return HatedStuffPagerFragment.newInstance(mHatedStuff.get(position));
    }
}
