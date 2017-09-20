package com.zaske.about_steve.aboutsteve.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.models.Aww;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Guest on 9/20/17.
 */

public class AwwRecyclerViewAdapter extends RecyclerView.Adapter<AwwRecyclerViewAdapter.AwwViewHolder> {
    private ArrayList<Aww> mAwws = new ArrayList<>();
    private Context mContext;

    public AwwRecyclerViewAdapter(Context mContext, ArrayList<Aww> mAwws) {
        this.mAwws = mAwws;
        this.mContext = mContext;
    }

    @Override
    public AwwRecyclerViewAdapter.AwwViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aww_recyclerview_item,parent,false);
        AwwViewHolder viewHolder = new AwwViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AwwRecyclerViewAdapter.AwwViewHolder holder, int position) {
        holder.bindAww(mAwws.get(position));
    }

    @Override
    public int getItemCount() {
        return mAwws.size();
    }


    //
    // This is the viewHolder class
    //
    //
    //

    public class AwwViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbImageView)
        ImageView mThumbImageView;
        @BindView(R.id.titleTextView) TextView mTitleTextView;

        private Context mContext;

        public AwwViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindAww(Aww aww) {
            mTitleTextView.setText(aww.getTitle());
            //Add thumbnail later
            //(aww.getThumbnail());
        }
    }


}
