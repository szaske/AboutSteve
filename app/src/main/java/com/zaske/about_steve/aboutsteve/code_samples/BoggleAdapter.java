package com.zaske.about_steve.aboutsteve.code_samples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zaske.about_steve.aboutsteve.R;

import java.util.ArrayList;

/**
 * Created by steve on 9/8/2017.
 */

public class BoggleAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mBoggleRoll;

    public BoggleAdapter (Context context, ArrayList<String> boggleRoll){
        this.mContext = context;
        this.mBoggleRoll = boggleRoll;
    }

    @Override
    public int getCount() {
        return mBoggleRoll.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (view == null) {
            // get layout from xml file
            gridView = inflater.inflate(R.layout.boggle_grid_item, null);

            // pull views
            TextView dieView = gridView
                    .findViewById(R.id.boggle_die);

            // set values into views
            dieView.setText(mBoggleRoll.get(position));
        } else {
            gridView = view;
        }
        return gridView;
    }
}
