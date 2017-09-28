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

public class FirebaseAwwViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    // a key for easier reference.  Used as a Key name in an intent
    private static final String AWW_KEY = "AWW";

    View mView;
    Context mContext;

    public FirebaseAwwViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindAww(Aww aww) {
        ImageView awwImageView = (ImageView) mView.findViewById(R.id.awwImageView);
        TextView awwTitleTextView = (TextView) mView.findViewById(R.id.awwTitleTextView);

        Picasso.with(mContext).setLoggingEnabled(true);

        Picasso.with(mContext)
                .load(aww.getThumbnail())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(awwImageView);

        awwTitleTextView.setText(aww.getTitle());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Aww> awws = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_AWW)
                .child(uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    awws.add(snapshot.getValue(Aww.class));
                }

                // TODO look into this code
                int itemPosition = getLayoutPosition();

                Intent showAwwIntent = new Intent(mContext, SavedAwwDetailActivity.class);
                showAwwIntent.putExtra(AWW_KEY, awws.get(itemPosition));
                mContext.startActivity(showAwwIntent);

//                Intent intent = new Intent(mContext, AwwDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("awws", Parcels.wrap(awws));

//                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
