package com.zaske.about_steve.aboutsteve.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.models.Aww;
import com.zaske.about_steve.aboutsteve.ui.Awwsome.AwwDetailActivity;

import java.util.ArrayList;

/**
 * Created by Guest on 9/21/17.
 */

public class AwwsRecyclerAdapter extends RecyclerView.Adapter<AwwsRecyclerAdapter.AwwHolder> {

    // My variables
    private ArrayList<Aww> mAwws;

    // extend RecyclerView.ViewHolder, allowing it to be used as a ViewHolder for the adapter
    public static class AwwHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // a list of references to the lifecycle of the object to allow the
        // ViewHolder to hang on to your ImageView and TextView, so it
        // doesn’t have to repeatedly query the same information.
        // This is why we're using a recyclerview
        private ImageView mAwwImage;
        private TextView mAwwTitle;
        private Aww mAww;

        // a key for easier reference.  Used as a Key name in an intent
        private static final String AWW_KEY = "AWW";

        // The Constructor
        public AwwHolder(View v) {
            super(v);

            // TODO: Implement Butterknife
            mAwwImage = (ImageView) v.findViewById(R.id.aww_item_image);
            // mAwwTitle = (TextView) v.findViewById(R.id.aww_item_title);
            v.setOnClickListener(this);
        }

        /**
         * @param v the Clicked view
         */
        @Override
        public void onClick(View v) {
            Log.d("RecyclerView", "CLICK on" + v.toString());

            //itemView is a special object held by the viewHolder
            Context context = itemView.getContext();
            //Parcelable wrapped = Parcels.wrap(context.mAwwList(1));

            Intent showAwwIntent = new Intent(context, AwwDetailActivity.class);
            showAwwIntent.putExtra(AWW_KEY, mAww);
            context.startActivity(showAwwIntent);
        }

        /**
         * This method binds the photo object to the viewHolder
         *
         * @param aww The Aww object to be bound
         */
        public void bindAww(Aww aww) {

            // This makes it so the Holder has a copy of the object
            mAww = aww;
            Picasso.with(mAwwImage.getContext()).load(aww.getThumbnail()).into(mAwwImage);
        }
    }  //end of viewHolder class


    public AwwsRecyclerAdapter(ArrayList<Aww> awws) {
        mAwws = awws;
    }

    @Override
    public AwwsRecyclerAdapter.AwwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row, parent, false);
        Log.d("onCreateViewHolder: ", "Fired" );
        return new AwwHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(AwwsRecyclerAdapter.AwwHolder holder, int position) {
        Aww itemAww = mAwws.get(position);
        holder.bindAww(itemAww);
    }

    @Override
    public int getItemCount() {
        return mAwws.size();
    }
}

