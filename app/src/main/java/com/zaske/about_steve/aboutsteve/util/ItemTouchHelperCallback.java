package com.zaske.about_steve.aboutsteve.util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by Guest on 9/28/17.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    //
    /// //Variables
    //

    // This makes an object that has 2 methods - onItemMove & onItemDismiss
    private final ItemTouchHelperAdapter mItemTouchAdapter;

    // The constructor
    public ItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {

        //This links an adapter to the Helper
        mItemTouchAdapter = adapter;
    }

    /**
     *  The method below informs the ItemTouchHelperAdapter that drag gestures are enabled.
     *  We could also disable drag gestures by returning 'false'.
     *
     * @return  Boolean to indicate that dragging is enabled.
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     *  The method below informs the ItemTouchHelperAdapter that swipe gestures are enabled.
     *  We could also disable them by returning 'false'.
     * @return  Boolean to indicate that Swiping is enabled.
     *
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     *  getMovementFlags informs the ItemTouchHelper which movement directions are supported.
     *
     * @param recyclerView
     * @param viewHolder
     * @return An Integer that
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        Log.d("getMovementFlags int: ", String.valueOf(makeMovementFlags(dragFlags, swipeFlags)) );
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     *  Notifies the adapter that an item has moved.
     *  This triggers the onItemMove override in our Firebase adapter,
     *  which will eventually handle updating the ArrayList to reflect the item's new position.
     *
     * @param recyclerView  The RecyclerView to which ItemTouchHelper is attached to.
     * @param source    The ViewHolder which is being dragged by the user.
     * @param target    The ViewHolder over which the currently active item is being dragged.
     * @return A boolean, true if the viewHolder has been moved to the adapter position of target.
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {

        // Not sure what this does. Logging to find out
        if (source.getItemViewType() != target.getItemViewType()) {
            Log.d("FALSE onMove: ", "source ItemViewType="+String.valueOf(source.getItemViewType())+", target:"+String.valueOf(target.getItemViewType()));
            return false;
        }

        Log.d("TRUE onMove:", "source ItemViewType="+String.valueOf(source.getItemViewType())+", target:"+String.valueOf(target.getItemViewType()));

        // This is where the magic occurs, this fires the onItemMove method in our adapter
        mItemTouchAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        // This triggers the onItemDismiss override in our Firebase adapter
        mItemTouchAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
} //end of class

