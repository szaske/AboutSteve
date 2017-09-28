package com.zaske.about_steve.aboutsteve.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.zaske.about_steve.aboutsteve.models.Aww;
import com.zaske.about_steve.aboutsteve.util.ItemTouchHelperAdapter;
import com.zaske.about_steve.aboutsteve.util.OnStartDragListener;

/**
 * Created by Guest on 9/28/17.
 */

public class FirebaseAwwListAdapter extends FirebaseRecyclerAdapter<Aww, FirebaseAwwViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseAwwListAdapter(Class<Aww> modelClass,
                                  int modelLayout,
                                  Class<FirebaseAwwViewHolder> viewHolderClass,
                                  Query ref,
                                  OnStartDragListener onStartDragListener,
                                  Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(final FirebaseAwwViewHolder viewHolder, Aww model, int position) {
        viewHolder.bindAww(model);

        //According to the documentation This event listener is set here instead of the viewHolder,
        // because the FirebaseRecyclerAdapter handles the construction of viewholder internally
        // so we don't have access to the constructor.  But this doesn't seem correct.

        viewHolder.mAwwImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("onTouch: ", "Action detected");
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}