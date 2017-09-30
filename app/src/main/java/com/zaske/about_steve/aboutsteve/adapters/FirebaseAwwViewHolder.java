package com.zaske.about_steve.aboutsteve.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.zaske.about_steve.aboutsteve.Constants;
import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.models.Aww;
import com.zaske.about_steve.aboutsteve.ui.Awwsome.AwwDetailActivity;
import com.zaske.about_steve.aboutsteve.ui.Awwsome.SavedAwwDetailActivity;

import java.util.ArrayList;

/**
 * Created by steve on 9/22/2017.
 */

public class FirebaseAwwViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    //This view is made public so we can access it via an onClick event listener
    // In the Adapter
    public ImageView mAwwImageView;

    View mView;
    Context mContext;

    public FirebaseAwwViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindAww(Aww aww) {
        mAwwImageView = mView.findViewById(R.id.awwImageView);
        TextView awwTitleTextView = mView.findViewById(R.id.awwTitleTextView);

        Picasso.with(mContext).setLoggingEnabled(true);

        Picasso.with(mContext)
                .load(aww.getThumbnail())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mAwwImageView);

        awwTitleTextView.setText(aww.getTitle());
    }
}
