package com.zaske.about_steve.aboutsteve.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.zaske.about_steve.aboutsteve.Constants;
import com.zaske.about_steve.aboutsteve.models.Aww;
import com.zaske.about_steve.aboutsteve.ui.Awwsome.SavedAwwDetailActivity;
import com.zaske.about_steve.aboutsteve.util.ItemTouchHelperAdapter;
import com.zaske.about_steve.aboutsteve.util.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Guest on 9/28/17.
 */

public class FirebaseAwwListAdapter extends FirebaseRecyclerAdapter<Aww, FirebaseAwwViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    // implementing this ChildEventListener interface can be used to
    // receive events about changes in the child locations of a given Firebase ref
    private ChildEventListener mChildEventListener;
    private ArrayList<Aww> mAwws = new ArrayList<>();

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

        //Created in the constructor, this interface gives us the ability to
        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //this will save each object into our array
                mAwws.add(dataSnapshot.getValue(Aww.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d("onChildMoved: ", "Something moved");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    } // End of constructor

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

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d("onTouch: ", "ACTION DOWN detected");
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        // You can affect the viewholder here
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent showAwwIntent = new Intent(mContext, SavedAwwDetailActivity.class);
                showAwwIntent.putExtra(Constants.AWW_KEY, mAwws.get(viewHolder.getAdapterPosition()));
                mContext.startActivity(showAwwIntent);
            }
        });
    } // end of populateViewHolder

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {

        // This method swaps objects in an arraylist
        Collections.swap(mAwws, fromPosition, toPosition);
        // This lets Firebase know that we've moved items in a list
        notifyItemMoved(fromPosition, toPosition);

        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        // This removes the item from an arraylist
        mAwws.remove(position);
        // This tells Firebase to do the same
        getRef(position).removeValue();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }


    private void setIndexInFirebase() {

        // For each aww in the arraylist
        for (Aww aww : mAwws) {
            int index = mAwws.indexOf(aww);
            DatabaseReference ref = getRef(index);
            aww.setIndex(Integer.toString(index));
            ref.setValue(aww);
        }
    }
}
