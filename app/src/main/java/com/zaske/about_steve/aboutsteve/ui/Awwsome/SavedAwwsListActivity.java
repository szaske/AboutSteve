package com.zaske.about_steve.aboutsteve.ui.Awwsome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.zaske.about_steve.aboutsteve.Constants;
import com.zaske.about_steve.aboutsteve.R;
import com.zaske.about_steve.aboutsteve.adapters.FirebaseAwwListAdapter;
import com.zaske.about_steve.aboutsteve.adapters.FirebaseAwwViewHolder;
import com.zaske.about_steve.aboutsteve.models.Aww;
import com.zaske.about_steve.aboutsteve.util.ItemTouchHelperCallback;
import com.zaske.about_steve.aboutsteve.util.OnStartDragListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedAwwsListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mAwwFireBaseReference;
    private FirebaseAwwListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @BindView(R.id.savedAwwRecyclerView) RecyclerView mSavedAwwRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_awws_list);
        ButterKnife.bind(this);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_AWW)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);;

        mFirebaseAdapter = new FirebaseAwwListAdapter(Aww.class, R.layout.saved_aww_list_item, FirebaseAwwViewHolder.class, query, this, this);
        mSavedAwwRecyclerView.setHasFixedSize(true);
        mSavedAwwRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSavedAwwRecyclerView.setAdapter(mFirebaseAdapter);

        // This implements the ItemTouch helper class to track actions
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mSavedAwwRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
