package com.zaske.about_steve.aboutsteve.ui.Awwsome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zaske.about_steve.aboutsteve.Constants;
import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.adapters.FirebaseAwwViewHolder;
import com.zaske.about_steve.aboutsteve.models.Aww;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedAwwsListActivity extends AppCompatActivity {
    private DatabaseReference mAwwFireBaseReference;
    private FirebaseRecyclerAdapter<Aww,FirebaseAwwViewHolder> mFirebaseAdapter;

    @BindView(R.id.savedAwwRecyclerView) RecyclerView mSavedAwwRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_awws_list);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mAwwFireBaseReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_AWW)
                .child(uid);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Aww, FirebaseAwwViewHolder> (Aww.class, R.layout.saved_aww_list_item, FirebaseAwwViewHolder.class, mAwwFireBaseReference) {

            @Override
            protected void populateViewHolder(FirebaseAwwViewHolder viewHolder, Aww model, int position) {
                Log.d("SZ Debug", "populateViewHolder ID: " + model.getId());
                viewHolder.bindAww(model);
            }
        };
        mSavedAwwRecyclerView.setHasFixedSize(true);
        mSavedAwwRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSavedAwwRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
