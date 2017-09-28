package com.zaske.about_steve.aboutsteve.util;

/**
 * Created by Guest on 9/28/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
