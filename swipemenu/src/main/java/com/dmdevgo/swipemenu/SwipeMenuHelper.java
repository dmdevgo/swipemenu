package com.dmdevgo.swipemenu;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @author Dmitriy Gorbunov
 */
public class SwipeMenuHelper {

    private final ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new SwipeMenuCallback());

    public void attachToRecyclerView(RecyclerView recyclerView) {
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
